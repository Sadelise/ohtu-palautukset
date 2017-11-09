package ohtu.verkkokauppa;

import ohtu.verkkokauppa.interfaces.Viite;
import org.springframework.stereotype.Component;

@Component
public class Viitegeneraattori implements Viite {

    private int seuraava;

    public Viitegeneraattori() {
        seuraava = 1;
    }

    @Override
    public int uusi() {
        return seuraava++;
    }
}
