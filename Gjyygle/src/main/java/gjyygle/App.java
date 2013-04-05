package gjyygle;

import gjyygle.kayttoliittyma.Kayttoliittyma;
import gjyygle.kayttoliittyma.KonsoliIO;
import gjyygle.xml.XmlTietokanta;
import java.io.File;
import java.io.IOException;


public class App {

    public static void main(String[] args) throws IOException {
        
        
        BibtexTietokanta kanta = new XmlTietokanta(new File("bibtex.xml"));
        Kayttoliittyma liittyma = new Kayttoliittyma(new KonsoliIO(), kanta);
        liittyma.kaynnista();
    }
}
