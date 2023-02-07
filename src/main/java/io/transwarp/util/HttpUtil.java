package io.transwarp.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

public class HttpUtil {

    static HttpClient httpClient = null;

    private static RequestConfig default_request_config = null;

    private static final int Connection_Timeout = 120000;

    private static final int Socket_Timeout = 40000;

    private static final int Request_Timeout = 40000;

    private static final int Max_Host_Connections = 200;

    private static final int Max_Total_Connections = 2000;

    private static final String Utf8 = "utf-8";

    public static String Success_Key = "success";

    static {
        default_request_config = RequestConfig.custom().setSocketTimeout(40000).setConnectTimeout(120000).setConnectionRequestTimeout(40000).build();
        PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager();
        connMgr.setMaxTotal(2000);
        connMgr.setDefaultMaxPerRoute(200);
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager((HttpClientConnectionManager)connMgr);
        httpClientBuilder.setDefaultRequestConfig(default_request_config);
        ConnectionConfig connConfig = ConnectionConfig.custom().setCharset(Charset.forName("utf-8")).build();
        httpClientBuilder.setDefaultConnectionConfig(connConfig);
        httpClient = (HttpClient)httpClientBuilder.build();
    }

    public static JSONObject post(String url, HashMap<String, String> param, String token) throws Exception {
        PostMethod postMethod = new PostMethod(url);
        postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        postMethod.setRequestHeader("Authorization", "Bearer "+token);
        ArrayList<org.apache.commons.httpclient.NameValuePair> data = new ArrayList<NameValuePair>();
        for(String key :param.keySet()) {
            org.apache.commons.httpclient.NameValuePair valuePair =
                    new org.apache.commons.httpclient.NameValuePair(key, param.get(key));
            data.add(valuePair);
        }
        postMethod.setRequestBody(data.toArray(new NameValuePair[data.size()]));
        org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
        int response = httpClient.executeMethod(postMethod);
        //String result1 = postMethod.getResponseBodyAsString() ;
        InputStream inputStream = postMethod.getResponseBodyAsStream();
        if(inputStream == null) {
            System.out.println("HTTP Post Error: inputStream is null");
            return null;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer result = new StringBuffer();
        String temp = "";
        while ((temp = br.readLine()) != null) {
            result.append(temp);
        }
        System.out.println("HTTP Post Code: " + response);
        inputStream.close();
        br.close();
        return JSONObject.parseObject(result.toString());
    }

    public static JSONObject post(String url, String accessToken, String apiKey, String param) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        BasicResponseHandler basicResponseHandler = new BasicResponseHandler();
        StringEntity requestEntity = new StringEntity(param, "utf-8");
        requestEntity.setContentEncoding("utf-8");
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-type", "application/json");
        httpPost.addHeader("accessToken", accessToken);
        httpPost.addHeader("apikey", apiKey);
        httpPost.setEntity((HttpEntity)requestEntity);
        String returnValue = "";
        try {
            returnValue = (String)httpClient.execute((HttpUriRequest)httpPost, (ResponseHandler)basicResponseHandler);
        } catch (Exception e) {
            System.out.println("HttpUtil post error, "+ url + param +  e.getMessage());
        } finally {
            httpClient.close();
        }
        return JSONObject.parseObject(returnValue);
    }

    public static JSONObject get(String url, JSONObject params) throws Exception {
        StringBuilder sb = new StringBuilder();
        if (params == null)
            params = new JSONObject();
        for (String key : params.keySet()) {
            String value = params.getString(key);
            if (value == null)
                continue;
            value = URLEncoder.encode(value, "utf8");
            sb.append(key + "=" + value).append("&");
        }
        if (!StringUtils.isEmpty(sb.toString()))
            url = url + (url.contains("?") ? "&" : "?") + sb;
        HttpRequestBase method = null;
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(5000).build();
        httpGet.setConfig(requestConfig);
        JSONObject jobj = new JSONObject();
        httpGet.addHeader("Content-type", "application/json; charset=utf-8");
        httpGet.setHeader("Accept", "*/*");
        HttpResponse response = httpClient.execute((HttpUriRequest)httpGet);
        String body = EntityUtils.toString(response.getEntity(), "utf-8");
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            jobj.put(Success_Key, Boolean.valueOf(true));
            jobj.put("data", body);
        } else {
            jobj.put("msg", body);
            jobj.put(Success_Key, Boolean.valueOf(false));
        }
        response.getEntity().getContent().close();
        return jobj;
    }
}

