package com.example.kemei.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/video")
public class VidoeController {
	/**
	 * 保存图片
	 * @author Caron
	 * @time 2018年12月27日下午3:12:52
	 */
	
	@RequestMapping("/")
	public Object videoImg() {

	   return "video";
	}


}
