package com.study.main.Entity;
import java.io.Serializable;
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
	private BmobRelation love;	
	private BmobRelation favour;

	
	public BmobRelation getLove() {
		return love;
	}
	public void setLove(BmobRelation love) {
		this.love = love;
	}
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


	public BmobRelation getFavour() {
		return favour;
	}
	public void setFavour(BmobRelation favour) {
		this.favour = favour;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
