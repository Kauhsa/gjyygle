/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gjyygle.kayttoliittyma;

import gjyygle.kayttoliittyma.IO;
import java.util.ArrayList;

public class IOForTest implements IO {

    String[] inputit;
    int palautettava = 0;
    ArrayList<String> printatut;

    public IOForTest(String[] inputit) {
        this.inputit = inputit;
        printatut = new ArrayList<String>();
    }

    @Override
    public String nextLine() {
        return inputit[palautettava++];
    }

    @Override
    public void print(String printtaa) {
        printatut.add(printtaa);
    }

    @Override
    public void println(String printtaa) {
        printatut.add(printtaa + "\n");
    }
    
    public void asetaInput(String[] input) {
        this.inputit = input;
        printatut = new ArrayList<String>();
    }

    public String monesPrintattu(int mones) {
        return printatut.get(mones);
    }

    public boolean loytyykoRivi(String rivi) {
        return printatut.contains(rivi);
    }
}
