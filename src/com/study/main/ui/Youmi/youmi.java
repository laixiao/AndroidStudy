package com.study.main.ui.Youmi;
 
import net.youmi.android.offers.OffersManager;

import com.study.main.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class youmi extends Activity{

	private ImageView youmi_back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.youmi);
		
		youmi_back=(ImageView) this.findViewById(R.id.youmi_back);
		setListener();
		
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
	}

	private void setListener() {
		// TODO Auto-generated method stub
		youmi_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

}
