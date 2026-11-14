RADIO CIRA – IPHONE WEBAPP (PWA)
================================

INHOUD
- Live speler gekoppeld aan https://stream.rschosting.nl:8018/stream
- Neon paars/roze/blauwe vormgeving
- Verzoekjesformulier (PHP slaat aanvragen op in data/requests.json)
- Favoriet onthouden op het toestel
- Deelknop, volumeregeling en dempen
- PWA-bestanden voor toevoegen aan beginscherm
- Offline caching van de app-interface (de live stream vereist internet)

INSTALLATIE OP JE PHP/Hestia HOSTING
1. Upload de volledige inhoud van deze ZIP naar bijvoorbeeld:
   https://jouwdomein.nl/radiocira/
2. Zorg dat PHP 7.4+ actief is en dat PHP schrijfrechten heeft op de map
   waarin data/requests.json wordt aangemaakt (de map data mag PHP aanmaken).
3. Open de URL via HTTPS.
4. Test de speler en verstuur een testverzoekje.
5. Maak in config.js de chatUrl en eventueel scheduleUrl compleet.

INSTALLEREN OP IPHONE
1. Open de website in Safari (niet in een ingebouwde browser).
2. Tik op de Deel-knop.
3. Kies 'Zet op beginscherm' / 'Add to Home Screen'.
4. Bevestig met 'Voeg toe'. De app staat nu als Radio CiRa op het beginscherm.

AANPASSEN
Open config.js om streamUrl, requestUrl, chatUrl en scheduleUrl aan te passen.

BELANGRIJKE OPMERKINGEN
- De streamspeler is direct gekoppeld aan jouw opgegeven stream-URL.
- De huidige track/DJ wordt nog niet automatisch uit SonicPanel gelezen.
  Daarvoor is de SonicPanel status/API-URL of metadata-endpoint nodig.
- Verzoekjesknop opent het bestaande formulier: https://radiocira.nl/request.php
- Chatknoppen openen: https://radiocira.rsc-hosting.nl/
- Programmaknoppen openen: https://radiocira.nl/schedule.php
- Voor publicatie in de Apple App Store is een aparte native wrapper en
  Apple Developer-account nodig. Deze PWA is bedoeld voor Safari en het
  iPhone-beginscherm, zonder App Store.
