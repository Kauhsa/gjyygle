/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gjyygle.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXML {

    Document doc;
    ArrayList<HashMap<String, String>> parsed;

    public ReadXML(String file) {

        try {

            File fXmlFile = new File(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        parseEntries();
    }

    private void parseEntries() {
        parsed = new ArrayList();
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
    }
    
    public ArrayList<HashMap<String, String>> getEntries() {
        return parsed;
    }

    public void printEntries() {
        for (int i = 0; i < parsed.size(); i++) {
            System.out.println("-----------entry " + i + " ------------");
            HashMap<String, String> entry = parsed.get(i);
            Iterator<Entry<String,String>> data = entry.entrySet().iterator();
            while(data.hasNext()) {
                Entry<String,String> e = data.next();
                System.out.println(e.getKey() + "=" + e.getValue());
            }
        }
    }

    public static void main(String[] args) {
        ReadXML t = new ReadXML("testXml.xml");
        t.printEntries();
        WriteXML.write("xmlout.xml", t.getEntries());
    }
}
