
package gjyygle.bibtex;

import java.util.EnumMap;
import java.util.HashMap;

public class BibtexEntry {
    private EnumMap<BibtexField, String> arvot;
    private BibtexEntryType type;
    public BibtexEntry(BibtexEntryType type) {
        arvot = new EnumMap<BibtexField, String>(BibtexField.class);
        this.type = type;
    }
    public BibtexEntry(HashMap<String, String> values, BibtexEntryType type) {
        arvot = new EnumMap<BibtexField, String>(BibtexField.class);
        this.type = type;
        BibtexField[] required = type.getRequiredFields();
        lisaaArvot(values, required);
        BibtexField[] optional = type.getOptionalFields();
        lisaaArvot(values, optional);
        //halutaanko ei-required, ei-optional joita arto kuitenkin käyttää
        lisaaArvot(values, BibtexField.values());
        if (!hasFields()) {
            throw new IllegalArgumentException();
        }
    }
    public void poistaTyhjat() {
        for (BibtexField i : arvot.keySet()) {
            if (arvot.get(i) == null || arvot.get(i).equals(""))
            {
                arvot.remove(i);
            }
        }
    }
    private void lisaaArvot(HashMap<String, String> values, BibtexField[] kentat) {
        for (BibtexField i : kentat) {
            arvot.put(i, values.get(i.getName()));
        }
    }
    /**
     * Tarkistaa että entryllä on kaikkissa tarvittavissa kentissä arvo
     * @return onko kaikilla kentillä arvo
     */
    public boolean hasFields() {
        for (BibtexField i : type.getRequiredFields()) {
            if (!arvot.containsKey(i)) {
                return false;
            }
        }
        return true;
    }
    /**
     * Vaihtaa entryn tyypin (artikkeli, kirja, jne..)
     * @param type uusi tyyppi
     */
    public void setType(BibtexEntryType type) {
        this.type = type;
    }
    public BibtexEntryType getType() {
        return type;
    }
    public EnumMap<BibtexField, String> getAllValues() {
        return arvot;
    }
    public void setValue(BibtexField key, String value) throws ValidationException {
        if (key.validate(value)) {
            arvot.put(key, value);
        }
        else
        {
            throw new ValidationException("Arvo " + value + " ei käy kentälle " + key.getName());
        }
    }
    public String getValue(BibtexField key) {
        return arvot.get(key);
    }
    /**
     * Palauttaa arvon numerona, tai -1 jos arvo ei ole numero
     * @param key arvo, tai -1 jos ei ole numero
     * @return 
     */
    public int getValueInt(BibtexField key) {
        String ret = arvot.get(key);
        if (ret == null) {
            return -1;
        }
        if (key.validateInteger(ret))
        {
            return new Integer(ret);
        }
        return -1;
    }
}
