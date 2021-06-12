package com.infotrends.in.SpringbootV1;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AppSringBootTest {
	@Test  
	void contextLoads()   
	{  
		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8081/testConnection").openConnection();
			connection.setRequestMethod("HEAD");
			int responseCode = connection.getResponseCode();
			if (responseCode == 200) {
			    System.out.println("System is Up!------------------------------->");
			} else {
				System.out.println("System is Down!-------------------------------> " + responseCode);
			}
	    } catch (IOException e) {
	    	System.out.println("System is Down!-------------------------------> " + e.toString());
	    }
	}  
}
