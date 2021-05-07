package com.hw.csdn.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MyBodyResolver  implements HandlerMethodArgumentResolver  {

    private static final String POINT = ".";
    private static final String LEFT_PARENTHESIS = "[";
    private static final String RIGHT_PARENTHESIS = "]";
    private static final String PARENTHESIS = "[]";
    private static final String CONTENT_TYPE = "application/json";
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestJson.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String jsonStr;
        //判断content-type是否是application/json 的数据类型
        String contentType = webRequest.getHeader("content-type");
        if ( contentType.contains(CONTENT_TYPE)){
            jsonStr = getJSONParam(webRequest);
        }else {
            jsonStr = parseParamToJSONStr(webRequest);
        }
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        Iterator iter = jsonObject.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
            Pattern p = Pattern.compile(regEx);
            System.out.println(entry.getValue());
            String str = entry.getValue()+"";
            Matcher m = p.matcher(str);
            boolean flag = m.find();
            if (true==flag){
                throw new Exception("参数异常");
            }
        }
        try {
            return JSON.parseObject(jsonStr, parameter.getGenericParameterType());
        }catch (JSONException e){
            return null;
        }
    }

    /**
     *@Description 获取JSON字符串中的数据
     *@Author  phacxj
     *@Createtime  2019/2/12 16:12
     *@Param [request]
     *@Return java.lang.String
     */
    private String getJSONParam(NativeWebRequest request){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //1.获取输入流
            BufferedReader stramReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(request.getNativeRequest(HttpServletRequest.class)).getInputStream()));

            //2.写入数据到StringBuilder
            String line;
            while ((line = stramReader.readLine()) != null){
                stringBuilder.append(line);
            }
        }catch (Exception e){
        }
        return stringBuilder.toString();
    }

    /**
     *@Description 将user.id=1这种形式的数据转为{user:{id:1}}形式
     *@Author  phacxj
     *@Createtime  2019/2/13 9:42
     *@Param [request]
     *@Return java.lang.String
     */
    private String parseParamToJSONStr(NativeWebRequest request){
        Map<String,String [] > conditionMap = request.getParameterMap();
        JSONObject jsonObject = new JSONObject();
        Iterator<String> names = request.getParameterNames();
        while (names.hasNext()){
            String name = names.next();
            String [] value = conditionMap.get(name);
            if (value != null){
                parseHavePointParamToJSON(jsonObject,name,value);
            }
        }
        return jsonObject.toJSONString();
    }

    /**
     *@Description 处理带有点的参数
     *@Author  phacxj
     *@Createtime  2019/2/13 10:12
     *@Param [jsonObject, name, value]
     *@Return void
     */
    private void parseHavePointParamToJSON(JSONObject jsonObject, String name, String [] value){
        //处理中括号
        if (name.contains(LEFT_PARENTHESIS)){
            name = name
                    .replace(PARENTHESIS,"")
                    .replace(LEFT_PARENTHESIS,POINT)
                    .replace(RIGHT_PARENTHESIS,"");

        }
        if (!name.contains(POINT)){
            //如果没有小数点，证明已经是最后一个了
            //判断是否是已经设置过值
            if (value.length>1){
                //如果是组数，则以数组的形式存放数据
                JSONArray jsonArray = new JSONArray();
                jsonArray.addAll(Arrays.asList(value));
                jsonObject.put(name,jsonArray);
            }else {
                jsonObject.put(name,value[0]);
            }
        }else {
            //如果还有小数点
            int pointIndex = name.indexOf(POINT);
            String tempName = name.substring(0,pointIndex);
            String subName = name.substring(name.indexOf(POINT)+1,name.length());
            JSONObject subJSONObject;
            //判断是否已经有对应的属性了
            if (jsonObject.getJSONObject(tempName) == null){
                //如果没有
                subJSONObject = new JSONObject();
            }else {
                //如果有
                subJSONObject = jsonObject.getJSONObject(tempName);
            }
            jsonObject.put(tempName,subJSONObject);
            //递归处理
            parseHavePointParamToJSON(subJSONObject,subName,value);
        }
    }


}
