package com.whoops.dataset;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static File folder = new File(""); // Data directory path
    static String temp = "";

    public static void main(String[] args) {
        System.out.println("Reading files under the folder " + folder.getAbsolutePath());
        processFilesInDirectory(folder);
    }

    public static void processFilesInDirectory(final File folder) {
        //Get the file reference
        Path path = Paths.get("./result_dataset.txt");

        try {
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write("put your headers here\n");


            for (final File fileEntry : folder.listFiles()) {
                if (fileEntry.isDirectory()) {
                    processFilesInDirectory(fileEntry);
                } else {
                    // put file name regex here
                    if (fileEntry.isFile() && fileEntry.getName().matches("^[A-Z]{1}(\\d{2})_[A-Z]{2}(\\d{2})_[A-Z]{1}(\\d{2}).txt")) {

                        System.out.println(fileEntry.getName());
                        temp = fileEntry.getName();

                        Stream<String> stream = Files.lines(fileEntry.toPath());

                        for(String line: stream.collect(Collectors.toList())) {
                            line = line.substring(0, line.length() - 1);
                            line = line.replace(" ", "");
                            String values[] = line.split(",");
                            // Read and process your values

                            try {
                                writer.write("write your values\n");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }
            writer.flush();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
