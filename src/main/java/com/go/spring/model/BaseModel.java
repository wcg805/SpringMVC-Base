package com.go.spring.model;

import java.util.Date;

import com.go.spring.json.bean.ResponseBaseBean;

/**
 *  @author chenhewen
 *	createTime 创建时间
 *	editTime   修改时间
 *  creator    创建人
 *  editor     修改人
 *  isdel      是否删除 0 否  1 是
 */
public class BaseModel extends ResponseBaseBean{
	
	public Integer id = null;

	public String createTime = null;
	
	public Date editTime = null;
	
	public Integer creator = null;
	
	public Integer editor = null;
	
	public Integer isdel = null;


	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Integer getEditor() {
		return editor;
	}

	public void setEditor(Integer editor) {
		this.editor = editor;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

}
