package com.study.main;
 
import net.youmi.android.AdManager;
import net.youmi.android.offers.OffersManager;
import net.youmi.android.spot.SpotManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import com.study.main.Entity.User;
import com.study.main.ui.User.LoginAndRegister;
import com.study.main.utils.ConfigValue;

public class SplashActivity extends Activity{
	//1.延时5秒
	private static final long DELAY_TIME =1500L;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splashactivity);
		//2.初始化Bmob
		Bmob.initialize(this, ConfigValue.AppId);
		//3.初始化有米(记得关闭调试模式)
		AdManager.getInstance(this).init("baf3139c309608ca","13d5837e6a1eeab4", false);
		//开启有米功能
		OffersManager.getInstance(this).onAppLaunch();
		//开启插屏广告功能
		SpotManager.getInstance(this).loadSpotAds();
		ChangeUi();
		
	}
	/**
	 * 1.根据时间进行页面跳转
	 */
	private void ChangeUi() {
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				
				User currentUser=BmobUser.getCurrentUser(SplashActivity.this, User.class);
				if(currentUser!=null){
					
					Intent intent=new Intent();
					intent.setClass(SplashActivity.this, MainActivity.class);
					startActivity(intent);
					finish();
				}else{
					
					Intent intent=new Intent();
					intent.setClass(SplashActivity.this, LoginAndRegister.class);
					startActivity(intent);
					finish();
				}
					
			}
		
		}, DELAY_TIME);
	}

}
