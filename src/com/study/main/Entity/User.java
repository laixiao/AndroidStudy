package com.study.main.Entity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

public class User extends BmobUser{
	private boolean sex;

	private String phonenumber;
	private String nickname;
	private String birthday;
	private String signature;
	
	private BmobFile avatar;
	private Integer favourcount;
	
	private BmobRelation wallet;
	private BmobRelation favorite;
	private BmobRelation movie;
	
	
	
	
	
	
	
	
	
	
	

	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "User [phonenumber=" + phonenumber + ", nickname=" + nickname
				+ ", birthday=" + birthday + "]";
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public BmobRelation getMovie() {
		return movie;
	}
	public void setMovie(BmobRelation movie) {
		this.movie = movie;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public BmobRelation getFavorite() {
		return favorite;
	}
	public void setFavorite(BmobRelation favorite) {
		this.favorite = favorite;
	}
	public Integer getFavourcount() {
		return favourcount;
	}
	public void setFavourcount(Integer favourcount) {
		this.favourcount = favourcount;
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
	
	public BmobFile getAvatar() {
		return avatar;
	}
	public void setAvatar(BmobFile avatar) {
		this.avatar = avatar;
	}
	public BmobRelation getWallet() {
		return wallet;
	}
	public void setWallet(BmobRelation wallet) {
		this.wallet = wallet;
	}


}
