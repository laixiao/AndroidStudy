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
	// ��Ÿ��б�����
	//List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
	List<List<laoluo_child>> childData=new ArrayList<List<laoluo_child>>();
	// �����б��б�����
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
				Toast.makeText(laoluoActivity.this, "�ɹ�1", Toast.LENGTH_LONG).show();
				
				//��1��ѯ
				final List<laoluo_child> children1 = new ArrayList<laoluo_child>();
				BmobQuery<laoluo_child> query1=new BmobQuery<laoluo_child>();
				query1.addWhereEqualTo("parent", "L5Xp1116");
			//	query1.setCachePolicy(CachePolicy.CACHE_THEN_NETWORK);
				query1.findObjects(laoluoActivity.this, new FindListener<laoluo_child>() {
					
					public void onSuccess(List<laoluo_child> arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(laoluoActivity.this, "�ɹ�2", Toast.LENGTH_LONG).show();
						children1.addAll(arg0);
						childData.add(children1);
						expandableAdapter.notifyDataSetChanged();
												
						//��2��ѯ
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
				Toast.makeText(laoluoActivity.this, "ʧ��"+arg1, Toast.LENGTH_LONG).show();
			}
		});
		
	}
	
	//������
	class ExpandableAdapter extends BaseExpandableListAdapter {
		laoluoActivity exlistview;
		@SuppressWarnings("unused")
		private int mHideGroupPos = -1;

		public ExpandableAdapter(laoluoActivity elv) {
			super();
			exlistview = elv;
		}
//=========1.�Ӳ�������========
		
		//��ȡ������
		public Object getChild(int groupPosition, int childPosition) {
			return childData.get(groupPosition).get(childPosition);
		}
		//��ȡ��ID
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}
		//��ȡ������
		public int getChildrenCount(int groupPosition) {
		return childData.get(groupPosition).size();
		}
		//��ȡ�Ӳ���
		public View getChildView(int groupPosition, int childPosition,boolean isLastChild, View convertView, ViewGroup parent) {
			View view = convertView;
			if (view == null) {
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);//���������
				view = inflater.inflate(R.layout.childitem, null);//��䲼��
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
					Toast.makeText(laoluoActivity.this, "��ʼ����"+entity.getUrl(), Toast.LENGTH_LONG).show();
					String filePath = Environment.getExternalStorageDirectory()+"/AndroidStudy/"+ entity.getName();
			        try {
			            downloadManager.addNewDownload(entity.getUrl(),
			                    null,
			                    filePath,
			                    true, // ���Ŀ���ļ����ڣ�����δ��ɵĲ��ּ������ء���������֧��RANGEʱ���������ء�
			                    false, // ��������󷵻���Ϣ�л�ȡ���ļ�����������ɺ��Զ���������
			                    null);
			        } catch (DbException e) {
			            LogUtils.e(e.getMessage(), e);
			        }
					Intent intent=new Intent(laoluoActivity.this,DownloadListActivity.class);
					startActivity(intent);
				}
			});
			
			//��������
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
			return view;//�����Ӳ���
		}
//=========2.����������=======
		
		//��ȡ������
		public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent) {
			View view = convertView;
			if (view == null) {
				LayoutInflater inflaterGroup = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);//���������
				view = inflaterGroup.inflate(R.layout.groupitem, null);//��䲼��
			}
			//��������
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
		//��ȡ��ID
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}
		//��ȡ������
		public Object getGroup(int groupPosition) {
			return groupData.get(groupPosition);
		}
		//��ȡ������
		public int getGroupCount() {
			return groupData.size();

		}
		//�������Ӻ���id�Ƿ��ȶ��ڵײ����ݵı仯��
		public boolean hasStableIds() {
			return true;
		}
		//ѡ���¼�
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return false;
		}
		//���ظ�
		public void hideGroup(int groupPos) {
			mHideGroupPos = groupPos;
		}
	}
	
	
}

