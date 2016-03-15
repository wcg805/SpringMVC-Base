package com.go.spring.action.service;

import com.go.spring.action.bean.TsUserLoginRequestBean;
import com.go.spring.action.bean.TsUserLoginResponseBean;

public interface TsUserService {
	
	public TsUserLoginResponseBean getOneTsUser(TsUserLoginRequestBean tsUser);
}
