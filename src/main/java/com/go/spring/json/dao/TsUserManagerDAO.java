package com.go.spring.json.dao;

import java.util.List;

import com.go.spring.json.bean.TsUserManagerRequestBean;
import com.go.spring.json.bean.TsUserManagerResponseBean;

public interface TsUserManagerDAO {

	public List<TsUserManagerResponseBean> getAllUser(TsUserManagerRequestBean tsUserManagerRequestBean);
	
	public void addTsUser(TsUserManagerRequestBean tsUserManagerRequestBean);
	
	public int updateTsUser(TsUserManagerRequestBean tsUserManagerRequestBean);
	
	public int deleteTsUser(TsUserManagerRequestBean tsUserManagerRequestBean);
	
	public TsUserManagerResponseBean getOneUser(TsUserManagerRequestBean tsUserManagerRequestBean);
	
	public int updateTsUserPasswd(TsUserManagerRequestBean tsUserManagerRequestBean);
	
	public TsUserManagerResponseBean validateUserNameAndPwd(TsUserManagerRequestBean tsUserManagerRequestBean);
	
}
