package com.chaining.iot.webserver.controller;

import com.chaining.iot.webserver.mapper.MenuMapper;
import com.chaining.iot.webserver.service.impl.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单控制层
 *
 * @program: wxstcgateway
 * @ClassName MenuController
 * @author: ning.chai@foxmail.com
 * @create: 2020-04-15 21:10
 * @Version 1.0
 **/
@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuServiceImpl menuService;

    private MenuMapper menuMapper;
    @GetMapping("/test")
    public String test(){
        return "hello";
    }
}
