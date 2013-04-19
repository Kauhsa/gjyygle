import gjyygle.kayttoliittyma.IO;
import gjyygle.kayttoliittyma.Kayttoliittyma;
import gjyygle.kayttoliittyma.TestIO;
import gjyygle.xml.XmlTietokanta;
import gjyygle.utils.FileWrite;
import java.io.File;

scenario "käyttäjä pystyy syöttämään käyttöliittymään book-tyyppisen viitteen pakolliset tiedot", {
    given 'kirjan syöttö-toiminto avattu', {
        tempXMLTietokanta = File.createTempFile("temp", "file")
        tempXMLTietokanta.deleteOnExit();
        String fileContents = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><root></root>";
        FileWrite.stringToFile(fileContents, tempXMLTietokanta);

        String[] input = ["1", "2", "artonkirjaiidee123", "arto1", "joku muu", "suuri kirja", "limes ry", "2010", "e", "3"]
        io = new TestIO(input)        
        XmlTietokanta tietokanta = new XmlTietokanta(tempXMLTietokanta)
        juttu = new Kayttoliittyma(io, tietokanta)
    }

    when 'pakollinen data syötetty eikä syötetä valinnaisia tietoja', {
        juttu.kaynnista()
    }

    then 'kirja on syötetty tietokantaan', {
        teksti = new Scanner(tempXMLTietokanta).useDelimiter("\\A").next()
        teksti.contains("<ID>artonkirjaiidee123</ID>").shouldEqual(true)
        teksti.contains("<Author>arto1</Author>").shouldEqual(true)
        teksti.contains("<Type>Book</Type>").shouldEqual(true)
    }
}

scenario "käyttäjä pystyy syöttämään käyttöliittymään book-tyyppisen viitteen pakolliset ja valinnaiset tiedot", {
    given 'kirjan syöttö-toiminto avattu', {
        tempXMLTietokanta = File.createTempFile("temp", "file")
        tempXMLTietokanta.deleteOnExit()
        fileContents = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><root></root>"
        FileWrite.stringToFile(fileContents, tempXMLTietokanta)

        String[] input = ["1", "2", "asdfa213", "arto2", "joku muu", "pieni kirja", "limes ry", "2009", "k", "1", "2", "5", "kumpula", "1st", "5", "huomio!", "jep", "3"]
        io = new TestIO(input)        
        XmlTietokanta tietokanta = new XmlTietokanta(tempXMLTietokanta)
        juttu = new Kayttoliittyma(io, tietokanta)
    }

    when 'datat syötetty', {
        juttu.kaynnista()
    }

    then 'kirjan on syötetty tietokantaan', {
        teksti = new Scanner(tempXMLTietokanta).useDelimiter("\\A").next()
        teksti.contains("<ID>asdfa213</ID>").shouldEqual(true)
        teksti.contains("<Author>arto2</Author>").shouldEqual(true)
        teksti.contains("<Type>Book</Type>").shouldEqual(true)
        teksti.contains("<Note>huomio!</Note>").shouldEqual(true)
    }
}