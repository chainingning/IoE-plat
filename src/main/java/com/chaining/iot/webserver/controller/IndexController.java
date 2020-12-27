package com.chaining.iot.webserver.controller;

import com.chaining.iot.webserver.domain.Menu;
import com.chaining.iot.webserver.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 首页 业务处理
 *
 * @program: wxstcgateway
 * @ClassName IndexController
 * @author: ning.chai@foxmail.com
 * @create: 2020-04-15 22:37
 * @Version 1.0
 **/
@Controller
public class IndexController {

    @Autowired
    private IMenuService menuService;

//    @Autowired
//    private IConfigService configService;

    @GetMapping("/index")
    public String index(ModelMap modelMap){
        //查询所有的菜单
        List<Menu> menus = menuService.selectMenusByUser();
        modelMap.put("menus", menus);
//        modelMap.put("sideTheme", configService.selectConfigByKey("sys.index.sideTheme"));
//        modelMap.put("skinName", configService.selectConfigByKey("sys.index.skinName"));
        return "index";
    }
}
