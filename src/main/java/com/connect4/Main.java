package com.connect4;

import java.util.Scanner;

/* Main osztály:
A játék fő működését irányítja, összekapcsolva a többi osztályt,
és lehetővé téve a játékosok számára, hogy lépéseket tegyenek.

Feladata:
- Tábla és játékosok létrehozása
- Játék ciklus
- Lépés ellenőrzése
- Játékos váltás
- Scanner lezárása
*/

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


        /*Létrehozzuk a táblát 7 sorral és 6 oszloppal*/
        Tabla tabla = new Tabla(7, 6);

        //Emberi játékos
        Jatekos jatekos1 = new Ember('X');
        //Gépi játékos
        Jatekos jatekos2 = new Gep('O');

        //Az aktuális játékos
        Jatekos jelenlegiJatekos = jatekos1;
        HighScoreManager highScoreManager = new HighScoreManager();


        while (true) {
            //Kiírja a játékállást
            tabla.kiirTabla();
            System.out.println("Játékos " + jelenlegiJatekos.getJel() + ", válassz egy oszlopot (0-6): ");

            int oszlop;
            if (jelenlegiJatekos instanceof Ember) {
                //Emberi játékos lépése
                oszlop = scanner.nextInt();

            } else {
                //Gépi játékos lépése
                oszlop = jelenlegiJatekos.lepes(tabla);
                System.out.println("A gép " + oszlop + "-ba tett lépést.");
            }
            game.saveBoard("Savedgame.txt");
            //Ellenőrizzük, hogy sikerült-e a lépés
            if (tabla.lehelyez(oszlop, jelenlegiJatekos.getJel())) {
                //Ellenőrizzük, hogy nyert-e
                if (tabla.ellenorizGyoz(jelenlegiJatekos.getJel())) {
                    //Kiírja a végső állást
                    tabla.kiirTabla();
                    //Nyertes kiírása
                    System.out.println("Játékos " + jelenlegiJatekos.getJel() + " nyert!");

                    // Frissítjük a high score táblázatot
                    System.out.print("Add meg a neved a high score táblázathoz: ");
                    String nev = scanner.next();
                    highScoreManager.mentesHighScore(nev, 1); // Minden győzelem 1 pontot ér
                    highScoreManager.kiirHighScores(); // Kiírja a high score táblázatot
                    break;
                }
                //Változtassuk meg a játékost
                jelenlegiJatekos = (jelenlegiJatekos == jatekos1) ? jatekos2 : jatekos1;
            } else {
                System.out.println("Az oszlop tele van, válassz másikat!");
            }


        }



        //Scanner lezárása
        scanner.close();

    }
}
