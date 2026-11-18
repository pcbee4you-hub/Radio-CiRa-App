package nl.radiocira.app

import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService

class RadioPlaybackService : MediaSessionService() {
    private var mediaSession: MediaSession? = null

    override fun onCreate() {
        super.onCreate()
        val player = ExoPlayer.Builder(this).build()
        val item = MediaItem.Builder()
            .setUri(RADIO_STREAM)
            .setMediaMetadata(
                MediaMetadata.Builder()
                    .setTitle("Radio CiRa – Live")
                    .setArtist("Jouw muziek, jouw verzoekjes")
                    .build()
            )
            .build()
        player.setMediaItem(item)
        mediaSession = MediaSession.Builder(this, player).build()
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? = mediaSession

    override fun onDestroy() {
        mediaSession?.run {
            player.release()
            release()
        }
        mediaSession = null
        super.onDestroy()
    }
}
