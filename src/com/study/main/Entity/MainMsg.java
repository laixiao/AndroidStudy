package com.study.main.Entity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class MainMsg extends BmobObject{

	private String title;
	private String content;
	private String url;
	private BmobFile contentfig;
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public BmobFile getContentfig() {
		return contentfig;
	}
	public void setContentfig(BmobFile contentfig) {
		this.contentfig = contentfig;
	}
	

}
