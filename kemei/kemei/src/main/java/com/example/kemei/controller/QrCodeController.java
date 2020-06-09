package com.example.kemei.controller;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.kemei.utils.QrCodeUtils;

@Controller
public class QrCodeController {
	@RequestMapping("/createQrCode")
    public void createQrCode(HttpServletRequest request, HttpServletResponse response) {
        StringBuffer url = request.getRequestURL();
        try {
            OutputStream os = response.getOutputStream();
            //从配置文件读取需要生成二维码的连接
//            String requestUrl = GraphUtils.getProperties("requestUrl");
            //requestUrl:需要生成二维码的连接，logoPath：内嵌图片的路径，os：响应输出流，needCompress:是否压缩内嵌的图片
            QrCodeUtils.encode("http://www.baidu.com", "/static/images/1.jpg", os, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
