package com.lixiaoxuan.config;

import java.io.IOException;
import java.util.Properties;

/**
 * @author lixiaoxuan
 * @description: 读取配置文件
 * @date 2021/5/27 13:50
 */
public class PropertyMgr {

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key) {
        if (properties == null) {
            return null;
        }
        return properties.get(key);
    }

}
