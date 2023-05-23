package com.oppla.server.global.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElasticBeanstalkController {

    @GetMapping("/")
    public HttpStatus elasticBeanstalkOK(){
        return HttpStatus.OK;
    }
}
