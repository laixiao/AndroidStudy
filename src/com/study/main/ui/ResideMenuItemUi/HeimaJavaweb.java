package com.study.main.ui.ResideMenuItemUi;

import java.util.ArrayList;
import java.util.List;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;
import com.study.main.R;
import com.study.main.Entity.HeimaJavawebChild;
import com.study.main.Entity.HeimaJavawebParent;
import com.study.main.ui.Simple.DownloadListActivity;
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

public class HeimaJavaweb extends Activity {
	List<HeimaJavawebParent> groupData=new ArrayList<HeimaJavawebParent>();
	List<List<HeimaJavawebChild>> childData=new ArrayList<List<HeimaJavawebChild>>();
	
	private ExpandableListView heimajavaweb_expandableListView1;
	ExpandableAdapter expandableAdapter;
	private DownloadManager downloadManager;
	private ImageView heimajavaweb_back;  
	private SwipeRefreshLayout heimajavaweb_swip;
	BmobQuery.CachePolicy cachePolicy;
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {  
            super.handleMessage(msg); 
          //当更新完数据后，调用setRefreshing(false);来关闭刷新。
            heimajavaweb_swip.setRefreshing(false);  
        }  
    };  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.heimajavaweb);
		init();
		initData();

	}
	private void init() {
		// TODO Auto-generated method stub
		heimajavaweb_expandableListView1 = (ExpandableListView) this.findViewById(R.id.heimajavaweb_expandableListView1);
		heimajavaweb_back=(ImageView) this.findViewById(R.id.heimajavaweb_back);
		heimajavaweb_swip=(SwipeRefreshLayout) this.findViewById(R.id.heimajavaweb_swip);
		View v=new View(this);
		heimajavaweb_expandableListView1.addHeaderView(v);
		
		expandableAdapter=new ExpandableAdapter(this);
		heimajavaweb_expandableListView1.setAdapter(expandableAdapter);
		heimajavaweb_expandableListView1.setGroupIndicator(null);
		
		
		downloadManager = DownloadService.getDownloadManager(HeimaJavaweb.this);
		cachePolicy=CachePolicy.CACHE_ELSE_NETWORK;
		//1.back
		heimajavaweb_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		//2.refresh
		heimajavaweb_swip.setOnRefreshListener(new OnRefreshListener() {
			
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
		
		
		BmobQuery<HeimaJavawebParent> query0=new BmobQuery<HeimaJavawebParent>();
		query0.setCachePolicy(cachePolicy);
		query0.setLimit(99);
		query0.order("sortid");
		query0.findObjects(HeimaJavaweb.this, new FindListener<HeimaJavawebParent>() {
			
			@Override
			public void onSuccess(List<HeimaJavawebParent> arg0) {
				
				groupData.addAll(arg0);
				
				//子1查询
				final List<HeimaJavawebChild> children1 = new ArrayList<HeimaJavawebChild>();
				BmobQuery<HeimaJavawebChild> query1=new BmobQuery<HeimaJavawebChild>();
				query1.addWhereEqualTo("parent", "1gS6QQQd");
				query1.order("sortid");
				query1.setLimit(99);
				query1.setCachePolicy(cachePolicy);
				query1.findObjects(HeimaJavaweb.this, new FindListener<HeimaJavawebChild>() {
					
					public void onSuccess(List<HeimaJavawebChild> arg1) {
						// TODO Auto-generated method stub
						
						children1.addAll(arg1);
						childData.add(children1);
						//expandableAdapter.notifyDataSetChanged();
												
						//子2查询
						final List<HeimaJavawebChild> children2 = new ArrayList<HeimaJavawebChild>();
						BmobQuery<HeimaJavawebChild> query2=new BmobQuery<HeimaJavawebChild>();
						query2.addWhereEqualTo("parent", "Hb68JJJK");
						query2.order("sortid");
						query2.setLimit(99);
						query2.setCachePolicy(cachePolicy);
						query2.findObjects(HeimaJavaweb.this, new FindListener<HeimaJavawebChild>() {				
							public void onSuccess(List<HeimaJavawebChild> arg2) {
								// TODO Auto-generated method stub
								children2.addAll(arg2);
								childData.add(children2);
							//	expandableAdapter.notifyDataSetChanged();
								
								//子3查询
								final List<HeimaJavawebChild> children3 = new ArrayList<HeimaJavawebChild>();
								BmobQuery<HeimaJavawebChild> query3=new BmobQuery<HeimaJavawebChild>();
								query3.addWhereEqualTo("parent", "wh4p999L");
								query3.order("sortid");
								query3.setLimit(99);
								query3.setCachePolicy(cachePolicy);
								query3.findObjects(HeimaJavaweb.this, new FindListener<HeimaJavawebChild>() {				
									public void onSuccess(List<HeimaJavawebChild> arg3) {
										// TODO Auto-generated method stub
										children3.addAll(arg3);
										childData.add(children3);
										//expandableAdapter.notifyDataSetChanged();
										//子4查询
										final List<HeimaJavawebChild> children4 = new ArrayList<HeimaJavawebChild>();
										BmobQuery<HeimaJavawebChild> query4=new BmobQuery<HeimaJavawebChild>();
										query4.addWhereEqualTo("parent", "bgXd555Z");
										query4.order("sortid");
										query4.setLimit(99);
										query4.setCachePolicy(cachePolicy);
										query4.findObjects(HeimaJavaweb.this, new FindListener<HeimaJavawebChild>() {				
											public void onSuccess(List<HeimaJavawebChild> arg4) {
												// TODO Auto-generated method stub
												children4.addAll(arg4);
												childData.add(children4);
											//	expandableAdapter.notifyDataSetChanged();
												//子5查询
												final List<HeimaJavawebChild> children5 = new ArrayList<HeimaJavawebChild>();
												BmobQuery<HeimaJavawebChild> query5=new BmobQuery<HeimaJavawebChild>();
												query5.addWhereEqualTo("parent", "pFBG2225");
												query5.order("sortid");
												query5.setLimit(99);
												query5.setCachePolicy(cachePolicy);
												query5.findObjects(HeimaJavaweb.this, new FindListener<HeimaJavawebChild>() {				
													public void onSuccess(List<HeimaJavawebChild> arg5) {
														// TODO Auto-generated method stub
														children5.addAll(arg5);
														childData.add(children5);
												//		expandableAdapter.notifyDataSetChanged();
														//子6查询
														final List<HeimaJavawebChild> children6 = new ArrayList<HeimaJavawebChild>();
														BmobQuery<HeimaJavawebChild> query6=new BmobQuery<HeimaJavawebChild>();
														query6.addWhereEqualTo("parent", "793t222E");
														query6.order("sortid");
														query6.setLimit(99);
														query6.setCachePolicy(cachePolicy);
														query6.findObjects(HeimaJavaweb.this, new FindListener<HeimaJavawebChild>() {				
															public void onSuccess(List<HeimaJavawebChild> arg6) {
																// TODO Auto-generated method stub
																children6.addAll(arg6);
																childData.add(children6);
													//			expandableAdapter.notifyDataSetChanged();
																//子7查询
																final List<HeimaJavawebChild> children7 = new ArrayList<HeimaJavawebChild>();
																BmobQuery<HeimaJavawebChild> query7=new BmobQuery<HeimaJavawebChild>();
																query7.addWhereEqualTo("parent", "Ipamkkkp");
																query7.order("sortid");
																query7.setLimit(99);
																query7.setCachePolicy(cachePolicy);
																query7.findObjects(HeimaJavaweb.this, new FindListener<HeimaJavawebChild>() {				
																	public void onSuccess(List<HeimaJavawebChild> arg7) {
																		// TODO Auto-generated method stub
																		children7.addAll(arg7);
																		childData.add(children7);
														//				expandableAdapter.notifyDataSetChanged();
																		//子8查询
																		final List<HeimaJavawebChild> children8 = new ArrayList<HeimaJavawebChild>();
																		BmobQuery<HeimaJavawebChild> query8=new BmobQuery<HeimaJavawebChild>();
																		query8.addWhereEqualTo("parent", "UciCLLLP");
																		query8.order("sortid");
																		query8.setLimit(99);
																		query8.setCachePolicy(cachePolicy);
																		query8.findObjects(HeimaJavaweb.this, new FindListener<HeimaJavawebChild>() {				
																			public void onSuccess(List<HeimaJavawebChild> arg8) {
																				// TODO Auto-generated method stub
																				children8.addAll(arg8);
																				childData.add(children8);
																			//	expandableAdapter.notifyDataSetChanged();
																				//子9查询
																				final List<HeimaJavawebChild> children9 = new ArrayList<HeimaJavawebChild>();
																				BmobQuery<HeimaJavawebChild> query9=new BmobQuery<HeimaJavawebChild>();
																				query9.addWhereEqualTo("parent", "47dMPPPc");
																				query9.order("sortid");
																				query9.setLimit(99);
															
																				query9.setCachePolicy(cachePolicy);
																				query9.findObjects(HeimaJavaweb.this, new FindListener<HeimaJavawebChild>() {				
																					public void onSuccess(List<HeimaJavawebChild> arg9) {
																						// TODO Auto-generated method stub
																						children9.addAll(arg9);
																						childData.add(children9);
																					//	expandableAdapter.notifyDataSetChanged();
																						//子10查询
																						final List<HeimaJavawebChild> children10 = new ArrayList<HeimaJavawebChild>();
																						BmobQuery<HeimaJavawebChild> query10=new BmobQuery<HeimaJavawebChild>();
																						query10.addWhereEqualTo("parent", "s4at555j");
																						query10.order("sortid");
																						query10.setLimit(99);
																						query10.setCachePolicy(cachePolicy);
																						query10.findObjects(HeimaJavaweb.this, new FindListener<HeimaJavawebChild>() {				
																							public void onSuccess(List<HeimaJavawebChild> arg10) {
																								// TODO Auto-generated method stub
																								children10.addAll(arg10);
																								childData.add(children10);
																						//		expandableAdapter.notifyDataSetChanged();
																								//子11查询
																								final List<HeimaJavawebChild> children11 = new ArrayList<HeimaJavawebChild>();
																								BmobQuery<HeimaJavawebChild> query11=new BmobQuery<HeimaJavawebChild>();
																								query11.addWhereEqualTo("parent", "ZrimNNNH");
																								query11.order("sortid");
																								query11.setLimit(99);
																								query11.setCachePolicy(cachePolicy);
																								query11.findObjects(HeimaJavaweb.this, new FindListener<HeimaJavawebChild>() {				
																									public void onSuccess(List<HeimaJavawebChild> arg11) {
																										// TODO Auto-generated method stub
																										children11.addAll(arg11);
																										childData.add(children11);
																									//	expandableAdapter.notifyDataSetChanged();
																										//子12查询
																										final List<HeimaJavawebChild> children12 = new ArrayList<HeimaJavawebChild>();
																										BmobQuery<HeimaJavawebChild> query12=new BmobQuery<HeimaJavawebChild>();
																										query12.addWhereEqualTo("parent", "m8M9OOOP");
																										query12.order("sortid");
																										query12.setLimit(99);
																										query12.setCachePolicy(cachePolicy);
																										query12.findObjects(HeimaJavaweb.this, new FindListener<HeimaJavawebChild>() {				
																											public void onSuccess(List<HeimaJavawebChild> arg12) {
																												// TODO Auto-generated method stub
																												children12.addAll(arg12);
																												childData.add(children12);
																										//		expandableAdapter.notifyDataSetChanged();
																												//子13查询
																												final List<HeimaJavawebChild> children13 = new ArrayList<HeimaJavawebChild>();
																												BmobQuery<HeimaJavawebChild> query13=new BmobQuery<HeimaJavawebChild>();
																												query13.addWhereEqualTo("parent", "bWiV222A");
																												query13.order("sortid");
																												query13.setLimit(99);
																												query13.setCachePolicy(cachePolicy);
																												query13.findObjects(HeimaJavaweb.this, new FindListener<HeimaJavawebChild>() {				
																													public void onSuccess(List<HeimaJavawebChild> arg13) {
																														// TODO Auto-generated method stub
																														children13.addAll(arg13);
																														childData.add(children13);
																												//		expandableAdapter.notifyDataSetChanged();
																														//子14查询
																														final List<HeimaJavawebChild> children14 = new ArrayList<HeimaJavawebChild>();
																														BmobQuery<HeimaJavawebChild> query14=new BmobQuery<HeimaJavawebChild>();
																														query14.addWhereEqualTo("parent", "0UY4dddD");
																														query14.order("sortid");
																														query14.setLimit(99);
																														query14.setCachePolicy(cachePolicy);
																														query14.findObjects(HeimaJavaweb.this, new FindListener<HeimaJavawebChild>() {				
																															public void onSuccess(List<HeimaJavawebChild> arg14) {
																																// TODO Auto-generated method stub
																																children14.addAll(arg14);
																																childData.add(children14);
																													//			expandableAdapter.notifyDataSetChanged();
																																//子15查询
																																final List<HeimaJavawebChild> children15 = new ArrayList<HeimaJavawebChild>();
																																BmobQuery<HeimaJavawebChild> query15=new BmobQuery<HeimaJavawebChild>();
																																query15.addWhereEqualTo("parent", "l1D7B77B");
																																query15.order("sortid");
																																query15.setLimit(99);
																																query15.setCachePolicy(cachePolicy);
																																query15.findObjects(HeimaJavaweb.this, new FindListener<HeimaJavawebChild>() {				
																																	public void onSuccess(List<HeimaJavawebChild> arg15) {
																																		// TODO Auto-generated method stub
																																		children15.addAll(arg15);
																																		childData.add(children15);
																																//		expandableAdapter.notifyDataSetChanged();
																																		//子16查询
																																		final List<HeimaJavawebChild> children16 = new ArrayList<HeimaJavawebChild>();
																																		BmobQuery<HeimaJavawebChild> query16=new BmobQuery<HeimaJavawebChild>();
																																		query16.addWhereEqualTo("parent", "R9l5SIIS");
																																		query16.order("sortid");
																																		query16.setLimit(99);
																																		query16.setCachePolicy(cachePolicy);
																																		query16.findObjects(HeimaJavaweb.this, new FindListener<HeimaJavawebChild>() {				
																																			public void onSuccess(List<HeimaJavawebChild> arg16) {
																																				// TODO Auto-generated method stub
																																				children16.addAll(arg16);
																																				childData.add(children16);
																																		//		expandableAdapter.notifyDataSetChanged();
																																				//子17查询
																																				final List<HeimaJavawebChild> children17 = new ArrayList<HeimaJavawebChild>();
																																				BmobQuery<HeimaJavawebChild> query17=new BmobQuery<HeimaJavawebChild>();
																																				query17.addWhereEqualTo("parent", "R3zWP11P");
																																				query17.order("sortid");
																																				query17.setLimit(99);
																																				query17.setCachePolicy(cachePolicy);
																																				query17.findObjects(HeimaJavaweb.this, new FindListener<HeimaJavawebChild>() {				
																																					public void onSuccess(List<HeimaJavawebChild> arg17) {
																																						// TODO Auto-generated method stub
																																						children17.addAll(arg17);
																																						childData.add(children17);
																																			//			expandableAdapter.notifyDataSetChanged();
																																						//子18查询
																																						final List<HeimaJavawebChild> children18 = new ArrayList<HeimaJavawebChild>();
																																						BmobQuery<HeimaJavawebChild> query18=new BmobQuery<HeimaJavawebChild>();
																																						query18.addWhereEqualTo("parent", "ZylSpDDp");
																																						query18.order("sortid");
																																						query18.setLimit(99);
																																						query18.setCachePolicy(cachePolicy);
																																						query18.findObjects(HeimaJavaweb.this, new FindListener<HeimaJavawebChild>() {				
																																							public void onSuccess(List<HeimaJavawebChild> arg18) {
																																								children18.addAll(arg18);
																																								childData.add(children18);
																																								//expandableAdapter.notifyDataSetChanged();
																																					
																																								//子19查询
																																								final List<HeimaJavawebChild> children19 = new ArrayList<HeimaJavawebChild>();
																																								BmobQuery<HeimaJavawebChild> query19=new BmobQuery<HeimaJavawebChild>();
																																								query19.addWhereEqualTo("parent", "g2QuMIIM");
																																								query19.order("sortid");
																																								query19.setLimit(99);
																																								query19.setCachePolicy(cachePolicy);
																																								query19.findObjects(HeimaJavaweb.this, new FindListener<HeimaJavawebChild>() {				
																																									public void onSuccess(List<HeimaJavawebChild> arg19) {
																																										children19.addAll(arg19);
																																										childData.add(children19);
																																										//expandableAdapter.notifyDataSetChanged();
																																							
																																										//子20查询
																																										final List<HeimaJavawebChild> children20 = new ArrayList<HeimaJavawebChild>();
																																										BmobQuery<HeimaJavawebChild> query20=new BmobQuery<HeimaJavawebChild>();
																																										query20.addWhereEqualTo("parent", "YrrsGBBG");
																																										query20.order("sortid");
																																										query20.setLimit(99);
																																										query20.setCachePolicy(cachePolicy);
																																										query20.findObjects(HeimaJavaweb.this, new FindListener<HeimaJavawebChild>() {				
																																											public void onSuccess(List<HeimaJavawebChild> arg20) {
																																												children20.addAll(arg20);
																																												childData.add(children20);
																																												//expandableAdapter.notifyDataSetChanged();
																																									
																																												//子21查询
																																												final List<HeimaJavawebChild> children21 = new ArrayList<HeimaJavawebChild>();
																																												BmobQuery<HeimaJavawebChild> query21=new BmobQuery<HeimaJavawebChild>();
																																												query21.addWhereEqualTo("parent", "wjUcI44I");
																																												query21.order("sortid");
																																												query21.setLimit(99);
																																												query21.setCachePolicy(cachePolicy);
																																												query21.findObjects(HeimaJavaweb.this, new FindListener<HeimaJavawebChild>() {				
																																													public void onSuccess(List<HeimaJavawebChild> arg21) {
																																														children21.addAll(arg21);
																																														childData.add(children21);
																																														//expandableAdapter.notifyDataSetChanged();
																																											
																																														//子22查询
																																														final List<HeimaJavawebChild> children22 = new ArrayList<HeimaJavawebChild>();
																																														BmobQuery<HeimaJavawebChild> query22=new BmobQuery<HeimaJavawebChild>();
																																														query22.addWhereEqualTo("parent", "ZQqzN77N");
																																														query22.order("sortid");
																																														query22.setLimit(99);
																																														query22.setCachePolicy(cachePolicy);
																																														query22.findObjects(HeimaJavaweb.this, new FindListener<HeimaJavawebChild>() {				
																																															public void onSuccess(List<HeimaJavawebChild> arg22) {
																																																new SweetAlertDialog(HeimaJavaweb.this, SweetAlertDialog.SUCCESS_TYPE)
																																																.setTitleText("亲，数据获取成功啦！")
																																										                        .setContentText("")
																																										                        .show();
																																																children22.addAll(arg22);
																																																childData.add(children22);
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
				// TODO Auto-generated method stub
				Toast.makeText(HeimaJavaweb.this, "失败"+arg1, Toast.LENGTH_LONG).show();
			}
		});
		
	}
	
	//适配器
	class ExpandableAdapter extends BaseExpandableListAdapter {
		HeimaJavaweb exlistview;
		@SuppressWarnings("unused")
		private int mHideGroupPos = -1;

		public ExpandableAdapter(HeimaJavaweb elv) {
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
			final HeimaJavawebChild entity=(HeimaJavawebChild) getChild(groupPosition, childPosition);
			
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
					Toast.makeText(HeimaJavaweb.this, "开始下载"+entity.getUrl(), Toast.LENGTH_LONG).show();
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
					Intent intent=new Intent(HeimaJavaweb.this,DownloadListActivity.class);
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
					 intent.setClass(HeimaJavaweb.this, localVideoView.class);
					 startActivity(intent);
				}
			});
			//title.setText(childData.get(groupPosition).get(childPosition).get("child_text1").toString());
	
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
			HeimaJavawebParent entity=(HeimaJavawebParent) getGroup(groupPosition);
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

