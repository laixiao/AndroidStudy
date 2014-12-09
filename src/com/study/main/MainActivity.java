package com.study.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.study.main.Entity.ShuoShuo;
import com.study.main.Entity.User;
import com.study.main.ResideMenu.ResideMenu;
import com.study.main.ResideMenu.ResideMenuInfo;
import com.study.main.ResideMenu.ResideMenuItem;
import com.study.main.ResideMenu.ResideMenuSetting;
import com.study.main.test.test1;
import com.study.main.ui.Simple.Fabiaoshuoshuo;
import com.study.main.ui.Simple.SettingActivity;
import com.study.main.ui.Simple.laoluoActivity;
import com.study.main.ui.User.LoginAndRegister;
import com.study.main.ui.User.UserInfo;
import com.study.main.ui.User.commentActivity;
import com.study.main.ui.User.otherInfo;
import com.study.main.ui.media.ArrayAdapter;
import com.study.main.ui.media.FileUtils;
import com.study.main.ui.media.localVideoView;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener,OnItemClickListener {
	private RelativeLayout titlelayout01, titlelayout02, titlelayout03,middlelayout1, middlelayout2, middlelayout3;
	private ListView listView01;
	private Button button1, button2, button3;
	private Button fabiaoshuoshuo;
	private ImageButton imageButton1;
	private ResideMenu resideMenu;
	private ResideMenuItem menu_item01;
	private ResideMenuItem menu_item02;
	private ResideMenuItem menu_item03;
	private ResideMenuItem menu_item04;
	private ResideMenuItem menu_item05;
	// private ResideMenuItem itemFile;
	private ResideMenuInfo info;
	private ResideMenuSetting setting;
	private TextView text1, text2, text3;
	private boolean is_closed = false;
	private long mExitTime;
	private TextView shezhi;
	private FileAdapter mAdapter;
	private ListView listView;
	private SwipeRefreshLayout swip;
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// �����������ݺ󣬵���setRefreshing(false);���ر�ˢ�¡�
			swip.setRefreshing(false);
		}
	};

	
	PullToRefreshListView list;
	private ILoadingLayout loadingLayout;
	ListView listview;
	private DisplayImageOptions options01,options02;
	//1.get data
	List<ShuoShuo> shuoshuoList = new ArrayList<ShuoShuo>();
	firstListAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		init();
		init3();
	}

	private void init3() {
		// TODO Auto-generated method stub
		list=(PullToRefreshListView)findViewById(R.id.list);
		adapter=new firstListAdapter(this);
		
		options01 = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.icon_profile)
		.showImageForEmptyUri(R.drawable.icon_profile)
		.showImageOnFail(R.drawable.icon_profile)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.displayer(new RoundedBitmapDisplayer(90))
		.build();
		options02 = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.icon_profile)
		.showImageForEmptyUri(R.drawable.icon_profile)
		.showImageOnFail(R.drawable.icon_profile)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.displayer(new RoundedBitmapDisplayer(10))
		.build();
		
		list.setMode(Mode.BOTH);
		loadingLayout = list.getLoadingLayoutProxy();
		loadingLayout.setLastUpdatedLabel("");
		loadingLayout.setPullLabel(getString(R.string.pull_to_refresh_bottom_pull));
		loadingLayout.setRefreshingLabel(getString(R.string.pull_to_refresh_bottom_refreshing));
		loadingLayout.setReleaseLabel(getString(R.string.pull_to_refresh_bottom_release));
		//1.��������
		list.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				//��ʼ��������������
				if (firstVisibleItem == 0) {
					loadingLayout.setLastUpdatedLabel("");
					loadingLayout.setPullLabel(getString(R.string.pull_to_refresh_top_pull));
					loadingLayout.setRefreshingLabel(getString(R.string.pull_to_refresh_top_refreshing));
					loadingLayout.setReleaseLabel(getString(R.string.pull_to_refresh_top_release));
				} else if (firstVisibleItem + visibleItemCount + 1 == totalItemCount) {
					loadingLayout.setLastUpdatedLabel("");
					loadingLayout.setPullLabel(getString(R.string.pull_to_refresh_bottom_pull));
					loadingLayout.setRefreshingLabel(getString(R.string.pull_to_refresh_bottom_refreshing));
					loadingLayout.setReleaseLabel(getString(R.string.pull_to_refresh_bottom_release));
				}
			}
		});
		//2. fresh listener
		list.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// 0.����ˢ��(�ӵ�һҳ��ʼװ������)
				queryData(0, STATE_REFRESH);
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// 0.�������ظ���(������һҳ����)
				queryData(curPage, STATE_MORE);
			}
		});
		listview=list.getRefreshableView();		
		listview.setAdapter(adapter);
		queryData(0, STATE_REFRESH);
	}

	private void init() {
		titlelayout01 = (RelativeLayout) this.findViewById(R.id.titlelayout01);
		titlelayout02 = (RelativeLayout) this.findViewById(R.id.titlelayout02);
		titlelayout03 = (RelativeLayout) this.findViewById(R.id.titlelayout03);
		middlelayout1 = (RelativeLayout) this.findViewById(R.id.middlelayout1);
		middlelayout2 = (RelativeLayout) this.findViewById(R.id.middlelayout2);
		middlelayout3 = (RelativeLayout) this.findViewById(R.id.middlelayout3);
		button1 = (Button) this.findViewById(R.id.button1);
		button1.setSelected(true);
		button2 = (Button) this.findViewById(R.id.button2);
		button3 = (Button) this.findViewById(R.id.button3);
		listView01 = (ListView) this.findViewById(R.id.listView01);
		fabiaoshuoshuo = (Button) this.findViewById(R.id.fabiaoshuoshuo);
		mAdapter = new FileAdapter(this, null);
		listView = (ListView) this.findViewById(android.R.id.list);
		swip = (SwipeRefreshLayout) this.findViewById(R.id.swip);
		listView.setAdapter(mAdapter);
		listView.setOnItemClickListener(this);
		new ScanVideoTask().execute();
		swip.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				mAdapter.clear();
				new ScanVideoTask().execute();
				handler.sendEmptyMessageDelayed(1, 5000);
				Toast.makeText(MainActivity.this, "����ˢ�±����б����Ժ�...",
						Toast.LENGTH_LONG).show();
			}
		});

		imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
		// attach to current activity;
		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(R.drawable.residebackground1);
		resideMenu.attachToActivity(this);
		resideMenu.setMenuListener(menuListener);
		// valid scale factor is between 0.0f and 1.0f. leftmenu'width is
		// 150dip.
		resideMenu.setScaleValue(0.6f);
		// ��ֹʹ���Ҳ�˵�
		resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
		// resideMenu.setDirectionDisable(ResideMenu.DIRECTION_RIGHT);
		// create menu items;
		menu_item01 = new ResideMenuItem(this, R.drawable.residebutton1,
				R.string.menu_item01);
		menu_item02 = new ResideMenuItem(this, R.drawable.residebutton5,
				R.string.menu_item02);
		menu_item03 = new ResideMenuItem(this, R.drawable.residebutton3,
				R.string.menu_item03);
		menu_item04 = new ResideMenuItem(this, R.drawable.residebutton4,
				R.string.menu_item04);
		menu_item05 = new ResideMenuItem(this, R.drawable.residebutton2,
				R.string.menu_item05);
		// itemFile = new ResideMenuItem(this, R.drawable.residebutton4,
		// "�Ӳ˵�1");
		info = new ResideMenuInfo(this, R.drawable.icon_profile, "name",
				"money");
		setting = new ResideMenuSetting(this, "setting", "");

		resideMenu.addMenuItem(menu_item01, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(menu_item02, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(menu_item03, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(menu_item04, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(menu_item05, ResideMenu.DIRECTION_LEFT);
		// resideMenu.addMenuItem(itemFile, ResideMenu.DIRECTION_LEFT);

		resideMenu.addMenuInfo(info);
		resideMenu.addMenuSetting(setting);
		//�����¼�������
		menu_item01.setOnClickListener(this);
		menu_item02.setOnClickListener(this);
		menu_item03.setOnClickListener(this);
		menu_item04.setOnClickListener(this);
		menu_item05.setOnClickListener(this);
		info.setOnClickListener(this);
		setting.setOnClickListener(this);
		imageButton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
			}
		});
		
		buttonListener();

	}

	private void buttonListener() {
		// TODO Auto-generated method stub
		button1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				titlelayout01.setVisibility(View.VISIBLE);
				titlelayout02.setVisibility(View.INVISIBLE);
				titlelayout03.setVisibility(View.INVISIBLE);
				middlelayout1.setVisibility(View.VISIBLE);
				middlelayout2.setVisibility(View.INVISIBLE);
				middlelayout3.setVisibility(View.INVISIBLE);
				button1.setSelected(true);
				button2.setSelected(false);
				button3.setSelected(false);

			}
		});
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				titlelayout01.setVisibility(View.INVISIBLE);
				titlelayout02.setVisibility(View.VISIBLE);
				titlelayout03.setVisibility(View.INVISIBLE);
				middlelayout1.setVisibility(View.INVISIBLE);
				middlelayout2.setVisibility(View.VISIBLE);
				middlelayout3.setVisibility(View.INVISIBLE);

				button1.setSelected(false);
				button2.setSelected(true);
				button3.setSelected(false);
			}
		});
		button3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				titlelayout01.setVisibility(View.INVISIBLE);
				titlelayout02.setVisibility(View.INVISIBLE);
				titlelayout03.setVisibility(View.VISIBLE);
				middlelayout1.setVisibility(View.INVISIBLE);
				middlelayout2.setVisibility(View.INVISIBLE);
				middlelayout3.setVisibility(View.VISIBLE);

				button1.setSelected(false);
				button2.setSelected(false);
				button3.setSelected(true);
			}
		});

		fabiaoshuoshuo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BmobUser currentUser = BmobUser
						.getCurrentUser(MainActivity.this);
				if (currentUser != null) {
					// �����û�ʹ��Ӧ��,�������û���Ψһ��ʶ����������Ϊ�������ݵ��ֶ�
					// String name = currentUser.getUsername();
					// String email = currentUser.getEmail();
					// LogUtils.i(TAG,"username:"+name+",email:"+email);
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, Fabiaoshuoshuo.class);
					startActivity(intent);
				} else {
					// �����û�����Ϊ��ʱ�� �ɴ��û�ע����桭
					Toast.makeText(MainActivity.this, "���ȵ�¼��",
							Toast.LENGTH_SHORT).show();
					// redictToActivity(mContext,
					// RegisterAndLoginActivity.class, null);
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, LoginAndRegister.class);
					startActivity(intent);
				}
			}
		});
	}



	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return resideMenu.dispatchTouchEvent(ev);
	}

	@Override
	public void onClick(View view) {
		if (view == menu_item01) {
			Intent intent = new Intent();		
			intent.setClass(getApplicationContext(), laoluoActivity.class);
			startActivity(intent);
		} else if (view == menu_item02) {
			Toast.makeText(MainActivity.this, "menu_item02", Toast.LENGTH_LONG).show();
		} else if (view == menu_item03) {
			//Intent intent = new Intent();
			// intent.putExtra("flog", "װ��");
			// intent.setClass(getApplicationContext(), LocalList.class);
			// startActivity(intent);
		} else if (view == menu_item04) {
//			Intent intent = new Intent();
//			// intent.putExtra("flog", "�ղ�");
//			intent.setClass(getApplicationContext(), text1.class);
//			startActivity(intent);
		} else if (view == menu_item05) {
			Intent intent = new Intent();
			// intent.putExtra("flog", "���");
			intent.setClass(getApplicationContext(), test1.class);
			startActivity(intent);
		}
		else if (view == info) {
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), UserInfo.class);
			startActivity(intent);
		} else if (view == setting) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, SettingActivity.class);
			startActivity(intent);
		}
	}

	private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
		@Override
		public void openMenu() {
			is_closed = false;
			// leftMenu.setVisibility(View.GONE);
		}

		@Override
		public void closeMenu() {
			is_closed = true;
			// leftMenu.setVisibility(View.VISIBLE);
		}
	};

	// What good method is to access resideMenu��
	public ResideMenu getResideMenu() {
		return resideMenu;
	}

	// �����ֻ��ϵ�BACK��
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// �жϲ˵��Ƿ�ر�
			if (is_closed) {
				// // �ж����ε����ʱ������Ĭ������Ϊ2�룩
				// if ((System.currentTimeMillis() - mExitTime) > 2000) {
				// Toast.makeText(this, "�ٰ�һ���˳�����", Toast.LENGTH_SHORT).show();
				//
				// mExitTime = System.currentTimeMillis();
				// } else {
				// finish();
				// System.exit(0);
				// super.onBackPressed();
				// }
				// ģ��HOME��Ч��
				Intent i = new Intent(Intent.ACTION_MAIN);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				i.addCategory(Intent.CATEGORY_HOME);
				startActivity(i);
			} else {
				resideMenu.closeMenu();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	// 1.���صײ��˵�
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	// 2.�����ײ��˵�
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.exit:
			Toast.makeText(MainActivity.this, "�������˳�����", Toast.LENGTH_LONG)
					.show();
			finish();
			System.exit(0);
			super.onBackPressed();
			break;
		case R.id.checkversion:
			Toast.makeText(MainActivity.this, "�����˰汾���·���", Toast.LENGTH_LONG)
					.show();
			break;
		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	// 1.listView����¼�
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		final File f = mAdapter.getItem(position);

		Intent intent = new Intent(MainActivity.this, localVideoView.class);
		intent.putExtra("path", f.getPath());
		startActivity(intent);

	}

	/** 2.ɨ��SD�� */
	private class ScanVideoTask extends AsyncTask<Void, File, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// �ж�SD���Ƿ����
			if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
				eachAllMedias(Environment.getExternalStorageDirectory());
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(File... values) {
			mAdapter.add(values[0]);
			mAdapter.notifyDataSetChanged();
		}

		/** ���������ļ��У����ҳ���Ƶ�ļ� */
		public void eachAllMedias(File f) {
			if (f != null && f.exists() && f.isDirectory()) {
				File[] files = f.listFiles();
				if (files != null) {
					for (File file : f.listFiles()) {
						if (file.isDirectory()) {
							eachAllMedias(file);
						} else if (file.exists() && file.canRead()
								&& FileUtils.isVideoOrAudio(file)) {
							publishProgress(file);
						}
					}
				}
			}
		}
	}

	// 3.listView������
	public class FileAdapter extends ArrayAdapter<File> {

		public FileAdapter(Context ctx, ArrayList<File> l) {
			super(ctx, l);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final File f = getItem(position);
			if (convertView == null) {
				final LayoutInflater mInflater = getLayoutInflater();
				convertView = mInflater.inflate(R.layout.fragment_file_item,
						null);
			}
			((TextView) convertView.findViewById(R.id.title)).setText(f
					.getName());
			return convertView;
		}
	}
	//������
	private class firstListAdapter extends BaseAdapter  {		
		Context context;		
		public firstListAdapter(Context context){
			this.context = context;
		}
		@SuppressLint("InflateParams") @Override
		public View getView(final int position, View convertView,ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {				
				convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
				holder = new ViewHolder();	
				holder.userName = (TextView)convertView.findViewById(R.id.list_item_user_name);
				holder.userLogo = (ImageView)convertView.findViewById(R.id.list_item_user_logo);
				holder.favMark = (ImageView)convertView.findViewById(R.id.list_item_action_fav);
				holder.contentText = (TextView)convertView.findViewById(R.id.list_item_content_text);
				holder.contentImage = (ImageView)convertView.findViewById(R.id.list_item_content_image);
				holder.love = (TextView)convertView.findViewById(R.id.list_item_action_love);
				holder.hate = (TextView)convertView.findViewById(R.id.list_item_action_hate);
				holder.share = (TextView)convertView.findViewById(R.id.list_item_action_share);
				holder.comment = (TextView)convertView.findViewById(R.id.list_item_action_comment);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			//1.Get QiangYu
			final ShuoShuo shuoshuo = (ShuoShuo) getItem(position);	
			final User user=shuoshuo.getAuthor();
			if(user==null){
				Toast.makeText(MainActivity.this, position+"user is null", Toast.LENGTH_LONG).show();
			}else if(user.getAvatar()==null){
				Toast.makeText(MainActivity.this, position+"Avatar is null", Toast.LENGTH_LONG).show();
			}else {	
			
				ImageLoader.getInstance().displayImage(user.getAvatar().getFileUrl(), holder.userLogo, options01,null);				
			}
			//2.userName
			if(user!=null){
			holder.userName.setText(shuoshuo.getAuthor().getNickname());
			//3.userLogo
			holder.userLogo.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {									
						Intent intent=new Intent(MainActivity.this, otherInfo.class);
						intent.putExtra("data",user);
						startActivity(intent);					
				}
			});
			}
			//4.contentText
			holder.contentText.setText(shuoshuo.getContent());
			//5.Contentfigureurl
			if(shuoshuo.getContentfigureurl()!=null){
				holder.contentImage.setVisibility(View.VISIBLE);
				ImageLoader.getInstance().displayImage(shuoshuo.getContentfigureurl().getFileUrl(), holder.contentImage, options02,null);			
			}else {
				holder.contentImage.setVisibility(View.GONE);
			}
			//6.comment
			holder.comment.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(MainActivity.this, commentActivity.class);
					intent.putExtra("data",shuoshuo);
					startActivity(intent);
				}
			});
			
			return convertView;
		}
		class ViewHolder {
			public TextView share;
			public TextView comment;
			public TextView hate;
			public TextView love;
			public ImageView contentImage;
			public TextView contentText;
			public TextView userName;
			public ImageView favMark;
			public ImageView userLogo;
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
	
	
	//3.����ˢ�»�ȡ����
	private static final int STATE_REFRESH = 0;// ����ˢ��
	private static final int STATE_MORE = 1;// ���ظ���	
	private int limit = 10;		// ÿҳ��������10��
	private int curPage = 0;		// ��ǰҳ�ı�ţ���0��ʼ
	
	/**
	 * ��ҳ��ȡ����
	 * @param page	ҳ��
	 * @param actionType	ListView�Ĳ������ͣ�����ˢ�¡��������ظ��ࣩ
	 */
	private void queryData(final int page, final int actionType){
		//Log.i("bmob", "pageN:"+page+" limit:"+limit+" actionType:"+actionType);
		
		BmobQuery<ShuoShuo> query = new BmobQuery<ShuoShuo>();
		query.setLimit(limit);			// 1.����ÿҳ����������
		query.setSkip(page*limit);		// 2.�ӵڼ������ݿ�ʼ
		query.order("-createdAt");
		query.include("author");
		query.findObjects(this, new FindListener<ShuoShuo>() {
			
			@Override
			public void onSuccess(List<ShuoShuo> arg0) {
				// TODO Auto-generated method stub
				
				if(arg0.size()>0){
					//1.��ʼ��	�� ��������ˢ�²���ʱ������ǰҳ�ı������Ϊ0������bankCards��գ�������ӣ�
					if(actionType == STATE_REFRESH){
						curPage = 0;
						shuoshuoList.clear();
					}
					
					// �����β�ѯ��������ӵ�bankCards��
					for (ShuoShuo td : arg0) {
						shuoshuoList.add(td);
						adapter.notifyDataSetChanged();
					}
					
					// ������ÿ�μ��������ݺ󣬽���ǰҳ��+1������������ˢ�µ�onPullUpToRefresh�����оͲ���Ҫ����curPage��
					curPage++;
				//	showToast("��"+(page+1)+"ҳ���ݼ������");
				}else if(actionType == STATE_MORE){
					//����������û�и���������
					Toast.makeText(MainActivity.this, "not data", Toast.LENGTH_LONG).show();
				}else if(actionType == STATE_REFRESH){
					//����������û�и���������
					Toast.makeText(MainActivity.this, "not data", Toast.LENGTH_LONG).show();
				}
				list.onRefreshComplete();
			}		
			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, arg0+"error"+arg1, Toast.LENGTH_LONG).show();
				//���һ������ˢ��
				list.onRefreshComplete();
			}
		});
	}

}
