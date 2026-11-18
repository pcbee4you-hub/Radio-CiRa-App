# Radio CiRa – Android-app

Een native Android Studio-project met paarse neon-uitstraling en de opgegeven Radio CiRa-stream.

## Stream
`https://stream.rschosting.nl:8018/stream`

## Openen en bouwen
1. Pak `RadioCiRa-Android.zip` uit.
2. Open de map `RadioCiRa-Android` in Android Studio.
3. Gebruik JDK 17 en laat Gradle synchroniseren.
4. Kies **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
5. De debug-APK staat normaal in `app/build/outputs/apk/debug/app-debug.apk`.

## Bestaand logo
Het door de gebruiker aangeleverde Radio CiRa-logo is opgenomen als `app/src/main/res/drawable-nodpi/radio_cira_logo.png` en wordt in de app-header getoond.

## Opmerking
Dit is de Android Studio-broncode. Een ondertekende release-APK vereist een eigen Android signing key. Controleer de stream op het toestel en test achtergrondweergave/notificaties vóór publicatie.


## APK bouwen via GitHub Actions (zonder Android Studio op je pc)
1. Upload deze projectmap naar je GitHub-repository `Radio-CiRa-App` (of vervang daar de bestaande projectbestanden).
2. Open op GitHub het tabblad **Actions**.
3. Kies **Build Radio CiRa Android APK** en klik op **Run workflow**.
4. Wacht tot de workflow groen is. Open de uitgevoerde workflow en download onder **Artifacts** het bestand `Radio-CiRa-debug-APK`.
5. Pak de download uit; daarin staat `app-debug.apk`. Zet die APK op je Android-telefoon en installeer hem. Mogelijk moet Android toestemming vragen om apps uit die bron te installeren.

De workflow maakt een debug-APK voor testen. Voor publicatie in Google Play is een ondertekende release-build nodig.
