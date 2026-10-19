package com.tarum;

import com.tarum.io.Logger;
import com.tarum.io.content.ContentContainer;
import com.tarum.io.content.ContentContainerImpl;
import com.tarum.io.content.ContentManager;

import java.io.File;

public class Debugger {

    private Logger logger;

    public Debugger(){
    }

    public void start(){
        this.logger = new Logger(System.getenv("APPDATA")+"./Tarum/logs/debugger/");
        logger.start();

        debugIOFunctionality();
    }

    private void debugIOFunctionality(){
        ContentManager contentManager = new ContentManager();

        String dir = contentManager.getDirectory();

//        ContentContainer container =  contentManager.createContainer("Test_Container");
//        long containerUid = container.getUid();
//        File containerFile = container.getFile();

//        log ("Created ContentContainer (Uid: " + containerUid + ", file_path:" + containerFile.getAbsolutePath() + ")");

    }

    public void log (String message){

    }

    static void main (String[] args){
        Debugger debugger = new Debugger();
        debugger.start();
    }

}
