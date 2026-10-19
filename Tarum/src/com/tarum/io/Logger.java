package com.tarum.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Logger {

    private String directory = DEFAULT_DIRECTORY;
    private String fileName = "Log";

    private String messagePrefix = "Logger: ";

    private Configuration config;

    private File file;
    private FileWriter fileWriter;

    private LocalDate localDate;

    public static String DEFAULT_DIRECTORY = System.getenv("APPDATA")+"/.Cynet/logs/";

    public String getDirectory() {
        return directory;
    }
    public void setDirectory(String directory) {
        this.directory = directory;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getMessagePrefix() {
        return messagePrefix;
    }
    public void setMessagePrefix(String messagePrefix) {
        this.messagePrefix = messagePrefix;
    }
    public Configuration getConfig() {
        return config;
    }
    public void setConfig(Configuration config) {
        this.config = config;
    }
    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }
    public FileWriter getFileWriter() {
        return fileWriter;
    }
    public void setFileWriter(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public Logger(){
        this(DEFAULT_DIRECTORY);
    }
    public Logger(String directory){
        this.directory = directory == null ? DEFAULT_DIRECTORY : directory;
    }

    private void initialize(){
        if (directory == null) this.directory = DEFAULT_DIRECTORY;
        File dir = new File (getDirectory());

        if (!dir.exists()) dir.mkdirs();

        if (file == null){
            this.file = generateFile();
            setDirectory(file.getParent());
        }

        try{
            this.fileWriter = new FileWriter(getFile());

        } catch (IOException e1){
            e1.printStackTrace();
        }
    }

    public LocalDate getLocalDate(){
        return this.localDate = LocalDate.now();
    }

    public File generateFile(){
        File dir = new File (getDirectory() == null ? DEFAULT_DIRECTORY : getDirectory());
        if (dir == null){
            dir = new File (DEFAULT_DIRECTORY);
        }

        File[] files = dir.listFiles();
        if (files == null) {
            return this.file = new File(dir.getAbsolutePath(), (getFileName() == null ? this.fileName = "Log" : getFileName()) +".txt");
        }

        int fileCount = files.length;

        /**
         * TODO :
         */
        this.file = new File (dir.getAbsolutePath(), (getFileName() == null ? this.fileName = "Log" : getFileName()) + "_" + fileCount + ".txt");
        this.fileName = file.getName();
        return this.file;
    }

    public void println (String message){
        String prefix = "[" + getLocalDate().toString() + "]: ";
        System.out.println (prefix + message);
    }
    public void writeln (String message){
        String prefix = "[" + getLocalDate().toString() + "_"+System.currentTimeMillis() + "]: ";

        try {
            fileWriter.write(prefix + message + "\n");
            flush();

            System.out.println(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void flush() {
        if (fileWriter == null){
            println("Unable to flush content to specified file: (FileWriter == null || Logger not initialized)! ");
            return;
        }

        try {
            this.fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start(){
        initialize();

        writeln("Successfully started logger!");
    }
    public void stop(){
        if (getFileWriter() != null){
            try {
                flush();
                this.fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
