package com.study.main.Entity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Comment extends BmobObject{
	private ShuoShuo shuoshuo;
	private String content;
	private BmobFile contentPic;
	private User user;
	public ShuoShuo getShuoshuo() {
		return shuoshuo;
	}
	public void setShuoshuo(ShuoShuo shuoshuo) {
		this.shuoshuo = shuoshuo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public BmobFile getContentPic() {
		return contentPic;
	}
	public void setContentPic(BmobFile contentPic) {
		this.contentPic = contentPic;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	

	
	
	
}
