package com.samyu.demos.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import android.util.Xml;

public class ReadXML {

    public static String FILE_PATH = "/storage/sdcard0/com.samyu.demos/config.xml";

    public static void getDataFromXML(TreeMap<String, Item> publicTreeMap, ArrayList<String> privateName) {
        try {
            FileInputStream is = new FileInputStream(FILE_PATH);

            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(is, "UTF-8");

            int eventType = parser.getEventType();
            String name = null;
            ArrayList<Item> items = new ArrayList<Item>();
            while(eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                case XmlPullParser.START_TAG:
                    String nameSpace = parser.getNamespace();
                    if (parser.getName().equals("public")) {
                        String key = parser.getAttributeValue(nameSpace, "name");
                        String d = parser.getAttributeValue(nameSpace, "description");
                        String t = parser.getAttributeValue(nameSpace, "type");
                        eventType = parser.next();
                        String v = parser.getText();
                        Item item = new Item(key, d, t, v);
                        publicTreeMap.put(key, item);
                    } else if (parser.getName().equals("private")) {
                        name = parser.getAttributeValue(nameSpace, "name");
                        privateName.add(name);
                    }
                    break;
                }
                eventType = parser.next();
            }

            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getDataFromXML(String name, ArrayList<Item> items) {
        try {
            FileInputStream is = new FileInputStream(FILE_PATH);

            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(is, "UTF-8");

            int eventType = parser.getEventType();
            String n = "";
            while(eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                case XmlPullParser.START_TAG:
                    String nameSpace = parser.getNamespace();
                    if (parser.getName().equals("private")) {
                        n = parser.getAttributeValue(nameSpace, "name");
                    } else if (name.equals(n) && parser.getName().equals("item")) {
                        String key = parser.getAttributeValue(nameSpace, "name");
                        String d = parser.getAttributeValue(nameSpace, "description");
                        String t = parser.getAttributeValue(nameSpace, "type");
                        eventType = parser.next();
                        String v = parser.getText();
                        Item i = new Item(key, d, t, v);
                        items.add(i);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (parser.getName().equals("private")) {
                        n = "";
                    }
                    break;
                }
                eventType = parser.next();
            }

            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
