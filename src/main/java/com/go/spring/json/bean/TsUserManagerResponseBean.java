package com.go.spring.json.bean;

import com.go.spring.model.TsUser;

public class TsUserManagerResponseBean extends TsUser{
	
	private String creatorStr = null;
	private String creatorTimeStr = null;
	
	public String getCreatorStr() {
		return creatorStr;
	}
	public void setCreatorStr(String creatorStr) {
		this.creatorStr = creatorStr;
	}
	public String getCreatorTimeStr() {
		return creatorTimeStr;
	}
	public void setCreatorTimeStr(String creatorTimeStr) {
		this.creatorTimeStr = creatorTimeStr;
	}
	
}
