package com.go.spring.json.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.go.spring.json.bean.TsUserManagerRequestBean;
import com.go.spring.json.bean.TsUserManagerResponseBean;
import com.go.spring.json.dao.TsUserManagerDAO;

@Repository("tsUserManagerDao")
public class TsUserManagerDAOImpl  implements TsUserManagerDAO {

	@Autowired
	private SqlMapClientTemplate sqlMapClient;
	
	@Override
	public List<TsUserManagerResponseBean> getAllUser(TsUserManagerRequestBean tsUserManagerRequestBean) {
		return sqlMapClient.queryForList("tsUserManager.getAllUser" , tsUserManagerRequestBean);
	}
	
	@Override
	public void addTsUser(TsUserManagerRequestBean tsUserAddRequestBean) {
		sqlMapClient.insert("tsUserManager.addUser" , tsUserAddRequestBean);
	}

	@Override
	public int updateTsUser(TsUserManagerRequestBean tsUserManagerRequestBean) {
		return sqlMapClient.update("tsUserManager.updateUser" , tsUserManagerRequestBean);
	}

	@Override
	public int deleteTsUser(TsUserManagerRequestBean tsUserManagerRequestBean) {
		return sqlMapClient.update("tsUserManager.deleteUser" , tsUserManagerRequestBean);
	}

	@Override
	public TsUserManagerResponseBean getOneUser(TsUserManagerRequestBean tsUserManagerRequestBean) {
		return (TsUserManagerResponseBean) sqlMapClient.queryForObject("tsUserManager.getOneUser" , tsUserManagerRequestBean);
	}

	@Override
	public int updateTsUserPasswd(TsUserManagerRequestBean tsUserManagerRequestBean) {
		return sqlMapClient.update("tsUserManager.updateUserPasswd" , tsUserManagerRequestBean);
	}

	@Override
	public TsUserManagerResponseBean validateUserNameAndPwd(TsUserManagerRequestBean tsUserManagerRequestBean) {
		return (TsUserManagerResponseBean) sqlMapClient.queryForObject("tsUserManager.validateUserNameAndPwd" , tsUserManagerRequestBean);
	
	}

}