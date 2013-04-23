package gjyygle.bibtex;

import gjyygle.BibtexTietokanta;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;

public class BibtexGen {

    private BibtexTietokanta db;

    public BibtexGen(BibtexTietokanta db) {
        this.db = db;
    }

    public void generate(OutputStream out) {
        PrintStream ps = new PrintStream(out);
        List<BibtexEntry> lista = db.listaaArtikkelit();
        for (BibtexEntry entry : lista) {
            entry.poistaTyhjat();
            EnumMap<BibtexField, String> map = palautaTulostettavaEnumMap(entry.getAllValues());
            ps.println("@" + entry.getType().toString().toLowerCase() + "{" + entry.getValue(BibtexField.ID) + ",");
            Iterator entries = map.entrySet().iterator();
            entryjenLapikaynti(entries, ps);
            ps.println("}");
            ps.println();
        }
        ps.close();
    }

    public String skandiMuunnin(String rivi) {
        rivi = rivi.replaceAll("-", "{-}");
        rivi = rivi.replaceAll("Ä", "\\\\\"{A}");
        rivi = rivi.replaceAll("ö", "\\\\\"{o}");
        rivi = rivi.replaceAll("Ö", "\\\\\"{O}");
        rivi = rivi.replaceAll("å", "{\\\\aa}");
        rivi = rivi.replaceAll("Å", "{\\\\AA}");
        return rivi.replaceAll("ä", "\\\\\"{a}");
    }
    
    private static EnumMap<BibtexField, String> palautaTulostettavaEnumMap(EnumMap<BibtexField, String> map) {
        EnumMap<BibtexField, String> printMap = new EnumMap<BibtexField, String>(BibtexField.class);
        for (EnumMap.Entry<BibtexField, String> arvot : map.entrySet()) {
            if (arvot.getValue() != null) {
                printMap.put(arvot.getKey(), arvot.getValue().toString());
            }
        }
        return printMap;
    }
    
    private void entryjenLapikaynti(Iterator entries, PrintStream ps) {
        while (entries.hasNext()) {
            EnumMap.Entry seuraavaArvo = (EnumMap.Entry) entries.next();
            if (seuraavaArvo.getKey().equals(BibtexField.ID)) {
                continue;
            }
            String rivi = "    " + seuraavaArvo.getKey().toString().toLowerCase() + " = \"{" + seuraavaArvo.getValue() + "}\"";
            rivi = skandiMuunnin(rivi);
            if (entries.hasNext()) {
                rivi += ",";
            }
            ps.println(rivi);
        }
    }    
//    public static void main(String[] args) {
//        OutputStream output = null;
//        try {
//            //        ByteArrayOutputStream foo = null;
//            //        generate(foo);
//            //        foo.toByteArray();
//            BibtexGen gen = new BibtexGen(new BibtexTietokantaMock());
//            output = new FileOutputStream("bibtex.bib");
//            gen.generate(output);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(BibtexGen.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                output.close();
//            } catch (IOException ex) {
//                Logger.getLogger(BibtexGen.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
}