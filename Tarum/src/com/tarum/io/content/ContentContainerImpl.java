package com.tarum.io.content;

import java.io.File;
import java.io.Serializable;

public class ContentContainerImpl extends ContentContainer implements Serializable {

    public ContentContainerImpl(ContentManager contentManager){
        this(contentManager, null);
    }
    public ContentContainerImpl (ContentManager contentManager, File containerFile){
        super(contentManager, containerFile);
    }


}
