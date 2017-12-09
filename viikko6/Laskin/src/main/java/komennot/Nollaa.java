/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komennot;

import javax.swing.JTextField;
import ohtu.Sovelluslogiikka;

/**
 *
 * @author Sade
 */
public class Nollaa implements Komento {

    private Sovelluslogiikka sovellus;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private int edellinenTulos;

    public Nollaa(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        this.sovellus = sovellus;
        this.syotekentta = syotekentta;
        this.tuloskentta = tuloskentta;
    }

    @Override
    public void suorita() {
        edellinenTulos = sovellus.tulos();
        sovellus.asetaTulos(0);
        tuloskentta.setText("0");
        syotekentta.setText("");
    }

    @Override
    public void peru() {
        sovellus.asetaTulos(edellinenTulos);
        tuloskentta.setText("" + edellinenTulos);
        syotekentta.setText("");
    }
}
