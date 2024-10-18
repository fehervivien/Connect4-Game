package com.connect4;

import java.util.Random;

// A Jatekos osztályból öröklődik
    public class Gep extends Jatekos {
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
