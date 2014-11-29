package com.study.main.ResideMenu;

import com.study.main.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResideMenuSetting extends LinearLayout{
private TextView shezhi,yewanpifu;

	public ResideMenuSetting(Context context) {
		super(context);
		initViews(context);
		
		// TODO Auto-generated constructor stub
	}
	public ResideMenuSetting(Context context, String shezhi1,String yewanpifu1) {
		super(context);
		initViews(context);
		shezhi.setText(shezhi1);
		yewanpifu.setText(yewanpifu1);
	}
	private void initViews(Context context) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.residemenu_setting, this);
		
		shezhi = (TextView) findViewById(R.id.shezhi);
		yewanpifu = (TextView) findViewById(R.id.yewanpifu);
	}
	
	
	
	public void setShezhi(TextView shezhi) {
	this.shezhi = shezhi;
	}
	public void setYewanpifu(TextView yewanpifu) {
	this.yewanpifu = yewanpifu;
	}
}
