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


//setRefreshing(boolean): 显示或隐藏刷新进度条 在请求跟新开始是 设置可见 请求结束后设置不可见
//isRefreshing(): 检查是否处于刷新状态 
//setColorScheme(): 设置进度条的颜色主题，最多能设置四种 默认是黑灰色 具体要和APP的整体配色融合

public class test1 extends Activity{
	 DisplayImageOptions options;
	 private ImageView imageView1;
	String url="http://file.bmob.cn/M00/D7/A0/oYYBAFSDBkiATQ3rAAA2S60H7pA2786353";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.test1);

		imageView1=(ImageView) this.findViewById(R.id.imageView1);
		
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.icon_profile)
		.showImageForEmptyUri(R.drawable.icon_profile)
		.showImageOnFail(R.drawable.icon_profile)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		//.displayer(new RoundedBitmapDisplayer(90))
		.build();
		// TODO Auto-generated constructor stub
		
		ImageLoader.getInstance().displayImage(url, imageView1,options);
		
	}


}
