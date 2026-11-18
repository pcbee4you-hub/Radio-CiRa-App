package nl.radiocira.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Radio
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import androidx.core.content.ContextCompat
import com.google.common.util.concurrent.MoreExecutors
import android.content.ComponentName
import com.google.common.util.concurrent.ListenableFuture

const val RADIO_STREAM = "https://stream.rschosting.nl:8018/stream"

private val Deep = Color(0xFF090611)
private val Panel = Color(0xFF171024)
private val Purple = Color(0xFFB44CFF)
private val Pink = Color(0xFFFF4FD8)
private val White = Color(0xFFF8F2FF)

class MainActivity : ComponentActivity() {
    private var controller: MediaController? = null
    private var controllerFuture: ListenableFuture<MediaController>? = null

    override fun onStart() {
        super.onStart()
        val token = SessionToken(this, ComponentName(this, RadioPlaybackService::class.java))
        controllerFuture = MediaController.Builder(this, token).buildAsync()
        controllerFuture?.addListener({
            controller = controllerFuture?.get()
        }, MoreExecutors.directExecutor())
    }

    override fun onStop() {
        controller = null
        controllerFuture?.let { MediaController.releaseFuture(it) }
        controllerFuture = null
        super.onStop()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { RadioScreen(
            onPlay = { controller?.let { if (it.isPlaying) it.pause() else { if (it.mediaItemCount == 0) it.setMediaItem(androidx.media3.common.MediaItem.fromUri(RADIO_STREAM)); it.prepare(); it.play() } } },
            isPlayingProvider = { controller?.isPlaying == true }
        ) }
    }
}

@Composable
private fun RadioScreen(onPlay: () -> Unit, isPlayingProvider: () -> Boolean) {
    var playing by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) {
            kotlinx.coroutines.delay(500)
            playing = isPlayingProvider()
        }
    }
    Box(
        modifier = Modifier.fillMaxSize().background(
            Brush.verticalGradient(listOf(Color(0xFF160B27), Deep, Color(0xFF08050D)))
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.radio_cira_logo),
                    contentDescription = "Radio CiRa logo",
                    modifier = Modifier.fillMaxWidth().height(172.dp),
                    contentScale = ContentScale.Fit
                )
                Text("JOUW MUZIEK, JOUW VERZOEKJES", color = Color(0xFFD7C4E8), fontSize = 10.sp, letterSpacing = 1.4.sp)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier.size(236.dp).background(
                        Brush.linearGradient(listOf(Purple.copy(alpha = .28f), Pink.copy(alpha = .12f))),
                        CircleShape
                    ).padding(14.dp).background(Panel, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.Radio, contentDescription = null, tint = Purple, modifier = Modifier.size(76.dp))
                        Spacer(Modifier.height(12.dp))
                        Text("CiRa", color = White, fontSize = 38.sp, fontWeight = FontWeight.ExtraBold)
                        Text("LIVE RADIO", color = Pink, fontSize = 11.sp, letterSpacing = 3.sp)
                    }
                }
                Spacer(Modifier.height(28.dp))
                Text(if (playing) "Je luistert live naar" else "Luister live naar", color = Color(0xFFD7C4E8), fontSize = 14.sp)
                Text("Radio CiRa", color = White, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(24.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.Bottom, modifier = Modifier.height(30.dp)) {
                    val heights = listOf(12, 23, 16, 29, 19, 26, 13, 22, 10)
                    heights.forEachIndexed { i, h ->
                        Box(Modifier.width(5.dp).height((if (playing) h else 5).dp).background(if (i % 2 == 0) Purple else Pink, RoundedCornerShape(4.dp)))
                    }
                }
                Spacer(Modifier.height(26.dp))
                Button(
                    onClick = onPlay,
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Purple),
                    modifier = Modifier.size(88.dp)
                ) {
                    Icon(if (playing) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = if (playing) "Pauzeren" else "Afspelen",
                        tint = Color.White, modifier = Modifier.size(44.dp))
                }
                Spacer(Modifier.height(12.dp))
                Text(if (playing) "PAUZE" else "AFSPELEN", color = White, fontSize = 11.sp, letterSpacing = 2.sp)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Altijd gezellig, altijd Radio CiRa", color = Color(0xFFBBA9C9), fontSize = 13.sp)
                Spacer(Modifier.height(8.dp))
                Text("Powered by RscHosting", color = Color(0xFF766583), fontSize = 10.sp)
            }
        }
    }
}
