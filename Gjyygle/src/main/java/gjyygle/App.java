package gjyygle;

import gjyygle.kayttoliittyma.Kayttoliittyma;
import gjyygle.kayttoliittyma.KonsoliIO;
import gjyygle.xml.XmlTietokanta;
import java.io.File;
import java.io.IOException;


public class App {

    public static void main(String[] args) throws IOException {
        
        File tiedosto = new File("bibtex.xml");
        if (!tiedosto.exists()) {
            tiedosto.createNewFile();
        }
        BibtexTietokanta kanta = new XmlTietokanta(tiedosto);
        Kayttoliittyma liittyma = new Kayttoliittyma(new KonsoliIO(), kanta);
        liittyma.kaynnista();
    }
}
