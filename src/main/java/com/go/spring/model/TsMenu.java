package com.go.spring.model;

public class TsMenu extends BaseModel{
	
	private String menuParent = null;
	private String menuId = null;
	
	public String getMenuParent() {
		return menuParent;
	}
	public void setMenuParent(String menuParent) {
		this.menuParent = menuParent;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	
	
}
