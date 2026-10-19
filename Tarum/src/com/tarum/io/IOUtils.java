package com.tarum.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IOUtils {

    public static long GenerateUID(){
        UUID uuid = UUID.randomUUID();
        long uid = UUID.randomUUID().getLeastSignificantBits() & Long.MAX_VALUE;
        return uid;
    }
    public static long GenerateUID(byte[] data){
        UUID uuid = UUID.nameUUIDFromBytes(data);
        long uid = (uuid.getLeastSignificantBits() * uuid.getMostSignificantBits());
        return uid;
    }

    // get a file from the resources folder
    // works everywhere, IDEA, unit test and JAR file.
    public static InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = IOUtils.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

    public static byte[] SerializeObjectToByteArray (Object object){
        return null;
    }

    public static boolean WriteBytesToFile (byte[] data, File targetFile){
        FileOutputStream fos = null;
        ByteArrayOutputStream bos = null;

        try{

            fos = new FileOutputStream(targetFile);
            bos = new ByteArrayOutputStream();

            bos.writeBytes(data);

            bos.writeTo(fos);

        }catch (IOException e){
            e.printStackTrace();
        }
        return true;
    }

    public static List<String> ReadAllLines (File targetFile) throws IOException {
        List<String> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(targetFile));
        String ln = null;

        while ((ln = reader.readLine()) != null){
            result.add(ln);
        }

        return null;
    }

    public static boolean IsFileDuplicate (File file1, File file2) throws IOException, NoSuchAlgorithmException {
        return isDuplicate(Paths.get(file1.toURI()), Paths.get(file2.toURI()));
    }
    public static boolean isDuplicate(Path file1, Path file2) throws IOException, NoSuchAlgorithmException {
        // Step 1: Check if it's the exact same file path
        if (Files.isSameFile(file1, file2)) {
            return true;
        }

        // Step 2: Compare file sizes first (fast optimization)
        if (Files.size(file1) != Files.size(file2)) {
            return false;
        }

        // Step 3: Compare content hashes
        String hash1 = getFileChecksum(file1);
        String hash2 = getFileChecksum(file2);

        return hash1.equals(hash2);
    }
    private static String getFileChecksum(Path filePath) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        try (InputStream is = Files.newInputStream(filePath)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
        }

        // Convert the byte array into a hex string
        StringBuilder sb = new StringBuilder();
        for (byte b : digest.digest()) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static List<File> RetrieveFilesRecursively (String directory){
        if (directory == null) return new ArrayList<>();
        return RetrieveFilesRecursively(new File (directory));
    }
    public static List<File> RetrieveFilesRecursively (File targetDir){
        List<File> result = new ArrayList<>();

        if (targetDir == null || !targetDir.exists()) return result;

        File[] files = targetDir.listFiles();
        if (files == null) return result;

        for (File file : files){
            if (file.isDirectory()){
                List<File> subDir = RetrieveFilesRecursively(file);

                result.addAll(subDir);
            } else {
                result.add(file);
            }
        }
        return result;
    }

    public static boolean CopyFileTo (File targetFile, String targetDest){
        FileInputStream fis = null;

        byte[] data = new byte[(int) targetFile.length()];

        try{
            fis = new FileInputStream(targetFile);
            int read = 0;

            fis.read(data, 0, (int) targetFile.length());

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e1){
                    e1.printStackTrace();
                }
            }

            if (data != null){
                return WriteBytesToFile(data, new File (targetDest, targetFile.getName()));
            }
        }

        return true;
    }

}
