package com.tarum.io.content;

import com.tarum.io.Configuration;
import com.tarum.io.IOUtils;
import com.tarum.io.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ContentManager {

    private String directory;
    private Configuration config;

    private Logger logger;

    // TODO: Implement specification of generic type for Key and Value
//    private Class keyType = Object.class;
//    private Class valueType = Object.class;
    //private Map<?, ?> contentMap;
    private Map<Object,Object> contentMap = new HashMap<>();

    public String getDirectory() {
        return directory;
    }
    public void setDirectory(String directory) {
        this.directory = directory;
    }
    public Configuration getConfig() {
        return config;
    }
    public void setConfig(Configuration config) {
        this.config = config;
    }
    public Logger getLogger() {
        return logger;
    }
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public ContentManager (){
    }
    public ContentManager (String directory){
        this.directory = directory;

        init();
    }

    private void init(){
        if (this.directory == null) this.directory = System.getenv("APPDATA")+"/.Tarum/.tmp/content/";

        /**
         * Prepare the work environment
         */
        File dir = new File (directory);
        if (!dir.exists()) dir.mkdirs();
    }

    public void setContentMap (Map<Object,Object> contentMap){
        this.contentMap = contentMap;
    }

    public Object get (Object key){
        if (contentMap == null) return null;
        return contentMap.get(key);
    }
    public Object set (Object key, Object value){
        return put (key, value);
    }
    public Object put (Object key, Object value){
        if (contentMap == null){
            contentMap = new HashMap<>();
        }

        putEntry(contentMap, key, value);
        return null;
    }

    // Define a generic type parameter <K, V>
    public <K, V> void putEntry(Map<K, V> map, K key, V value) {
        map.put(key, value);
    }

    public void clear(){
        if (contentMap != null){
            contentMap.clear();
        }
    }
    public void unload(){
        clear();
    }

    public void log (String message){
        if (logger == null) return;

        logger.writeln("ContentManager: " + message);
    }

}
