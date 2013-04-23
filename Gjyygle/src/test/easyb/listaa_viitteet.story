import gjyygle.kayttoliittyma.IO;
import gjyygle.kayttoliittyma.Kayttoliittyma;
import gjyygle.kayttoliittyma.TestIO;
import gjyygle.xml.XmlTietokanta;
import gjyygle.utils.FileWrite;
import java.io.File;

scenario "käyttäjä pystyy näkemään viitteiden lyhyen muodon", {
    given 'tietokannassa on tietoa', {
        tempXMLTietokanta = File.createTempFile("temp", "file")
        tempXMLTietokanta.deleteOnExit();
        String fileContents = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><root><entry><Year>2011</Year><Journal>testiJournal</Journal><Type>Article</Type><Author>A. Baka</Author><ID>baka00</ID><Title>Peeling onions</Title></entry><entry><Year>2012</Year><Journal>testiJournal2</Journal><Type>Book</Type><Author>A. Baka</Author><ID>baka01</ID><Title>Peeling onions, the book</Title></entry><entry><Year>2010</Year><Journal>testiJournal3</Journal><Type>inproceedings</Type><Author>A. Baka</Author><ID>baka02</ID><Title>Peeling onions: the future?</Title></entry></root>"
        FileWrite.stringToFile(fileContents, tempXMLTietokanta);

        String[] input = ["3", "x"]
        io = new TestIO(input)        
        XmlTietokanta tietokanta = new XmlTietokanta(tempXMLTietokanta)
        juttu = new Kayttoliittyma(io, tietokanta)
    }

    when 'listaa viitteet-toiminto valittu', {
        juttu.kaynnista()
    }

    then 'viitteet printataan ruudulle', {
        io.loytyykoRivi("baka00, A. Baka, Peeling onions, 2011, Article.\n").shouldEqual(true)
        io.loytyykoRivi("baka01, A. Baka, Peeling onions, the book, 2012, Book.\n").shouldEqual(true)
    }
}
