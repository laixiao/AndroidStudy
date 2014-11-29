package com.study.main.Entity;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

public class UserMovie extends BmobObject {

	private String moviename;
	private BmobFile moviefile; 
	
	private BmobUser user;

	
	
	
	


	public UserMovie(String moviename, BmobFile moviefile, BmobUser user) {
		super();
		this.moviename = moviename;
		this.moviefile = moviefile;
		this.user = user;
	}

	public BmobUser getUser() {
		return user;
	}

	public void setUser(BmobUser user) {
		this.user = user;
	}

	public String getMoviename() {
		return moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}

	public BmobFile getMoviefile() {
		return moviefile;
	}

	public void setMoviefile(BmobFile moviefile) {
		this.moviefile = moviefile;
	}

	
}
