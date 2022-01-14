package com.supinfo.java.day2;

import java.io.*;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Exercise 2: Compress a ZIP file
 * <p>
 * To compress ("zip") a file, we use an instance of the ZipOutputStream class.
 * <p>
 * The ZipEntry class represents an entry in the compressed file.
 * Write a Java program which compresses a file "source.txt" to "file.zip"
 */
public class CompressZipFile {

    /**
     * To compress ("zip") a file.
     *
     * @param filePath location of file to compress.
     * @return location of compressed file, usually same location as original file (just replacing the extension).
     */
    public Optional<String> compressZipFile(final String filePath) throws IOException {
        Optional<String> compressedFileResult = Optional.empty();
        // prepare file object
        final File file = new File(filePath);
        if (file.exists()) {
            if (file.isFile()) {
                compressedFileResult = Optional.of(compressOneFile(file));
            } else {
                // compress a folder (no recursive approach first)
                compressedFileResult = Optional.of(compressOneFolder(file));
            }
        } // else, we can't compress a ghost
        return compressedFileResult;
    }

    private String compressOneFolder(File folder) throws IOException {
        // build information about target location
        // extract original file name without its extension
        final String fileName = folder.getName();
        final File compressedFile = new File("build", fileName + ".zip");

        try (final OutputStream outputStream = new FileOutputStream(compressedFile);
             final ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
            for (File file : folder.listFiles()) {
                try (final FileInputStream fileInputStream = new FileInputStream(file)) {
                    // create a zip entry
                    final ZipEntry zipEntry = new ZipEntry(file.getName());
                    zipOutputStream.putNextEntry(zipEntry);
                    // write content of original file
                    final byte[] buffer = new byte[1024];
                    int length = fileInputStream.read(buffer);
                    while (length != -1) {
                        zipOutputStream.write(buffer, 0, length);

                        // read next buffer content
                        length = fileInputStream.read(buffer);
                    }
                }
            }
        }

        // We have created our zip file
        return compressedFile.getPath();
    }

    private String compressOneFile(File file) throws IOException {
        // build information about target location
        // extract original file name without its extension
        final String fileName = file.getName();
        final int extensionIndex = fileName.lastIndexOf(".");
        final File compressedFile;
        if (extensionIndex != -1) {
            compressedFile = new File("build", fileName.substring(0, extensionIndex) + ".zip");
        } else {
            // no current extension
            compressedFile = new File("build", fileName + ".zip");
        }

        try (
                final FileInputStream fileInputStream = new FileInputStream(file);
                final OutputStream outputStream = new FileOutputStream(compressedFile);
                final ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
            // create a zip entry
            final ZipEntry zipEntry = new ZipEntry(fileName);
            zipOutputStream.putNextEntry(zipEntry);
            // write content of original file
            final byte[] buffer = new byte[1024];
            int length = fileInputStream.read(buffer);
            while (length != -1) {
                zipOutputStream.write(buffer, 0, length);

                // read next buffer content
                length = fileInputStream.read(buffer);
            }
        }
        // We have created our zip file
        return compressedFile.getPath();
    }
}
