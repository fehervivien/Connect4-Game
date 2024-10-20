Connect4-Game java fejlesztésű parancssoros játék.

Feladatleírása (Egyetem projekt):
Connect 4 parancssoros játék implementáció
• A félév során a hallgatóknak önállóan kell lefejleszteni egy Java parancssoros Connect-4 játékot
• Ennek leírása itt olvasható angolul: https://hu.wikipedia.org/wiki/Connect_four
• A connect4 kétszemélyes stratégiai táblajáték, mely 1 db NxM-es (N és M pozitiv egész szám, 4 <=M <= N <=12), tipikusan 7x6-as táblán játszható. N -- sorok, M -- oszlopok száma.
• Az oszlopok számozása tipikusan a,b,c,... betükkel történik, a soroké 1,2,3,...N sorszámokkal -- de ettől nem függ a játékprogram.
• Induláskor a tábla üres.
• A két játékos közül az egyik a sárga szinű korongokat, a másik a pirosakat vezeti. A sárga szin lesz a humán játékosé, a piros a gépi játékosé. A sárga kezd.
• A játékosok felülről csúsztatják bele a táblába a saját színeiket, így a jelrakások lehetőségei is korlátozottak a többi amöba típusú játékhoz képest. Tehát egy lépés megadásához mindig elég megadni, mely oszlopba fogjuk csúsztatni a korongunkat.
• A gépi ellenfél ebben a félévben még rém egyszerű, csak random generál egy lehetséges oszlopot, mindegyiket egyenlő valószínűséggel.
• Az a játékos nyer, amelyik függőlegesen, vízszintesen, vagy átlósan kirakott négyet a saját színéből.
• A beadandó feladatot két ütemben kell majd elkészíteni és megvédeni
• A védések az óra időpontjában fognak történni (7. és 13. héten)
• Elvárások az első (7. heti) védésre:
• Egy publikus GitHub repository létrehozása
• A létrehozott Git repository tartalmazza a beadandó forráskódját
• A repository tartalmaz egy megfelelő .gitignore fájlt annak érdekébe, hogy IDE vagy Maven specifikus ideiglenes fájlok ne kerüljenek fel a repository-ba
• Egy Java 21-es Maven projekt létrehozása (pom.xml és Maven folder struktúra)
• A Maven projekt az alábbi konfigurációkat tartalmazza:
■ Plugin-ek:
■ org.apache.maven.plugins.maven-jar-plugin - annak érdekében, hogy felkonfiguráljuk az alkalmazásunk belépési pontját (Main Class)
org.apache.maven.plugins.maven-assembly-plugin - annak érdekében, hogy egy függőségeket tartalmazó, futtatható JAR fájl jöjjön létre az alkalmazás build-elése eredményeként
org.jacoco.jacoco-maven-plugin - annak érdekében, hogy a megírt Egység tesztek kód lefedettségét tudjuk mérni
org.apache.maven.plugins.maven-checkstyle-plugin- annak érdekében, hogy a projekten elkövetett kód formázási hibákat és egyéb rossz praktikák automatikus detektáljunk
■ Függőségek:
JUnit5
■ Mockito
■ Logback
• Az alkalmazás objektum-orientált modellezésének megkezdése
■ Az alkalmazásunkhoz szükséges VO (Value Object) osztályok létrehozása (ügyelve és figyelembe véve a "best practice"-eket: Object methods overriding. Immutability, stb)
• Az induláskor egy szövegfájlból beolvas egy játékállást, ha nincs meg az input fájl, akkor üres pályáról indulunk
• Az alkalmazás képes kezdetleges felhasználói interakciókat fogadni
■ egy szövegfájlból betölteni egy pályát
■ egy szövegfájlba kiĺrni egy pályát
■ Például: Játékos nevének bekérése, Játék elindítása, a játéktér kiiratása, Egy lépés fogadása a parancssoron, a lépés vizsgálata abból a szempontból, hogy alkalmazható-e; a lépés alkalmazása és az eredmény kiírása, stb
■ Itt nem határozunk meg kötelező elvárásokat, tetszőleges kezdetleges interakciók elegendőek
• Egység tesztek 80% lefedettséget biztosítanak üzleti logikát tartalmazó osztályokra (tehát például VO osztályokra nem szükséges egységteszteket írni)
• Elvárások a második (14. heti) védésre:
• A teljes Connect4 játék funkcionalitás lefejlesztésre került (lehetséges egy játékot végig játszani elejétől a végéig)
• A projekt a mvn clean install parancs futtatására hiba nélkül fordul
• Az alkalmazás egy adatbázisba lementi a játékosok nevét és azt, hogy hányszor nyertek
■ Az alkalmazás képes megjeleníteni parancssorban egy high score táblázatot (melyik játékos hány meccset nyert)
• Opcionális (plusz pontért): egy aktuálisan folyamatban lévő játék állást az alkalmazás képes egy XML fájlba kimenteni és később visszatölteni (tehát a játékos onnan folytathatja a játékot, ahol korábban abba hagyta)
• Egység tesztek továbbra is 80% lefedettséget biztosítanak üzleti logikát tartalmazó osztályokra
