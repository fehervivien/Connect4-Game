import com.connect4.Main;
import com.connect4.PalyaOlvasKiir;
import com.connect4.Tabla;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private PalyaOlvasKiir game;
    private Tabla tabla;



    @BeforeEach
    void setUp() {
        // Inicializálj egy üres játékot
        game = new PalyaOlvasKiir();
        tabla = new Tabla(7, 6);
    }

    @Test
    void testRunGameWithEmptyBoard() {
        String simulatedInput = "Nem\n"; // A bemeneti adatok
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner scanner = new Scanner(in);

        Main.runGame(scanner); // A játék futtatása a szimulált bemenettel

        // Itt további ellenőrzéseket végezhetsz
    }

    @Test
    void testLoadBoardFromFile() {
        // Teszteld, hogy a játékos be tud tölteni egy fájlt
        String simulatedInput = "Igen\nsrc/test/resources/testBoard.txt\n";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        Main.main(new String[]{});

        // Itt ellenőrizheted, hogy a tábla a várt állapotot tükrözi
        char[][] expectedBoard = {
                {' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' '},
                {'X', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' '}
        };
        assertArrayEquals(expectedBoard, game.getBoard());
    }

    @Test
    void testEmptyBoardInitialization() {
        // Teszteld, hogy az üres tábla megfelelően inicializálódik
        String simulatedInput = "Nem\n";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        Main.main(new String[]{});

        // Ellenőrizd, hogy a tábla üres
        char[][] expectedBoard = new char[7][6];
        for (int i = 0; i < expectedBoard.length; i++) {
            for (int j = 0; j < expectedBoard[i].length; j++) {
                expectedBoard[i][j] = ' ';
            }
        }
        assertArrayEquals(expectedBoard, game.getBoard());
    }
}