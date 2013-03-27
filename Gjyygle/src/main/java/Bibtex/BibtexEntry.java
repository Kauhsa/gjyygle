
package Bibtex;

import java.util.EnumMap;
import java.util.HashMap;

public class BibtexEntry {
    private EnumMap<BibtexField, String> arvot;
    private BibtexEntryType type;
    public BibtexEntry() {
        arvot = new EnumMap<BibtexField, String>(BibtexField.class);
    }
    public BibtexEntry(HashMap<String, String> values, BibtexEntryType type) {
        arvot = new EnumMap<BibtexField, String>(BibtexField.class);
        this.type = type;
        BibtexField[] required = type.getRequiredFields();
        lisaaArvot(values, required);
        BibtexField[] optional = type.getOptionalFields();
        lisaaArvot(values, required);
    }
    private void lisaaArvot(HashMap<String, String> values, BibtexField[] kentat) {
        for (BibtexField i : kentat) {
            if (!values.containsKey(i.getName())) {
                throw new IllegalArgumentException();
            }
            arvot.put(i, values.get(i.getName()));
        }
    }
    public void setType(BibtexEntryType type) {
        this.type = type;
    }
    public BibtexEntryType getType() {
        return type;
    }
    public EnumMap<BibtexField, String> getAllValues() {
        return arvot;
    }
    public void setValue(BibtexField key, String value) {
        if (key.validate(value)) {
            arvot.put(key, value);
        }
    }
    public String getValue(BibtexField key) {
        return arvot.get(key);
    }
    public int getValueInt(BibtexField key) {
        String ret = arvot.get(key);
        if (key.validateInteger(ret))
        {
            return new Integer(ret);
        }
        return -1;
    }

}
