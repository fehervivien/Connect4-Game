package com.connect4;

/* TablaKezelo osztály:
Egy egyszerű modellje a játékosoknak, amely tárolja
a nevüket és a játék által használt karakterüket,
lehetővé teszi a játékosok azonosítását
és a játék állapotának kezelését. */

public class TablaKezelo {
    private String nev; // Játékos neve
    private char karakter; // X vagy O

    /* Ez a konstruktor felelős az osztály példányának
    létrehozásáért */
    public TablaKezelo(String nev, char karakter) {
        this.nev = nev;
        this.karakter = karakter;
    }

    /* Lehetővé teszik a nev és karakter adattagok
    értékének lekérését */
    public String getNev() {
        return nev;
    }
    public char getKarakter() {
        return karakter;
    }

}
