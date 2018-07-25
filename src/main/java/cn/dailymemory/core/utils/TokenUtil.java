package cn.dailymemory.core.utils;

import java.util.UUID;

/**
 * Created by dm on 2017/7/15.
 */
public class TokenUtil {

    public static String getToken(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
