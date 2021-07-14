package io.gitee.hek97.web.utils;

import java.io.UnsupportedEncodingException;


public class DownLoadUtils {

    public static String getFileName(String agent, String filename) throws UnsupportedEncodingException {
        // 针对IE或者以IE为内核的浏览器：
        if (agent.contains("MSIE") || agent.contains("Trident")) {
            filename = java.net.URLEncoder.encode(filename, "UTF-8");
        } else {
            // 非IE浏览器的处理：
            filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
        }
        return filename;
    }
}
