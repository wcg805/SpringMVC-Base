package com.go.spring.action.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.go.spring.action.bean.TsMenuItemResponseBean;
import com.go.spring.action.bean.TsMenuResponseBean;
import com.go.spring.action.dao.MenuDAO;
import com.go.spring.action.service.MenuService;
@Service("menuService")
public class MenuImpl implements MenuService {
	@Resource(name = "menuDao")
	private MenuDAO menuDao;

	@Override
	public List<TsMenuResponseBean> queryFatherMenu() {
		// TODO Auto-generated method stub
		return menuDao.getFatherMenuList();
	}

	@Override
	public List<TsMenuItemResponseBean> queryAllMenu() {
		// TODO Auto-generated method stub
		return menuDao.getAllMenuList();
	}

	
}
