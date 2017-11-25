package ohtu.verkkokauppa;

import ohtu.verkkokauppa.interfaces.Rahalaitos;
import ohtu.verkkokauppa.interfaces.TuoteVarasto;
import ohtu.verkkokauppa.interfaces.Viite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Kauppa {

    private TuoteVarasto varasto;
    private Rahalaitos pankki;
    private Ostoskori ostoskori;
    private Viite viitegeneraattori;
    private String kaupanTili;

    @Autowired
    public Kauppa(TuoteVarasto varasto, Rahalaitos pankki, Viite viite) {
        this.varasto = varasto;
        this.pankki = pankki;
        this.viitegeneraattori = viite;
        this.kaupanTili = "33333-44455";
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = varasto.haeTuote(id);
        varasto.palautaVarastoon(t);
        ostoskori.poista(t);
    }

    public void lisaaKoriin(int id) {
        if (varasto.saldo(id) > 0) {
            Tuote t = varasto.haeTuote(id);
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();

        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
