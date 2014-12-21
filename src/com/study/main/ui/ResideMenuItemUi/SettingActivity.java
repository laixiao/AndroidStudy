package com.study.main.ui.ResideMenuItemUi;

import net.youmi.android.offers.OffersManager;
import net.youmi.android.offers.PointsManager;
import net.youmi.android.spot.SpotManager;

import com.study.main.R;
import com.study.main.ui.media.DownloadListActivity;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class SettingActivity extends Activity {
	private ImageView setting_back;
	private Button setting_button1,setting_button2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.settingactivity);

		setting_back=(ImageView) this.findViewById(R.id.setting_back);
		setting_button1=(Button) this.findViewById(R.id.setting_button1);
		setting_button2=(Button) this.findViewById(R.id.setting_button2);
		//1.��ʾ�������
		SpotManager.getInstance(SettingActivity.this).showSpotAds(SettingActivity.this);
		setListener();
		
	}

	private void setListener() {
		setting_button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(SettingActivity.this, DownloadListActivity.class);
				startActivity(intent);
				
			}
		});
		setting_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				finish();
			}
		});
		setting_button2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {			
				int myPointBalance = PointsManager.getInstance(SettingActivity.this).queryPoints();
				Toast.makeText(SettingActivity.this, "��Ŀǰ�Ļ���"+myPointBalance, Toast.LENGTH_LONG).show();
				
				OffersManager.getInstance(SettingActivity.this).showOffersWall();
				
				
//				boolean isSuccess = PointsManager.getInstance(SettingActivity.this).spendPoints(10);
//				if(isSuccess){
//					Toast.makeText(SettingActivity.this, "���ֿ۳��ɹ�", Toast.LENGTH_LONG).show();
//				}else {
//					Toast.makeText(SettingActivity.this, "���ֲ���...", Toast.LENGTH_LONG).show();
//				}
				
//				boolean isSuccess = PointsManager.getInstance(SettingActivity.this).awardPoints(1000);
//				if(isSuccess){
//					Toast.makeText(SettingActivity.this, "�������ӳɹ�", Toast.LENGTH_LONG).show();
//				}else {
//					Toast.makeText(SettingActivity.this, "��������ʧ��", Toast.LENGTH_LONG).show();
//				}
				
				
			}
		});
	
	}
	@Override
	public void onBackPressed() {
	    // 2.�������Ҫ�����Ե�����˹رղ�����棨��ѡ����
	    if (!SpotManager.getInstance(this).disMiss(true)) {
	        super.onBackPressed();
	    }
	}

	@Override
	protected void onStop() {
	    //3.��������ô˷�������home����ʱ������ͼ���޷���ʾ�������
	    SpotManager.getInstance(this).disMiss(false);
	    super.onStop();
	}
		//3.ע���㲥
	protected void onDestroy() {
        SpotManager.getInstance(this) .unregisterSceenReceiver();
        super.onDestroy();
	}
	
	
	
}
