package com.study.main.Entity;

import cn.bmob.v3.BmobObject;

public class Favour extends BmobObject{

	private ShuoShuo shuoshuo;
	private User user;

	
	
	public ShuoShuo getShuoshuo() {
		return shuoshuo;
	}
	public void setShuoshuo(ShuoShuo shuoshuo) {
		this.shuoshuo = shuoshuo;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Favour [shuoshuo=" + shuoshuo + ", user=" + user + "]";
	}

	
}
