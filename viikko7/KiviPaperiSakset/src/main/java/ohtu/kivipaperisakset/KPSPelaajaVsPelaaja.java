package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja extends KPS {

    private static final Scanner scanner = new Scanner(System.in);

    private KPSPelaajaVsPelaaja() {
    }

    public static KPSPelaajaVsPelaaja luoKaksinpeli() {
        return new KPSPelaajaVsPelaaja();
    }

    @Override
    void setTekoaly() {
        // ei tee mitään
    }

    @Override
    String toisenSiirto() {
        System.out.println("Toisen pelaajan siirto: ");
        String tokanSiirto = scanner.nextLine();
        return tokanSiirto;
    }

    @Override
    void tekoalyAsetaSiirto(String ekanSiirto) {
        // ei tee mitään
    }

}
