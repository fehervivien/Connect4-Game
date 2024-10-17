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
        // Létrehozzuk a teszt fájlt, hogy a HighScoreManager tesztelni tudja
        File testFile = new File(testFileName);
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
                        highScores.add(new HighScore(nev, pontszam));
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
        // Ellenőrizzük, hogy a fájl betöltése helyesen történt
        assertEquals(3, highScoreManager.getHighScores().size());
        assertEquals("Alice", highScoreManager.getHighScores().get(0).getNev());
        assertEquals(100, highScoreManager.getHighScores().get(0).getPontszam());
    }

    @Test
    public void testMentesHighScore() {
        // Új pontszám mentése
        highScoreManager.mentesHighScore("Dave", 80);
        assertEquals(4, highScoreManager.getHighScores().size()); // Ellenőrizzük, hogy a méret 4
        assertEquals("Dave", highScoreManager.getHighScores().get(0).getNev()); // Ellenőrizzük, hogy Dave az első
        assertEquals(80, highScoreManager.getHighScores().get(0).getPontszam()); // Ellenőrizzük a pontszámot
    }

    @Test
    public void testKiirHighScores() {
        // Ellenőrizzük a kiírást (ez nem annyira ellenőrizhető, de az outputot megvizsgálhatod)
        highScoreManager.kiirHighScores(); // Ez csak kiírja a magas pontszámokat a konzolra
    }
}
