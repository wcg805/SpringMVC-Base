package com.go.spring.action.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.go.spring.action.bean.TsUserLoginRequestBean;
import com.go.spring.action.bean.TsUserLoginResponseBean;
import com.go.spring.action.dao.TsUserDAO;
import com.go.spring.action.service.TsUserService;
@Service("tsUserService")
public class TsUserServiceImpl implements TsUserService {
	@Resource(name = "tsUserDao") 
	private TsUserDAO tsuserDao;

	public TsUserLoginResponseBean getOneTsUser(TsUserLoginRequestBean tsUser){
		return tsuserDao.getOneTsUser(tsUser);
	}
	
}
