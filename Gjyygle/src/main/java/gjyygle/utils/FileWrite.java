/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gjyygle.utils;

import gjyygle.bibtex.BibtexField;
import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.EnumMap;

/**
 *
 * @author mkctammi
 */
public class FileWrite {

    public static void stringToFile(String string, File file) {
        try {
            java.io.FileWriter fstream = new java.io.FileWriter(file);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(string);
            out.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
