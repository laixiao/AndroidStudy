package com.study.main.ui.ResideMenuItemUi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SettingActivity extends Activity {
	private ImageView setting_back;
	 List<Map<String, Object>> list;  
	private ListView setting_listView1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.settingactivity);

		setting_back=(ImageView) this.findViewById(R.id.setting_back);
		setting_listView1=(ListView) this.findViewById(R.id.setting_listView1);

		
		setListener();
		
		list=getListForSimpleAdapter();
		SimpleAdapter simpleAdapter=new SimpleAdapter(SettingActivity.this, list, R.layout.setting_list_item01,
				new String[]{"main_list_imageView1","main_list_textView1"}, new int[]{R.id.main_list_imageView1,R.id.main_list_textView1});
		setting_listView1.setAdapter(simpleAdapter);
		
	}

	private void setListener() {

		setting_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				finish();
			}
		});
		setting_listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					Intent intent=new Intent(SettingActivity.this, DownloadListActivity.class);
					startActivity(intent);
					break;
				case 1:
					int myPointBalance = PointsManager.getInstance(SettingActivity.this).queryPoints();
					Toast.makeText(SettingActivity.this, "您目前的积分"+myPointBalance, Toast.LENGTH_LONG).show();
					//1.显示插屏广告
					SpotManager.getInstance(SettingActivity.this).showSpotAds(SettingActivity.this);
					OffersManager.getInstance(SettingActivity.this).showOffersWall();
					
					
//					boolean isSuccess = PointsManager.getInstance(SettingActivity.this).spendPoints(10);
//					if(isSuccess){
//						Toast.makeText(SettingActivity.this, "积分扣除成功", Toast.LENGTH_LONG).show();
//					}else {
//						Toast.makeText(SettingActivity.this, "积分不足...", Toast.LENGTH_LONG).show();
//					}
					
//					boolean isSuccess = PointsManager.getInstance(SettingActivity.this).awardPoints(1000);
//					if(isSuccess){
//						Toast.makeText(SettingActivity.this, "积分增加成功", Toast.LENGTH_LONG).show();
//					}else {
//						Toast.makeText(SettingActivity.this, "积分增加失败", Toast.LENGTH_LONG).show();
//					}
					
					break;
				case 2:
					new SweetAlertDialog(SettingActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
	                .setTitleText("作者信息")
	                .setContentText("QQ：2515097216\n邮箱：2515097216@qq.com\n官网：www\n声明：本人大学生一枚，不知世道，如有侵权，请联系我哦。")
	                .setCustomImage(R.drawable.ic_launcher)	              	              
	                .setCancelClickListener(new OnSweetClickListener() {
						public void onClick(SweetAlertDialog sweetAlertDialog) {
							
							sweetAlertDialog.dismiss();
						}
					})				
	                .show();
					
					break;

				default:
					break;
				}
			}
		});


	
	}
	
	
	
	
    private List<Map<String, Object>> getListForSimpleAdapter() {  
    	List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();  
           Map<String, Object> map = new HashMap<String, Object>();           
           map.put("main_list_textView1", "下载管理");  
           map.put("main_list_imageView1", R.drawable.residebutton1);  
           list.add(map);  
           
           map = new HashMap<String, Object>();          
           map.put("main_list_textView1", "获取积分");  
           map.put("main_list_imageView1", R.drawable.residebutton2);  
           list.add(map);  
   
           map = new HashMap<String, Object>();          
           map.put("main_list_textView1", "关于作者");  
           map.put("main_list_imageView1", R.drawable.residebutton5);  
           list.add(map);  
       return list;  
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
