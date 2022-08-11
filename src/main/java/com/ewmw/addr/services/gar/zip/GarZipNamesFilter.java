package com.ewmw.addr.services.gar.zip;

import com.ewmw.addr.services.gar.files.GarFileType;
import org.springframework.stereotype.Service;

@Service
public class GarZipNamesFilter {

    public boolean weWant(String archiveFilePath) {
        String[] pathParts = archiveFilePath.split("/");

        int size = pathParts.length;

        if (size < 2)
            return false;

        return weWantFile(pathParts[size - 1]);
    }

    protected boolean weWantFile(String fileName) {
        return GarFileType.PREFIXES
                .stream()
                .anyMatch(
                        (prefix) -> fileName.startsWith(prefix) && fileName.endsWith(".XML")
                );
    }
}
