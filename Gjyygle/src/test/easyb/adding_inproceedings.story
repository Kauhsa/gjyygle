import gjyygle.kayttoliittyma.IO;
import gjyygle.kayttoliittyma.Kayttoliittyma;
import gjyygle.kayttoliittyma.TestIO;
import gjyygle.xml.XmlTietokanta;
import gjyygle.utils.FileWrite;
import java.io.File;

scenario "käyttäjä pystyy syöttämään käyttöliittymään inproceedings-tyyppisen viitteen pakolliset tiedot", {
    given 'inproceedingsin syöttö-toiminto avattu', {
        tempXMLTietokanta = File.createTempFile("temp", "file")
        tempXMLTietokanta.deleteOnExit();
        String fileContents = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><root></root>";
        FileWrite.stringToFile(fileContents, tempXMLTietokanta);

        String[] input = ["1", "3", "inproceedings123", "tyyppi", "Hieno paperi", "kirja", "2005", "e", "3"]
        io = new TestIO(input)        
        XmlTietokanta tietokanta = new XmlTietokanta(tempXMLTietokanta)
        juttu = new Kayttoliittyma(io, tietokanta)
    }

    when 'pakollinen data syötetty eikä syötetä valinnaisia tietoja', {
        juttu.kaynnista()
    }

    then 'inproceedings on syötetty tietokantaan', {
        teksti = new Scanner(tempXMLTietokanta).useDelimiter("\\A").next()
        teksti.contains("<ID>inproceedings123</ID>").shouldEqual(true)
        teksti.contains("<Author>tyyppi</Author>").shouldEqual(true)
        teksti.contains("<Type>inproceedings</Type>").shouldEqual(true)
    }
}

scenario "käyttäjä pystyy syöttämään käyttöliittymään inproceedings-tyyppisen viitteen pakolliset ja valinnaiset tiedot", {
    given 'inproceedings syöttö-toiminto avattu', {
        tempXMLTietokanta = File.createTempFile("temp", "file")
        tempXMLTietokanta.deleteOnExit()
        fileContents = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><root></root>"
        FileWrite.stringToFile(fileContents, tempXMLTietokanta)

        String[] input = ["1", "3", "inproceedings1234", "tyyppi", "Hieno paperi", "kirja", "2005", "k", "joku", "1", "2", "50", "4--11", "paikka", "5", "tktl", "publishing oy", "aika hieno artikkeli", "avain", "3"]
        io = new TestIO(input)        
        XmlTietokanta tietokanta = new XmlTietokanta(tempXMLTietokanta)
        juttu = new Kayttoliittyma(io, tietokanta)
    }

    when 'datat syötetty', {
        juttu.kaynnista()
    }

    then 'inproceedings on syötetty tietokantaan', {
        teksti = new Scanner(tempXMLTietokanta).useDelimiter("\\A").next()
        teksti.contains("<ID>inproceedings1234</ID>").shouldEqual(true)
        teksti.contains("<Author>tyyppi</Author>").shouldEqual(true)
        teksti.contains("<Type>inproceedings</Type>").shouldEqual(true)
        teksti.contains("<Note>aika hieno artikkeli</Note>").shouldEqual(true)
    }
}