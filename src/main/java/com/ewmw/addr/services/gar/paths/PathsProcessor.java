package com.ewmw.addr.services.gar.paths;

import com.ewmw.addr.interfaces.Processor;
import com.ewmw.addr.models.gar.GarPath;
import com.ewmw.addr.models.reposotories.GarPathRepository;
import com.ewmw.addr.services.gar.files.GarFilesFilter;
import com.ewmw.addr.services.gar.files.GarFilesManager;
import com.ewmw.addr.services.gar.xml.XmlStreamNode;
import com.ewmw.addr.services.gar.xml.parser.XmlFilesProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

@Service
public class PathsProcessor extends XmlFilesProcessor implements Processor {

    @Autowired
    GarFilesFilter filesFilter;

    @Autowired
    GarFilesManager filesManager;

    @Autowired
    GarPathRepository garPathRepository;

    @Override
    public void process() {
        processFiles(filesManager.getAsAdmHierarchyFiles());
    }

    private void processFiles(List<File> fileList) {
        fileList
                .forEach(file -> executor.submit(() -> {
                    try {
                        parseXml(file.getAbsolutePath(), "ITEM", this::handleXml);
                        System.out.println("Completed #" + executor.getCompletedTaskCount());
                    } catch (FileNotFoundException | XMLStreamException e) {
                        e.printStackTrace();
                    }
                }));
    }

    private Void handleXml(StartElement startElement) {
        XmlStreamNode node = new XmlStreamNode(startElement);

        if (! node.isActive())
            return null;

        String path = node.extractAttribute("PATH");
        String isActive = node.extractAttribute("ISACTIVE");

        List<String> parts = Arrays.asList(path.split("\\."));
        Long lastPart = Long.parseLong(parts.get(parts.size() - 1));

        GarPath garPath = new GarPath();
        garPath.setPath(path);
        garPath.setIsActive(isActive);
        garPath.setObjetId(lastPart);

        garPathRepository.save(garPath);

        return null;
    }
}
