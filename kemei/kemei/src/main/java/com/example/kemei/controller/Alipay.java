package com.example.kemei.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.example.kemei.Config.AlipayConfig;
import com.example.kemei.entity.User;
import com.example.kemei.utils.Result;


@RequestMapping("/alipay")
@Controller
public class Alipay {
      @Value("${alipay.GATEWAY_URL}")
	  private String GATEWAY_URL;
      
      @Value("${alipay.APP_ID}")
	  private String APP_ID;
      
      @Value("${alipay.APP_PRIVATE_KEY}")
	  private String APP_PRIVATE_KEY;
      
      @Value("${alipay.FORMAT}")
	  private String FORMAT;
      
      @Value("${alipay.CHARSET}")
	  private String CHARSET;
     
      @Value("${alipay.ALIPAY_PUBLIC_KEY}")
	  private String ALIPAY_PUBLIC_KEY;
      
      @Value("${alipay.SIGN_TYPE}")
	  private String SIGN_TYPE;
      
      @Value("${alipay.RETURN_URL}")
	  private String RETURN_URL;
      
      @Value("${alipay.NOTIFY_URL}")
	  private String NOTIFY_URL;
	
	  @RequestMapping("/alipay")
	    public void alipay(HttpServletResponse httpResponse) throws IOException {

	        Random r=new Random();
	        //实例化客户端,填入所需参数
	        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
	        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
	        //在公共参数中设置回跳和通知地址
	        request.setReturnUrl(RETURN_URL);
	        request.setNotifyUrl(NOTIFY_URL);

	        //商户订单号，商户网站订单系统中唯一订单号，必填
	        //生成随机Id
	        String out_trade_no = 	Integer.toString(r.nextInt(9999999)+1000000);
	        //付款金额，必填
	        String total_amount =Integer.toString(r.nextInt(9999999)+1000000);
	        //订单名称，必填
	        String subject ="奥迪A8 2016款 A8L 60 TFSl quattro豪华型";
	        //商品描述，可空
	        String body = "尊敬的会员欢迎购买奥迪A8 2016款 A8L 60 TFSl quattro豪华型";
	        request.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
	                + "\"total_amount\":\""+ total_amount +"\","
	                + "\"subject\":\""+ subject +"\","
	                + "\"body\":\""+ body +"\","
	                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
	        String form = "";
	        try {
	            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
	        } catch (AlipayApiException e) {
	            e.printStackTrace();
	        }
	        httpResponse.setContentType("text/html;charset=" + CHARSET);
	        httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
	        httpResponse.getWriter().flush();
	        httpResponse.getWriter().close();
	    }
      
	    @RequestMapping("/result")
	    @ResponseBody 
	    public Result payResult() {
	    	  return Result.ok();
	    }
	    
	    
	    @RequestMapping(value = "/returnUrl", method = RequestMethod.GET)
	    public String returnUrl(HttpServletRequest request, HttpServletResponse response)
	            throws IOException, AlipayApiException {
	        System.out.println("=================================同步回调=====================================");

	        // 获取支付宝GET过来反馈信息
	        Map<String, String> params = new HashMap<String, String>();
	        Map<String, String[]> requestParams = request.getParameterMap();
	        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
	            String name = (String) iter.next();
	            String[] values = (String[]) requestParams.get(name);
	            String valueStr = "";
	            for (int i = 0; i < values.length; i++) {
	                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
	            }
	            // 乱码解决，这段代码在出现乱码时使用
	            valueStr = new String(valueStr.getBytes("utf-8"), "utf-8");
	            params.put(name, valueStr);
	        }

	        System.out.println(params);//查看参数都有哪些
	        boolean signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE); // 调用SDK验证签名
	        //验证签名通过
	        if(signVerified){
	            // 商户订单号
	            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
	            
	            // 支付宝交易号
	            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
	            
	            // 付款金额
	            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
	            
	            System.out.println("商户订单号="+out_trade_no);
	            System.out.println("支付宝交易号="+trade_no);
	            System.out.println("付款金额="+total_amount);
	            
	            //支付成功，修复支付状态
	         //   payService.updateById(Integer.valueOf(out_trade_no));
	            return "ok";//跳转付款成功页面
	        }else{
	            return "no";//跳转付款失败页面
	        }
	        
	    }

		 @RequestMapping("/")
		 public String index() {
			 System.out.println(1);
			  return "index";
		 }
		 
	
}
