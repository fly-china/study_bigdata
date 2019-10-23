package com.lpf.interview.fastjson;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * ClassName:HttpsUtil
 * Description:
 * Author:李朋飞
 * Date: 2017/11/10 11:03
 */
public class HttpClientUtil {


    private static final int SO_TIME_OUT = 15000;
    private static final int CONNECTION_TIME_OUT = 5000;
    public static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";
    public static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded;charset=UTF-8";


    public static String doPost(String url, String paramStr, String contentType) {
        String result = "";
        org.apache.http.client.HttpClient client = null;
        HttpPost httpPost = null;
        try {
            if (StringUtils.isBlank(contentType)) {
                throw new RuntimeException("HttpsUtil.doPost|Content-Type can not be null");
            }
            client = HttpClients.custom().build();
            httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", contentType);
            httpPost.addHeader("SSOToken", "D96024A8AEC2E946F8D54D602E59079E#10023");
            StringEntity postEntity = new StringEntity(paramStr, "UTF-8");
            httpPost.setEntity(postEntity);
            httpPost.setConfig(RequestConfig.custom().setSocketTimeout(SO_TIME_OUT).setConnectTimeout(CONNECTION_TIME_OUT).build());

            long start_http = System.currentTimeMillis();
            HttpResponse response = client.execute(httpPost);
            System.out.println("请求" + url + "耗时：" + (System.currentTimeMillis() - start_http));
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");

        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
        return result;
    }

}