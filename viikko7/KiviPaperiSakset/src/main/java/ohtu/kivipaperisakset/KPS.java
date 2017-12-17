package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KPS {

    private static final Scanner scanner = new Scanner(System.in);

    public void pelaa() {
        Tuomari tuomari = new Tuomari();
        setTekoaly();
        String ekanSiirto = "";
        String tokanSiirto = "";

        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            System.out.println("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = scanner.nextLine();

            toisenSiirto();
            tekoalyAsetaSiirto(ekanSiirto);

            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }

    public static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

    abstract void setTekoaly();

    abstract String toisenSiirto();

    abstract void tekoalyAsetaSiirto(String ekanSiirto);
}
