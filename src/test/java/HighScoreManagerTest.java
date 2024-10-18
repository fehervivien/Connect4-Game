import com.connect4.HighScore;
import com.connect4.HighScoreManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class HighScoreManagerTest {

    private HighScoreManager highScoreManager;
    private final String testFileName = "test_highscores.txt"; // Teszt fájl

    @Before
    public void setUp() throws IOException {
        // Létrehozzuk a teszt fájlt, és felülírjuk a tartalmát (újraindítjuk)
        File testFile = new File(testFileName);
        if (testFile.exists()) {
            testFile.delete(); // Töröljük a korábbi fájlt
        }
        testFile.createNewFile(); // Új, üres fájl létrehozása

        // Írjunk néhány alapértelmezett adatot a teszthez
        BufferedWriter writer = new BufferedWriter(new FileWriter(testFile));
        writer.write("Alice: 100\n");
        writer.write("Bob: 50\n");
        writer.write("Charlie: 75\n");
        writer.close();

        // Inicializáljuk a HighScoreManager-t a teszt fájl használatával
        highScoreManager = new HighScoreManager() {
            @Override
            public void betoltHighScores() {
                try (BufferedReader br = new BufferedReader(new FileReader(testFileName))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split(":");
                        String nev = parts[0];
                        int pontszam = Integer.parseInt(parts[1].trim());
                        getHighScores().add(new HighScore(nev, pontszam));
                    }
                } catch (IOException e) {
                    System.out.println("Hiba a high score fájl betöltésekor: " + e.getMessage());
                }
            }
        };
    }



    @After
    public void tearDown() {
        // Töröljük a teszt fájlt a teszt végén
        File testFile = new File(testFileName);
        testFile.delete();
    }

    @Test
    public void testBetoltHighScores() {
        assertEquals(3, highScoreManager.getHighScores().size()); // Ellenőrizzük, hogy három pontszámot betöltöttünk
        assertEquals("Alice", highScoreManager.getHighScores().get(0).getNev()); // Ellenőrizzük, hogy az első név Alice
        assertEquals(100, highScoreManager.getHighScores().get(0).getPontszam()); // Ellenőrizzük, hogy az első pontszám 100
    }

    @Test
    public void testMentesHighScore() {
        // Új pontszám mentése
        highScoreManager.mentesHighScore("Dave", 80);

        assertEquals(4, highScoreManager.getHighScores().size()); // Ellenőrizzük, hogy a méret 4

        // Ellenőrizzük, hogy az első Alice maradt
        assertEquals("Alice", highScoreManager.getHighScores().get(0).getNev());
        assertEquals(100, highScoreManager.getHighScores().get(0).getPontszam());

        // Ellenőrizzük, hogy Dave lett a második helyen
        assertEquals("Dave", highScoreManager.getHighScores().get(1).getNev());
        assertEquals(80, highScoreManager.getHighScores().get(1).getPontszam());
    }


    @Test
    public void testKiirHighScores() {
        // Ellenőrizzük a kiírást (ez nem annyira ellenőrizhető, de az outputot megvizsgálhatod)
        highScoreManager.kiirHighScores(); // Ez csak kiírja a magas pontszámokat a konzolra
    }
}
