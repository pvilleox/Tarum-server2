package com.tarum.io.content.xml;

import java.io.File;

public class XMLDocument {

    private File file;

    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }

    public XMLDocument(){
        this(null);
    }
    public XMLDocument (File file){
        this.file = file;
    }

}