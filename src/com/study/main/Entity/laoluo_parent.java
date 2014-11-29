package com.study.main.Entity;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

public class laoluo_parent extends BmobObject{

	private BmobFile ico;
	private String  name;
	private String count;
	private BmobRelation child;
	
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
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	
}
