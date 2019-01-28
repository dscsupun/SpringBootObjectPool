package com.pool.controller;

import com.pool.Service.SimpleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.target.CommonsPool2TargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pool")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CommonsPool2TargetSource simpleObjectPool;

    @RequestMapping(value = "/simple", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> call() throws Exception {

        logger.info("Simple Object pool, Max size : {}, Max idle : {}, Min idle : {}, Max wait : {}, Active count : {}",
                simpleObjectPool.getMaxSize(), simpleObjectPool.getMaxIdle(), simpleObjectPool.getMinIdle(),
                simpleObjectPool.getMaxWait(), simpleObjectPool.getActiveCount());

        SimpleService simpleService = (SimpleService) simpleObjectPool.getTarget();

        logger.info("Acquired object hashcode : {}", simpleService.hashCode());

        simpleService.doWork();

        simpleObjectPool.releaseTarget(simpleService);

        logger.info("Object released");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}