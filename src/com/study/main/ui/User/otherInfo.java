package com.study.main.ui.User;
 
import com.study.main.R;
import com.study.main.Entity.ShuoShuo;
import com.study.main.Entity.User;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class otherInfo extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.otheractivity);
		
		User user=(User) getIntent().getSerializableExtra("data");
		Toast.makeText(otherInfo.this,user.toString(), Toast.LENGTH_LONG).show();
		
	}

}
