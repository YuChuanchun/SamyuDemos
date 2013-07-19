package com.samyu.demos.xml;

import java.io.FileOutputStream;
import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.util.Log;

public class WriteXML {

    public static String FILE_PATH = "/storage/sdcard0/com.samyu.demos/config.xml";

    public static void writePublicXML(String name, String value) {
        try {
            FileInputStream is = new FileInputStream(FILE_PATH);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(is);

            NodeList nodes = doc.getElementsByTagName("public");
            for (int i=0;i<nodes.getLength();i++) {
                Node n = nodes.item(i).getAttributes().getNamedItem("name");
                String key = n.getTextContent();
                if (key.equals(name)) {
                    nodes.item(i).setTextContent(value);
                }
            }
            FileOutputStream out = new FileOutputStream(FILE_PATH);
            TransformerFactory fact = TransformerFactory.newInstance();
            Transformer trans = fact.newTransformer();
            trans.transform(new DOMSource(doc), new StreamResult(out));

            is.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writePrivateXML(String privateName, String itemName, String value) {
        try {
            FileInputStream is = new FileInputStream(FILE_PATH);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(is);

            Element rootElement = doc.getDocumentElement();
            NodeList items = rootElement.getElementsByTagName("private");
            for (int i = 0; i < items.getLength(); i++) {
                Node item = items.item(i);
                NodeList properties = item.getChildNodes();
                if (item.getAttributes().getNamedItem("name").getTextContent().equals(privateName)) {
                    for (int j = 0; j < properties.getLength(); j++) {
                        Node property = properties.item(j);
                        String nodeName = property.getNodeName();
                        if (nodeName.equals("item") &&
                                property.getAttributes().getNamedItem("name").getTextContent().equals(itemName)) {
                            property.setTextContent(value);
                        }
                    }
                }
            }
            FileOutputStream out = new FileOutputStream(FILE_PATH);
            TransformerFactory fact = TransformerFactory.newInstance();
            Transformer trans = fact.newTransformer();
            trans.transform(new DOMSource(doc), new StreamResult(out));

            is.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
