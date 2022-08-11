package com.ewmw.addr.services.gar.xml.parser;

import lombok.Getter;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class XmlParserThreadsManager {

    public static int THREADS_AMOUNT = 8;

    @Getter
    protected ThreadPoolExecutor executor;

    @Getter
    protected ArrayList<Future> futures;

    public XmlParserThreadsManager() {
        executor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(THREADS_AMOUNT);

        futures = new ArrayList<>();
    }

    public void run(Runnable runnable) {
        executor.submit(runnable);
    }
}
