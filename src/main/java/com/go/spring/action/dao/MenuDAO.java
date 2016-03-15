package com.go.spring.action.dao;

import java.util.List;

import com.go.spring.action.bean.TsMenuItemResponseBean;
import com.go.spring.action.bean.TsMenuResponseBean;

public interface MenuDAO {

	public List<TsMenuResponseBean> getFatherMenuList();
	public List<TsMenuItemResponseBean> getAllMenuList();

	/**
	 * 查询菜单
	 */
}
