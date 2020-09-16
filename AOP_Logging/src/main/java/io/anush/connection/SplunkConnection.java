package io.anush.connection;

import com.splunk.Application;
import com.splunk.Args;
import com.splunk.HttpService;
import com.splunk.Receiver;
import com.splunk.SSLSecurityProtocol;
import com.splunk.Service;
import com.splunk.ServiceArgs;

public class SplunkConnection {
	
	
	  public static Service getService() {
		  

		  HttpService.setSslSecurityProtocol(SSLSecurityProtocol.TLSv1_2);
		  
		  ServiceArgs loginArgs= new ServiceArgs();
		  loginArgs.setUsername("anusha@1234");
		  loginArgs.setPassword("Snapchat@1234"); 
		 // loginArgs.setHost("localhost");
		  loginArgs.setHost("DESKTOP-H60G1IE");
		  
		  loginArgs.setPort(8089);
		   
		  Service service= Service.connect(loginArgs);
		  		  
		return service;
	  
	  }
	 
}