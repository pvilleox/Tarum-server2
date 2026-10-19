package com.tarum.io.content.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XMLParser {

    private File file;
    private DocumentBuilder documentBuilder;

    private Document document;

    private XMLDocument xmlDocument;

    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }
    public DocumentBuilder getDocumentBuilder() {
        return documentBuilder;
    }
    public void setDocumentBuilder(DocumentBuilder documentBuilder) {
        this.documentBuilder = documentBuilder;
    }
    public Document getDocument() {
        return document;
    }
    public void setDocument(Document document) {
        this.document = document;
    }
    public XMLDocument getXmlDocument() {
        return xmlDocument;
    }
    public void setXmlDocument(XMLDocument xmlDocument) {
        this.xmlDocument = xmlDocument;
    }

    public XMLParser(){
        this(null);
    }
    public XMLParser(File file){
        this.file = file;
    }

    public XMLDocument parse() throws ParserConfigurationException, IOException, SAXException {
        return parse (getFile());
    }
    public XMLDocument parse (File file) throws ParserConfigurationException, IOException, SAXException {
        if (file != null && file.exists()){
            setFile(file);
        }

        XMLDocument xmlDocument = new XMLDocument();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        this.documentBuilder = factory.newDocumentBuilder();

        this.document = documentBuilder.parse(getFile());

        Element element = document.getDocumentElement();

        NodeList nodeList = element.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++){
            Node node = nodeList.item(i);

            NodeList childNodes = node.getChildNodes();

            for (int j = 0; j < childNodes.getLength(); j++){
                Node subNode = childNodes.item(j);


            }

            System.out.println("Node: " + node.getNodeName());

        }

        return xmlDocument;
    }

    public boolean export (){
        return export (getXmlDocument(), getFile());
    }
    public boolean export (XMLDocument xmlDocument, File targetFile){
        return false;
    }

}
