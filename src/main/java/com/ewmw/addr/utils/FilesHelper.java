package com.ewmw.addr.utils;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class FilesHelper {
    public static String getFileContent(String path) throws IOException {
        return IOUtils.toString(new FileReader(path));
    }

    public static File getFirstFileByPrefix(String dirPath, String prefix) throws Exception {
        File file = new File(dirPath);

        if (! file.isDirectory())
            throw new Exception("dirPath must be directory path");

        Optional<File> firstFile = Arrays
                .stream(Objects.requireNonNull(file.listFiles()))
                .filter(f -> f.getName().startsWith(prefix))
                .findFirst();

        return firstFile.get();

    }
}
