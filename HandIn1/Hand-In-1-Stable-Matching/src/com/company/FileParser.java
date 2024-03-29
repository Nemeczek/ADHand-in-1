package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by GROUP C on 02-09-2015.
 */
class FileParser {

    public static String[] ReadFile(String path) throws IOException {
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        String fileString = new String(data, "UTF-8");
        return fileString.split("\n");
    }
}
