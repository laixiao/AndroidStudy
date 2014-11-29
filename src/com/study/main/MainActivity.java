package com.study.main;

import cn.bmob.v3.BmobUser;

import com.study.main.ResideMenu.ResideMenu;
import com.study.main.ResideMenu.ResideMenuInfo;
import com.study.main.ResideMenu.ResideMenuItem;
import com.study.main.ResideMenu.ResideMenuSetting;
import com.study.main.test.test1;
import com.study.main.ui.Simple.Fabiaoshuoshuo;
import com.study.main.ui.Simple.SettingActivity;
import com.study.main.ui.Simple.laoluoActivity;
import com.study.main.ui.Simple.mainActivity01;
import com.study.main.ui.User.LoginAndRegister;
import com.study.main.ui.User.UserInfo;
import com.study.main.ui.media.LocalList;
import com.study.main.ui.media.uploadfile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private RelativeLayout titlelayout01, titlelayout02, titlelayout03,
			middlelayout1, middlelayout2, middlelayout3;
	private ListView listView01;
	private ImageButton button1, button2, button3;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		setUpMenu();
		setListener();
		init();

	}

	private void init() {

		titlelayout01 = (RelativeLayout) this.findViewById(R.id.titlelayout01);
		titlelayout02 = (RelativeLayout) this.findViewById(R.id.titlelayout02);
		titlelayout03 = (RelativeLayout) this.findViewById(R.id.titlelayout03);
		middlelayout1 = (RelativeLayout) this.findViewById(R.id.middlelayout1);
		middlelayout2 = (RelativeLayout) this.findViewById(R.id.middlelayout2);
		middlelayout3 = (RelativeLayout) this.findViewById(R.id.middlelayout3);
		button1 = (ImageButton) this.findViewById(R.id.button1);
		button2 = (ImageButton) this.findViewById(R.id.button2);
		button3 = (ImageButton) this.findViewById(R.id.button3);
		listView01 = (ListView) this.findViewById(R.id.listView01);

		fabiaoshuoshuo = (Button) this.findViewById(R.id.fabiaoshuoshuo);
		buttonListener();

	}

	private void buttonListener() {
		// TODO Auto-generated method stub
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				titlelayout01.setVisibility(View.VISIBLE);
				titlelayout02.setVisibility(View.INVISIBLE);
				titlelayout03.setVisibility(View.INVISIBLE);
				middlelayout1.setVisibility(View.VISIBLE);
				middlelayout2.setVisibility(View.INVISIBLE);
				middlelayout3.setVisibility(View.INVISIBLE);
				button1.setBackgroundResource(R.drawable.inactive);

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

			}
		});

		fabiaoshuoshuo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BmobUser currentUser = BmobUser
						.getCurrentUser(MainActivity.this);
				if (currentUser != null) {
					// 允许用户使用应用,即有了用户的唯一标识符，可以作为发布内容的字段
					// String name = currentUser.getUsername();
					// String email = currentUser.getEmail();
					// LogUtils.i(TAG,"username:"+name+",email:"+email);
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, Fabiaoshuoshuo.class);
					startActivity(intent);
				} else {
					// 缓存用户对象为空时， 可打开用户注册界面…
					Toast.makeText(MainActivity.this, "请先登录啦",
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

	private void setUpMenu() {
		imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
		//
		//
		// text1 = (TextView) findViewById(R.id.text1);
		// text2 = (TextView) findViewById(R.id.text2);
		// text3 = (TextView) findViewById(R.id.text3);

		// attach to current activity;
		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(R.drawable.residebackground1);
		resideMenu.attachToActivity(this);
		resideMenu.setMenuListener(menuListener);
		// valid scale factor is between 0.0f and 1.0f. leftmenu'width is
		// 150dip.
		resideMenu.setScaleValue(0.6f);
		// 禁止使用右侧菜单
		resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
		// resideMenu.setDirectionDisable(ResideMenu.DIRECTION_RIGHT);

		// create menu items;
		menu_item01 = new ResideMenuItem(this, R.drawable.residebutton1, R.string.menu_item01);
		menu_item02 = new ResideMenuItem(this, R.drawable.residebutton5, R.string.menu_item02);
		menu_item03 = new ResideMenuItem(this, R.drawable.residebutton3,R.string.menu_item03);
		menu_item04 = new ResideMenuItem(this, R.drawable.residebutton4,R.string.menu_item04);
		menu_item05 = new ResideMenuItem(this, R.drawable.residebutton2,R.string.menu_item05);
		// itemFile = new ResideMenuItem(this, R.drawable.residebutton4,
		// "子菜单1");

		info = new ResideMenuInfo(this, R.drawable.icon_profile, "name", "money");
		setting = new ResideMenuSetting(this, "setting", "");

		resideMenu.addMenuItem(menu_item01, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(menu_item02, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(menu_item03, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(menu_item04, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(menu_item05, ResideMenu.DIRECTION_LEFT);
		// resideMenu.addMenuItem(itemFile, ResideMenu.DIRECTION_LEFT);

		resideMenu.addMenuInfo(info);
		resideMenu.addMenuSetting(setting);

	}

	private void setListener() {

		menu_item01.setOnClickListener(this);
		menu_item02.setOnClickListener(this);
		menu_item03.setOnClickListener(this);
		menu_item04.setOnClickListener(this);
		menu_item05.setOnClickListener(this);
		// itemFile.setOnClickListener(this);

		// text1.setOnClickListener(this);
		// text2.setOnClickListener(this);
		// text3.setOnClickListener(this);

		info.setOnClickListener(this);
		setting.setOnClickListener(this);

		imageButton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
			}
		});
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return resideMenu.dispatchTouchEvent(ev);
	}

	@Override
	public void onClick(View view) {
		// if (view.getId() == R.id.text1) {
		// changeFragment(new NewsFragment());
		// } else if (view.getId() == R.id.text2) {
		// changeFragment(new ContactsFragment());
		// } else if (view.getId() == R.id.text3) {
		// changeFragment(new DongtaiFragment());
		// } else
		if (view == menu_item01) {
			Intent intent = new Intent();
			// intent.putExtra("flag", "MainActivity");
			intent.setClass(getApplicationContext(), laoluoActivity.class);
			startActivity(intent);
		} else if (view == menu_item02) {
			Toast.makeText(MainActivity.this, "menu_item02", Toast.LENGTH_LONG)
			.show();
			// Intent intent = new Intent();
			// intent.putExtra("flog", "钱包");
			// intent.setClass(getApplicationContext(), mediaActivity01.class);
			// startActivity(intent);
		} else if (view == menu_item03) {
			Intent intent = new Intent();
			// intent.putExtra("flog", "装扮");
			intent.setClass(getApplicationContext(), LocalList.class);
			startActivity(intent);
		} else if (view == menu_item04) {
			Intent intent = new Intent();
			// intent.putExtra("flog", "收藏");
			intent.setClass(getApplicationContext(), mainActivity01.class);
			startActivity(intent);
		} else if (view == menu_item05) {
			Intent intent = new Intent();
			// intent.putExtra("flog", "相册");
			intent.setClass(getApplicationContext(), test1.class);
			startActivity(intent);
		}
		// else if (view == itemFile) {
		// Intent intent = new Intent();
		// intent.putExtra("flog", "文件");
		// intent.setClass(getApplicationContext(), SettingActivity.class);
		// startActivity(intent);
		// }
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

	// What good method is to access resideMenu？
	public ResideMenu getResideMenu() {
		return resideMenu;
	}

	// 监听手机上的BACK键
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 判断菜单是否关闭
			if (is_closed) {
				// // 判断两次点击的时间间隔（默认设置为2秒）
				// if ((System.currentTimeMillis() - mExitTime) > 2000) {
				// Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				//
				// mExitTime = System.currentTimeMillis();
				// } else {
				// finish();
				// System.exit(0);
				// super.onBackPressed();
				// }
				// 模拟HOME键效果
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

	// 1.加载底部菜单
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	// 2.监听底部菜单
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.exit:
			Toast.makeText(MainActivity.this, "触发了退出方法", Toast.LENGTH_LONG)
					.show();
			finish();
			System.exit(0);
			super.onBackPressed();
			break;
		case R.id.checkversion:
			Toast.makeText(MainActivity.this, "触发了版本更新方法", Toast.LENGTH_LONG)
					.show();
			break;
		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

}
