package com.chaining.iot.nettyserver.process;

import com.chaining.iot.common.constans.EnumMessageType;

import java.util.HashMap;
import java.util.Map;

/**
 * 处理业务工厂类
 *
 * @program: wxstcgateway
 * @ClassName ProcessServicesFactory
 * @author: ning.chai@foxmail.com
 * @create: 2020-04-10 23:23
 * @Version 1.0
 **/
public class ProcessServicesFactory {
    private static ProcessServicesFactory factory = new ProcessServicesFactory();
    private static Map<EnumMessageType,IProcessServices> processMap = new HashMap<>();
    static{
        processMap.put(EnumMessageType.COMMAND6, new DefaultProcessServices());
        processMap.put(EnumMessageType.COMMAND8, new DefaultProcessServices());
    }
    public static ProcessServicesFactory getInstance() {
        return factory;
    }

    public IProcessServices getProcess(String unitId, EnumMessageType type) {
        IProcessServices process = processMap.get(type);
        if (process == null) {
            // 通用报文处理程序
            process = processMap.get(EnumMessageType.GENERAL);
        }
        return process;
    }

}
