package com.bcg.tools;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import net.minidev.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Auther: baicg
 * @Date: 2018/12/12 21:48
 * @Description:基础工具类
 */
public class BusiUtil {

    /**
    public static void main(String[] args){

        Map<String, Object> payload = new HashMap<String,Object>();

        payload.put("userId","zhangsan");
        payload.put("userPass","111111");

        String str = BusiUtil.createToken(payload);

        String vString = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJ6aGFuZ3NhbiIsInVzZXJQYXNzIjoiMTExMTExIn0.Sef5eJwqNUIFnPTL6nH0VUoK29YYpQ1wIbxtpFVbQpE";

    }
    */
    /**
     * 功能：HttpServletRequest转Map
     * @param request
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Map getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    //Token生成32-byte的密钥
    /**
     * 秘钥
     */
    private static final byte[] SECRET="3ddD0d2276917dFsdfSDFSDsdfs345WRESDG04467dfwqdf".getBytes();
    //校验成功
    private static String TOKEN_SUCCESS = "0";
    //校验失败
    private static String TOKEN_TIME_OUT = "1";
    //校验失败
    private static String TOKEN_FAIL = "2";

    private static final JWSHeader header=new JWSHeader(JWSAlgorithm.HS256, JOSEObjectType.JWT, null, null, null, null, null, null, null, null, null, null, null);

    /**
     * 功能：生成token-初次登录时需要生成
     */
    public static String createToken(Map<String, Object> payload) {

        String tokenString=null;
        // new JWS object
        JWSObject jwsObject = new JWSObject(header, new Payload(new JSONObject(payload)));
        try {
            // 签名
            jwsObject.sign(new MACSigner(SECRET));
            tokenString=jwsObject.serialize();
        } catch (JOSEException e) {
            //签名失败
            e.printStackTrace();
        }
        return tokenString;
    }

