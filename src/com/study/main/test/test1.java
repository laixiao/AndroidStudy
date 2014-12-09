package com.study.main.test;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.study.main.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;


//setRefreshing(boolean): ��ʾ������ˢ�½����� ��������¿�ʼ�� ���ÿɼ� ������������ò��ɼ�
//isRefreshing(): ����Ƿ���ˢ��״̬ 
//setColorScheme(): ���ý���������ɫ���⣬������������� Ĭ���Ǻڻ�ɫ ����Ҫ��APP��������ɫ�ں�

public class test1 extends Activity{
	private DisplayImageOptions options;//1.
	ImageLoader imageLoader = ImageLoader.getInstance();//2.
	
	
	 private ImageView imageView1;
	String url="http://file.bmob.cn/M00/D9/C0/oYYBAFSGnaWAay9AAACcMWNHV6k7828490";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.test1);
		 //3.
        options = new DisplayImageOptions.Builder()
		.displayer(new RoundedBitmapDisplayer(90)).build();
		imageView1=(ImageView) this.findViewById(R.id.text_image);
		
		//4.
		imageLoader.displayImage("http://file.bmob.cn/M00/D9/C0/oYYBAFSGnaWAay9AAACcMWNHV6k7828490",imageView1, options);
		
	}


}
