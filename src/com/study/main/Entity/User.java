package com.study.main.Entity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

public class User extends BmobUser{
	
	private boolean sex;		//�Ա�
	private String phonenumber;	//�ֻ�����
	private String nickname;	//�ǳ�
	private String birthday;	//����	
	private String signature;	//ǩ��
	private BmobFile avatar;	//ͷ��
	private Integer favourcount;//���޹�ע����
	private BmobRelation shuoshuo;//˵˵
	private Integer money;
	
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public BmobFile getAvatar() {
		return avatar;
	}
	public void setAvatar(BmobFile avatar) {
		this.avatar = avatar;
	}
	public Integer getFavourcount() {
		return favourcount;
	}
	public void setFavourcount(Integer favourcount) {
		this.favourcount = favourcount;
	}
	public BmobRelation getShuoshuo() {
		return shuoshuo;
	}
	public void setShuoshuo(BmobRelation shuoshuo) {
		this.shuoshuo = shuoshuo;
	}
	@Override
	public String toString() {
		return "User [sex=" + sex + ", phonenumber=" + phonenumber
				+ ", nickname=" + nickname + ", birthday=" + birthday
				+ ", signature=" + signature + ", avatar=" + avatar
				+ ", favourcount=" + favourcount + ", shuoshuo=" + shuoshuo
				+ ", money=" + money + "]";
	}


	
	



}
