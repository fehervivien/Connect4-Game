package com.connect4;

import java.util.Random;
import java.util.Scanner;

/* A Jatekos absztrakt osztály + 2 konkrét osztály:
Kétféle játékost valósít meg, egy Embert és egy Gépet
illetve a játékosok lépéseit.

- Jatekos absztrakt osztály:
    Egy absztrakt osztály,amely meghatározza a közös
     viselkedést (pl. a karakter és a lépés fogalma).
- Ember osztály:
    Egy emberi játékos osztály, amely jelenleg nincs teljesen implementálva
    (a felhasználó választ oszlopot).
- Gep osztály:
    Egy gépi játékos, amely véletlenszerűen választja
    ki az oszlopot, ahová a lépést megteszi.
*/


public abstract class Jatekos {
    /* A játékos jelölése ('X' vagy 'O'), amelyet
    a tábla megjelenít.*/    
    protected char jel;

        /* A konstruktor beállítja a játékos
        karakterét */
        public Jatekos(char jel) {
            this.jel = jel;
        }

        /* Minden játékos számára egyedi lesz,
        és felelős a lépésért. Mivel absztrakt,
        minden alosztályban implementálni kell.*/
        public abstract int lepes(Tabla tabla);

        /*Visszaadja a játékos karakterét.*/
        public char getJel() {
            return jel;
        }
}

    // A Jatekos osztályból öröklődik.
    class Ember extends Jatekos {
        // Beolvasás a felhasználótól
        private Scanner scanner;
        
        /* Konstruktor: Meghívja a szülő
        konstruktorát, hogy beállítsa a karaktert.*/
        public Ember(char jel) {
            super(jel);
            /* Inicializáljuk a Scanner objektumot */
            scanner = new Scanner(System.in);
        }

        @Override
        public int lepes(Tabla tabla) {
            int oszlop = -1;
            boolean ervenyesLepes = false;

            /* Ismétlődően kérje a felhasználótól
             az oszlopot, amíg nem kap érvényes
             lépést, ha nem jó oszlopot mond akkor
             hibaüzenetet ad vissza*/
            while (!ervenyesLepes) {
                System.out.print("Játékos '" + jel + "', add meg az oszlop számát (0 - " + (tabla.getOszlopok() - 1) + "): ");
                oszlop = scanner.nextInt();

                if (oszlop >= 0 && oszlop < tabla.getOszlopok()) {
                    ervenyesLepes = true;
                } else {
                    System.out.println("Érvénytelen oszlop! Próbáld újra.");
                }
            }

            return oszlop;
        }

    }

    // A Jatekos osztályból öröklődik
    class Gep extends Jatekos {
        private Random random;

        /* Konstruktor: Meghívja a szülő
        konstruktorát a karakter beállításához.*/
        public Gep(char jel) {
            super(jel);
            // Inicializáljuk a Random objektumot
            random = new Random();
        }

        @Override
        /*A gépi játékos véletlenszerűen választ
        egy oszlopot a tábla méretén belül.
        A Random osztályt használja, hogy
        kiválasszon egy érvényes oszlopot
        (amit a tabla.getOszlopok() ad meg).*/
        public int lepes(Tabla tabla) {
            return random.nextInt(tabla.getOszlopok());
        }
}


