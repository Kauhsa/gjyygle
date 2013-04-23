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
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

/**
 *
 * @author mkctammi
 */
public class XmlTietokanta implements BibtexTietokanta {

    private File savefile;
    private ArrayList<BibtexEntry> viitteet;
    private ArrayList<String> filtterit;
    private ArrayList<BibtexEntry> filtteroidytViitteet;

    public XmlTietokanta(File save) throws ValidationException {
        viitteet = new ArrayList();
        filtterit = new ArrayList();;
        savefile = save;
        loadEntries();
    }

    public final void loadEntries() throws ValidationException {
        ArrayList<HashMap<String, String>> read;
        try {
            read = ReadXML.read(savefile);
        } catch (FileNotFoundException ex) {
            return;
        }
        for (HashMap<String, String> map : read) {
            String typename = map.get("Type");
            map.remove("Type");
            if (typename == null) {
                throw new ValidationException("Type is missing from entry!");
            }
            BibtexEntryType entryType = BibtexEntryType.getType(typename);
            if (entryType == null) {
                throw new ValidationException("Invalid type for entry!");
            }
            viitteet.add(new BibtexEntry(map, entryType));
        }
    }

    @Override
    public void lisaaArtikkeli(BibtexEntry entry) throws ValidationException {
        String ID = entry.getValue(BibtexField.ID).trim();
        for (BibtexEntry be : viitteet) {
            String vertailuID = be.getValue(BibtexField.ID).trim();
            if (String.CASE_INSENSITIVE_ORDER.compare(ID, vertailuID) == 0) {
                throw new ValidationException("Database already has an entry with id " + entry.getValue(BibtexField.ID));
            }
        }
        viitteet.add(entry);
    }

    @Override
    public List<BibtexEntry> listaaArtikkelit() {
        if (filtteroidytViitteet == null) {
            filtteroidytViitteet = new ArrayList();
            for (BibtexEntry tutkittavaViite : viitteet) {
                String kentatStringina = tutkittavaViite.kaikkiKentatStringina();
                boolean match = true;
                for (String filtteri : filtterit) {
                    if (!kentatStringina.contains(filtteri)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    filtteroidytViitteet.add(tutkittavaViite);
                }
            }
        }
        return Collections.unmodifiableList(filtteroidytViitteet);
    }

    @Override
    public void tallenna() {
        ArrayList<HashMap<String, String>> r = new ArrayList();
        for (BibtexEntry viite : viitteet) {
            HashMap<String, String> l = new HashMap();
            r.add(l);
            l.put("Type", viite.getType().getName());
            Iterator<Entry<BibtexField, String>> it = viite.getAllValues().entrySet().iterator();
            while (it.hasNext()) {
                Entry<BibtexField, String> next = it.next();
                l.put(next.getKey().getName(), next.getValue());
            }
        }
        WriteXML.write(savefile, r);
    }

    @Override
    public void lisaaFiltteri(String filtteri) {
        filtteroidytViitteet = null;
        filtterit.add(filtteri);
    }

    @Override
    public List<String> getFiltterit() {
        return Collections.unmodifiableList(filtterit);
    }

    @Override
    public void nollaaFiltterit() {
        filtteroidytViitteet = viitteet;
        filtterit.clear();
    }
}
