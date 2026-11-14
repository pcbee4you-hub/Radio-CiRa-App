RADIO CIRA ANDROID – ANDROID STUDIO PROJECT
===========================================

Dit is het Android Studio-project voor de Radio CiRa-app. De app opent de
meegeleverde neon Radio CiRa-interface in een Android WebView. De stream,
chat, verzoekjes- en programmapagina zijn ingesteld met de URL's die je gaf.

INGESTELDE ADRESSEN
- Stream: https://stream.rschosting.nl:8018/stream
- Chat: https://radiocira.rsc-hosting.nl/
- Verzoekjes: https://radiocira.nl/request.php
- DJ-programma: https://radiocira.nl/schedule.php

APK BOUWEN
1. Installeer Android Studio op een Windows-, Mac- of Linux-computer.
2. Pak deze ZIP uit.
3. Open Android Studio en kies Open.
4. Selecteer de map Radio-CiRa-Android (de map met settings.gradle).
5. Laat Gradle synchroniseren en installeer eventueel Android SDK Platform 35.
6. Kies Build > Build Bundle(s) / APK(s) > Build APK(s).
7. De debug-APK verschijnt normaal in app/build/outputs/apk/debug/app-debug.apk.

LET OP
Deze ZIP is het complete Android Studio-bronproject, niet een reeds gebouwde
APK. In deze uitvoeromgeving zijn Android SDK/Gradle build-tools niet aanwezig,
dus ik kan hier geen installeerbare APK compileren of testen. Android Studio
bouwt de APK op je computer. Voor publicatie in Google Play is daarnaast een
ondertekende release-build en een Play Console-account nodig.

De verzoekjesknop en overige koppelingen volgen de URL's uit config.js. Het
verzoekjessysteem zelf moet op de website actief zijn; deze Android-wrapper
verandert je bestaande server/database niet.
