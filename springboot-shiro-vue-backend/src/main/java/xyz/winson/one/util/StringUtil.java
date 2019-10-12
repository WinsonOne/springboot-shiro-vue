package xyz.winson.one.util;

import java.util.UUID;

/**
 * @author : 温伟聪
 * @Description: TODO
 * @date Date : 2019年10月10日 17:14
 */
public class StringUtil {
    private StringUtil() {
    }

    /**
     * 生成32位的UUID
     * @return
     */
    public static String generateUUID() {
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        return uuid;
    }
}
