/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gjyygle.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ReadXML {

    public static InputSource fileToInputSource(File file) {
        try {
            return new InputSource(new StringReader(new Scanner( file, "UTF-8" ).useDelimiter("\\A").next()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadXML.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static ArrayList<HashMap<String, String>> read(File file) {
        return read(fileToInputSource(file));
    }
    
   public static ArrayList<HashMap<String, String>> read(InputSource is) {

        Document doc;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(is);
            doc.getDocumentElement().normalize();
            return parseEntries(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static ArrayList<HashMap<String, String>> parseEntries(Document doc) {
        ArrayList<HashMap<String, String>> parsed = new ArrayList();
        NodeList entries = doc.getElementsByTagName("entry");
        for (int i = 0; i < entries.getLength(); i++) {
            Node entry = entries.item(i);
            HashMap<String, String> entryMap = new HashMap();
            parsed.add(entryMap);
            NodeList data = entry.getChildNodes();
            for (int j = 0; j < data.getLength(); j++) {
                Node n = data.item(j);
                String s = n.getNodeName();
                if (!s.equals("#text")) {
                    entryMap.put(s, n.getTextContent());
                }
            }
        }
        return parsed;
    }

    private static void printEntries(ArrayList<HashMap<String, String>> parsed) {
        for (int i = 0; i < parsed.size(); i++) {
            System.out.println("-----------entry " + i + " ------------");
            HashMap<String, String> entry = parsed.get(i);
            Iterator<Entry<String, String>> data = entry.entrySet().iterator();
            while (data.hasNext()) {
                Entry<String, String> e = data.next();
                System.out.println(e.getKey() + "=" + e.getValue());
            }
        }
    }

}
