package ohtu.verkkokauppa;

import ohtu.verkkokauppa.interfaces.TapahtumaLogi;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class Kirjanpito implements TapahtumaLogi {

    private ArrayList<String> tapahtumat;

    public Kirjanpito() {
        tapahtumat = new ArrayList<String>();
    }

    @Override
    public void lisaaTapahtuma(String tapahtuma) {
        tapahtumat.add(tapahtuma);
    }

    @Override
    public ArrayList<String> getTapahtumat() {
        return tapahtumat;
    }
}
