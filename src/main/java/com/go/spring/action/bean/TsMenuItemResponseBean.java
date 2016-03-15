package com.go.spring.action.bean;

import java.io.Serializable;

public class TsMenuItemResponseBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private String itemId = null;//二级菜单编号
	
	private String itemUrl = null;//二级菜单Action URL

	private String itemName = null; //二级菜单名称
	
	private String itemParent = null; //上级菜单

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemUrl() {
		return itemUrl;
	}

	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemParent() {
		return itemParent;
	}

	public void setItemParent(String itemParent) {
		this.itemParent = itemParent;
	}
	

	
	
}
