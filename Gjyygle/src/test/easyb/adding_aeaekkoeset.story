import gjyygle.kayttoliittyma.IO;
import gjyygle.kayttoliittyma.Kayttoliittyma;
import gjyygle.kayttoliittyma.TestIO;
import gjyygle.xml.XmlTietokanta;
import gjyygle.utils.FileWrite;
import java.io.File;

scenario "käyttäjä pystyy syöttämään käyttöliittymään ääkkösiä", {
    given 'artikkelin syöttö-toiminto avattu', {
        tempXMLTietokanta = File.createTempFile("temp", "file")
        tempXMLTietokanta.deleteOnExit();
        String fileContents = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><root></root>";
        FileWrite.stringToFile(fileContents, tempXMLTietokanta);

        String[] input = ["1", "1", "756", "Åke", "Äöäöää", "ÅÄÖåäö", "1999", "e", "3"]
        io = new TestIO(input)        
        XmlTietokanta tietokanta = new XmlTietokanta(tempXMLTietokanta)
        juttu = new Kayttoliittyma(io, tietokanta)
    }

    when 'ääkkösiä data syötetty eikä syötetä valinnaisia tietoja', {
        juttu.kaynnista()
    }

    then 'artikkeli on syötetty tietokantaan', {
        teksti = new Scanner(tempXMLTietokanta).useDelimiter("\\A").next()
        teksti.contains("<ID>756</ID>").shouldEqual(true)
        teksti.contains("<Type>Article</Type>").shouldEqual(true)
        teksti.contains("<Author>Åke</Author>").shouldEqual(true)
        teksti.contains("<Title>Äöäöää</Title>").shouldEqual(true)
        teksti.contains("<Journal>ÅÄÖåäö</Journal>").shouldEqual(true)
    }
}

scenario "käyttäjä pystyy generoimaan bibtexin jossa on ääkkösiä", {
    given 'artikkelin syöttö-toiminto avattu', {
        tempXMLTietokanta = File.createTempFile("temp", ".xml")
        tempXMLTietokanta.deleteOnExit();
        String fileContents = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><root><entry><Year>2011</Year><Journal>testiJournal</Journal><Type>Article</Type><Author>ÅÄÖåäö</Author><ID>ääkkönen</ID><Title>Åke</Title></entry></root>";
        FileWrite.stringToFile(fileContents, tempXMLTietokanta);

        tempBibTexFile = File.createTempFile("temp", ".bib")
        tempBibTexFile.deleteOnExit()

        String[] input = ["2", tempBibTexFile.getAbsolutePath(), "3"]
        io = new TestIO(input)        
        XmlTietokanta tietokanta = new XmlTietokanta(tempXMLTietokanta)
        juttu = new Kayttoliittyma(io, tietokanta)
    }

    when 'ääkkösiä data syötetty eikä syötetä valinnaisia tietoja', {
        juttu.kaynnista()
    }

    then 'artikkeli on syötetty tietokantaan', {
        teksti = new Scanner(tempBibTexFile).useDelimiter("\\A").next()
        teksti.contains("author = \"{\\AA\\\"{A}\\\"{O}\\aa\\\"{a}\\\"{o}}\"").shouldEqual(true)
    }
}
