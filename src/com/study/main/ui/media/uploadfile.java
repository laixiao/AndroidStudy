package com.study.main.ui.media;

import java.io.File;
import java.util.List;

import com.study.main.R;
import com.study.main.Entity.UserMovie;

import h.in;
import io.vov.vitamio.utils.Log;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class uploadfile extends Activity {
	private Button choicefile01, uploadfile01, watchfile;
	private String path;
	private BmobUser currentuser;
	private TextView message01;

	// private Handler handle=new Handler(){
	//
	// @Override
	// public void handleMessage(Message msg) {
	// // TODO Auto-generated method stub
	// int jd=msg.arg1;
	//
	// message01.setText(""+jd);
	// }
	//
	//
	// };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uploadfile);

		choicefile01 = (Button) this.findViewById(R.id.choicefile01);
		uploadfile01 = (Button) this.findViewById(R.id.uploadfile01);
		watchfile = (Button) this.findViewById(R.id.watchfile);
		message01 = (TextView) this.findViewById(R.id.message01);

		path = getIntent().getStringExtra("path");
		setListener();

	}

	private void setListener() {
		// TODO Auto-generated method stub
		choicefile01.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent1 = new Intent(uploadfile.this,
						LocalList.class);
				startActivity(intent1);
			}
		});

		uploadfile01.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// Toast.makeText(uploadfile.this, path,
				// Toast.LENGTH_LONG).show();
				final BmobFile file = new BmobFile(new File(path));
				//currentuser = BmobUser.getCurrentUser(uploadfile.this);

				file.upload(uploadfile.this, new UploadFileListener() {

					@Override
					public void onProgress(Integer jindu) {
						// TODO Auto-generated method stub
						// Message msg=Message.obtain(handle);
						// msg.arg1=jindu;
						// msg.sendToTarget();
						//Toast.makeText(uploadfile.this, ""+(int)jindu, Toast.LENGTH_LONG).show();
					}

					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						final UserMovie movie = new UserMovie(file.getFilename(), file, null);
						movie.save(uploadfile.this, new SaveListener() {

							@Override
							public void onSuccess() {
								// TODO Auto-generated method stub
								Log.e("信息", "-->创建数据成功：" + movie.getObjectId());

							}

							@Override
							public void onFailure(int arg0, String arg1) {
								// TODO Auto-generated method stub
								Log.e("失败信息", "错误码" + arg0 + arg1);

							}
						});

					}

					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO Auto-generated method stub

					}
				});

			}
		});

		watchfile.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
//				BmobQuery<UserMovie> movieQuery = new BmobQuery<UserMovie>();
//				movieQuery.findObjects(uploadfile.this,new FindListener<UserMovie>() {
//
//							@Override
//							public void onSuccess(List<UserMovie> arg0) {
//								// TODO Auto-generated method stub
//								// toast("查询成功：共"+object.size()+"条数据。");
//								Toast.makeText(uploadfile.this, "电影信息"+arg0.get(0).getMoviefile().getFileUrl(), Toast.LENGTH_LONG).show();
//								Intent intent=new Intent(uploadfile.this,localVideoView.class);
//								intent.putExtra("path", arg0.get(0).getMoviefile().getFileUrl());
//								startActivity(intent);
//							}
//
//							@Override
//							public void onError(int arg0, String arg1) {
//								// TODO Auto-generated method stub
//
//							}
//						});

			}
		});

	}

}
