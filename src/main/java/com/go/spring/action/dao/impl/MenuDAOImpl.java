package com.go.spring.action.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.go.spring.action.bean.TsMenuItemResponseBean;
import com.go.spring.action.bean.TsMenuResponseBean;
import com.go.spring.action.dao.MenuDAO;

@Repository("menuDao")
public class MenuDAOImpl implements MenuDAO{
	@Autowired
	private SqlMapClientTemplate sqlMapClient;
	
	@Override
	public List<TsMenuResponseBean> getFatherMenuList() {
		return sqlMapClient.queryForList("menu.getFatherMenulist");
	}
	@Override
	public List<TsMenuItemResponseBean> getAllMenuList() {
		// TODO Auto-generated method stub
		return sqlMapClient.queryForList("menu.getAllMenulist");
	}
}
