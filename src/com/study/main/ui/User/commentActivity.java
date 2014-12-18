package com.study.main.ui.User;
import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.study.main.R;
import com.study.main.Entity.ShuoShuo;
import com.study.main.Entity.User;
import com.study.main.Entity.Comment;

public class commentActivity extends Activity {

	private ListView comment_listView1;
	List<Comment> commentList = new ArrayList<Comment>();
	firstListAdapter adapter;
	private ImageView comment_activity_back;
	private EditText comment_content;
	private Button comment_commit;
	Comment comment;
	ShuoShuo shuoshuo;
	User currentUser;
	DisplayImageOptions options;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.commentactivity);

		adapter=new firstListAdapter(commentActivity.this);
		currentUser=BmobUser.getCurrentUser(commentActivity.this, User.class);
		comment_listView1 = (ListView) this.findViewById(R.id.comment_listView1);
		comment_activity_back=(ImageView) this.findViewById(R.id.comment_activity_back);
		comment_content=(EditText) this.findViewById(R.id.comment_content);
		comment_commit=(Button) this.findViewById(R.id.comment_commit);
		shuoshuo= (ShuoShuo) getIntent().getSerializableExtra("data");
		
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.icon_profile)
		.showImageForEmptyUri(R.drawable.icon_profile)
		.showImageOnFail(R.drawable.icon_profile)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.displayer(new RoundedBitmapDisplayer(90))
		.build();
		
		fetch();
		
		comment_listView1.setAdapter(adapter);
		
		setListener();
	
	}

	private void setListener() {
		//1.back
		comment_activity_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		//2.comment_commit
	comment_commit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				comment=new Comment();
				comment.setShuoshuo(shuoshuo);
				comment.setContent(comment_content.getText().toString());
				comment.setUser(currentUser);
				comment.save(commentActivity.this, new SaveListener() {
					
					@Override
					public void onSuccess() {
						new SweetAlertDialog(commentActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("评论成功")
                        .setContentText("")
                        .show();
						
						commentList.clear();
						fetch();
						
						//comment.delete(commentActivity.this);
					}
					
					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO Auto-generated method stub
						new SweetAlertDialog(commentActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("评论失败")
                        .setContentText("原因："+arg1)
                        .show();
					}
				});
				
				
			}
		});
	}

	private void fetch() {
		// TODO Auto-generated method stub
		BmobQuery<Comment> query = new BmobQuery<Comment>();
		query.addWhereEqualTo("shuoshuo", shuoshuo);
		query.include("user");
		query.setLimit(999);
		query.findObjects(commentActivity.this, new FindListener<Comment>() {
			@Override
			public void onSuccess(List<Comment> arg0) {
				// TODO Auto-generated method stub
				commentList.addAll(arg0);
				adapter.notifyDataSetChanged();
			}

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub

			}
		});
	}

	@SuppressWarnings("unused")
	private class firstListAdapter extends BaseAdapter {
		Context context;

		public firstListAdapter(Context context) {
			this.context = context;
		}

		public View getView(final int position, View convertView,ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(R.layout.comment_list, null);
				holder = new ViewHolder();
				holder.comment_list_name = (TextView) convertView.findViewById(R.id.comment_list_name);
				holder.comment_list_time = (TextView) convertView.findViewById(R.id.comment_list_time);
				holder.comment_list_content = (TextView) convertView.findViewById(R.id.comment_list_content);
				holder.comment_list_index = (TextView) convertView.findViewById(R.id.comment_list_index);
				holder.list_item_user_logo=(ImageView) convertView.findViewById(R.id.list_item_user_logo);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			Comment comment=(Comment) getItem(position);
			
			holder.comment_list_name.setText(comment.getUser().getNickname());
			holder.comment_list_time.setText(comment.getCreatedAt()+"");
			holder.comment_list_content.setText(comment.getContent());
			holder.comment_list_index.setText(position+"楼");
			ImageLoader.getInstance().displayImage(comment.getUser().getAvatar().getFileUrl(context), holder.list_item_user_logo, options,null);
			return convertView;
		}

		class ViewHolder {
			public TextView comment_list_name, comment_list_time, comment_list_content,comment_list_index;
			public ImageView list_item_user_logo;

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return commentList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return commentList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

	}

}
