package com.go.spring.util;


public class TestMain {

	public static void main(String[] args) throws Exception {

//		String protocolName = "userLogin";		//登录
//		String protocolName = "userReg";		//注册
//		String protocolName = "getLoginOrReg";	//注册或登录
		
//		String protocolName = "viewspotStory";	
//		String protocolName = "viewspotFood"; 
//		String protocolName = "viewspotEnvm";
//		String protocolName = "viewspotArticle";
		String protocolName = "viewspotManager";
		
		DataUtil.SendHttpTest(protocolName,"127.0.0.1",8080);
	}

}
