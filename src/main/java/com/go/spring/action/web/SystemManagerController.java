package com.go.spring.action.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system.action")
public class SystemManagerController {
	protected static Logger logger = Logger.getLogger(SystemManagerController.class);
	
	@RequestMapping(params = "method=systemUserManage")
	public String systemUserManage(HttpServletRequest request) {
		return "jsp/systemUserManager/systemUserManager";
	}
	
}


