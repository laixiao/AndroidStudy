package com.study.main.ui.Simple;

import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.study.main.R;
import com.study.main.Entity.laoluo_child;
import com.study.main.Entity.laoluo_parent;
import com.study.main.adapter.MyApplication;
import com.study.main.ui.media.DownloadManager;
import com.study.main.ui.media.DownloadService;
import com.study.main.ui.media.localVideoView;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobQuery.CachePolicy;
import cn.bmob.v3.listener.FindListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class laoluoActivity extends Activity {
	List<laoluo_parent> groupData=new ArrayList<laoluo_parent>();
	// 存放父列表数据
	//List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
	List<List<laoluo_child>> childData=new ArrayList<List<laoluo_child>>();
	// 放子列表列表数据
	//List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
	
	private ExpandableListView expandableListView1;
	ExpandableAdapter expandableAdapter;
	  private DownloadManager downloadManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.laoluo_main);

		initData();
		init();

	}
	private void init() {
		// TODO Auto-generated method stub
		expandableListView1 = (ExpandableListView) this.findViewById(R.id.expandableListView1);
		View v=new View(this);
		expandableListView1.addHeaderView(v);
		
		expandableAdapter=new ExpandableAdapter(this);
		expandableListView1.setAdapter(expandableAdapter);
		expandableListView1.setGroupIndicator(null);
		
		downloadManager = DownloadService.getDownloadManager(laoluoActivity.this);
	}

	private void initData() {	
		BmobQuery<laoluo_parent> query0=new BmobQuery<laoluo_parent>();
		//query0.setCachePolicy(CachePolicy.CACHE_THEN_NETWORK);
		query0.findObjects(laoluoActivity.this, new FindListener<laoluo_parent>() {
			
			@Override
			public void onSuccess(List<laoluo_parent> arg0) {
				// TODO Auto-generated method stub
				groupData.addAll(arg0);
				expandableAdapter.notifyDataSetChanged();
				Toast.makeText(laoluoActivity.this, "成功1", Toast.LENGTH_LONG).show();
				
				//子1查询
				final List<laoluo_child> children1 = new ArrayList<laoluo_child>();
				BmobQuery<laoluo_child> query1=new BmobQuery<laoluo_child>();
				query1.addWhereEqualTo("parent", "L5Xp1116");
			//	query1.setCachePolicy(CachePolicy.CACHE_THEN_NETWORK);
				query1.findObjects(laoluoActivity.this, new FindListener<laoluo_child>() {
					
					public void onSuccess(List<laoluo_child> arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(laoluoActivity.this, "成功2", Toast.LENGTH_LONG).show();
						children1.addAll(arg0);
						childData.add(children1);
						expandableAdapter.notifyDataSetChanged();
												
						//子2查询
						final List<laoluo_child> children2 = new ArrayList<laoluo_child>();
						BmobQuery<laoluo_child> query2=new BmobQuery<laoluo_child>();
						query2.addWhereEqualTo("parent", "kmgL555P");
					//	query2.setCachePolicy(CachePolicy.CACHE_THEN_NETWORK);
						query2.findObjects(laoluoActivity.this, new FindListener<laoluo_child>() {				
							public void onSuccess(List<laoluo_child> arg0) {
								// TODO Auto-generated method stub
								children2.addAll(arg0);
								childData.add(children2);
								expandableAdapter.notifyDataSetChanged();
							}							
							@Override
							public void onError(int arg0, String arg1) {
								// TODO Auto-generated method stub
								
							}
						});	
						
					}
					
					@Override
					public void onError(int arg0, String arg1) {
						// TODO Auto-generated method stub
						
					}
				});
				
				
				
				//expandableAdapter.notifyDataSetChanged();
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(laoluoActivity.this, "失败"+arg1, Toast.LENGTH_LONG).show();
			}
		});
		
	}
	
	//适配器
	class ExpandableAdapter extends BaseExpandableListAdapter {
		laoluoActivity exlistview;
		@SuppressWarnings("unused")
		private int mHideGroupPos = -1;

		public ExpandableAdapter(laoluoActivity elv) {
			super();
			exlistview = elv;
		}
//=========1.子布局设置========
		
		//获取子数据
		public Object getChild(int groupPosition, int childPosition) {
			return childData.get(groupPosition).get(childPosition);
		}
		//获取子ID
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}
		//获取子数量
		public int getChildrenCount(int groupPosition) {
		return childData.get(groupPosition).size();
		}
		//获取子布局
		public View getChildView(int groupPosition, int childPosition,boolean isLastChild, View convertView, ViewGroup parent) {
			View view = convertView;
			if (view == null) {
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);//布局填充器
				view = inflater.inflate(R.layout.childitem, null);//填充布局
			}
			final laoluo_child entity=(laoluo_child) getChild(groupPosition, childPosition);
			
			ImageView child_image=(ImageView) view.findViewById(R.id.child_image);
