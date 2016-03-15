package com.go.spring.action.service;

import java.util.List;

import com.go.spring.action.bean.TsMenuItemResponseBean;
import com.go.spring.action.bean.TsMenuResponseBean;

public interface MenuService {
	/**
	 * 查询菜单列表
	 * @return
	 */
	public List<TsMenuResponseBean> queryFatherMenu();
	public List<TsMenuItemResponseBean> queryAllMenu();
}
