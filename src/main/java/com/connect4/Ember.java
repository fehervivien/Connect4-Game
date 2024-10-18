package com.connect4;

import java.util.Scanner;

// A Jatekos osztályból öröklődik.
    public class Ember extends Jatekos {
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
