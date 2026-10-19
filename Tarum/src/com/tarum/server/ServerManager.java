package com.tarum.server;

import com.tarum.io.content.xml.XMLDocument;
import com.tarum.io.content.xml.XMLParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ServerManager {

    public static String DEFAULT_ENVIRONMENT_DIRECTORY = System.getenv("APPDATA")+"/.Tarum/";

    public ServerManager(){
    }

    public void start(){
        debugXMLParsing();

    }

    private void debugXMLParsing(){
        File file = new File (System.getenv("APPDATA")+"/.Tarum/xml/world_generator_config.xml");
        XMLParser parser = new XMLParser(file);
        XMLDocument doc = null;

        try {
            doc = parser.parse();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    static void main (String[] args){
        ServerManager serverManager = new ServerManager();

        serverManager.start();
    }

}