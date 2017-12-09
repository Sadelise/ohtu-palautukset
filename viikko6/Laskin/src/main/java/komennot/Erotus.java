package komennot;

import javax.swing.JTextField;
import ohtu.Sovelluslogiikka;

public class Erotus implements Komento {
    
    private Sovelluslogiikka sovellus;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private int edellinenTulos;
    
    public Erotus(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        this.sovellus = sovellus;
        this.syotekentta = syotekentta;
        this.tuloskentta = tuloskentta;
    }
    
    private int syote() {
        int arvo = 0;
        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }
        return arvo;
    }
    
    @Override
    public void suorita() {
        edellinenTulos = sovellus.tulos();
        sovellus.miinus(syote());
        syotekentta.setText("");
        tuloskentta.setText("" + sovellus.tulos());
    }
    
    @Override
    public void peru() {
        sovellus.asetaTulos(edellinenTulos);
        tuloskentta.setText("" + edellinenTulos);
    }
}
