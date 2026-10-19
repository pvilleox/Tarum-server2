package com.tarum.server.world;

import com.tarum.io.Entity;

import java.io.Serializable;

public class World extends Entity implements Serializable {

    private transient WorldManager worldManager;

    public WorldManager getWorldManager() {
        return worldManager;
    }
    public void setWorldManager(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

    public World (WorldManager worldManager){
        this(worldManager, -1);
    }
    public World (WorldManager worldManager, long worldUid){
        this.worldManager = worldManager;
        if (worldUid > -1) {
            this.setUid(worldUid);
        }
    }


}
