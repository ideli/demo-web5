package com.meilele.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;



public class HttpUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static String postForm(String url, Map<String, String> params) {
        URL u = null;
        HttpURLConnection con = null;
        // 构建请求参数
        StringBuffer sb = new StringBuffer();
        String ss = "";
        if (params != null) {
            for (Entry<String, String> e : params.entrySet()) {
                sb.append(e.getKey());
                sb.append("=");
                sb.append(e.getValue());
                sb.append("&");
            }
            ss = sb.substring(0, sb.length() - 1);
        }
        System.out.println("send_url:" + url);
        System.out.println("send_data:" + ss.toString());
        // 尝试发送请求
        try {
            u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            //// POST 只能为大写，严格限制，post会不识别
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded;charset=UTF-8");
            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
            osw.write(ss);
            osw.flush();
            osw.close();
        } catch (Exception e) {
            logger.error("", e);
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }

        // 读取返回内容
        StringBuffer buffer = new StringBuffer();
        try {
            //一定要有返回值，否则无法把请求发送给server端。
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),
                "UTF-8"));
            String temp;
            while ((temp = br.readLine()) != null) {
                buffer.append(temp);
                buffer.append("\n");
            }
        } catch (Exception e) {
            logger.error("http发送：失败  {},{}", e, e.getMessage());
            e.printStackTrace();
        }
        return buffer.toString();
    }

    /**
    * 发送HTTP请求
    * @param url
    * @param propsMap 发送的参数
     * @return 
    */
    public static String httpSend(String url, Map<String, String> propsMap,
                                  Map<String, String> cookies) {
        HttpClient httpClient = new HttpClient();
        UTF8PostMethod postMethod = new UTF8PostMethod(url);//POST请求

        //参数设置
        NameValuePair[] postData = null;
        if (propsMap != null) {
            Set<String> keySet = propsMap.keySet();
            postData = new NameValuePair[keySet.size()];
            int index = 0;
            for (String key : keySet) {
                postData[index++] = new NameValuePair(key, propsMap.get(key).toString());
            }
        }
        String cookie = "";
        if (cookies != null) {
            for (Map.Entry<String, String> entry : cookies.entrySet()) {
                cookie += entry.getKey() + "=" + entry.getValue() + "; ";
            }
        }
        postMethod.addParameters(postData);
        postMethod.setRequestHeader("Cookie", cookie);

        try {
            httpClient.executeMethod(postMethod);//发送请求
            return postMethod.getResponseBodyAsString();
        } catch (HttpException e) {
            logger.error("http发送：失败  {},{}", e, e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("http发送：失败  {},{}", e, e.getMessage());
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();//关闭连接
        }
        return null;
    }

    public static class GBKPostMethod extends PostMethod {
        public GBKPostMethod(String url) {
            super(url);
        }

        @Override
        public String getRequestCharSet() {
            //return super.getRequestCharSet();
            return "GBK";
        }
    }

    public static class UTF8PostMethod extends PostMethod {
        public UTF8PostMethod(String url) {
            super(url);
        }

        @Override
        public String getRequestCharSet() {
            //return super.getRequestCharSet();
            return "UTF-8";
        }
    }

    public static String postWithJSON(String url, String params) {
        HttpClient httpClient = new HttpClient();
        PostMethod method = new PostMethod(url);
        try {
            if (params != null && !params.trim().equals("")) {
                RequestEntity requestEntity = new StringRequestEntity(params, "application/json",
                    "UTF-8");
                method.setRequestEntity(requestEntity);
            }
            method.releaseConnection();
            httpClient.executeMethod(method);
            String responses = method.getResponseBodyAsString();
            return responses;
        } catch (HttpException e) {
            logger.error("http发送：失败  {},{}", e, e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("http发送：失败  {},{}", e, e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static String postWithJSON(String url, Map<String, String> params) {
        HttpClient httpClient = new HttpClient();
        PostMethod method = new PostMethod(url);
        try {
            String paramStr = JSON.toJSONString(params);
            if (paramStr != null && !paramStr.trim().equals("")) {
                RequestEntity requestEntity = new StringRequestEntity(paramStr, "application/json",
                    "UTF-8");
                method.setRequestEntity(requestEntity);
            }
            method.releaseConnection();
            httpClient.executeMethod(method);
            String responses = method.getResponseBodyAsString();
            return responses;
        } catch (HttpException e) {
            logger.error("http发送：失败  {},{}", e, e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("http发送：失败  {},{}", e, e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            /*            Map<String, List<String>> map = connection.getHeaderFields();
                        // 遍历所有的响应头字段
                        for (String key : map.keySet()) {
                            System.out.println(key + "--->" + map.get(key));
                        }*/
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.error("http发送：失败  {},{}", e, e.getMessage());
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }
}