package Bibtex;

import gjyygle.BibtexTietokanta;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BibtexGen {

    private List<BibtexEntry> lista;

    public BibtexGen(BibtexTietokanta db) {
        lista = db.listaaArtikkelit();
    }
// SAMPLE BIBTEXFILUSTA
//    @ARTICLE{Bailey,
//   author = "D. H. Bailey and P. N. Swarztrauber",
//   title = "The fractional {F}ourier transform and applications",
//   journal = "SIAM Rev.",
//   volume = 33,
//   number = 3,
//   pages = "389--404",
//   year = 1991
//   }

    public void generate() {
        PrintWriter tiedot = null;
        try {
            tiedot = new PrintWriter(new File("bibtex.bib"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BibtexGen.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (BibtexEntry entry : lista) {
            tiedot.println("@" + entry.getType() + "{" + entry.getValue(BibtexField.ID));
            EnumMap<BibtexField, String> map = entry.getAllValues();
            for (EnumMap.Entry<BibtexField, String> arvot : map.entrySet()) {
                if (arvot.getKey().equals(BibtexField.ID)) {
                    continue;
                }
                tiedot.println(arvot.getKey() + " : " + arvot.getValue());
            }
            tiedot.println("}");
        }
        tiedot.close();
    }
//    public static void main(String[] args) {
//        BibtexGen gen = new BibtexGen();
//        gen.generate();
//    }
}
