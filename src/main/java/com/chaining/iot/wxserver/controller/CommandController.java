package com.chaining.iot.wxserver.controller;

import com.chaining.iot.common.constans.RelayNum;
import com.chaining.iot.common.entity.Command;
import com.chaining.iot.common.entity.Connection;
import com.chaining.iot.nettyserver.handler.ConnectionHandler;
import com.chaining.iot.nettyserver.message.MessageOutFor135;
import com.chaining.iot.nettyserver.message.MessageOutFor136;
import com.chaining.iot.nettyserver.process.SendExecutorManager;
import com.chaining.iot.nettyserver.process.SendProcessTask;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 控制设备
 *
 * @program: wxstcgateway
 * @ClassName CommandController
 * @author: ning.chai@foxmail.com
 * @create: 2020-04-22 22:04
 * @Version 1.0
 **/
@RestController
@RequestMapping("/command")
public class CommandController {
    /**
     *
     * @return
     */
    @RequestMapping("/test")
    public String test() {
        return "控制成功";
    }


    /**
     * @param userId
     * @param unitId
     */
    @RequestMapping("/bindunit")
    public void bindUnit(String userId, String unitId) {
        //查看设备是否在线
        if (!ConnectionHandler.isOnline(unitId)) {
        }
        //查看是否已经绑定
    }

    /**
     * 查询温度
     *
     * @param unitId
     * @param action
     */
    @RequestMapping("/querytemp")
    public void queryTemp(String unitId, Integer action) {

    }

    /**
     * 查询湿度
     *
     * @param unitId
     * @param action
     */
    @RequestMapping("/queryhum")
    public void queryHum(String unitId, Integer action) {
        System.out.println(unitId+"--"+action);
    }

    /**
     * 查询光照度
     *
     * @param unitId
     * @param action
     */
    @RequestMapping("/querylus")
    public void queryLus(String unitId, Integer action) {

    }

    /**
     * 控制继电器
     */
    @PostMapping("/controlrelay")
    public ResponseEntity controlRelay(@RequestBody Command req) {
        System.out.println("lll"+req.getUnitId());
        //查看是否有此继电器
        if (!RelayNum.RelayNumEnum.codeOf(req.getRelayNum())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        //发送控制指令
        Connection connection = ConnectionHandler.getConnection(req.getUnitId());
        if (null == connection) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        MessageOutFor135 messageOutFor135 = new MessageOutFor135(RelayNum.RelayNumEnum.toEnumValue(req.getRelayNum()), RelayNum.ActionEnum.toEnumValue(req.getAction()));
        SendProcessTask task = new SendProcessTask(connection.getChannel(), messageOutFor135);
        SendExecutorManager.getInstance().execute(task);
        return ResponseEntity.ok("控制成功");
    }
    /**
     * 控制继电器
     */
    @PostMapping("/sensor")
    public ResponseEntity sensor(@RequestBody Command req) {
        System.out.println("lll"+req.getUnitId());
        //发送控制指令
        Connection connection = ConnectionHandler.getConnection(req.getUnitId());
        if (null == connection) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        MessageOutFor136 messageOutFor136 = new MessageOutFor136(req.getRelayNum());
        SendProcessTask task = new SendProcessTask(connection.getChannel(), messageOutFor136);
        SendExecutorManager.getInstance().execute(task);
        return ResponseEntity.ok("控制成功");
    }



}
