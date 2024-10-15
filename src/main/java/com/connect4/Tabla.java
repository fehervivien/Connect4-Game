package com.connect4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

/* Tábla osztály:
Ez az osztály a tábla kezeléséért felel,
a lépések elhelyezésétől kezdve a győzelem ellenőrzéséig
és a tábla állapotának mentéséig. Felelős a játék alapvető mechanikájáért,
mint a lépések megtétele, és a nyertes keresése.
*/

public class Tabla {
    /*Ez a kétdimenziós tömb a játék tábláját reprezentálja,
    ahol minden elem vagy egy üres hely (' '),
    vagy egy játékos karaktere ('X' vagy 'O'):*/
    private char[][] tabla;
    private int sorok; /*A tábla sorainak és oszlopainak számát tárolják.*/
    private int oszlopok;

    /*Inicializálja a tábla méretét a megadott sorokkal és oszlopokkal,
    és üres (' ') karakterekkel tölti fel a táblát.*/
    public Tabla(int sorok, int oszlopok) {
        this.sorok = sorok;
        this.oszlopok = oszlopok;
        tabla = new char[sorok][oszlopok];
        for (int i = 0; i < sorok; i++) {
            for (int j = 0; j < oszlopok; j++) {
                tabla[i][j] = ' ';
            }
        }
    }

    /*A játékos megadott oszlopába helyezi a karakterét,
    ha az oszlop még nincs tele.
    Azt is ellenőrzi, hogy az oszlop létező és érvényes-e.*/
    public boolean lehelyez(int oszlop, char jatekos) {
        if (oszlop < 0 || oszlop >= oszlopok) {
            return false; // Érvénytelen oszlop
        }
        for (int i = sorok - 1; i >= 0; i--) {
            if (tabla[i][oszlop] == ' ') {
                tabla[i][oszlop] = jatekos;
                return true; // Sikeres lehelyezés
            }
        }
        return false; // Az oszlop tele van
    }

    /* Ellenőrzi, hogy a megadott játékos nyert-e. */
    public boolean ellenorizGyoz(char jatekos) {
        return ellenorizGyozVizszintes(jatekos) || ellenorizGyozFuggoleges(jatekos) || ellenorizGyozAtlo(jatekos);
    }

    /*A győzelmet három különböző módon (vízszintes, függőleges, átlós)
    ellenőrzi (alább is):*/
    private boolean ellenorizGyozVizszintes(char jatekos) {
        for (int i = 0; i < sorok; i++) {
            for (int j = 0; j < oszlopok - 3; j++) {
                if (tabla[i][j] == jatekos && tabla[i][j + 1] == jatekos && tabla[i][j + 2] == jatekos && tabla[i][j + 3] == jatekos) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean ellenorizGyozFuggoleges(char jatekos) {
        for (int i = 0; i < sorok - 3; i++) {
            for (int j = 0; j < oszlopok; j++) {
                if (tabla[i][j] == jatekos && tabla[i + 1][j] == jatekos && tabla[i + 2][j] == jatekos && tabla[i + 3][j] == jatekos) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean ellenorizGyozAtlo(char jatekos) {
        for (int i = 0; i < sorok - 3; i++) {
            for (int j = 0; j < oszlopok - 3; j++) {
                if (tabla[i][j] == jatekos && tabla[i + 1][j + 1] == jatekos && tabla[i + 2][j + 2] == jatekos && tabla[i + 3][j + 3] == jatekos) {
                    return true;
                }
            }
        }
        for (int i = 3; i < sorok; i++) {
            for (int j = 0; j < oszlopok - 3; j++) {
                if (tabla[i][j] == jatekos && tabla[i - 1][j + 1] == jatekos && tabla[i - 2][j + 2] == jatekos && tabla[i - 3][j + 3] == jatekos) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Megjeleníti a táblát a konzolon, soronként és oszloponként.*/
    public void kiirTabla() {
        for (int i = 0; i < sorok; i++) {
            for (int j = 0; j < oszlopok; j++) {
                System.out.print("|" + tabla[i][j]);
            }
            System.out.println("|");
        }
        for (int j = 0; j < oszlopok; j++) {
            System.out.print("--");
        }
        System.out.println("-");
    }

    /* Visszaadja az oszlopok számát (érvényes oszlop),
    hogy más részek is elérhessék.*/
    public int getOszlopok() {
        return oszlopok;
    }

    /* Betölti a tábla állapotát egy fájlból.
    Minden sor karakterei beolvashatók egy soronkénti
    BufferedReader segítségével.*/
    public void betoltTabla(String fajlNev) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fajlNev))) {
            for (int i = 0; i < sorok; i++) {
                String line = br.readLine();
                for (int j = 0; j < oszlopok; j++) {
                    tabla[i][j] = line.charAt(j);
                }
            }
        }
    }

    /* Elmenti a tábla aktuális állapotát egy fájlba, karakterenként,
    majd soronként.*/
    public void mentTabla(String fajlNev) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fajlNev))) {
            for (int i = 0; i < sorok; i++) {
                for (int j = 0; j < oszlopok; j++) {
                    bw.write(tabla[i][j]);
                }
                bw.newLine();
            }
        }
    }

}
