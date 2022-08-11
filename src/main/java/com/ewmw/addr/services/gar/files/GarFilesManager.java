package com.ewmw.addr.services.gar.files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Service
public class GarFilesManager {

    @Autowired
    private GarFilesFilter filesFilter;

    private final String basePath = "gar_ext/";

    private File[] dirs;

    public GarFilesManager() {
        File baseDir = new File(basePath);

        dirs = baseDir.listFiles();
    }

    public List<File> getAsHouseFiles() {
        return getFilteredFiles((File file) -> filesFilter.isHouse(file.getName()));
    }

    public List<File> getAsAdmHierarchyFiles() {
        return getFilteredFiles((File file) -> filesFilter.isAdmHierarchy(file.getName()));
    }

    public List<File> getAsMunHierarchyFiles() {
        return getFilteredFiles((File file) -> filesFilter.isMunHierarchy(file.getName()));
    }

    public List<File> getAsHouseParamsFiles() {
        return getFilteredFiles((File file) -> filesFilter.isHouseParams(file.getName()));
    }

    public List<File> getAsObjAddressesFiles() {
        List<File> files = new ArrayList<>();

        for (File dir : dirs) {
            File[] dirFiles = dir.listFiles();


            Stream.of(dirFiles)
                    .filter(file -> filesFilter.isAddrObj(file.getName()))
                    .forEach(files::add);
        }

        return files;
    }

    private List<File> getFilteredFiles(Predicate<File> predicate) {
        List<File> files = new ArrayList<>();

        for (File dir : dirs) {
            File[] dirFiles = dir.listFiles();


            Stream.of(dirFiles)
                    .filter(predicate)
                    .forEach(files::add);
        }

        return files;
    }
}
