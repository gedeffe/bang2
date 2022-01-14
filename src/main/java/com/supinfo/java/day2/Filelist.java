package com.supinfo.java.day2;

import java.io.File;

/**
 * List of Filename of a directory
 * <p>
 * <p>
 * To display the contents of a directory, we  build an instance of the File class from the name of a directory
 * (“.” For the current directory),
 * we then call its list method which returns an array of strings containing filenames and subdirectories.
 * Write a java program displaying the contents of the directory whose name is passed as a parameter on the command line.
 */
public class Filelist {

    public static void main(String[] args) {
        Filelist filelist = new Filelist();
        filelist.displayActiveDirectory("src/main/java/com/supinfo/java/day1");
    }

    public void displayActiveDirectory(String pathname) {
        File directory = new File(pathname);

        for (File file : directory.listFiles()) {
            System.out.println(file.getAbsolutePath());
        }
    }
}
