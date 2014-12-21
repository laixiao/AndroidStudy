package com.study.main.ui.ResideMenuItemUi;

import java.util.ArrayList;
import java.util.List;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;
import com.study.main.R;
import com.study.main.Entity.HeimaAndroidChild;
import com.study.main.Entity.HeimaAndroidParent;
import com.study.main.ui.User.FavourActivity;
import com.study.main.ui.media.DownloadListActivity;
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

public class HeimaAndroid extends Activity {
	List<HeimaAndroidParent> groupData=new ArrayList<HeimaAndroidParent>();
	List<List<HeimaAndroidChild>> childData=new ArrayList<List<HeimaAndroidChild>>();
	
	private ExpandableListView heimaandroid_expandableListView1;
	ExpandableAdapter expandableAdapter;
	private DownloadManager downloadManager;
	private ImageView heimaandroid_back;  
	private SwipeRefreshLayout heimaandroid_swip;
	BmobQuery.CachePolicy cachePolicy;
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {  
            super.handleMessage(msg); 
          //�����������ݺ󣬵���setRefreshing(false);���ر�ˢ�¡�
            heimaandroid_swip.setRefreshing(false);  
        }  
    };  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.heimaandroid);
		init();
		initData();

	}
	private void init() {
		// TODO Auto-generated method stub
		heimaandroid_expandableListView1 = (ExpandableListView) this.findViewById(R.id.heimaandroid_expandableListView1);
		heimaandroid_back=(ImageView) this.findViewById(R.id.heimaandroid_back);
		heimaandroid_swip=(SwipeRefreshLayout) this.findViewById(R.id.heimaandroid_swip);
		View v=new View(this);
		heimaandroid_expandableListView1.addHeaderView(v);
		
		expandableAdapter=new ExpandableAdapter(this);
		heimaandroid_expandableListView1.setAdapter(expandableAdapter);
		heimaandroid_expandableListView1.setGroupIndicator(null);
		
		
		downloadManager = DownloadService.getDownloadManager(HeimaAndroid.this);
		cachePolicy=CachePolicy.CACHE_ELSE_NETWORK;
		//1.back
		heimaandroid_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		//2.refresh
		heimaandroid_swip.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
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
		
		final SweetAlertDialog sweetAlertDialog =new SweetAlertDialog(HeimaAndroid.this).setTitleText("���ڻ�ȡ���ݣ����Ժ�...").setContentText("");
		sweetAlertDialog.show();
		BmobQuery<HeimaAndroidParent> query0=new BmobQuery<HeimaAndroidParent>();
		query0.setCachePolicy(cachePolicy);
		query0.setLimit(99);
		query0.order("sortid");
		query0.findObjects(HeimaAndroid.this, new FindListener<HeimaAndroidParent>() {
			
			@Override
			public void onSuccess(List<HeimaAndroidParent> arg0) {
				// TODO Auto-generated method stub
				groupData.addAll(arg0);
			//	expandableAdapter.notifyDataSetChanged();
			//	Toast.makeText(laoluoActivity.this, "���ɹ�", Toast.LENGTH_LONG).show();
				
				//��1��ѯ
				final List<HeimaAndroidChild> children1 = new ArrayList<HeimaAndroidChild>();
				BmobQuery<HeimaAndroidChild> query1=new BmobQuery<HeimaAndroidChild>();
				query1.addWhereEqualTo("parent", "gIiF000V");
				query1.order("sortid");
				query1.setLimit(99);
				query1.setCachePolicy(cachePolicy);
				query1.findObjects(HeimaAndroid.this, new FindListener<HeimaAndroidChild>() {
					
					public void onSuccess(List<HeimaAndroidChild> arg1) {
						// TODO Auto-generated method stub
						
						children1.addAll(arg1);
						childData.add(children1);
						//expandableAdapter.notifyDataSetChanged();
												
						//��2��ѯ
						final List<HeimaAndroidChild> children2 = new ArrayList<HeimaAndroidChild>();
						BmobQuery<HeimaAndroidChild> query2=new BmobQuery<HeimaAndroidChild>();
						query2.addWhereEqualTo("parent", "0tQb222k");
						query2.order("sortid");
						query2.setLimit(99);
						query2.setCachePolicy(cachePolicy);
						query2.findObjects(HeimaAndroid.this, new FindListener<HeimaAndroidChild>() {				
							public void onSuccess(List<HeimaAndroidChild> arg2) {
								// TODO Auto-generated method stub
								children2.addAll(arg2);
								childData.add(children2);
							//	expandableAdapter.notifyDataSetChanged();
								
								//��3��ѯ
								final List<HeimaAndroidChild> children3 = new ArrayList<HeimaAndroidChild>();
								BmobQuery<HeimaAndroidChild> query3=new BmobQuery<HeimaAndroidChild>();
								query3.addWhereEqualTo("parent", "SmF5CCCL");
								query3.order("sortid");
								query3.setLimit(99);
								query3.setCachePolicy(cachePolicy);
								query3.findObjects(HeimaAndroid.this, new FindListener<HeimaAndroidChild>() {				
									public void onSuccess(List<HeimaAndroidChild> arg3) {
										// TODO Auto-generated method stub
										children3.addAll(arg3);
										childData.add(children3);
										//expandableAdapter.notifyDataSetChanged();
										//��4��ѯ
										final List<HeimaAndroidChild> children4 = new ArrayList<HeimaAndroidChild>();
										BmobQuery<HeimaAndroidChild> query4=new BmobQuery<HeimaAndroidChild>();
										query4.addWhereEqualTo("parent", "fv8T0004");
										query4.order("sortid");
										query4.setLimit(99);
										query4.setCachePolicy(cachePolicy);
										query4.findObjects(HeimaAndroid.this, new FindListener<HeimaAndroidChild>() {				
											public void onSuccess(List<HeimaAndroidChild> arg4) {
												// TODO Auto-generated method stub
												children4.addAll(arg4);
												childData.add(children4);
											//	expandableAdapter.notifyDataSetChanged();
												//��5��ѯ
												final List<HeimaAndroidChild> children5 = new ArrayList<HeimaAndroidChild>();
												BmobQuery<HeimaAndroidChild> query5=new BmobQuery<HeimaAndroidChild>();
												query5.addWhereEqualTo("parent", "EROQNNNW");
												query5.order("sortid");
												query5.setLimit(99);
												query5.setCachePolicy(cachePolicy);
												query5.findObjects(HeimaAndroid.this, new FindListener<HeimaAndroidChild>() {				
													public void onSuccess(List<HeimaAndroidChild> arg5) {
														// TODO Auto-generated method stub
														children5.addAll(arg5);
														childData.add(children5);
												//		expandableAdapter.notifyDataSetChanged();
														//��6��ѯ
														final List<HeimaAndroidChild> children6 = new ArrayList<HeimaAndroidChild>();
														BmobQuery<HeimaAndroidChild> query6=new BmobQuery<HeimaAndroidChild>();
														query6.addWhereEqualTo("parent", "8j2UDDDP");
														query6.order("sortid");
														query6.setLimit(99);
														query6.setCachePolicy(cachePolicy);
														query6.findObjects(HeimaAndroid.this, new FindListener<HeimaAndroidChild>() {				
															public void onSuccess(List<HeimaAndroidChild> arg6) {
																// TODO Auto-generated method stub
																children6.addAll(arg6);
																childData.add(children6);
													//			expandableAdapter.notifyDataSetChanged();
																//��7��ѯ
																final List<HeimaAndroidChild> children7 = new ArrayList<HeimaAndroidChild>();
																BmobQuery<HeimaAndroidChild> query7=new BmobQuery<HeimaAndroidChild>();
																query7.addWhereEqualTo("parent", "hp3NXXXp");
																query7.order("sortid");
																query7.setLimit(99);
																query7.setCachePolicy(cachePolicy);
																query7.findObjects(HeimaAndroid.this, new FindListener<HeimaAndroidChild>() {				
																	public void onSuccess(List<HeimaAndroidChild> arg7) {
																		// TODO Auto-generated method stub
																		children7.addAll(arg7);
																		childData.add(children7);
														//				expandableAdapter.notifyDataSetChanged();
																		//��8��ѯ
																		final List<HeimaAndroidChild> children8 = new ArrayList<HeimaAndroidChild>();
																		BmobQuery<HeimaAndroidChild> query8=new BmobQuery<HeimaAndroidChild>();
																		query8.addWhereEqualTo("parent", "NeYj555l");
																		query8.order("sortid");
																		query8.setLimit(99);
																		query8.setCachePolicy(cachePolicy);
																		query8.findObjects(HeimaAndroid.this, new FindListener<HeimaAndroidChild>() {				
																			public void onSuccess(List<HeimaAndroidChild> arg8) {
																				// TODO Auto-generated method stub
																				children8.addAll(arg8);
																				childData.add(children8);
																			//	expandableAdapter.notifyDataSetChanged();
																				//��9��ѯ
																				final List<HeimaAndroidChild> children9 = new ArrayList<HeimaAndroidChild>();
																				BmobQuery<HeimaAndroidChild> query9=new BmobQuery<HeimaAndroidChild>();
																				query9.addWhereEqualTo("parent", "i1WqSSSb");
																				query9.order("sortid");
																				query9.setLimit(99);
																			
																				query9.setCachePolicy(cachePolicy);
																				query9.findObjects(HeimaAndroid.this, new FindListener<HeimaAndroidChild>() {				
																					public void onSuccess(List<HeimaAndroidChild> arg9) {
																						// TODO Auto-generated method stub
																						children9.addAll(arg9);
																						childData.add(children9);
																					//	expandableAdapter.notifyDataSetChanged();
																						//��10��ѯ
																						final List<HeimaAndroidChild> children10 = new ArrayList<HeimaAndroidChild>();
																						BmobQuery<HeimaAndroidChild> query10=new BmobQuery<HeimaAndroidChild>();
																						query10.addWhereEqualTo("parent", "jmRuFFFK");
																						query10.order("sortid");
																						query10.setLimit(99);
																						query10.setCachePolicy(cachePolicy);
																						query10.findObjects(HeimaAndroid.this, new FindListener<HeimaAndroidChild>() {				
																							public void onSuccess(List<HeimaAndroidChild> arg10) {
																								// TODO Auto-generated method stub
																								children10.addAll(arg10);
																								childData.add(children10);
																						//		expandableAdapter.notifyDataSetChanged();
																								//��11��ѯ
																								final List<HeimaAndroidChild> children11 = new ArrayList<HeimaAndroidChild>();
																								BmobQuery<HeimaAndroidChild> query11=new BmobQuery<HeimaAndroidChild>();
																								query11.addWhereEqualTo("parent", "m6WtQQQS");
																								query11.order("sortid");
																								query11.setLimit(99);
																								query11.setCachePolicy(cachePolicy);
																								query11.findObjects(HeimaAndroid.this, new FindListener<HeimaAndroidChild>() {				
																									public void onSuccess(List<HeimaAndroidChild> arg11) {
																										sweetAlertDialog.dismiss();
																										children11.addAll(arg11);
																										childData.add(children11);
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
//				// TODO Auto-generated method stub
//				Toast.makeText(HeimaAndroid.this, "ʧ��"+arg1, Toast.LENGTH_LONG).show();
			}
		});
		
	}
	
	//������
	class ExpandableAdapter extends BaseExpandableListAdapter {
		HeimaAndroid exlistview;
		@SuppressWarnings("unused")
		private int mHideGroupPos = -1;

		public ExpandableAdapter(HeimaAndroid elv) {
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
			final HeimaAndroidChild entity=(HeimaAndroidChild) getChild(groupPosition, childPosition);
			
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
					Toast.makeText(HeimaAndroid.this, "��ʼ����"+entity.getUrl(), Toast.LENGTH_LONG).show();
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
					Intent intent=new Intent(HeimaAndroid.this,DownloadListActivity.class);
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
					 intent.setClass(HeimaAndroid.this, localVideoView.class);
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
			HeimaAndroidParent entity=(HeimaAndroidParent) getGroup(groupPosition);
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

