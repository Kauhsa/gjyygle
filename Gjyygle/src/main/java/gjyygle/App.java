package gjyygle;

import gjyygle.kayttoliittyma.Kayttoliittyma;
import gjyygle.kayttoliittyma.KonsoliIO;


public class App {

    public static void main(String[] args) {
        //String input = "1\n3\n2\n3";
        //Scanner lukija = new Scanner(input);
        Kayttoliittyma liittyma = new Kayttoliittyma(new KonsoliIO());
        liittyma.kaynnista();
    }
}
