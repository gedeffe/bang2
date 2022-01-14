package com.supinfo.java.day2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

class CompressZipFileTests {

    @Test
    void testCompressZipFile_thenOneFile() throws IOException {
        // Given
        CompressZipFile compressZipFile = new CompressZipFile();

        // When
        Optional<String> compressedFile = compressZipFile.compressZipFile("src/main/java/com/supinfo/java/day1/ArraySorter.java");

        // Then
        Assertions.assertNotNull(compressedFile);
        Assertions.assertTrue(compressedFile.isPresent());
        Assertions.assertEquals("src/main/java/com/supinfo/java/day1/ArraySorter.zip", compressedFile.get());
    }

    @Test
    void testCompressZipFile_thenOneFolder() throws IOException {
        // Given
        CompressZipFile compressZipFile = new CompressZipFile();

        // When
        Optional<String> compressedFile = compressZipFile.compressZipFile("src/main/java/com/supinfo/java/day1");

        // Then
        Assertions.assertNotNull(compressedFile);
        Assertions.assertTrue(compressedFile.isPresent());
        Assertions.assertEquals("src/main/java/com/supinfo/java/day1.zip", compressedFile.get());
    }
}
