package com.tarum.io.content.db;

import com.tarum.io.Configuration;
import com.tarum.io.content.ContentContainer;
import com.tarum.io.content.ContentManager;

import java.util.Collections;
import java.util.HashMap;

/**
 * Represents an individual session to a
 * database, typically used to control
 * the streaming of particular data
 * from a database.
 */
public class DBSession {

    /**
     * The configuration settings for the
     * session.
     */
    private Configuration configuration;

    private Database database;

    /**
     * Responsible for storing the relative
     * query request and result information.
     */
    private ContentManager contentManager;

    public DBSession(){
        this(null);
    }
    public DBSession(Database db){
        this.database = db;

        init();
    }

    private void init(){
        this.contentManager = new ContentManager();

        /**
         * Key = Operation (Unique identifier)
         * Value = Container storing individual operation information (ContentContainer type)
         */
        contentManager.setContentMap(Collections.unmodifiableMap(new HashMap<Long,ContentContainer>()));
    }

}
