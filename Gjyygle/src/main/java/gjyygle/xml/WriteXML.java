/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gjyygle.xml;

import gjyygle.bibtex.BibtexField;
import gjyygle.utils.FileWrite;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author mkctammi
 */
public class WriteXML {

    public static Document createDocument(ArrayList<EnumMap<BibtexField, String>> entries) {
        try {
            DocumentBuilderFactory documentBuilderFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder =
                    documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element rootElement = document.createElement("root");
            document.appendChild(rootElement);

            for (Iterator<EnumMap<BibtexField, String>> it = entries.iterator(); it.hasNext();) {
                EnumMap<BibtexField, String> entry = it.next();
                Element entryElement = document.createElement("entry");
                Iterator<Entry<BibtexField, String>> elements = entry.entrySet().iterator();
                while (elements.hasNext()) {
                    Entry<BibtexField, String> element = elements.next();
                    String e = element.getKey().getName();
                    String d = element.getValue();
                    if (d != null) {
                        Element em = document.createElement(e);
                        em.appendChild(document.createTextNode(d));
                        entryElement.appendChild(em);
                    }
                }
                rootElement.appendChild(entryElement);
            }
            return document;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String getString(ArrayList<EnumMap<BibtexField, String>> entries) {
        try {
            Document document = createDocument(entries);
            TransformerFactory transformerFactory =
                    TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StringWriter writer = new StringWriter();
            transformer.transform(source, new StreamResult(writer));
            String output = writer.getBuffer().toString().replaceAll("\n|\r", "");
            return output;
        } catch (TransformerException ex) {
            Logger.getLogger(WriteXML.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static void write(File file, ArrayList<EnumMap<BibtexField, String>> entries) {
        String data = getString(entries);
        FileWrite.stringToFile(data, file);
    }
}
