/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gjyygle.xml;

import gjyygle.bibtex.BibtexEntry;
import gjyygle.bibtex.BibtexEntryType;
import gjyygle.bibtex.BibtexField;
import gjyygle.BibtexTietokanta;
import java.io.File;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author mkctammi
 */
public class XmlTietokanta implements BibtexTietokanta {

    File infile;
    File outfile;
    ArrayList<BibtexEntry> viitteet = new ArrayList();
    
    public XmlTietokanta(File in, File out) {
        infile = in;
        ArrayList<HashMap<String,String>> read = ReadXML.read(infile);
        for (HashMap<String,String> map : read) {
            viitteet.add(new BibtexEntry(map,BibtexEntryType.ARTICLE));
        }
        outfile = out;
    }
    @Override
    public void lisaaArtikkeli(BibtexEntry entry) {
        viitteet.add(entry);
    }

    @Override
    public List<BibtexEntry> listaaArtikkelit() {
        return viitteet;
    }

    @Override
    public void tallenna() {
        ArrayList<EnumMap<BibtexField,String>> r = new ArrayList();
        for(BibtexEntry viite : viitteet) {
            r.add(viite.getAllValues());
        }
        WriteXML.write(outfile, r);
    }
    
    public static void main(String[] args) {
        XmlTietokanta t = new XmlTietokanta(new File("testXml.xml"), new File("xmlout3.xml"));
        t.tallenna();
    }
}
