package com.go.spring.action.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.go.spring.action.bean.TsUserLoginRequestBean;
import com.go.spring.action.bean.TsUserLoginResponseBean;
import com.go.spring.action.dao.TsUserDAO;

@Repository("tsUserDao")
public class TsUserDAOImpl  implements TsUserDAO {

	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@Override
	public TsUserLoginResponseBean getOneTsUser(TsUserLoginRequestBean tsUser) {
		// TODO Auto-generated method stub
		return (TsUserLoginResponseBean) sqlMapClient.queryForObject("tsUser.getOneTsUser", tsUser);
	}
}