import gjyygle.kayttoliittyma.IO;
import gjyygle.kayttoliittyma.Kayttoliittyma;
import gjyygle.kayttoliittyma.TestIO;
import gjyygle.xml.XmlTietokanta;
import gjyygle.utils.FileWrite;
import java.io.File;

scenario "käyttäjä pystyy syöttämään käyttöliittymään article-tyyppisen viitteen pakolliset tiedot", {
    given 'ohjelma avattu, tietokannassa dataa valmiina', {
        tempXMLTietokanta = File.createTempFile("temp", "file")
        tempXMLTietokanta.deleteOnExit();
        String fileContents = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><root><entry><ID>baka00</ID><Author>A. Baka</Author><Title>Peeling onions</Title><Journal>mkyong</Journal><Year>2000</Year></entry></root>";
        FileWrite.stringToFile(fileContents, tempXMLTietokanta);

        String[] input = ["2", "3"]
        io = new TestIO(input)        
        XmlTietokanta tietokanta = new XmlTietokanta(tempXMLTietokanta)
        juttu = new Kayttoliittyma(io, tietokanta)
    }

    when 'generoidaan tiedosto', {
        juttu.kaynnista()
    }

    then 'bibtex-tiedosto generoitu oikealla sisällöllä', {
        File file = new File('kovakoodattuOutputTiedosto.bib')
        teksti = new Scanner(file).useDelimiter("\\A").next()
        teksti.contains("@ARTICLE{baka00").shouldEqual(true)
    }
}