import com.connect4.PalyaOlvasKiir;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class PalyaOlvasKiirTest {

    private PalyaOlvasKiir palyaOlvasKiir;
    private final String testFilePath = "testBoard.txt";

    @BeforeEach
    public void setUp() {
        // Inicializáljuk a PalyaOlvasKiir osztályt, üres táblával
        palyaOlvasKiir = new PalyaOlvasKiir();
    }

    @AfterEach
    public void tearDown() {
        // Töröljük a tesztfájlt a teszt után
        File file = new File(testFilePath);
        if (file.exists()) {
            //file.delete();
        }
    }

    @Test
    public void testInitializeBoard() {
        char[][] board = palyaOlvasKiir.getBoard();
        for (int i = 0; i < 7; i++) { // 7 sor
            for (int j = 0; j < 6; j++) { // 6 oszlop
                assertEquals(' ', board[i][j], "A tábla elemei nem üres karakterek.");
            }
        }
    }

    @Test
    public void testLoadBoardValid() throws IOException {
        // Teszteljük a tábla betöltését egy érvényes fájlból
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFilePath))) {
            writer.write("XXXXXX\n");
            writer.write("XXXXXX\n");
            writer.write("XXXXXX\n");
            writer.write("XXXXXX\n");
            writer.write("XXXXXX\n");
            writer.write("XXXXXX\n");
            writer.write("XXXXXX\n");
        }

        palyaOlvasKiir.loadBoard(testFilePath);
        char[][] board = palyaOlvasKiir.getBoard();

        // Ellenőrizzük, hogy a tábla helyesen töltődött be
        for (int i = 0; i < 7; i++) { // 7 sor
            for (int j = 0; j < 6; j++) { // 6 oszlop
                assertEquals('X', board[i][j], "A tábla betöltése nem sikerült.");
            }
        }
    }

    @Test
    public void testLoadBoardInvalidLine() throws IOException {
        // Teszteljük a tábla betöltését egy érvénytelen fájlból
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFilePath))) {
            writer.write("XXXXXXX\n"); // Érvénytelen sor (7 karakter)
            writer.write("XXX\n");     // Érvénytelen sor
            writer.write("XXXXXXX\n");
        }

        palyaOlvasKiir.loadBoard(testFilePath);
        char[][] board = palyaOlvasKiir.getBoard();

        // Ellenőrizzük, hogy a tábla üres maradt
        assertEquals(' ', board[0][0], "Az első sor első elemének üresnek kell lennie.");
        assertEquals(' ', board[1][0], "A második sor első elemének üresnek kell lennie.");
    }



    //nem megy
    @Test
    public void testSaveBoard() throws IOException {
        // Frissítsük a táblát
        palyaOlvasKiir.updateBoard(0, 'X');

        // Mentsük a táblát
        palyaOlvasKiir.saveBoard(testFilePath);

        // Ellenőrizzük, hogy a fájl létrejött-e és tartalmazza-e a helyes adatokat
        try (BufferedReader reader = new BufferedReader(new FileReader(testFilePath))) {
            String line = reader.readLine();
            assertNotNull(line, "A fájl üres!");

            // Debug nyomtatás, hogy lássuk a tényleges fájl tartalmát
            System.out.println("Fájl tartalma: '" + line + "'");

            // Módosítsd a várt tartalmat, ha szükséges
            assertEquals("X     ", line, "A fájl tartalma nem megfelelő."); // 6 oszlop, az X után 5 üres hely

        }
    }



}
