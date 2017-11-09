package ohtu.verkkokauppa;

import ohtu.verkkokauppa.interfaces.Viite;

public class Viitegeneraattori implements Viite {
    
    private int seuraava;
    
    public Viitegeneraattori(){
        seuraava = 1;    
    }
    
    @Override
    public int uusi(){
        return seuraava++;
    }
}
