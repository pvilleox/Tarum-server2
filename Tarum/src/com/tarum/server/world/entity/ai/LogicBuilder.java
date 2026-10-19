package com.tarum.server.world.entity.ai;

import com.tarum.io.Configuration;
import com.tarum.io.content.ContentContainer;
import com.tarum.io.content.ContentManager;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Used to construct and prepare the logic for AI player controllers;
 *
 * Logic information is eventually formatted and imported/exported
 * through the LogicLoader class.
 */
public class LogicBuilder {

    /**
     * Primarily responsible for the storage of
     * essential information used to build the logic
     * for our controller.
     */
    private ContentManager contentManager;

    public ContentManager getContentManager() {
        return contentManager;
    }
    public void setContentManager(ContentManager contentManager) {
        this.contentManager = contentManager;
    }

    public LogicBuilder (){
        this(new ContentManager());
    }
    public LogicBuilder (ContentManager contentManager){
        this.contentManager = contentManager;
    }

    /**
     * Create a pipeline between two or more content containers,
     * we typically use these pipelines to feed information
     * to different containers within a database using
     * db queries.
     *
     * Afterward we format and prepare the information to later
     * be used by a LogicLoader.
     *
     * We use a ContentManager so that we can quickly return to where
     * we left off with handling the content pipeline and make
     * adjustments as needed.
     * @return
     */
    public ContentManager beginContentPipelineCreation (){
        ContentManager contentManager1 = new ContentManager();
        Configuration config = contentManager1.getConfig();

        // Prepare the map we're going to use to store data
        // we're pulling from the database
        contentManager1.setContentMap(Collections.unmodifiableMap(new HashMap<Long,ContentContainer[]>()));
        return contentManager1;
    }

}

/**
 * Utilized to assist with the implementation
 * of pattern planning in logic,
 *
 * Generally we create a type of ContentManager
 * specifically for each pattern and pull information
 * using queries to feed the necessary information
 * to our algorithm piece by piece.
 */
class PatternPlanner{

    /**
     * The content manager responsible for the
     * source content to be processed, sorted
     * and formatted before being stored within
     * our primary content manager.
     *
     */
    private ContentManager sourceContentManager;

    /**
     * Responsible for storing all relative information
     * to our pattern planning algorithm,
     * we store this information in a number of arrays
     * mapped to a unique identifier.
     */
    private ContentManager contentManager;

    public PatternPlanner (ContentManager sourceContentManager){
        this.sourceContentManager = sourceContentManager;

        init();
    }

    private void init(){
        this.contentManager = new ContentManager();
        contentManager.setContentMap(Collections.unmodifiableMap(new HashMap<Long, HashMap<Long, ContentContainer[]>>()));
    }

}

/**
 * We often check information being processed
 * for types of patterns already implemented
 * to, typically, perform an optimization
 * check and adjustment if possible.
 *
 * Otherwise it is generally used by the
 * LogicLoader to process information
 * and cross-check patterns implemented.
 */
class PatternRecognitionInterpreter{

}