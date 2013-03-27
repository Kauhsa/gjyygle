
package Bibtex;

import java.util.EnumMap;
import java.util.HashMap;

public class BibtexEntry {
    protected EnumMap<BibtexField, String> arvot;
    public BibtexEntry() {
        arvot = new EnumMap<BibtexField, String>(BibtexField.class);
    }
    public BibtexEntry(HashMap<String, String> values) {
        BibtexField[] required = BibtexEntryType.ARTICLE.getRequiredFields();
        for (BibtexField i : required) {
            if (!values.containsKey(i.getName())) {
                throw new IllegalArgumentException();
            }
            arvot.put(i, values.get(i.getName()));
        }
    }
    public EnumMap<BibtexField, String> getAllValues() {
        return arvot;
    }
    private int findInteger(String key) {
        String found = arvot.get(key);
        if (found == null) {
            return -1;
        }
        return new Integer(found);
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
