package com.vivo.soft.excel.springexceldemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张贵东
 * @Company TODO
 * @date 2018-11-17.
 * @Time 15:19
 * @Description TODO
 * @Version 2.0.0
 */
@RestController
@RequestMapping("hello")
public class HelloWorldController {
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String hello(@RequestParam String name){
        return "hello,"+name;
    }
}
