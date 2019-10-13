package com.bcg.tools;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;
import sun.net.www.protocol.https.DefaultHostnameVerifier;

import org.apache.http.conn.util.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
@Slf4j
public class HttpClientHelper {
    //设置 连接超时、 请求超时 、 读取超时  毫秒   默认：60秒
    private int requestTimeout = 60000,connectTimeout = 60000,socketTimeout = 60000;
    //请求参数配置
    private RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout)
            .setConnectionRequestTimeout(requestTimeout).build();

    private static HttpClientHelper instance = null;
    //无参构造函数
    public HttpClientHelper() {
    }
    //带有超时时间的有参构造函数
    public HttpClientHelper(int requestTimeout,int connectTimeout,int socketTimeout) {
        this.connectTimeout = connectTimeout;
        this.requestTimeout = requestTimeout;
        this.socketTimeout = socketTimeout;
    }
    //单例模式，生成默认的HttpClient
    public static HttpClientHelper getInstance() {
        if (instance == null) {
            instance = new HttpClientHelper();
        }
        return instance;
    }
    //单例模式，生成自定义的HttpClient
    public static HttpClientHelper getInstance(int requestTimeout,int connectTimeout,int socketTimeout) {
        if (instance == null) {
            instance = new HttpClientHelper(requestTimeout,connectTimeout,socketTimeout);
        }
        return instance;
    }

    /**
     * 发送 post请求
     * 当请求内容为空时调用此函数
     * @param httpUrl
     *            地址
     */
    public String sendHttpPost(String httpUrl) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        return sendHttpPost(httpPost);
    }

    /**
     * 发送 post请求
     * @param httpUrl 地址
     * @param params  参数(格式:key1=value1&key2=value2)
     */
    public String sendHttpPost(String httpUrl, String params) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        try {
            // 设置参数
            StringEntity stringEntity = new StringEntity(params, "UTF-8");
            stringEntity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(stringEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendHttpPost(httpPost);
    }

    /**
     * 发送 post请求
     * @param httpUrl  地址
     * @param maps 参数
     */
    public String sendHttpPost(String httpUrl, Map<String, String> maps) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        // 创建参数队列
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        for (String key : maps.keySet()) {
            nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendHttpPost(httpPost);
    }

    /**
     * 发送 post请求（带文件）
     * @param httpUrl 地址
     * @param maps 参数
     * @param fileLists  附件
     */
    public String sendHttpPost(String httpUrl, Map<String, String> maps, List<File> fileLists) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
        if (maps != null) {
            for (String key : maps.keySet()) {
                meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));
            }
        }
        if (fileLists != null) {
            for (File file : fileLists) {
                FileBody fileBody = new FileBody(file);
                meBuilder.addPart("files", fileBody);
            }
        }
        HttpEntity reqEntity = meBuilder.build();
        httpPost.setEntity(reqEntity);
        return sendHttpPost(httpPost);
    }

    /**
     * 发送Post请求--->发送请求并接收响应
     * @param httpPost
     * @return
     */
    private String sendHttpPost(HttpPost httpPost) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpPost.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }

    /**
     * httpJson请求
     * @param url
     * @param json
     * @return
     */
    public String sendJsonHttpPost(String url, String json) {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        String responseInfo = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
            ContentType contentType = ContentType.create("application/json", CharsetUtils.get("UTF-8"));
            httpPost.setEntity(new StringEntity(json, contentType));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                if (null != entity) {
                    responseInfo = EntityUtils.toString(entity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseInfo;
    }

    /**
     * 发送 get请求
     * 如：http://www.baidu.com？name=张三&password=111111
     * @param httpUrl
     */
    public String sendHttpGet(String httpUrl) {
        HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
        return sendHttpGet(httpGet);
    }

    /**
     * 发送 get请求
     * 如：http://www.baidu.com？name=张三&password=111111
     * @param httpUrl
     */
    public String sendHttpGet(String httpUrl,Map<String, String> maps) {

        HttpGet httpGet = null;

       /*
        * 由于GET请求的参数都是拼装在URL地址后方，所以我们要构建一个URL，带参数
        */
        try{
            URIBuilder uriBuilder = new URIBuilder(httpUrl);

            // 创建参数队列
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            for (String key : maps.keySet()) {
                nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
            }
            uriBuilder.setParameters(nameValuePairs);

            httpGet = new HttpGet(uriBuilder.build());// 创建get请求

        } catch (ParseException e) {
            log.info("文件解析错误");
            e.printStackTrace();
        } catch (URISyntaxException e) {
            log.info("URL解析异常");
            e.printStackTrace();
        }finally {

        }

        return sendHttpGet(httpGet);
    }
    /**
     * 发送Get请求
     *
     * @param httpGet
     * @return
     */
    private String sendHttpGet(HttpGet httpGet) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpGet.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }

    /**
     * 发送Get请求Https
     *
     * @param httpGet
     * @return
     */
    /*
    private String sendHttpsGet(HttpGet httpGet) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            PublicSuffixMatcher publicSuffixMatcher = PublicSuffixMatcherLoader
                    .load(new URL(httpGet.getURI().toString()));
            DefaultHostnameVerifier hostnameVerifier = new DefaultHostnameVerifier(publicSuffixMatcher);
            httpClient = HttpClients.custom().setSSLHostnameVerifier(hostnameVerifier).build();
            httpGet.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }
    */
    /*
    public static void main(String[] args) {
        HttpClientHelper h = new HttpClientHelper();
        Map map = new HashMap();
        map.put("messageid", "test00201612210002");
        map.put("clientid", "test00");
        map.put("index_id", "买方会员");
        map.put("threshold", 0.9);
        List<String> data = new ArrayList<String>();
        data.add("wo xiang cha xun jin tian de yao pin jia ge lie biao");
        map.put("data", data);

        String json = JSON.toJSONString(map);


        //String reply = h.sendJsonHttpPost("http://127.0.0.1:8080/checkUser", json);
        //String reply = h.sendHttpPost("http://127.0.0.1:8080/", map);
        //String reply = h.sendHttpPost("http://127.0.0.1:8080/checkUser", "name=baicg&password=111111&addr=陕西西安");
        System.out.println("reply->"+reply);
    }
    */
}