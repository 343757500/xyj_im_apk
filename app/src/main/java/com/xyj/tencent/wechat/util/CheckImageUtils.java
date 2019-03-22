package com.xyj.tencent.wechat.util;

public class CheckImageUtils {

    public static boolean isImage(String path){

        return path.toLowerCase().endsWith(".jpg") || path.toLowerCase().endsWith(".png") || path.toLowerCase().endsWith(".jpeg")
                || path.toLowerCase().endsWith(".bmp");
    }
}
