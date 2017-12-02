package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Syötetyt arvot eivät voi olla negatiivisia.");
        }
        lukujono = new int[kapasiteetti];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (!kuuluuJoukkoon(luku)) {
            lukujono[alkioidenLkm] = luku;
            alkioidenLkm++;
            kasvataTaulukkoa();
            return true;
        }
        return false;
    }

    private void kasvataTaulukkoa() {
        if (alkioidenLkm % lukujono.length == 0) {
            int[] taulukkoOld = lukujono;
            lukujono = new int[alkioidenLkm + kasvatuskoko];;
            System.arraycopy(taulukkoOld, 0, lukujono, 0, taulukkoOld.length);
        }
    }

    public boolean kuuluuJoukkoon(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return true;
            }
        }
        return false;
    }

    private int etsiLuvunIndeksi(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return i;
            }
        }
        return -1;
    }

    public boolean poista(int luku) {
        int luvunIndeksi = etsiLuvunIndeksi(luku);
        if (luvunIndeksi >= 0) {
            siirraLukujaYhdellaTaaksepain(luvunIndeksi);
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    private void siirraLukujaYhdellaTaaksepain(int aloitusIndeksi) {
        for (int j = aloitusIndeksi; j < alkioidenLkm - 1; j++) {
            int apu = lukujono[j];
            lukujono[j] = lukujono[j + 1];
            lukujono[j + 1] = apu;
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        StringBuilder tuotos = new StringBuilder();
        tuotos.append("{");
        for (int i = 0; i < alkioidenLkm; i++) {
            tuotos.append(lukujono[i]);
            if (i < alkioidenLkm - 1) {
                tuotos.append(", ");
            }
        }
        tuotos.append("}");
        return tuotos.toString();
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujono[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < bTaulu.length; i++) {
            a.lisaa(bTaulu[i]);
        }
        return a;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        int[] aTaulu = a.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            if(!b.kuuluuJoukkoon(aTaulu[i])) {
                a.poista(aTaulu[i]);
            }
        }
        return a;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < bTaulu.length; i++) {
            a.poista(bTaulu[i]);
        }
        return a;
    }
}
