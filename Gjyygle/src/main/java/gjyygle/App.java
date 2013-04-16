package gjyygle;

import gjyygle.bibtex.BibtexGen;
import gjyygle.bibtex.ValidationException;
import gjyygle.kayttoliittyma.Kayttoliittyma;
import gjyygle.kayttoliittyma.KonsoliIO;
import gjyygle.xml.XmlTietokanta;
import java.io.File;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException, ValidationException {


        BibtexTietokanta kanta = new XmlTietokanta(new File("bibtex.xml"));
        //  BibtexGen generaattori = new BibtexGen(kanta);
        Kayttoliittyma liittyma = new Kayttoliittyma(new KonsoliIO(), kanta);
        liittyma.kaynnista();
    }
}
