package com.go.spring.json.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.go.spring.json.bean.TsUserManagerRequestBean;
import com.go.spring.json.service.JsonService;
import com.go.spring.util.DataUtil;

@Controller
@RequestMapping("/tsUserManager.json")
public class TsUserManagerController {
	protected static Logger logger = Logger.getLogger(TsUserManagerController.class);

	@Autowired
	public JsonService tsUserManagerService;

	@RequestMapping(method = RequestMethod.POST)
	public void executeJsonRequest(HttpServletRequest request, HttpServletResponse response) {
		try {
			Object responseBean = DataUtil.executeCommonJsonRequest(request, tsUserManagerService,
					TsUserManagerRequestBean.class);

			String responseJson = JSONObject.toJSONString(responseBean);
			response.setCharacterEncoding(DataUtil.CHARACTER_ENCODING);
			response.setContentType(DataUtil.CONTENT_TYPE);
			response.getWriter().write(responseJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
}
