package com.dzg.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {
    @RequestMapping("/hello")
    @ResponseBody
    @Test
    public String hello() {
        log.info("hello");
        return "hello,permission";
        // return JsonData.success("hello, permission");
    }
}
