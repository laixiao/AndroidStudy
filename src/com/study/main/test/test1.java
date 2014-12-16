package com.study.main.test;

import java.util.ArrayList;
import java.util.List;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.study.main.R;
import com.study.main.Entity.Isfavour;
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

	private ListView user_info_listView1;
	DisplayImageOptions options,options2;
	List<ShuoShuo> shuoshuoList=new ArrayList<ShuoShuo>();
	UserInfoAdapter adapter;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.test1);
		
		adapter=new UserInfoAdapter(test1.this);
		user_info_listView1=(ListView) this.findViewById(R.id.user_info_listView1);	
		user_info_listView1.setAdapter(adapter);
		getData();
		
	}

	
	private void getData() {
		BmobQuery<ShuoShuo>  query=new BmobQuery<ShuoShuo>();
		query.setLimit(999);
		query.findObjects(test1.this, new FindListener<ShuoShuo>() {
			
			@Override
			public void onSuccess(List<ShuoShuo> arg0) {
				// TODO Auto-generated method stub
				shuoshuoList.addAll(arg0);
				adapter.notifyDataSetChanged();
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
		});
	}








	private class UserInfoAdapter extends BaseAdapter  {		
		Context context;		
		
		public UserInfoAdapter(Context context){
			this.context = context;
		}
		@SuppressLint("InflateParams") @Override
		public View getView(final int position, View convertView,ViewGroup parent) {
			ViewHolder holder = null;
			final ShuoShuo shuoshuo;
			Isfavour isfavour;
			if (convertView == null) {				
				convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
				holder = new ViewHolder();	
				holder.list_item_user_name = (TextView)convertView.findViewById(R.id.list_item_user_name);
				holder.list_item_user_logo = (ImageView)convertView.findViewById(R.id.list_item_user_logo);
				holder.list_item_action_fav = (TextView)convertView.findViewById(R.id.list_item_action_fav);
				holder.list_item_content_text = (TextView)convertView.findViewById(R.id.list_item_content_text);
				holder.list_item_content_image = (ImageView)convertView.findViewById(R.id.list_item_content_image);			
				holder.list_item_action_comment = (TextView)convertView.findViewById(R.id.list_item_action_comment);
				holder.list_item_time=(TextView) convertView.findViewById(R.id.list_item_time);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			//1.Get QiangYu
			
			shuoshuo= (ShuoShuo) getItem(position);	
			final User author=shuoshuo.getAuthor();
			if(author==null){
				Toast.makeText(test1.this, position+"user is null", Toast.LENGTH_LONG).show();
			}else if(author.getAvatar()==null){
				Toast.makeText(test1.this, position+"Avatar is null", Toast.LENGTH_LONG).show();
			}else {				
				ImageLoader.getInstance().displayImage(author.getAvatar().getFileUrl(test1.this), holder.list_item_user_logo, options,null);				
			}
			//2.userName
			if(author!=null){
			holder.list_item_user_name.setText(shuoshuo.getAuthor().getNickname());
			//3.userLogo
			holder.list_item_user_logo.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {									
						Intent intent=new Intent(test1.this, otherInfo.class);
						intent.putExtra("data",author);
						startActivity(intent);					
				}	
			});
			}
			//4.contentText
			if(shuoshuo.getContent()!=null){
				holder.list_item_content_text.setVisibility(View.VISIBLE);
			holder.list_item_content_text.setText(shuoshuo.getContent());
			}else{
				holder.list_item_content_text.setVisibility(View.INVISIBLE);
			}
			//5.Contentfigureurl
			if(shuoshuo.getContentfigureurl()!=null){
				holder.list_item_content_image.setVisibility(View.VISIBLE);
				ImageLoader.getInstance().displayImage(shuoshuo.getContentfigureurl().getFileUrl(test1.this), holder.list_item_content_image, options,null);			
			}else {
				holder.list_item_content_image.setVisibility(View.GONE);
			}
			//6.comment
			holder.list_item_action_comment.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(test1.this, commentActivity.class);
					intent.putExtra("data",shuoshuo);
					startActivity(intent);
				}
			});
			//7.time 
			holder.list_item_time.setText(shuoshuo.getCreatedAt());

			return convertView;
		}
		class ViewHolder {
			public TextView list_item_time;
			public TextView list_item_action_comment;
			public ImageView list_item_content_image;
			public TextView list_item_content_text;
			public TextView list_item_user_name;
			public TextView list_item_action_fav;
			public ImageView list_item_user_logo;
			TextView list_item_textView1;			
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return shuoshuoList.size();
		}
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return shuoshuoList.get(position);
		}
		
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
	}
	
	
	


}
