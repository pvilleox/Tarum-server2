package com.tarum.io.content;

import com.tarum.io.Entity;
import com.tarum.io.IOUtils;
import java.io.File;
import java.io.Serializable;
import java.nio.ByteBuffer;

public abstract class ContentContainer extends Entity implements Serializable {

    private transient ContentManager contentManager;
    private String name;

    private String filePath;

    private byte[] content;

    private String[] tags;

    public static String DEFAULT_NAME = "content_container", DEFAULT_FILE_EXTENSION = ".dat";

    public ContentManager getContentManager() {
        return contentManager;
    }
    public void setContentManager(ContentManager contentManager) {
        this.contentManager = contentManager;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public File getFile(){
        if (filePath == null) return null;
        return new File (filePath);
    }

    public byte[] getContent() {
        return content;
    }
    public void setContent(byte[] content) {
        this.content = content;
    }

    public String[] getTags() {
        return tags;
    }
    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public ContentContainer (){
        this (new ContentManager());
    }
    public ContentContainer (ContentManager contentManager){
        this(contentManager, DEFAULT_NAME);
    }
    public ContentContainer (ContentManager contentManager, String name){
        this(contentManager, name, -1);
    }
    public ContentContainer (ContentManager contentManager, String name, int id){
        super(name);
        this.contentManager = contentManager;
        this.name = name;
        this.filePath = contentManager.getDirectory()+name+DEFAULT_FILE_EXTENSION;

        init();
    }

    public ContentContainer (ContentManager contentManager, File containerFile){
        this(contentManager, containerFile, -1);
    }
    public ContentContainer (ContentManager contentManager, File containerFile, int id){
        super(id);
        this.contentManager = contentManager;
        this.filePath = containerFile.getAbsolutePath();
        this.name = containerFile.getName();

        init();
    }

    private void init(){
        File file = new File (filePath);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public void export(){
        export (null);
    }
    public void export (String targetDirectory){
        File targetFile = null;

        if (targetDirectory != null){
            String dir = new File (filePath).getParent();
            String fileName = new File (filePath).getName();
            targetFile = new File (dir, fileName);
        }

        if (targetFile == null){
            targetFile = new File(filePath);
        }

        IOUtils.WriteBytesToFile(content, targetFile);

    }

}