(() => {
 const cfg=window.CIRA_CONFIG||{}, audio=document.getElementById('radioAudio');
 const playBtn=document.getElementById('playBtn'), playIcon=document.getElementById('playIcon');
 const status=document.getElementById('streamStatus'), label=document.getElementById('playLabel');
 const toastEl=document.getElementById('toast'); let toastTimer;
 audio.src=cfg.streamUrl||'';
 function toast(msg){toastEl.textContent=msg;toastEl.classList.add('show');clearTimeout(toastTimer);toastTimer=setTimeout(()=>toastEl.classList.remove('show'),2800)}
 function setPlaying(on){playIcon.textContent=on?'Ⅱ':'▶';playBtn.setAttribute('aria-label',on?'Pauzeren':'Afspelen');label.textContent=on?'Je luistert live':'Luister live';status.classList.toggle('playing',on);status.innerHTML=on?'<i></i> Je bent live verbonden':'<i></i> Klaar om af te spelen'}
 async function toggle(){if(audio.paused){try{await audio.play();setPlaying(true)}catch(e){toast('De stream kon niet starten. Controleer je internetverbinding en probeer opnieuw.');setPlaying(false)}}else{audio.pause();setPlaying(false)}}
 playBtn.addEventListener('click',toggle);
 audio.addEventListener('playing',()=>setPlaying(true));audio.addEventListener('pause',()=>setPlaying(false));
 audio.addEventListener('waiting',()=>{status.innerHTML='<i></i> Verbinding maken…'});
 audio.addEventListener('error',()=>{setPlaying(false);status.innerHTML='<i></i> Stream tijdelijk niet beschikbaar'});
 const volume=document.getElementById('volume');audio.volume=Number(volume.value);
 volume.addEventListener('input',()=>{audio.volume=Number(volume.value);document.getElementById('volumeLabel').textContent='Volume '+Math.round(audio.volume*100)+'%'});
 document.getElementById('muteBtn').addEventListener('click',()=>{audio.muted=!audio.muted;document.getElementById('muteBtn').textContent=audio.muted?'🔇':'🔊'});
 const fav=document.getElementById('favoriteBtn'), favText=document.getElementById('favoriteText');
 function favState(){const yes=localStorage.getItem('ciraFavorite')==='1';fav.querySelector('.quick-icon').textContent=yes?'♥':'♡';favText.textContent=yes?'Opgeslagen bij favorieten':'Bewaar Radio CiRa'}
 fav.addEventListener('click',()=>{localStorage.setItem('ciraFavorite',localStorage.getItem('ciraFavorite')==='1'?'0':'1');favState();toast(localStorage.getItem('ciraFavorite')==='1'?'Radio CiRa toegevoegd aan favorieten':'Radio CiRa verwijderd uit favorieten')});favState();
 const chat=cfg.chatUrl||'';['chatLink','navChat'].forEach(id=>{const el=document.getElementById(id);if(chat){el.href=chat}else{el.addEventListener('click',e=>{e.preventDefault();toast('De chatlink moet nog worden ingesteld in config.js')})}});
 document.getElementById('shareBtn').addEventListener('click',async()=>{try{if(navigator.share)await navigator.share({title:'Radio CiRa',text:'Luister gezellig mee naar Radio CiRa!',url:location.href});else{await navigator.clipboard.writeText(location.href);toast('Link gekopieerd!')}}catch(e){}});
 const requestLink=document.getElementById('requestLink');if(requestLink&&cfg.requestUrl)requestLink.href=cfg.requestUrl;
 const scheduleLink=document.getElementById('scheduleLink');if(scheduleLink&&cfg.scheduleUrl)scheduleLink.href=cfg.scheduleUrl;
 document.getElementById('year').textContent=new Date().getFullYear();
 if('serviceWorker'in navigator && location.protocol.startsWith('http'))window.addEventListener('load',()=>navigator.serviceWorker.register('sw.js').catch(()=>{}));
})();