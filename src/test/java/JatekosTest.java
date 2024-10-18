import com.connect4.Ember;
import com.connect4.Gep;
import com.connect4.Jatekos;
import com.connect4.Tabla;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class JatekosTest {
    private Tabla tabla;

    @BeforeEach
    void setUp() {
        // Példa tábla 7 oszloppal
        tabla = new Tabla(6, 7);
    }

    @Test
    void testEmberLepesErvenyes() {
        // Szimuláljunk egy érvényes bemenetet (pl. '3')
        String input = "3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Jatekos ember = new Ember('X');
        int oszlop = ember.lepes(tabla);

        // Ellenőrizzük, hogy a választott oszlop érvényes-e
        assertEquals(3, oszlop);
    }

    @Test
    void testEmberLepesHibaUzenet() {
        // Szimuláljunk egy érvénytelen bemenetet (pl. '10', majd érvényes bemenet '2')
        String input = "10\n2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Jatekos ember = new Ember('X');
        int oszlop = ember.lepes(tabla);

        // Ellenőrizzük, hogy a végső válasz érvényes oszlop
        assertEquals(2, oszlop);
    }

    @Test
    void testGepLepesVeletlenszeruErvenyes() {
        Jatekos gep = new Gep('O');
        int oszlop = gep.lepes(tabla);

        // Ellenőrizzük, hogy a generált oszlop a megfelelő tartományban van
        assertTrue(oszlop >= 0 && oszlop < tabla.getOszlopok());
    }

    @Test
    void testGepTobbLepes() {
        Jatekos gep = new Gep('O');
        boolean valid = true;

        for (int i = 0; i < 100; i++) {
            int oszlop = gep.lepes(tabla);
            if (oszlop < 0 || oszlop >= tabla.getOszlopok()) {
                valid = false;
                break;
            }
        }
        assertTrue(valid);
    }
}
