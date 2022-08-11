package com.ewmw.addr.utils;

import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class IOHelper {
    public static String getFileContent(String path) throws IOException {
        return IOUtils.toString(new FileReader(path));
    }
}
