package com.go.spring.json.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.go.spring.json.bean.TsUserManagerRequestBean;
import com.go.spring.json.bean.TsUserManagerResponseBean;
import com.go.spring.json.dao.TsUserManagerDAO;
import com.go.spring.json.service.JsonService;
import com.go.spring.util.MD5;

@Service("tsUserManagerService")
public class TsUserManagerServiceImpl implements JsonService {

	@Resource(name = "tsUserManagerDao")
	private TsUserManagerDAO tsUserManagerDao;

	@Override
	public Object execute(Object requestBeanObj) throws Exception {

		TsUserManagerRequestBean requestBean = (TsUserManagerRequestBean) requestBeanObj;

		List<TsUserManagerResponseBean> responseBeanList = new ArrayList<TsUserManagerResponseBean>();

		if (requestBean.getMethod() == null || "".equals(requestBean.getMethod())) {
			responseBeanList = tsUserManagerDao.getAllUser(requestBean);

			return responseBeanList;
		}
		if ("add".equals(requestBean.getMethod())) {

			TsUserManagerResponseBean responseBean = new TsUserManagerResponseBean();

			responseBean = tsUserManagerDao.getOneUser(requestBean);

			if (responseBean != null) {
				responseBean.setCode(2);// 用户名重复
			} else {
				try {
					requestBean.setUserPasswd(MD5.EncoderByMd5(requestBean.getUserPasswd()));
					tsUserManagerDao.addTsUser(requestBean);
					responseBean = new TsUserManagerResponseBean();
					responseBean = tsUserManagerDao.getOneUser(requestBean);
					responseBean.setCode(1);
				} catch (Exception e) {
					e.printStackTrace();
					responseBean.setCode(0);
				}
			}
			return responseBean;
		}
		if ("update".equals(requestBean.getMethod())) {
			TsUserManagerResponseBean responseBean = new TsUserManagerResponseBean();

			if (!requestBean.getOldUserName().equals(requestBean.getUserName())) {
				responseBean = tsUserManagerDao.getOneUser(requestBean);
				if (responseBean != null) {
					responseBean.setCode(2);// 用户名重复
					return responseBean;
				}
			}

			int code = tsUserManagerDao.updateTsUser(requestBean);
			responseBean = new TsUserManagerResponseBean();
			responseBean = tsUserManagerDao.getOneUser(requestBean);
			responseBean.setCode(code);

			return responseBean;
		}
		if ("updatePwd".equals(requestBean.getMethod())) {
			TsUserManagerResponseBean responseBean = new TsUserManagerResponseBean();
			try {
				requestBean.setUserPasswd(MD5.EncoderByMd5(requestBean.getUserPasswd()));
				requestBean.setUserNewPasswd(MD5.EncoderByMd5(requestBean.getUserNewPasswd()));
				responseBean = tsUserManagerDao.validateUserNameAndPwd(requestBean);
				if (responseBean == null) {
					responseBean = new TsUserManagerResponseBean();
					responseBean.setCode(0);// 密码错误
				} else {
					int code = tsUserManagerDao.updateTsUserPasswd(requestBean);
					responseBean.setCode(code);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				responseBean.setCode(2);
			}
			return responseBean;
		}
		if ("delete".equals(requestBean.getMethod())) {
			TsUserManagerResponseBean responseBean = new TsUserManagerResponseBean();
			if (requestBean.getIds() != null) {
				requestBean.setIds(requestBean.getIds().substring(0, requestBean.getIds().length() - 1));
			}
			String[] ids = requestBean.getIds().split(",");
			for (String id : ids) {
				requestBean.setId(Integer.parseInt(id));
				tsUserManagerDao.deleteTsUser(requestBean);
			}

			responseBean.setCode(1);
			return responseBean;
		}

		return null;
	}
}