    /**
     * 功能：TOKEN 验证
     * @param token
     * @return
     */
    public static Map<String, Object> validToken(String token) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            //生成JWSObject实体类
            JWSObject jwsObject = JWSObject.parse(token);
            //获取载荷
            Payload payload = jwsObject.getPayload();
            //生成JWSVerifier 实体类
            JWSVerifier verifier = new MACVerifier(SECRET);
            //校验
            if (jwsObject.verify(verifier)) {
                //将载荷转成JSONObject
                JSONObject jsonOBj = payload.toJSONObject();
                // token校验成功
                resultMap.put("state", TOKEN_SUCCESS);
                // 校验Token是否过期
                if (jsonOBj.containsKey("ext")) {
                    long extTime = Long.valueOf(jsonOBj.get("ext").toString());
                    long curTime = new Date().getTime();
                    // 如果过期则报错
                    if (curTime > extTime) {
                        resultMap.clear();
                        resultMap.put("state", TOKEN_TIME_OUT);
                    }
                }
                //饭回结果
                resultMap.put("data", jsonOBj);

            } else {
                // 校验失败
                resultMap.put("state", TOKEN_FAIL);
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.clear();
            resultMap.put("state", TOKEN_FAIL);
        }
        return resultMap;
    }

    /**
     * 判断字符串是否为空
     * PS:
     * 为空的条件：
     * 1. String对象为空
     * 2. 没有任何字符的字符串
     *
     * @param str 需要判断的字符串
     * @return 为空(true), 非空(false)
     */
    public static boolean isNull(String str) {
        return null == str || "".equals(str);
    }

    /**
     * 判断字符串是否为空
     * PS:
     * 为空的条件：
     * 1. String对象为空
     * 2. 没有任何字符的字符串
     *
     * @param str       需要判断的字符串
     * @param isTrimmed 判断前是否去掉字符串前后的空格：是(true), 否(false)
     * @return 为空(true), 非空(false)
     */
    public static boolean isNull(String str, boolean isTrimmed) {
        return isTrimmed ? null == str || "".equals(str.trim()) : null == str || "".equals(str);
    }

    /**
     * 判断对象是否为空
     *
     * @param obj 需要进行判断的对象
     * @return 为空(true), 不为空(false)
     */
    public static boolean isNull(Object obj) {
        return null == obj || "".equals(obj);
    }

    /**
     * 判断集合是否为空
     * PS：
     * 集合为空的条件：
     * 1. 集合对象为null
     * 2. 集合中没有元素
     *
     * @param collection 需要进行判断的集合
     * @return 为空(true), 不为空(false)
     */
    public static boolean isNull(Collection<?> collection) {
        return null == collection || collection.size() == 0;
    }

    /**
     * 判断对象数组是否为空
     * PS：
     * 对象数组为空的条件：
     * 1. 对象数组为null
     * 2. 对象数组中没有元素
     *
     * @param array 需要进行判断的对象数组
     * @return 为空(true), 不为空(false)
     */
    public static boolean isNull(Object[] array) {
        return null == array || array.length == 0;
    }

    /**
     * 判断数组是否为空
     * PS：
     * 数组为空的条件：
     * 1. 数组为null
     * 2. 数组中没有元素
     *
     * @param array 需要进行判断的数组
     * @return 为空(true), 不为空(false)
     */
    public static boolean isNull(long[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否为空
     * PS：
     * 数组为空的条件：
     * 1. 数组为null
     * 2. 数组中没有元素
     *
     * @param array 需要进行判断的数组
     * @return 为空(true), 不为空(false)
     */
    public static boolean isNull(int[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否为空
     * PS：
     * 数组为空的条件：
     * 1. 数组为null
     * 2. 数组中没有元素
     *
     * @param array 需要进行判断的数组
     * @return 为空(true), 不为空(false)
     */
    public static boolean isNull(short[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否为空
     * PS：
     * 数组为空的条件：
     * 1. 数组为null
     * 2. 数组中没有元素
     *
     * @param array 需要进行判断的数组
     * @return 为空(true), 不为空(false)
     */
    public static boolean isNull(char[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否为空
     * PS：
     * 数组为空的条件：
     * 1. 数组为null
     * 2. 数组中没有元素
     *
     * @param array 需要进行判断的数组
     * @return 为空(true), 不为空(false)
     */
    public static boolean isNull(byte[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否为空
     * PS：
     * 数组为空的条件：
     * 1. 数组为null
     * 2. 数组中没有元素
     *
     * @param array 需要进行判断的数组
     * @return 为空(true), 不为空(false)
     */
    public static boolean isNull(double[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否为空
     * PS：
     * 数组为空的条件：
     * 1. 数组为null
     * 2. 数组中没有元素
     *
     * @param array 需要进行判断的数组
     * @return 为空(true), 不为空(false)
     */
    public static boolean isNull(float[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否为空
     * PS：
     * 数组为空的条件：
     * 1. 数组为null
     * 2. 数组中没有元素
     *
     * @param array 需要进行判断的数组
     * @return 为空(true), 不为空(false)
     */
    public static boolean isNull(boolean[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断字符串不为空
     * @param str
     * @return
     */
    public static boolean isNotNull(String str){
        //StringUtils.isNotEmpty(str);
        return str != null && !"".equals(str);
    }
    /**
     * 集合判断是否为空
     * @param collection 使用泛型
     * @return
     */
    public static <T> boolean isNotNull(Collection<T> collection){
        if(collection != null){
            Iterator<T> iterator = collection.iterator();
            if(iterator != null){
                while(iterator.hasNext()){
                    Object next = iterator.next();
                    if(next != null){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 判断对象是否为空
     */
    public static boolean isNotNull(Object object){

        if (object == null){
            return false;
        }else {
            return true;
        }

    }
    /**
     * 获取系统流水号
     * @return 长度为20的全数字
     */
    public static String getRefNo(){
        return getRefNo(12, true);
    }
    /**
     * 获取系统流水号
     * @param length   指定流水号长度
     * @param isNumber 指定流水号是否全由数字组成
     */
    public static String getRefNo(int length, boolean isNumber){
        //replaceAll()之后返回的是一个由十六进制形式组成的且长度为32的字符串
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        if(uuid.length() > length){
            uuid = uuid.substring(0, length);
        }else if(uuid.length() < length){
            for(int i=0; i<length-uuid.length(); i++){
                uuid = uuid + Math.round(Math.random()*9);
            }
        }
        if(isNumber){
            return uuid.replaceAll("a", "1").replaceAll("b", "2").replaceAll("c", "3").replaceAll("d", "4").replaceAll("e", "5").replaceAll("f", "6");
        }else{
            return uuid;
        }
    }

    /**
     *功能：生成Token-基于HS256对称加密
     */
    /**
    public static String createToken(Map<String,Object> map){

        //1.创建JWT Header头部
        JWSHeader jwnHeader = new JWSHeader(JWSAlgorithm.ES256);
        //2.创建载荷，即将上送的数据放入载荷
        Payload payload = new Payload(new JSONObject(map));
        //3.将头部和载荷绑定到一起
        JWSObject jwsObject = new JWSObject(jwnHeader,payload);
        //4.初始化密钥
        JWSSigner jwsssSigner = new MACSigner(secret);

        return null;

    }
    */
}
