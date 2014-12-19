package com.study.main.ui.User;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobQuery.CachePolicy;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.study.main.MainActivity;
import com.study.main.R;
import com.study.main.Entity.Favour;
import com.study.main.Entity.ShuoShuo;
import com.study.main.Entity.User;

//setRefreshing(boolean): 显示或隐藏刷新进度条 在请求跟新开始是 设置可见 请求结束后设置不可见
//isRefreshing(): 检查是否处于刷新状态 
//setColorScheme(): 设置进度条的颜色主题，最多能设置四种 默认是黑灰色 具体要和APP的整体配色融合

public class FavourActivity extends Activity{

	ImageView favour_activity_back;
	private ListView favour_listView1;
	DisplayImageOptions options,options2;
	List<ShuoShuo> FavourShuoshuoList=new ArrayList<ShuoShuo>();
	List<Favour> favoursList=new ArrayList<Favour>();
	UserInfoAdapter adapter;
	User currentUser;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.favour);
		
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.icon_profile)
		.showImageForEmptyUri(R.drawable.icon_profile)
		.showImageOnFail(R.drawable.icon_profile)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.displayer(new RoundedBitmapDisplayer(90))
		.build();
		options2= new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.icon_profile)
		.showImageForEmptyUri(R.drawable.icon_profile)
		.showImageOnFail(R.drawable.icon_profile)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		//.displayer(new RoundedBitmapDisplayer(90))
		.build();
		
		currentUser = BmobUser.getCurrentUser(FavourActivity.this, User.class);
		adapter=new UserInfoAdapter(FavourActivity.this);
		favour_listView1=(ListView) this.findViewById(R.id.favour_listView1);
		favour_activity_back=(ImageView) this.findViewById(R.id.favour_activity_back);
		favour_listView1.setAdapter(adapter);
		setLisenter();
		getData();
		
	}

	
	private void setLisenter() {
		// TODO Auto-generated method stub
		favour_activity_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				
			}
		});
	}


	private void getData() {
		final SweetAlertDialog sweetAlertDialog =new SweetAlertDialog(FavourActivity.this).setTitleText("正在获取收藏喔，请稍后...").setContentText("");
		sweetAlertDialog.show();
		BmobQuery<Favour>  query=new BmobQuery<Favour>();
		query.order("-createdAt");
		query.include("shuoshuo");
		//query.setCachePolicy(CachePolicy.CACHE_THEN_NETWORK);
		query.setLimit(999);
		query.addWhereEqualTo("user", currentUser);
		query.findObjects(FavourActivity.this, new FindListener<Favour>() {
			public void onSuccess(List<Favour> arg0) {
				favoursList.addAll(arg0);
				for (final Favour td : arg0) {
					
					BmobQuery<ShuoShuo> query=new BmobQuery<ShuoShuo>();
					//query.setCachePolicy(CachePolicy.CACHE_THEN_NETWORK);
					query.include("author");
					query.getObject(FavourActivity.this,td.getShuoshuo().getObjectId(), new GetListener<ShuoShuo>() {
						
						@Override
						public void onSuccess(ShuoShuo arg0) {
							sweetAlertDialog.dismiss();
							FavourShuoshuoList.add(arg0);
							adapter.notifyDataSetChanged();
						}
						
						@Override
						public void onFailure(int arg0, String arg1) {
							// TODO Auto-generated method stub
							
						}
					});
					//FavourShuoshuoList.add(td.getShuoshuo());
					
				}
				
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
			Log.e("1",author.toString());
		//	Log.e("2",author.getAvatar().getFileUrl(context));
			if(author.getAvatar()==null){			
			}else {	
			//	Log.e("",position+":"+ favoursList.get(position).getUser().getObjectId());
				ImageLoader.getInstance().displayImage(author.getAvatar().getFileUrl(FavourActivity.this), holder.list_item_user_logo, options,null);				
			}
			//2.userName
			if(author!=null){
			holder.list_item_user_name.setText(shuoshuo.getAuthor().getNickname());
			//3.userLogo
			holder.list_item_user_logo.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {									
						Intent intent=new Intent(FavourActivity.this, otherInfo.class);
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
				ImageLoader.getInstance().displayImage(shuoshuo.getContentfigureurl().getFileUrl(FavourActivity.this), holder.list_item_content_image, options2,null);			
			}else {
				holder.list_item_content_image.setVisibility(View.GONE);
			}
			//6.comment
			holder.list_item_action_comment.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(FavourActivity.this, commentActivity.class);
					intent.putExtra("data",shuoshuo);
					startActivity(intent);
				}
			});
			//7.time 
			holder.list_item_time.setText(shuoshuo.getCreatedAt());

			//8.favour
			holder.list_item_action_fav.setText("删除");
			holder.list_item_action_fav.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Favour favour=favoursList.get(position);					
					favour.delete(context, new DeleteListener() {

						public void onSuccess() {
							FavourShuoshuoList.clear();
							getData();	
							adapter.notifyDataSetChanged();
							Toast.makeText(FavourActivity.this, "取消收藏成功", Toast.LENGTH_LONG).show();	
						}
						
						@Override
						public void onFailure(int arg0, String arg1) {							
							Toast.makeText(FavourActivity.this, "取消收藏失败", Toast.LENGTH_LONG).show();		
						}
					});
					
				}
			});
			
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
			return FavourShuoshuoList.size();
		}
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return FavourShuoshuoList.get(position);
		}
		
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
	}
	
	
	


}
