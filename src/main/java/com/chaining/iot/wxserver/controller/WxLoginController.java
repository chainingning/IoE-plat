package com.chaining.iot.wxserver.controller;

import com.chaining.iot.framework.web.domain.AjaxResult;
import com.chaining.iot.wxserver.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 小程序登录
 *
 * @program: wxstcgateway
 * @ClassName WxLoginController
 * @author: ning.chai@foxmail.com
 * @create: 2020-04-12 21:08
 * @Version 1.0
 **/
@RestController
@RequestMapping("/wxuser")
public class WxLoginController {

    /**
     *
     * @param userId 队伍编号
     *      * @param password 设备id
     * @param session
     * @param request
     * @return
     */
    @PostMapping("/wxlogin")
    public String wxLogin(String userId, String password, HttpSession session, HttpServletRequest request){
        //TODO 数据库取数据
        User user = new User();
        user.setUserName(userId);
        session.setAttribute("unitId", userId);
        session.setAttribute("user", user);

        return null;
    }


}
