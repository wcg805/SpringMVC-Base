package com.go.spring.util;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.go.spring.json.service.JsonService;

public class DataUtil {
	
	public static final String CHARACTER_ENCODING = "UTF-8";
	public static final String CONTENT_TYPE = "application/json; charset=UTF-8";
	public static String FILE_PATH;
	
	public static void main(String[] args) {
		System.out.println(getMp3Time("F:\\QQMiniDL\\8896148580669634.mp3"));
	}
	
	public static Object executeCommonJsonRequest(HttpServletRequest request,
			JsonService jsonService,Class<?> clazz) throws Exception{
		Object requestBean = clazz.newInstance();
        DataUtil.getRequestBean(request, requestBean);
        String protocolName = DataUtil.getRequestBeanName(clazz);
        String requestJson = JSONObject.toJSONString(requestBean);
        
        Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String dateTime = format.format(date);
        
        System.out.println(dateTime + " request  "+protocolName+":" + requestJson);
        
        Object responseBean = jsonService.execute(requestBean);
        String responseJson = JSONObject.toJSONString(responseBean);
        System.out.println(dateTime + " response "+protocolName+":" + responseJson);
        return responseBean;
	}
	
	public static String getOneUpperName(String name){
		if(name != null){
			name = name.substring(0,1).toUpperCase() + name.substring(1);
		}
		return name;
	}
	
	public static String getRequestBeanName(Class<?> clazz){
		String name = clazz.getSimpleName();
		return name.substring(0,1).toLowerCase() + name.substring(1,name.length()-11);
	}
	
	public static String getHttpParams(Object requestBeanObj) throws Exception{
		String params = "";
		Class<?> clazz = requestBeanObj.getClass();
		while(clazz != null){
			Field[] fields = clazz.getDeclaredFields();
			for(Field f:fields){
				Method method1 = clazz.getMethod("get"+DataUtil.getOneUpperName(f.getName()));
				Object requestBean1 = method1.invoke(requestBeanObj); 
				params += f.getName()+"="+requestBean1+"&";
			}
			clazz = clazz.getSuperclass();
		}
		
		if(params.endsWith("&")){
			params = params.substring(0,params.length()-1);
		}
		return params;
	}
	
	public static void getRequestBean(HttpServletRequest request,Object requestBean) throws Exception{
		Map<String, String[]> params = request.getParameterMap();  
        for (String key : params.keySet()) {  
            String[] values = params.get(key);  
            for (int i = 0; i < values.length; i++) {  
                String value = values[i];
                
                Field fild = null;
                Class<?> clazz = requestBean.getClass();
                while(clazz != null){
                	try{
                		fild = clazz.getDeclaredField(key);
                	}catch(NoSuchFieldException e){
                	}
                	
                	if(fild != null){
                		break;
                	}
                	clazz = clazz.getSuperclass();
                }
                
                if(fild != null && value!= null && !"null".equals(value)){
                	Object obj = null;
                	if(fild.getType() == String.class){
                		obj = value;
                	}else if(fild.getType() == Integer.class){
                		obj = Integer.parseInt(value);
                	}else if(fild.getType() == Float.class){
                		obj = Float.parseFloat(value);
                	}else if(fild.getType() == Double.class){
                		obj = Double.parseDouble(value);
                	}else if(fild.getType() == Date.class){
                		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
                		obj = sdf.parse(value); 
                	}else if(fild.getType() == Boolean.class){
                		obj = Boolean.parseBoolean(value);
                	}
                	
                	Method method = requestBean.getClass().getMethod("set"+DataUtil.getOneUpperName(key),fild.getType());
	                method.invoke(requestBean,obj);
                }
            }  
        }
	}
	
	public static void SendHttpTest(String protocolName,String ip,int port) throws Exception{
		Object requestBean = null;
		Class<?> cls = Class.forName("com.go.spring.util.GetRequestJson");
		Method method = cls.getMethod("get" + DataUtil.getOneUpperName(protocolName) + "RequestBean");
		requestBean = method.invoke(cls.newInstance());
		
		String params = DataUtil.getHttpParams(requestBean);
		
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String dateTime = format.format(date);
		
		String requestJson = JSONObject.toJSONString(requestBean);
        System.out.println(dateTime + " request  "+protocolName+":" + requestJson);
		
		String responseJson = HttpRequest.sendPost("http://"+ip+":"+port+"/EmujeesuPayWeb/"+protocolName+".json", params);
		System.out.println(dateTime + " response "+protocolName+":" + responseJson);
	}
	
	public static byte[] toByteArray(String filename) throws IOException {

		File f = new File(filename);
		if (!f.exists()) {
			throw new FileNotFoundException(filename);
		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(f));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bos.close();
		}
	}
	
	//生成随机数
	public static String random(int n) {
        if (n < 1 || n > 10) {
            throw new IllegalArgumentException("cannot random " + n + " bit number");
        }
        Random ran = new Random();
        if (n == 1) {
            return String.valueOf(ran.nextInt(10));
        }
        int bitField = 0;
        char[] chs = new char[n];
        for (int i = 0; i < n; i++) {
            while(true) {
                int k = ran.nextInt(10);
                if( (bitField & (1 << k)) == 0) {
                    bitField |= 1 << k;
                    chs[i] = (char)(k + '0');
                    break;
                }
            }
        }
        return new String(chs);
    }
	
	public static int getMp3Time(String mp3Path){
		int time = 0;
		Encoder encoder = new Encoder();
	     try {
	          MultimediaInfo m = encoder.getInfo(new File(mp3Path));
	          time = (int)m.getDuration()/1000;
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	     return time;
	}
	 
}
