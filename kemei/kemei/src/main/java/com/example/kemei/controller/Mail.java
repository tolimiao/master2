package com.example.kemei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kemei.service.MailService;
import com.example.kemei.utils.Result;

@RestController
@RequestMapping("/mail")
public class Mail {
      @Autowired
	  private MailService service;
      
      
      @RequestMapping("/sendSimpleMail")
      public Result sendSimpleMail() {
    	  try {
    		  service.sendSimpleMail("2720806015@qq.com","测试spring boot imail-主题","测试spring boot imail - 内容");
		} catch (Exception e) {
			 System.out.println(e);
			 return Result.error();
		}
    	  
    	  return Result.ok();
      }
	    
}
