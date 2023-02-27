package org.testing.functions;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NewDirectoryStreamFunction {

    public final static String ROOT_DIR = "C:\\Users\\taawipa1\\Documents";


    public NewDirectoryStreamFunction() {

    }
    public boolean writeAllFiles(String name) {

            DirectoryStream<Path> files = null;
            try {
                files = Files.newDirectoryStream(Paths.get(ROOT_DIR));
                for (Path path : files) {
                    if (path.getFileName().toString().equals(name)) {
                        return true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                if (files != null) {
                    try {
                        files.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return false;
        }
    }

