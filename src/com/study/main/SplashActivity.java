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
	//1.��ʱ5��
	private static final long DELAY_TIME =1500L;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splashactivity);
		//2.��ʼ��Bmob
		Bmob.initialize(this, ConfigValue.AppId);
		//3.��ʼ������(�ǵùرյ���ģʽ)
		AdManager.getInstance(this).init("baf3139c309608ca","13d5837e6a1eeab4", false);
		//�������׹���
		OffersManager.getInstance(this).onAppLaunch();
		//����������湦��
		SpotManager.getInstance(this).loadSpotAds();
		ChangeUi();
		
	}
	/**
	 * 1.����ʱ�����ҳ����ת
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
