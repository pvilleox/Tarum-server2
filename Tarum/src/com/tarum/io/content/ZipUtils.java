package com.tarum.io.content;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtils {

    public static void unzip(String zipFilePath, String destDirectory) throws IOException {
        Path targetDir = Paths.get(destDirectory).toAbsolutePath().normalize();

        // Create the destination directory if it doesn't exist
        if (Files.notExists(targetDir)) {
            Files.createDirectories(targetDir);
        }

        // Open the ZIP file using a try-with-resources statement to auto-close streams
        try (InputStream fis = Files.newInputStream(Paths.get(zipFilePath));
             ZipInputStream zis = new ZipInputStream(fis)) {

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                // Resolve the output path and normalize it to remove potential relative paths
                Path entryPath = targetDir.resolve(entry.getName()).normalize();

                // GUARD AGAINST ZIP SLIP: Verify the entry path is inside the target directory
                if (!entryPath.startsWith(targetDir)) {
                    throw new IOException("Malicious ZIP entry detected outside target directory: " + entry.getName());
                }

                if (entry.isDirectory()) {
                    // Create nested sub-directory structures
                    Files.createDirectories(entryPath);
                } else {
                    // Create parental directories if they do not exist for the file entry
                    if (Files.notExists(entryPath.getParent())) {
                        Files.createDirectories(entryPath.getParent());
                    }

                    // Efficiently copy the entry's content directly to the file destination
                    Files.copy(zis, entryPath, StandardCopyOption.REPLACE_EXISTING);
                }

                zis.closeEntry();
            }
        }
    }

}
