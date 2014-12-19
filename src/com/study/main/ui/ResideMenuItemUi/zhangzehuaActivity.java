package com.study.main.ui.ResideMenuItemUi;

import java.util.ArrayList;
import java.util.List;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;
import com.study.main.R;
import com.study.main.Entity.ZhangzehuaChild;
import com.study.main.Entity.ZhangzehuaParent;
import com.study.main.ui.Simple.DownloadListActivity;
import com.study.main.ui.User.FavourActivity;
import com.study.main.ui.media.DownloadManager;
import com.study.main.ui.media.DownloadService;
import com.study.main.ui.media.localVideoView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobQuery.CachePolicy;
import cn.bmob.v3.listener.FindListener;
import cn.pedant.SweetAlert.SweetAlertDialog;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
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

public class zhangzehuaActivity extends Activity {
	List<ZhangzehuaParent> groupData=new ArrayList<ZhangzehuaParent>();
	List<List<ZhangzehuaChild>> childData=new ArrayList<List<ZhangzehuaChild>>();
	
	private ExpandableListView expandableListView1;
	ExpandableAdapter expandableAdapter;
	private DownloadManager downloadManager;
	private ImageView laoluo_back;  
	private SwipeRefreshLayout laoluo_swip;
	BmobQuery.CachePolicy cachePolicy;
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {  
            super.handleMessage(msg); 
          //�����������ݺ󣬵���setRefreshing(false);���ر�ˢ�¡�
            laoluo_swip.setRefreshing(false);  
        }  
    };  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.zhangzehuaactivity);
		init();
		initData();

	}
	private void init() {
		// TODO Auto-generated method stub
		expandableListView1 = (ExpandableListView) this.findViewById(R.id.zhangzehua_expandableListView1);
		laoluo_back=(ImageView) this.findViewById(R.id.zhangzehua_back);
		laoluo_swip=(SwipeRefreshLayout) this.findViewById(R.id.zhangzehua_swip);
		View v=new View(this);
		expandableListView1.addHeaderView(v);		
		expandableAdapter=new ExpandableAdapter(this);
		expandableListView1.setAdapter(expandableAdapter);
		expandableListView1.setGroupIndicator(null);
		
		downloadManager = DownloadService.getDownloadManager(zhangzehuaActivity.this);	
		cachePolicy=CachePolicy.CACHE_ELSE_NETWORK;
		
		
		//1.back
		laoluo_back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
				
			}
		});
		//2.refresh
		laoluo_swip.setOnRefreshListener(new OnRefreshListener() {
			public void onRefresh() {				
				cachePolicy=CachePolicy.NETWORK_ONLY;
				groupData.clear();
				childData.clear();
				initData();
				handler.sendEmptyMessageDelayed(1, 3000);  
			}
		});
	}

	private void initData() {
		final SweetAlertDialog sweetAlertDialog =new SweetAlertDialog(zhangzehuaActivity.this).setTitleText("���ڻ�ȡ���ݣ����Ժ�...").setContentText("");
		sweetAlertDialog.show();
		//����ѯ
		BmobQuery<ZhangzehuaParent> query0=new BmobQuery<ZhangzehuaParent>();
		query0.setCachePolicy(cachePolicy);
		query0.setLimit(99);
		query0.order("sortid");
		query0.findObjects(zhangzehuaActivity.this, new FindListener<ZhangzehuaParent>() {
			public void onSuccess(List<ZhangzehuaParent> arg0) {
				groupData.addAll(arg0);
				
				
				//��1��ѯ
				final List<ZhangzehuaChild> children1 = new ArrayList<ZhangzehuaChild>();
				BmobQuery<ZhangzehuaChild> query1=new BmobQuery<ZhangzehuaChild>();
				query1.addWhereEqualTo("parent", "92fdgggh");
				query1.setLimit(125);
				query1.order("sortid");
				query1.setCachePolicy(cachePolicy);
				query1.findObjects(zhangzehuaActivity.this, new FindListener<ZhangzehuaChild>() {
					
					public void onSuccess(List<ZhangzehuaChild> arg1) {
						// TODO Auto-generated method stub
						
						children1.addAll(arg1);
						childData.add(children1);
						//expandableAdapter.notifyDataSetChanged();
												
						//��2��ѯ
						final List<ZhangzehuaChild> children2 = new ArrayList<ZhangzehuaChild>();
						BmobQuery<ZhangzehuaChild> query2=new BmobQuery<ZhangzehuaChild>();
						query2.addWhereEqualTo("parent", "oFV5000C");
						query2.order("sortid");
						query2.setLimit(125);
						query2.setCachePolicy(cachePolicy);
						query2.findObjects(zhangzehuaActivity.this, new FindListener<ZhangzehuaChild>() {				
							public void onSuccess(List<ZhangzehuaChild> arg2) {
								// TODO Auto-generated method stub
								children2.addAll(arg2);
								childData.add(children2);
							//	expandableAdapter.notifyDataSetChanged();
								
								//��3��ѯ
								final List<ZhangzehuaChild> children3 = new ArrayList<ZhangzehuaChild>();
								BmobQuery<ZhangzehuaChild> query3=new BmobQuery<ZhangzehuaChild>();
								query3.addWhereEqualTo("parent", "YZopfffm");
								query3.order("sortid");
								query3.setLimit(125);
								query3.setCachePolicy(cachePolicy);
								query3.findObjects(zhangzehuaActivity.this, new FindListener<ZhangzehuaChild>() {				
									public void onSuccess(List<ZhangzehuaChild> arg3) {
										// TODO Auto-generated method stub
										children3.addAll(arg3);
										childData.add(children3);
										//expandableAdapter.notifyDataSetChanged();
										//��4��ѯ
										final List<ZhangzehuaChild> children4 = new ArrayList<ZhangzehuaChild>();
										BmobQuery<ZhangzehuaChild> query4=new BmobQuery<ZhangzehuaChild>();
										query4.addWhereEqualTo("parent", "QUWmGGGg");
										query4.order("sortid");
										query4.setLimit(125);
										query4.setCachePolicy(cachePolicy);
										query4.findObjects(zhangzehuaActivity.this, new FindListener<ZhangzehuaChild>() {

											@Override
											public void onError(int arg0,
													String arg1) {
												// TODO Auto-generated method stub
												
											}

											@Override
											public void onSuccess(
													List<ZhangzehuaChild> arg4) {
												sweetAlertDialog.dismiss();
												children4.addAll(arg4);
												childData.add(children4);
												expandableAdapter.notifyDataSetChanged();
												
											}
											
										});
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
				//Toast.makeText(zhangzehuaActivity.this, "ʧ��"+arg1, Toast.LENGTH_LONG).show();
			}
		});
		
	}
	
	//������
	class ExpandableAdapter extends BaseExpandableListAdapter {
		zhangzehuaActivity exlistview;
		@SuppressWarnings("unused")
		private int mHideGroupPos = -1;

		public ExpandableAdapter(zhangzehuaActivity elv) {
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
			final ZhangzehuaChild entity=(ZhangzehuaChild) getChild(groupPosition, childPosition);
			
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
					Toast.makeText(zhangzehuaActivity.this, "��ʼ����"+entity.getUrl(), Toast.LENGTH_LONG).show();
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
					Intent intent=new Intent(zhangzehuaActivity.this,DownloadListActivity.class);
					startActivity(intent);
				}
			});
			
			//��������
			final TextView title = (TextView) view.findViewById(R.id.child_text);
			title.setText(entity.getName());
			title.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					 Intent intent = new Intent();
					 intent.putExtra("path", entity.getUrl());
					 intent.setClass(zhangzehuaActivity.this, localVideoView.class);
					 startActivity(intent);
				}
			});
			//title.setText(childData.get(groupPosition).get(childPosition).get("child_text1").toString());
	
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
			ZhangzehuaParent entity=(ZhangzehuaParent) getGroup(groupPosition);
			title.setText(entity.getName());
			
			ImageView image = (ImageView) view.findViewById(R.id.tubiao);
			//System.out.println("isExpanded----->" + isExpanded);
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

