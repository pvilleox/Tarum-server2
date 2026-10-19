package com.tarum.io;

import com.tarum.io.content.xml.XMLParser;
import com.tarum.server.ServerManager;

import java.io.File;
import java.util.HashMap;

public class Configuration {

    private File file;

    private HashMap<Object, Object> settings = new HashMap<>();

    public static String DEFAULT_DIRECTORY = ServerManager.DEFAULT_ENVIRONMENT_DIRECTORY+"config/";
    public static String DEFAULT_FILE_NAME = "config.cfg";

    public Configuration(){
        this(null);
    }
    public Configuration(File configFile){
        if (configFile != null){
            this.file = configFile;
        } else {
            this.file = getDefaultConfigFile();
        }
    }

    public boolean isEmpty(){
        return settings.isEmpty();
    }
    public boolean contains (Object key){
        if (settings.isEmpty()) return false;
        return settings.containsKey(key);
    }
    public Object get (Object key){
        if (settings.isEmpty() || !settings.containsKey(key)) return null;
        return settings.get(key);
    }
    public void set (Object key, Object value){
        if (key == null || value == null) return;
        settings.put(key, value);
    }

    public static File getDefaultConfigFile(){
        File result = new File (DEFAULT_DIRECTORY, DEFAULT_FILE_NAME);
        String dir = DEFAULT_DIRECTORY;
        File[] files = new File(dir).listFiles();
        String defaultFileName = DEFAULT_FILE_NAME;

        if (files != null){
            defaultFileName = "config_" + files.length + ".cfg";
        }

        result = new File (dir, defaultFileName);
        return result;
    }

    /**
     * TODO: IMPLEMENT XML PARSING FOR CONFIGURATION FILES
     * @param configFile
     * @return
     */
    public boolean load (File configFile){
        return false;
    }
    public boolean export (File configFile){
        return false;
    }

}