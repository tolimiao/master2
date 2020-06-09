package com.example.kemei.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kemei.utils.Result;
import com.example.kemei.utils.SnCal;


@RestController
@RequestMapping("/baidu")
public class Baidu {

    //最大连接数
    @Value("${http.maxTotal}")
    private Integer maxTotal;
 
    //并发数
    @Value("${http.defaultMaxPerRoute}")
    private Integer defaultMaxPerRoute;
 
    //创建连接的最长时间
    @Value("${http.connectTimeout}")
    private Integer connectTimeout;
 
    //从连接池中获取到连接的最长时间
    @Value("${http.connectionRequestTimeout}")
    private Integer connectionRequestTimeout;
 
    //数据传输的最长时间
    @Value("${http.socketTimeout}")
    private Integer socketTimeout;
 
    //提交请求前测试连接是否可用
    @Value("${http.staleConnectionCheckEnabled}")
    private boolean staleConnectionCheckEnabled;
           
    //百度服务ID
    @Value("${baidu.service_id}")
    private String service_id;
    
    //百度服务ID
    @Value("${baidu.ak}")
    private String ak;
    
    
    @Autowired
    private SnCal sncal;
	
	     @RequestMapping("/add")
	     public Result add() {
	    	    
	            HttpClient client = HttpClients.createDefault();// 创建默认http连接
	            HttpPost post = new HttpPost("http://yingyan.baidu.com/api/v3/entity/add");// 创建一个post请求
	            List<NameValuePair> paramList = new ArrayList<NameValuePair>();
	            paramList.add(new BasicNameValuePair("ak", ak));//传递的参数
	            paramList.add(new BasicNameValuePair("service_id", service_id));//传递的参数
	            paramList.add(new BasicNameValuePair("entity_name", "张三002"));//传递的参数
	            // 把参转码后放入请求实体中
	            try {
	            	paramList.add(new BasicNameValuePair("sn", sncal.query("凯腾大厦", ak)));//传递的参数
	            	HttpEntity entitya = new UrlEncodedFormEntity(paramList, "utf-8");
	  	            post.setEntity(entitya);// 把请求实体放post请求中
	  	            HttpResponse response = client.execute(post);// 用http连接去执行get请求并且获得http响应
	  	            HttpEntity entity = response.getEntity();// 从response中取到响实体
	  	            String html = EntityUtils.toString(entity);// 把响应实体转成文本
	  	            System.out.println(html);
				} catch (Exception e) {
				   return	Result.error();
				}
	            return Result.ok();
	            
	     }
	     
	     @RequestMapping("/delete")
	     public Result delete() {
	            HttpClient client = HttpClients.createDefault();// 创建默认http连接
	            HttpPost post = new HttpPost("http://yingyan.baidu.com/api/v3/entity/delete");// 创建一个post请求
	            List<NameValuePair> paramList = new ArrayList<NameValuePair>();
	            paramList.add(new BasicNameValuePair("ak", ak));//传递的参数
	            paramList.add(new BasicNameValuePair("service_id", service_id));//传递的参数
	            paramList.add(new BasicNameValuePair("entity_name", "张三002"));//传递的参数
	            try {
	            	paramList.add(new BasicNameValuePair("sn", sncal.query("凯腾大厦", ak)));//传递的参数
	            	HttpEntity entitya = new UrlEncodedFormEntity(paramList, "utf-8");
	  	            post.setEntity(entitya);// 把请求实体放post请求中
	  	            HttpResponse response = client.execute(post);// 用http连接去执行get请求并且获得http响应
	  	            HttpEntity entity = response.getEntity();// 从response中取到响实体
	  	            String html = EntityUtils.toString(entity);// 把响应实体转成文本
	  	            System.out.println(html);
				} catch (Exception e) {
				   return	Result.error();
				}
	            
	            return Result.ok();
	     }

}
