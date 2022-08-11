package com.ewmw.addr.services.gar.xml.parser;

import com.ewmw.addr.services.gar.interfaces.XmlProcessor;
import lombok.Getter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Function;

public abstract class XmlFilesProcessor {
    public static int THREADS_AMOUNT = 8;

    @Getter
    protected ThreadPoolExecutor executor;

    @Getter
    protected ArrayList<Future> futures;

    public XmlFilesProcessor() {
        executor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(THREADS_AMOUNT);

        futures = new ArrayList<>();
    }

    protected void parseXml(String fileName, String nodeName, Function<StartElement, Void> future) throws FileNotFoundException, XMLStreamException {
        Long time = new Date().getTime();

        InputStream in = new FileInputStream(fileName);
        Long nodesProcessed = 0L;
        Long nodesFailed = 0L;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = factory.createXMLEventReader(in);

        boolean found = false;
        while (!found && eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();

            if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
                StartElement node = event.asStartElement();

                if (nodeName.equals(node.getName().toString())) {
                    try {
                        future.apply(node);
                        nodesProcessed++;
                    } catch (Exception e) {
                        e.printStackTrace();
                        nodesFailed++;
                    }
                }
            }
        }


        System.out.println(
                fileName + " XML processed, nodes processed: "
                        + nodesProcessed + ", nodes failed: "
                        + nodesFailed + ". Time");
        System.out.println("Time: " + (new Date().getTime() - time));
    }

    protected void parseXml(String fileName, String nodeName, XmlProcessor processor) throws FileNotFoundException, XMLStreamException {
        long time = new Date().getTime();

        InputStream in = new FileInputStream(fileName);
        long nodesProcessed = 0L;
        long nodesFailed = 0L;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = factory.createXMLEventReader(in);

        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();

            if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
                StartElement node = event.asStartElement();

                if (nodeName.equals(node.getName().toString())) {
                    try {
                        processor.processNode(node);
                        nodesProcessed++;
                    } catch (Exception e) {
                        e.printStackTrace();
                        nodesFailed++;
                    }
                }
            }
        }

        processor.finish();

//        System.out.println(
//                fileName + " XML processed, nodes processed: "
//                        + nodesProcessed + ", nodes failed: "
//                        + nodesFailed);
//        System.out.println("Time: " + (new Date().getTime() - time));
    }

    protected void waitForFutures() {
        futures.stream().forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
