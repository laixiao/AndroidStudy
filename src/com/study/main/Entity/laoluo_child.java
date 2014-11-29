package com.study.main.Entity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class laoluo_child extends BmobObject{

	private BmobFile ico;
	private String name;
	private String content;
	private String url;
	private laoluo_parent parent;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public laoluo_parent getParent() {
		return parent;
	}
	public void setParent(laoluo_parent parent) {
		this.parent = parent;
	}
	public BmobFile getIco() {
		return ico;
	}
	public void setIco(BmobFile ico) {
		this.ico = ico;
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
