package com.study.main.ui.ResideMenuItemUi;

import net.youmi.android.offers.OffersManager;
import net.youmi.android.offers.PointsManager;
import net.youmi.android.spot.SpotManager;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener;
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
	private Button setting_button1,setting_button2,setting_button3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.settingactivity);

		setting_back=(ImageView) this.findViewById(R.id.setting_back);
		setting_button1=(Button) this.findViewById(R.id.setting_button1);
		setting_button2=(Button) this.findViewById(R.id.setting_button2);
		setting_button3=(Button) this.findViewById(R.id.setting_button3);
		//1.显示插屏广告
		SpotManager.getInstance(SettingActivity.this).showSpotAds(SettingActivity.this);
		setListener();
		
	}

	private void setListener() {
		//1.
		setting_button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(SettingActivity.this, DownloadListActivity.class);
				startActivity(intent);
				
			}
		});
		//2.
		setting_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				finish();
			}
		});
		//3.
		setting_button2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {			
				int myPointBalance = PointsManager.getInstance(SettingActivity.this).queryPoints();
				Toast.makeText(SettingActivity.this, "您目前的积分"+myPointBalance, Toast.LENGTH_LONG).show();
				
				OffersManager.getInstance(SettingActivity.this).showOffersWall();
				
				
//				boolean isSuccess = PointsManager.getInstance(SettingActivity.this).spendPoints(10);
//				if(isSuccess){
//					Toast.makeText(SettingActivity.this, "积分扣除成功", Toast.LENGTH_LONG).show();
//				}else {
//					Toast.makeText(SettingActivity.this, "积分不足...", Toast.LENGTH_LONG).show();
//				}
				
//				boolean isSuccess = PointsManager.getInstance(SettingActivity.this).awardPoints(1000);
//				if(isSuccess){
//					Toast.makeText(SettingActivity.this, "积分增加成功", Toast.LENGTH_LONG).show();
//				}else {
//					Toast.makeText(SettingActivity.this, "积分增加失败", Toast.LENGTH_LONG).show();
//				}
				
				
			}
		});
		
		//4.
		setting_button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new SweetAlertDialog(SettingActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                .setTitleText("选择相片")
                .setContentText("")
                .setCustomImage(R.drawable.ic_launcher)
                .showCancelButton(true)
                .setCancelText("相机拍照")
                .setConfirmText("本地相册")
                .setCancelClickListener(new OnSweetClickListener() {
					
					@Override
					public void onClick(SweetAlertDialog sweetAlertDialog) {
						// TODO Auto-generated method stub
						Toast.makeText(SettingActivity.this, "相机拍照", Toast.LENGTH_LONG).show();
						sweetAlertDialog.dismiss();
					}
				})
				.setConfirmClickListener(new OnSweetClickListener() {
					
					@Override
					public void onClick(SweetAlertDialog sweetAlertDialog) {
						// TODO Auto-generated method stub
						Toast.makeText(SettingActivity.this, "本地相册", Toast.LENGTH_LONG).show();
						sweetAlertDialog.dismiss();
					}
				})
                .show();
			}
		});
	
	}
	@Override
	public void onBackPressed() {
	    // 2.如果有需要，可以点击后退关闭插屏广告（可选）。
	    if (!SpotManager.getInstance(this).disMiss(true)) {
	        super.onBackPressed();
	    }
	}

	@Override
	protected void onStop() {
	    //3.如果不调用此方法，则按home键的时候会出现图标无法显示的情况。
	    SpotManager.getInstance(this).disMiss(false);
	    super.onStop();
	}
		//3.注销广播
	protected void onDestroy() {
        SpotManager.getInstance(this) .unregisterSceenReceiver();
        super.onDestroy();
	}
	
	
	
}
