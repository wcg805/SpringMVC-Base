package com.go.spring.action.dao;

import com.go.spring.action.bean.TsUserLoginRequestBean;
import com.go.spring.action.bean.TsUserLoginResponseBean;

public interface TsUserDAO {

	public TsUserLoginResponseBean getOneTsUser(TsUserLoginRequestBean tsUser);
}