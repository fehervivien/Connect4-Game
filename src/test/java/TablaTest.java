import com.connect4.Tabla;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TablaTest {

    @Test
    public void testLehelyez() {
        Tabla tabla = new Tabla(6, 7);
        assertTrue(tabla.lehelyez(0, 'X'));
        assertFalse(tabla.lehelyez(-1, 'X')); // Érvénytelen oszlop
        assertFalse(tabla.lehelyez(7, 'X')); // Érvénytelen oszlop
    }

    @Test
    public void testGyoztes() {
        Tabla tabla = new Tabla(6, 7);
        tabla.lehelyez(0, 'X');
        tabla.lehelyez(0, 'X');
        tabla.lehelyez(0, 'X');
        tabla.lehelyez(0, 'X');
        assertTrue(tabla.ellenorizGyoz('X'));
    }
}
