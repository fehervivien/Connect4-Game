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


