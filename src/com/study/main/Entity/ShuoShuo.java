package com.study.main.Entity;

import java.io.Serializable;
import java.util.ArrayList;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

public class ShuoShuo extends BmobObject implements Serializable{

	/**
	 * qiang yu entity,每个列表item内容
	 * 2014/4/27
	 */
	private static final long serialVersionUID = -6280656428527540320L;
	
	private User author;	
	private String content;
	private BmobFile Contentfigureurl;
	
	private BmobRelation relation;

	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public BmobFile getContentfigureurl() {
		return Contentfigureurl;
	}
	public void setContentfigureurl(BmobFile contentfigureurl) {
		Contentfigureurl = contentfigureurl;
	}

	public BmobRelation getRelation() {
		return relation;
	}
	public void setRelation(BmobRelation relation) {
		this.relation = relation;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
