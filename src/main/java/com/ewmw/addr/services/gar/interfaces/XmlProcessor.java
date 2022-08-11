package com.ewmw.addr.services.gar.interfaces;

import javax.xml.stream.events.StartElement;

public interface XmlProcessor {
    public <T extends Throwable> void processNode(StartElement startElement) throws T;

    public void finish();
}
