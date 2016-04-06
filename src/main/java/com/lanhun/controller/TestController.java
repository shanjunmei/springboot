package com.lanhun.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lanhun.controller.model.Bean;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("hello")
    public String hello() {
	return "hello";
    }

    @ResponseBody
    @RequestMapping(value="bean")
    public Bean bean(@RequestBody Bean bean) {
	bean.setId("bean-id");
	return bean;
    }

}
