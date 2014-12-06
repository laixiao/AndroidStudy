package com.study.main.ui.User;

import com.study.main.R;
import com.study.main.Entity.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginAndRegister extends Activity {

	private LinearLayout loginlayout01,registerlayout01;
	
	private EditText username01, password01,register_username01,register_password01,register_password02;
	private Button  register01,register_register01,register_return01;
	private ImageButton login01,loginclearbutton,register_clearbutton01;
	User user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.loginandregister);

		init();
		
	}
	private void init() {
		// TODO Auto-generated method stub
		login01 = (ImageButton) this.findViewById(R.id.login01);
		register01 = (Button) this.findViewById(R.id.register01);
		register_register01 = (Button) this.findViewById(R.id.register_register01);
		register_return01 = (Button) this.findViewById(R.id.register_return01);
		username01 = (EditText) this.findViewById(R.id.username01);
		password01 = (EditText) this.findViewById(R.id.password01);
		register_username01 = (EditText) this.findViewById(R.id.register_username01);
		register_password01 = (EditText) this.findViewById(R.id.register_password01);
		register_password02 = (EditText) this.findViewById(R.id.register_password02);
		loginclearbutton=(ImageButton) this.findViewById(R.id.loginclearbutton);
		register_clearbutton01=(ImageButton) this.findViewById(R.id.register_clearbutton01);
		registerlayout01=(LinearLayout) this.findViewById(R.id.registerlayout01);
		loginlayout01=(LinearLayout) this.findViewById(R.id.loginlayout01);

	
		username01.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (username01.getText().toString().length() > 0) {
					loginclearbutton.setVisibility(View.VISIBLE);
				}else{
					loginclearbutton.setVisibility(View.INVISIBLE);
				}
			}
		});
		register_username01.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (register_username01.getText().toString().length() > 0) {
					register_clearbutton01.setVisibility(View.VISIBLE);
				}else{
					register_clearbutton01.setVisibility(View.INVISIBLE);
				}
			}
		});
		
		
		
		
		loginclearbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				username01.setText("");
				loginclearbutton.setVisibility(View.INVISIBLE);
			}
		});
		register_clearbutton01.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				register_username01.setText("");
				register_clearbutton01.setVisibility(View.INVISIBLE);
			}
		});
		
		//登陆按钮
		login01.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String username=username01.getText().toString();
				String password=password01.getText().toString();
				user = new User();
				
				user.setUsername(username);
				user.setPassword(password);
			
				user.login(LoginAndRegister.this, new SaveListener() {
				    @Override
				    public void onSuccess() {
				        // TODO Auto-generated method stub
				      Toast.makeText(LoginAndRegister.this, "登陆成功，正在获取数据", Toast.LENGTH_LONG).show();
				      Intent intent=new Intent(LoginAndRegister.this, UserInfo.class);
				      startActivity(intent);
				      finish();
				    }
				    @Override
				    public void onFailure(int code, String msg) {
				        // TODO Auto-generated method stub
				    	  Toast.makeText(LoginAndRegister.this,code+ "登陆失败"+msg, Toast.LENGTH_LONG).show();
				    }
				});
				
				
			}
		});
		//跳转注册页面按钮
		register01.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				loginlayout01.setVisibility(View.GONE);
				registerlayout01.setVisibility(View.VISIBLE);
			}
		});

		//注册按钮
		register_register01.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String username=register_username01.getText().toString();
				String password=register_password01.getText().toString();
				String password2=register_password02.getText().toString();
				user = new User();
				if(username.length()<5||username.length()>20||password.length()<5||password.length()>25||password2.length()<5||password2.length()>20||password.compareTo(password2)!=0){
					new SweetAlertDialog(LoginAndRegister.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("请检查")
                    .setContentText("1.账号长度：5至20位\n2.密码长度：5至20位\n3.两次输出的密码必须相同")
                    .show();
				}else{
				user.setUsername(username);
				user.setPassword(password);
				
				user.signUp(LoginAndRegister.this, new SaveListener() {
					
					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						 Toast.makeText(LoginAndRegister.this,"注册成功啦", Toast.LENGTH_LONG).show();
						 Intent intent=new Intent(LoginAndRegister.this, UserInfo.class);
						 startActivity(intent);
						 finish();
					}
					
					@Override
					public void onFailure(int code, String msg) {
						// TODO Auto-generated method stub
						 Toast.makeText(LoginAndRegister.this,code+ "登陆失败"+msg, Toast.LENGTH_LONG).show();
					}
				});
				
				}
				
				
				
			}
		});
		//返回登陆界面按钮
		register_return01.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				loginlayout01.setVisibility(View.VISIBLE);
				registerlayout01.setVisibility(View.GONE);
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