//			if(entity.getIco().getFileUrl()!=null){
//			ImageLoader.getInstance().displayImage(
//					entity.getIco().getFileUrl(),
//					child_image,
//					MyApplication.getInstance().getOptions(
//							R.drawable.child01),
//					new SimpleImageLoadingListener() {
//
//						@Override
//						public void onLoadingComplete(String imageUri, View view,
//								Bitmap loadedImage) {
//							// TODO Auto-generated method stub
//							super.onLoadingComplete(imageUri, view, loadedImage);
//						}
//
//					});
//			}
			child_image.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Toast.makeText(laoluoActivity.this, "开始下载"+entity.getUrl(), Toast.LENGTH_LONG).show();
					String filePath = Environment.getExternalStorageDirectory()+"/AndroidStudy/"+ entity.getName();
			        try {
			            downloadManager.addNewDownload(entity.getUrl(),
			                    null,
			                    filePath,
			                    true, // 如果目标文件存在，接着未完成的部分继续下载。服务器不支持RANGE时将从新下载。
			                    false, // 如果从请求返回信息中获取到文件名，下载完成后自动重命名。
			                    null);
			        } catch (DbException e) {
			            LogUtils.e(e.getMessage(), e);
			        }
					Intent intent=new Intent(laoluoActivity.this,DownloadListActivity.class);
					startActivity(intent);
				}
			});
			
			//设置数据
			final TextView title = (TextView) view.findViewById(R.id.child_text);
			title.setText(entity.getName());
			title.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 Intent intent = new Intent();
					 intent.putExtra("path", entity.getUrl());
					 intent.setClass(laoluoActivity.this, localVideoView.class);
					 startActivity(intent);
				}
			});
			//title.setText(childData.get(groupPosition).get(childPosition).get("child_text1").toString());
			final TextView title2 = (TextView) view.findViewById(R.id.child_text2);
			title2.setText(entity.getContent());
			//title2.setText(childData.get(groupPosition).get(childPosition).get("child_text2").toString());
			return view;//返回子布局
		}
//=========2.父布局设置=======
		
		//获取父布局
		public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent) {
			View view = convertView;
			if (view == null) {
				LayoutInflater inflaterGroup = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);//布局填充器
				view = inflaterGroup.inflate(R.layout.groupitem, null);//填充布局
			}
			//设置数据
			TextView title = (TextView)view.findViewById(R.id.content_001);
			laoluo_parent entity=(laoluo_parent) getGroup(groupPosition);
			title.setText(entity.getName());
			
			ImageView image = (ImageView) view.findViewById(R.id.tubiao);
			System.out.println("isExpanded----->" + isExpanded);
			if (isExpanded) {
				image.setBackgroundResource(R.drawable.btn_browser2);
			} else {
				image.setBackgroundResource(R.drawable.btn_browser);
			}
			return view;
		}
		//获取父ID
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}
		//获取父数据
		public Object getGroup(int groupPosition) {
			return groupData.get(groupPosition);
		}
		//获取父数量
		public int getGroupCount() {
			return groupData.size();

		}
		//表明孩子和组id是否稳定在底层数据的变化。
		public boolean hasStableIds() {
			return true;
		}
		//选中事件
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return false;
		}
		//隐藏父
		public void hideGroup(int groupPos) {
			mHideGroupPos = groupPos;
		}
	}
	
	
}

