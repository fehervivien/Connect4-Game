package com.connect4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Szeretnél betölteni egy játékpályát egy fájlból? (Igen/Nem)");
        String choice = scanner.nextLine();

        PalyaOlvasasa game;
        if (choice.equalsIgnoreCase("Igen")) {
            System.out.print("Add meg a fájl elérési útját: ");
            String filePath = scanner.nextLine();
            game = new PalyaOlvasasa(filePath);
        } else {
            game = new PalyaOlvasasa(); // üres táblával indít
        }

        Tabla tabla = new Tabla(7, 6);
        Jatekos jatekos1 = new Ember('X');
        Jatekos jatekos2 = new Gep('O');
        Jatekos jelenlegiJatekos = jatekos1;
        HighScoreManager highScoreManager = new HighScoreManager();

        while (true) {
            tabla.kiirTabla();
            System.out.println("Játékos " + jelenlegiJatekos.getJel() + ", válassz egy oszlopot (0-6): ");
            int oszlop;

            if (jelenlegiJatekos instanceof Ember) {
                oszlop = scanner.nextInt();
            } else {
                oszlop = jelenlegiJatekos.lepes(tabla);
                System.out.println("A gép " + oszlop + "-ba tett lépést.");
            }

            // Frissítsük a játék állását
            game.updateBoard(oszlop, jelenlegiJatekos.getJel());

            // Ellenőrizzük, hogy sikerült-e a lépés
            if (tabla.lehelyez(oszlop, jelenlegiJatekos.getJel())) {
                game.saveBoard("Savedgame.txt"); // mentés
                if (tabla.ellenorizGyoz(jelenlegiJatekos.getJel())) {
                    tabla.kiirTabla();
                    System.out.println("Játékos " + jelenlegiJatekos.getJel() + " nyert!");
                    System.out.print("Add meg a neved a high score táblázathoz: ");
                    String nev = scanner.next();
                    highScoreManager.mentesHighScore(nev, 1);
                    highScoreManager.kiirHighScores();
                    break;
                }
                jelenlegiJatekos = (jelenlegiJatekos == jatekos1) ? jatekos2 : jatekos1;
            } else {
                System.out.println("Az oszlop tele van, válassz másikat!");
            }
        }

        scanner.close();
    }
}
