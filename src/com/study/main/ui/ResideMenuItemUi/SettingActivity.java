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
					Toast.makeText(SettingActivity.this, "��Ŀǰ�Ļ���"+myPointBalance, Toast.LENGTH_LONG).show();
					//1.��ʾ�������
					SpotManager.getInstance(SettingActivity.this).showSpotAds(SettingActivity.this);
					OffersManager.getInstance(SettingActivity.this).showOffersWall();
					
					
//					boolean isSuccess = PointsManager.getInstance(SettingActivity.this).spendPoints(10);
//					if(isSuccess){
//						Toast.makeText(SettingActivity.this, "���ֿ۳��ɹ�", Toast.LENGTH_LONG).show();
//					}else {
//						Toast.makeText(SettingActivity.this, "���ֲ���...", Toast.LENGTH_LONG).show();
//					}
					
//					boolean isSuccess = PointsManager.getInstance(SettingActivity.this).awardPoints(1000);
//					if(isSuccess){
//						Toast.makeText(SettingActivity.this, "�������ӳɹ�", Toast.LENGTH_LONG).show();
//					}else {
//						Toast.makeText(SettingActivity.this, "��������ʧ��", Toast.LENGTH_LONG).show();
//					}
					
					break;
				case 2:
					new SweetAlertDialog(SettingActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
	                .setTitleText("������Ϣ")
	                .setContentText("QQ��2515097216\n���䣺2515097216@qq.com\n������www\n���������˴�ѧ��һö����֪������������Ȩ������ϵ��Ŷ��")
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
           map.put("main_list_textView1", "���ع���");  
           map.put("main_list_imageView1", R.drawable.residebutton1);  
           list.add(map);  
           
           map = new HashMap<String, Object>();          
           map.put("main_list_textView1", "��ȡ����");  
           map.put("main_list_imageView1", R.drawable.residebutton2);  
           list.add(map);  
   
           map = new HashMap<String, Object>();          
           map.put("main_list_textView1", "��������");  
           map.put("main_list_imageView1", R.drawable.residebutton5);  
           list.add(map);  
       return list;  
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
