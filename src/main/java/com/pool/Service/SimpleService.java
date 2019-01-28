package com.pool.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class SimpleService {

    private static int objectCount = 0;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public SimpleService() {
        logger.info("Creating new object : {}", ++objectCount);
    }

    public void doWork() throws InterruptedException {
        Thread.sleep(10000);
    }
}
