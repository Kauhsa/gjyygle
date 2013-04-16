/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gjyygle.xml;

import gjyygle.bibtex.BibtexEntry;
import gjyygle.bibtex.BibtexEntryType;
import gjyygle.bibtex.BibtexField;
import gjyygle.BibtexTietokanta;
import gjyygle.bibtex.ValidationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mkctammi
 */
public class XmlTietokanta implements BibtexTietokanta {

    File savefile;
    ArrayList<BibtexEntry> viitteet = new ArrayList();
    
    public XmlTietokanta(File save) {
        savefile = save;
        loadEntries();
    }
    public final void loadEntries() {
        ArrayList<HashMap<String,String>> read;
        try {
            read = ReadXML.read(savefile);
        } catch (FileNotFoundException ex) {
            return;
        }
        for (HashMap<String,String> map : read) {
            viitteet.add(new BibtexEntry(map,BibtexEntryType.ARTICLE));
        }
    }
    @Override
    public void lisaaArtikkeli(BibtexEntry entry) throws ValidationException{
        for (BibtexEntry be : viitteet) {
            if (be.getValue(BibtexField.ID).equals(entry.getValue(BibtexField.ID))) {
                throw new ValidationException("Database already has an entry with id " + entry.getValue(BibtexField.ID));
            }
        }
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
        WriteXML.write(savefile, r);
    }
}
