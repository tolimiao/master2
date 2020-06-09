package kemei_kemei;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Test {
     @org.junit.Test
     public void a() {
    	  try {
    		  File file=new File("C:\\项目视频\\都市：做大佬从每日一善开始_正文(第1章-50章).txt");
        	  FileInputStream inputStream=new FileInputStream(file);
              InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"GB2312");
        	  BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        	  String readLine = bufferedReader.readLine();
        	  while ((readLine=bufferedReader.readLine())!=null) {
				System.out.println(readLine);
				Thread.sleep(3000);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			
		}
    	  
    	  
    	 
     }
}
