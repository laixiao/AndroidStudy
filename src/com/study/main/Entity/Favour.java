package com.study.main.Entity;

import cn.bmob.v3.BmobObject;

public class Favour extends BmobObject{

	private ShuoShuo shuoshuo;
	private User user;
	private String shuoshuoid;
	
	
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
	public String getShuoshuoid() {
		return shuoshuoid;
	}
	public void setShuoshuoid(String shuoshuoid) {
		this.shuoshuoid = shuoshuoid;
	}
	@Override
	public String toString() {
		return "Favour [shuoshuo=" + shuoshuo + ", user=" + user
				+ ", shuoshuoid=" + shuoshuoid + "]";
	}

	
}
