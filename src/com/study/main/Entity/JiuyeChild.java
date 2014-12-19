package com.study.main.Entity;

import cn.bmob.v3.BmobObject;

public class JiuyeChild extends BmobObject{

	private String name;
	private String content;
	private String url;
	private String sortid;
	private JiuyeParent parent;
	
	public String getSortid() {
		return sortid;
	}
	public void setSortid(String sortid) {
		this.sortid = sortid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public JiuyeParent getParent() {
		return parent;
	}
	public void setParent(JiuyeParent parent) {
		this.parent = parent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
