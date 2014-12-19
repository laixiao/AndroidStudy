package com.study.main.Entity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

public class ZhangzehuaParent extends BmobObject{

	
	private String  name;
	private String count;
	private String sortid;
	private BmobRelation child;
	
	public String getSortid() {
		return sortid;
	}
	public void setSortid(String sortid) {
		this.sortid = sortid;
	}
	public BmobRelation getChild() {
		return child;
	}
	public void setChild(BmobRelation child) {
		this.child = child;
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
