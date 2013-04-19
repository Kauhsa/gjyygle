import gjyygle.kayttoliittyma.IO;
import gjyygle.kayttoliittyma.Kayttoliittyma;
import gjyygle.kayttoliittyma.TestIO;
import gjyygle.xml.XmlTietokanta;
import gjyygle.utils.FileWrite;
import java.io.File;

scenario "käyttäjä pystyy syöttämään käyttöliittymään article-tyyppisen viitteen pakolliset tiedot", {
    given 'ohjelma avattu, tietokannassa dataa valmiina', {
        tempXMLTietokanta = File.createTempFile("temp", ".xml")
        tempXMLTietokanta.deleteOnExit();
        String fileContents = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><root><entry><Year>2011</Year><Journal>testiJournal</Journal><Type>Article</Type><Author>A. Baka</Author><ID>baka00</ID><Title>Peeling onions</Title></entry></root>"
        FileWrite.stringToFile(fileContents, tempXMLTietokanta);

        tempBibTexFile = File.createTempFile("temp", ".bib")
        tempBibTexFile.deleteOnExit()

        String[] input = ["2", tempBibTexFile.getAbsolutePath(), "3"]
        io = new TestIO(input)        
        XmlTietokanta tietokanta = new XmlTietokanta(tempXMLTietokanta)
        juttu = new Kayttoliittyma(io, tietokanta)
    }

    when 'generoidaan tiedosto', {
        juttu.kaynnista()
        
    }

    then 'bibtex-tiedosto generoitu oikealla sisällöllä', {
        teksti = new Scanner(tempBibTexFile).useDelimiter("\\A").next()
        teksti.contains("@article{baka00").shouldEqual(true)
    }
}