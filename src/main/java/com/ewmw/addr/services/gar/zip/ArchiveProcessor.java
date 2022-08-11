package com.ewmw.addr.services.gar.zip;

import com.ewmw.addr.interfaces.Processor;
import com.ewmw.addr.services.gar.xml.parser.XmlFilesProcessor;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class ArchiveProcessor extends XmlFilesProcessor implements Processor {

    @Autowired
    GarZipNamesFilter garZipNamesFilter;

    @Override
    public void process() throws IOException, ZipException {
        String zipFileName = "gar_xml.zip";

        executor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(8);

        futures = new ArrayList<>();

        ZipFile zipFile = new ZipFile(zipFileName);

        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFileName));
        ZipEntry zipEntry = zis.getNextEntry();

        while (zipEntry != null) {
            String name = zipEntry.getName();

            handleEntry(name, zipFile);

            zipEntry = zis.getNextEntry();
        }

        zis.closeEntry();
        zis.close();

        waitForFutures();
    }

    private void handleEntry(String zipEntryName, ZipFile zipFile) throws IOException {
        if (! garZipNamesFilter.weWant(zipEntryName)) {
            return;
        }

        System.out.println("Processing file: " + zipEntryName);

        Future<?> submit = executor.submit(() -> {
            try {
                zipFile.extractFile(zipEntryName, "gar_ext/");
            } catch (ZipException e) {
                e.printStackTrace();
            }
        });

        futures.add(submit);
    }
}
