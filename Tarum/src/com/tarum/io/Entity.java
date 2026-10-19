package com.tarum.io;

import java.io.Serializable;

public abstract class Entity implements Serializable {

    private long uid;
    private int id;
    private String name;

    public long getUid() {
        return uid;
    }
    public void setUid(long uid) {
        this.uid = uid;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Entity (){
        this(-1);
    }
    public Entity (String name) {
        this(-1, name);
    }
    public Entity (int id){
        this(id, null);
    }
    public Entity (int id, String name){
        this.id = id;
        this.name = name;
        this.uid = IOUtils.GenerateUID();
    }

}
