import gjyygle.kayttoliittyma.IO;
import gjyygle.kayttoliittyma.Kayttoliittyma;
import gjyygle.kayttoliittyma.TestIO;
import gjyygle.xml.XmlTietokanta;
import gjyygle.utils.FileWrite;
import java.io.File;

scenario "käyttäjä pystyy syöttämään käyttöliittymään article-tyyppisen viitteen pakolliset tiedot", {
    given 'artikkelin syöttö-toiminto avattu', {
        tempXMLTietokanta = File.createTempFile("temp", "file")
        tempXMLTietokanta.deleteOnExit();
        String fileContents = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><root></root>";
        FileWrite.stringToFile(fileContents, tempXMLTietokanta);

        String[] input = ["1", "1", "Kalle", "Peruna", "Medicus", "1999", "756", "e", "3"]
        io = new TestIO(input)        
        XmlTietokanta tietokanta = new XmlTietokanta(tempXMLTietokanta)
        juttu = new Kayttoliittyma(io, tietokanta)
    }

    when 'pakollinen data syötetty eikä syötetä valinnaisia tietoja', {
        juttu.kaynnista()
    }

    then 'artikkeli on syötetty tietokantaan', {
        teksti = new Scanner(tempXMLTietokanta).useDelimiter("\\A").next()
        teksti.contains("<ID>756</ID>").shouldEqual(true)
        teksti.contains("<Author>Kalle</Author>").shouldEqual(true)
        teksti.contains("<Type>Article</Type>").shouldEqual(true)
    }
}

scenario "käyttäjä pystyy syöttämään käyttöliittymään article-tyyppisen viitteen pakolliset ja valinnaiset tiedot", {
    given 'artikkelin syöttö-toiminto avattu', {
        tempXMLTietokanta = File.createTempFile("temp", "file")
        tempXMLTietokanta.deleteOnExit()
        fileContents = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><root></root>"
        FileWrite.stringToFile(fileContents, tempXMLTietokanta)

        String[] input = ["1", "1", "Kalle", "Peruna", "Medicus", "1999", "756", "k", "14", "15", "99", "100", "1", "moi", "avain", "3"]
        io = new TestIO(input)        
        XmlTietokanta tietokanta = new XmlTietokanta(tempXMLTietokanta)
        juttu = new Kayttoliittyma(io, tietokanta)
    }

    when 'datat syötetty', {
        juttu.kaynnista()
    }

    then 'artikkeli on syötetty tietokantaan', {
        teksti = new Scanner(tempXMLTietokanta).useDelimiter("\\A").next()
        teksti.contains("<ID>756</ID>").shouldEqual(true)
        teksti.contains("<Author>Kalle</Author>").shouldEqual(true)
        teksti.contains("<Type>Article</Type>").shouldEqual(true)
        teksti.contains("<Key>moi</Key>").shouldEqual(true)
    }
}