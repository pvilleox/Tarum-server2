package com.tarum.server;

import com.tarum.io.Configuration;
import com.tarum.io.content.ContentContainer;
import com.tarum.io.content.ContentManager;
import com.tarum.io.content.db.DBSession;
import com.tarum.server.world.entity.ai.AIManager;
import com.tarum.server.world.entity.ai.LogicBuilder;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;

public class Debugger {

    public Debugger(){
    }

    public void start(){

        debugControllerCreation();

    }

    private void debugControllerCreation(){

        log ("Debugging controller creation...");

        AIManager aiManager = new AIManager();

            /**
             * Used to form and create the logic for
             * our AI agents / player controllers.
             */
            LogicBuilder builder = new LogicBuilder();

            // Prepare a content pipeline to our database
            // specify the tables we're trying to enable
            // continuous search queries for.
            // We do this by creating an individual ContentManager
            // and creating a custom session for our Database,
            // this session will use a Stream to pass information
            // using queries as necessary.

            /**
             * Create our primary content pipeline to our database,
             * this will be responsible for providing essential
             * information to our logic builder's functionality
             * such as the formatting and processing of data
             * to be used later by our LogicLoader.
            */
            ContentManager dbContentPipeline1 = builder.beginContentPipelineCreation();
            dbContentPipeline1.setContentMap(Collections.unmodifiableMap(new HashMap<Long, ContentContainer[]>()));
//            dbContentPipeline1.setContentMap(Collections.unmodifiableMap(new HashMap<Long, ByteBuffer[]>()));

            /**
            * Set the file format type for our content container files
            */
            dbContentPipeline1.getConfig().set("default_content_file_format_type", "xml");

            /**
             * Pull the CombatEvent data from the database
             * TODO:
             */
            ContentManager combatFunctionalityHandler = builder.beginContentPipelineCreation();

            /**
            * Responsible for handling the information
            * relative to our diplomacy functionality ->
            * Such as the relationship value(s) used to
            * determine diplomatic functionality logic.
            */
            ContentManager diplomacyFunctionalityHandler = builder.beginContentPipelineCreation();

            /**
             * Create a new session to connect to the database
             * so that we may perform query requests and track
             * monitor the results.
             */
            DBSession dbSession = new DBSession();

    }


    public void log (String message){
        System.out.println("Debugger: " + message);
    }

    static void main (String[] args){
        Debugger debugger = new Debugger();

        debugger.start();
    }

}
