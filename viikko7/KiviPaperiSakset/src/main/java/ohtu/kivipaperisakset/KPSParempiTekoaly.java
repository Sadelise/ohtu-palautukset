package ohtu.kivipaperisakset;

import java.util.Scanner;

import java.util.Scanner;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends KPS {

    private static final Scanner scanner = new Scanner(System.in);
    private TekoalyParannettu tekoaly;

    private KPSParempiTekoaly() {
    }

    public static KPSParempiTekoaly luoVaikeaYksinpeli() {
        return new KPSParempiTekoaly();
    }

    @Override
    void setTekoaly() {
        tekoaly = new TekoalyParannettu(20);
    }

    @Override
    String toisenSiirto() {
        String tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        return tokanSiirto;
    }

    @Override
    void tekoalyAsetaSiirto(String ekanSiirto) {
        tekoaly.asetaSiirto(ekanSiirto);
    }
}
