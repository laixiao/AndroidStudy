package com.study.main.ui.ResideMenuItemUi;

import com.study.main.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class SettingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.settingactivity);

//		TextView text = (TextView) findViewById(R.id.textView);
//		Intent receive = getIntent();
//		String flog = receive.getStringExtra("flog");
//
//		text.setText(flog);

	}
}
