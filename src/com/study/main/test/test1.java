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


//setRefreshing(boolean): 显示或隐藏刷新进度条 在请求跟新开始是 设置可见 请求结束后设置不可见
//isRefreshing(): 检查是否处于刷新状态 
//setColorScheme(): 设置进度条的颜色主题，最多能设置四种 默认是黑灰色 具体要和APP的整体配色融合

public class test1 extends Activity implements OnRefreshListener {
	private SwipeRefreshLayout swip;
	Handler handler = new Handler(){  
	        @Override  
	        public void handleMessage(Message msg) {  
	            super.handleMessage(msg); 
	          //当更新完数据后，调用setRefreshing(false);来关闭刷新。
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

	// 刷新操作
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "我刷新了一次", Toast.LENGTH_LONG).show();
		  handler.sendEmptyMessageDelayed(1, 5000);  
		  
		  
	}

}
