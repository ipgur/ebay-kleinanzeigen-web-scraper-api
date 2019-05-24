Ebay doesn't offer a free API for ebay-kleinanzeigen.de.

This repo offers small spring-based API in front of ebay-kleinanzeigen.de which internally scrapes ebay-kleinanzeigen.de 
using Retrofit2 and jspoon

```
http://127.0.0.1:8080/search?keywords=iphone+se&locationStr=darmstadt&radius=100&maxPrice=90&minPrice=50
```

```json
{"products":[{"title":"Iphone se 32 gb leicht defekt! Tausch / verkauf","addedOn":"Heute, 14:13","price":"57 € VB","link":"www.ebay-kleinzeigen.de/s-anzeige/iphone-se-32-gb-leicht-defekt-tausch-verkauf/1128478919-173-18156"},{"title":"iPhone SE 64GB *Power-Button defekt*","addedOn":"Gestern, 11:07","price":"80 €","link":"www.ebay-kleinzeigen.de/s-anzeige/iphone-se-64gb-power-button-defekt-/1127754234-173-5317"},{"title":"iPhone SE 16GB","addedOn":"22.05.2019","price":"80 €","link":"www.ebay-kleinzeigen.de/s-anzeige/iphone-se-16gb/1127406533-173-7702"},{"title":"iPhone SE 16GB","addedOn":"22.05.2019","price":"75 € VB","link":"www.ebay-kleinzeigen.de/s-anzeige/iphone-se-16gb/1127230924-173-8139"},{"title":"Suche iPhone SE","addedOn":"21.05.2019","price":"60 €","link":"www.ebay-kleinzeigen.de/s-anzeige/suche-iphone-se/1126868758-173-5420"},{"title":"iPhone SE 16 gab Rose Gold","addedOn":"21.05.2019","price":"50 €","link":"www.ebay-kleinzeigen.de/s-anzeige/iphone-se-16-gab-rose-gold/1126783903-173-7703"},{"title":"Mainboard für Apple iPhone SE 16 GB Logicboard ,Platin","addedOn":"21.05.2019","price":"50 €","link":"www.ebay-kleinzeigen.de/s-anzeige/mainboard-fuer-apple-iphone-se-16-gb-logicboard-platin/1126433553-173-7416"},{"title":"Gehäuse und Display mit OVP für Apple iPhone SE - Space Grau","addedOn":"21.05.2019","price":"50 €","link":"www.ebay-kleinzeigen.de/s-anzeige/gehaeuse-und-display-mit-ovp-fuer-apple-iphone-se-space-grau/1126432160-173-7416"},{"title":"Iphone se 16GB space grau homebutton defekt","addedOn":"20.05.2019","price":"60 €","link":"www.ebay-kleinzeigen.de/s-anzeige/iphone-se-16gb-space-grau-homebutton-defekt/1126121701-173-7697"},{"title":"iPhone SE silber 32Gb","addedOn":"20.05.2019","price":"90 € VB","link":"www.ebay-kleinzeigen.de/s-anzeige/iphone-se-silber-32gb/1125839803-173-8139"},{"title":"Apple iPhone SE 64GB","addedOn":"19.05.2019","price":"90 € VB","link":"www.ebay-kleinzeigen.de/s-anzeige/apple-iphone-se-64gb/1125396970-173-4951"},{"title":"Iphone SE 64GB","addedOn":"18.05.2019","price":"89 € VB","link":"www.ebay-kleinzeigen.de/s-anzeige/iphone-se-64gb/1124083883-173-4497"},{"title":"Iphone se 32 gb leicht defekt! Tausch / verkauf","addedOn":"17.05.2019","price":"57 € VB","link":"www.ebay-kleinzeigen.de/s-anzeige/iphone-se-32-gb-leicht-defekt-tausch-verkauf/1123667178-173-18156"},{"title":"Apple iPhone SE - 32GB - Roségold (Ohne Simlock) A1723 (CDMA + GS","addedOn":"16.05.2019","price":"89 € VB","link":"www.ebay-kleinzeigen.de/s-anzeige/apple-iphone-se-32gb-ros-gold-ohne-simlock-a1723-cdma-gs/1122983800-173-7670"},{"title":"Apple iPhone SE, Space Grau, 16 GB + Tasche aus echtem Leder","addedOn":"16.05.2019","price":"60 €","link":"www.ebay-kleinzeigen.de/s-anzeige/apple-iphone-se-space-grau-16-gb-tasche-aus-echtem-leder/1122752150-173-5322"},{"title":"Iphone se 32gb weiß","addedOn":"13.05.2019","price":"90 €","link":"www.ebay-kleinzeigen.de/s-anzeige/iphone-se-32gb-weiss/1121116675-173-8354"},{"title":"iPhone SE mit 16GB","addedOn":"12.05.2019","price":"80 € VB","link":"www.ebay-kleinzeigen.de/s-anzeige/iphone-se-mit-16gb/1119803881-173-5324"},{"title":"Apple iPhone SE 16 GB","addedOn":"11.05.2019","price":"90 €","link":"www.ebay-kleinzeigen.de/s-anzeige/apple-iphone-se-16-gb/1119374141-173-4545"},{"title":"iPhone SE defekt","addedOn":"11.05.2019","price":"50 € VB","link":"www.ebay-kleinzeigen.de/s-anzeige/iphone-se-defekt/1119270281-173-4901"},{"title":"iPhone SE Silber Rechnung","addedOn":"10.05.2019","price":"80 €","link":"www.ebay-kleinzeigen.de/s-anzeige/iphone-se-silber-rechnung/1118810338-173-4868"},{"title":"iPhone SE zu verkaufen","addedOn":"08.05.2019","price":"50 € VB","link":"www.ebay-kleinzeigen.de/s-anzeige/iphone-se-zu-verkaufen/1117616104-173-4295"},{"title":"iPhone SE Roségold 16gb","addedOn":"06.05.2019","price":"70 €","link":"www.ebay-kleinzeigen.de/s-anzeige/iphone-se-ros-gold-16gb/1115837658-173-15353"},{"title":"Iphone SE, Space Grau, 16 GB","addedOn":"04.05.2019","price":"80 € VB","link":"www.ebay-kleinzeigen.de/s-anzeige/iphone-se-space-grau-16-gb/1114255222-173-19438"},{"title":"iPhone SE 16GB (Displayschaden)","addedOn":"04.05.2019","price":"80 € VB","link":"www.ebay-kleinzeigen.de/s-anzeige/iphone-se-16gb-displayschaden-/1114220574-173-23242"},{"title":"iPhone SE 5s Reparatur Display LCD in Graben-Neudorf","addedOn":"02.05.2019","price":"60 €","link":"www.ebay-kleinzeigen.de/s-anzeige/iphone-se-5s-reparatur-display-lcd-in-graben-neudorf/1047643960-226-8341"}]}
```

Name     | Status |
-------- | ------ |
Build    | [![CircleCI](https://circleci.com/gh/ipgur/ebay-kleinanzeigen-web-scraper.svg?style=svg)](https://circleci.com/gh/ipgur/ebay-kleinanzeigen-web-scraper) |
BetterCode    | [![BCH compliance](https://bettercodehub.com/edge/badge/ipgur/ebay-kleinanzeigen-web-scraper?branch=master)](https://bettercodehub.com/)|