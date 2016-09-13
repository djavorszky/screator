package com.liferay.support.screens.util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XmlUtil {

    public static Document getNormalizedDocumentFromFile(File file)
            throws SAXException, IOException, ParserConfigurationException {

        Document doc = getDocumentFromFile(file);
        doc.getDocumentElement().normalize();

        return doc;
    }

    public static Document getDocumentFromFile(File file)
            throws SAXException, IOException, ParserConfigurationException {

        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
    }


    public static boolean isNodeAnElement(Node node) {
        return node.getNodeType() == Node.ELEMENT_NODE;
    }

    public static NodeList getChildNodes(Document document) {
        return document.getDocumentElement().getChildNodes();
    }


}