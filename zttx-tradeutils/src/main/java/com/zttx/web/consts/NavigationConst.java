package com.zttx.web.consts;

/**
 * <p>File：NavigationConst.java </p>
 * <p>Title: NavigationConst </p>
 * <p>Description: NavigationConst </p>
 * <p>Copyright: Copyright (c) 2014 08/26/2015 08:41</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public enum NavigationConst {

    NAVIGATION_LADY("lady", "女装"),
    NAVIGATION_MAN("man", "男装"),
    NAVIGATION_TEXTILES("textiles", "家纺"),
    NAVIGATION_CHILDREN("children", "童装"),
    NAVIGATION_UNDERWEAR("underwear", "内衣"),
    NAVIGATION_SHOES_HATS("shoes&hats", "鞋帽"),
    NAVIGATION_BAGS("bags", "箱包");

    public String code;

    public String message;

    NavigationConst(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessage(Integer code) {
        String result = null;
        for (ClientConst c : ClientConst.values()) {
            if (c.code.equals(code)) {
                result = c.message;
            }
        }
        return result;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
