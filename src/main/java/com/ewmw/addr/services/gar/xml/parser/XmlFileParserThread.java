package com.ewmw.addr.services.gar.xml.parser;

import com.ewmw.addr.services.gar.interfaces.XmlProcessor;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

@AllArgsConstructor
public class XmlFileParserThread implements Runnable {
    private String filePath;
    private String nodeName;
    private XmlProcessor processor;

    @SneakyThrows
    @Override
    public void run() {
        long time = new Date().getTime();

        InputStream in = new FileInputStream(filePath);
        long nodesProcessed = 0L;
        long nodesFailed = 0L;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = factory.createXMLEventReader(in);

        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();

            if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
                StartElement node = event.asStartElement();

                String xmlNodeName = node.getName().toString();

                if (nodeName.equals(xmlNodeName)) {
                    try {
                        processor.processNode(node);
//                        System.out.println("works");
                        nodesProcessed++;
                    } catch (Throwable e) {
//                        e.printStackTrace();
                        nodesFailed++;
                    }
                }
            }
        }

        processor.finish();
    }
}
