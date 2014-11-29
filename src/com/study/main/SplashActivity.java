package com.study.main;
 
import com.study.main.utils.ConfigValue;

import cn.bmob.v3.Bmob;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class SplashActivity extends Activity{
	//1.延时5秒
	private static final long DELAY_TIME =1000L;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splashactivity);
		//2.初始化Bmob
		Bmob.initialize(this, ConfigValue.AppId);
		
		ChangeUi();
		
	}
	/**
	 * 1.根据时间进行页面跳转
	 */
	private void ChangeUi() {

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(SplashActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		}, DELAY_TIME);
	}

}
