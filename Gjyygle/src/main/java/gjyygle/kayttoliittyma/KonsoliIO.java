/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gjyygle.kayttoliittyma;

import java.util.Scanner;

/**
 *
 * @author ivahamaa
 */
public class KonsoliIO implements IO {
    private Scanner lukija;
    
    public KonsoliIO() {
        lukija = new Scanner(System.in);
    }
    
    

    @Override
    public void print(String m) {
        System.out.print(m );
    }

    @Override
    public String nextLine() {
        return lukija.nextLine();
    }

    @Override
    public void println(String m) {
        System.out.println(m );
    }
    
}
