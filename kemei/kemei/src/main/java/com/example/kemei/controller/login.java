package com.example.kemei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kemei.entity.User;
import com.example.kemei.utils.Result;
@CrossOrigin(origins = {"http://localhost:8002","null"})
@RestController
public class login {
     @Autowired
     private Result result;
     
     
	 @RequestMapping("/")
	 public Result index(User  user) {
		 //&& user.getPassword().equals("123456")
		  if (!user.getName().isEmpty()&& user.getName().equals("admin")&& !user.getPassword().isEmpty() && user.getPassword().equals("123456")) {
			  System.out.println("成功");
			  return result.ok(user);
		  }
		  System.out.println("失败");
		  return result.error("用户名不存在,或密码错误");
	 }
	 

}
