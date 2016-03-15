package com.go.spring.action.bean;

import java.io.Serializable;
import java.util.List;

public class TsMenuResponseBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String menuId = null;//菜单编号
	private String menuName = null; //菜单名称
	private String menuUrl = null; //菜单URL
	private List<TsMenuItemResponseBean> item = null;
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public List<TsMenuItemResponseBean> getItem() {
		return item;
	}
	public void setItem(List<TsMenuItemResponseBean> item) {
		this.item = item;
	}
	
}
