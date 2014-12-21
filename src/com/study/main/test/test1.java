package com.study.main.test;

import java.util.ArrayList;
import java.util.List;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobQuery.CachePolicy;
import cn.bmob.v3.listener.FindListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.study.main.MainActivity;
import com.study.main.R;
import com.study.main.Entity.Isfavour;
import com.study.main.Entity.MainMsg;
import com.study.main.Entity.ShuoShuo;
import com.study.main.Entity.User;
import com.study.main.ui.User.commentActivity;
import com.study.main.ui.User.otherInfo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


//setRefreshing(boolean): 显示或隐藏刷新进度条 在请求跟新开始是 设置可见 请求结束后设置不可见
//isRefreshing(): 检查是否处于刷新状态 
//setColorScheme(): 设置进度条的颜色主题，最多能设置四种 默认是黑灰色 具体要和APP的整体配色融合

public class test1 extends Activity{

	private ListView main_listView1;
	List<MainMsg> MainMsgList=new ArrayList<MainMsg>();
	MainListAdapter adapter;
	private DisplayImageOptions options01;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.test1);
		
		options01 = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.bg_pic_loading)
		.showImageForEmptyUri(R.drawable.bg_pic_loading)
		.showImageOnFail(R.drawable.bg_pic_loading)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
	//	.displayer(new RoundedBitmapDisplayer(90))
		.build();
		
		adapter=new MainListAdapter(test1.this);
		main_listView1=(ListView) this.findViewById(R.id.main_listView1);	
		main_listView1.setAdapter(adapter);
		getData();
		
	}

	
	private void getData() {
		
		
		BmobQuery<MainMsg>  query=new BmobQuery<MainMsg>();
		query.setLimit(999);
		//query.setMaxCacheAge(100);
		query.setCachePolicy(CachePolicy.CACHE_ELSE_NETWORK);
		query.findObjects(test1.this, new FindListener<MainMsg>() {
			@Override
			public void onSuccess(List<MainMsg> arg0) {
				// TODO Auto-generated method stub
				MainMsgList.addAll(arg0);
				adapter.notifyDataSetChanged();
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
		});
	}








	private class MainListAdapter extends BaseAdapter  {		
		Context context;		
		
		public MainListAdapter(Context context){
			this.context = context;
		}
		@SuppressLint("InflateParams") @Override
		public View getView(final int position, View convertView,ViewGroup parent) {
			ViewHolder holder = null;
			final MainMsg mainMsg;
			
			if (convertView == null) {				
				convertView = LayoutInflater.from(context).inflate(R.layout.main_list1, null);
				
				holder = new ViewHolder();	
				holder.main_listitem_username = (TextView)convertView.findViewById(R.id.main_listitem_username);	
				holder.main_listitem_content = (TextView)convertView.findViewById(R.id.main_listitem_content);			
				holder.main_listitem_contentimage=(ImageView) convertView.findViewById(R.id.main_listitem_contentimage);
							
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			//1.Get data		
			mainMsg= (MainMsg) getItem(position);	
	
			holder.main_listitem_username.setText(mainMsg.getTitle());
			holder.main_listitem_content.setText(mainMsg.getContent());
			if(mainMsg.getContentfig()!=null){
				ImageLoader.getInstance().displayImage(mainMsg.getContentfig().getFileUrl(test1.this), holder.main_listitem_contentimage, options01,null);
			}
			return convertView;
		}
		class ViewHolder {
			public TextView main_listitem_username,main_listitem_content;
			public ImageView main_listitem_contentimage;
				
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return MainMsgList.size();
		}
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return MainMsgList.get(position);
		}
		
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
	}
	
	
	


}
