package com.study.main.test;

import com.study.main.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.Window;
import android.widget.Toast;


//setRefreshing(boolean): ��ʾ������ˢ�½����� ��������¿�ʼ�� ���ÿɼ� ������������ò��ɼ�
//isRefreshing(): ����Ƿ���ˢ��״̬ 
//setColorScheme(): ���ý���������ɫ���⣬������������� Ĭ���Ǻڻ�ɫ ����Ҫ��APP��������ɫ�ں�

public class test1 extends Activity implements OnRefreshListener {
	private SwipeRefreshLayout swip;
	Handler handler = new Handler(){  
	        @Override  
	        public void handleMessage(Message msg) {  
	            super.handleMessage(msg); 
	          //�����������ݺ󣬵���setRefreshing(false);���ر�ˢ�¡�
	            swip.setRefreshing(false);  
	        }  
	    };  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.test1);

		swip = (SwipeRefreshLayout) this.findViewById(R.id.swip);
		swip.setOnRefreshListener(this);
		
	}

	// ˢ�²���
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "��ˢ����һ��", Toast.LENGTH_LONG).show();
		  handler.sendEmptyMessageDelayed(1, 5000);  
		  
		  
	}

}
