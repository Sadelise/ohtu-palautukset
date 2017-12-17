package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends KPS {

    private static final Scanner scanner = new Scanner(System.in);
    private Tekoaly tekoaly;

    private KPSTekoaly() {
    }

    public static KPSTekoaly luoHelppoYksinpeli() {
        return new KPSTekoaly();
    }
    
    @Override
    void setTekoaly() {
        tekoaly = new Tekoaly();
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
