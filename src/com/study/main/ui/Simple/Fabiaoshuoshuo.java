package com.study.main.ui.Simple;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import jdk.internal.org.objectweb.asm.Handle;

import com.study.main.R;
import com.study.main.Entity.ShuoShuo;
import com.study.main.Entity.User;
import com.study.main.ui.User.LoginAndRegister;
import com.study.main.utils.CacheUtils;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;
import cn.pedant.SweetAlert.SweetAlertDialog;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Fabiaoshuoshuo extends Activity {

	private static final int REQUEST_CODE_ALBUM = 1;
	private static final int REQUEST_CODE_CAMERA = 2;
	private EditText content;
	private LinearLayout openLayout;
	private LinearLayout takeLayout;
	private ImageView albumPic;
	private ImageView takePic;
	private ImageButton fanhui01,fabiao01;
	String dateTime;
	String targeturl = null;
	User currentUser;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fabiaoshuoshuo);
		currentUser = BmobUser.getCurrentUser(Fabiaoshuoshuo.this,User.class);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		content = (EditText) findViewById(R.id.edit_content);
		openLayout = (LinearLayout) findViewById(R.id.open_layout);
		takeLayout = (LinearLayout) findViewById(R.id.take_layout);
		albumPic = (ImageView) findViewById(R.id.open_pic);
		takePic = (ImageView) findViewById(R.id.take_pic);
		fabiao01 = (ImageButton) findViewById(R.id.fabiao01);
		fanhui01 = (ImageButton) findViewById(R.id.fanhui01);
		

		setListener();
	}

	private void setListener() {
		// TODO Auto-generated method stub
		//0.发表说说
		fabiao01.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String commitContent = content.getText().toString().trim();
				if (TextUtils.isEmpty(commitContent)&&targeturl == null) {
					new SweetAlertDialog(Fabiaoshuoshuo.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("亲，内容不能为空")
                    .setContentText("")
                    .show();
				}else if (targeturl == null) {
					publishWithoutFigure(commitContent, null);
				} else {
					publish(commitContent);
				}
			}
		});
		
		//1.打开相机获取图片
		openLayout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//1.获取时间
				Date date1 = new Date(System.currentTimeMillis());
				dateTime = date1.getTime() + "";
				//2.开启相机
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				//设置数据和类型
				intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
				startActivityForResult(intent, REQUEST_CODE_ALBUM);
			}
		});
		//1.照相获取图片
		takeLayout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//1.获取时间
				Date date = new Date(System.currentTimeMillis());
				dateTime = date.getTime() + "";
				//2.构建缓存
				File f = new File(CacheUtils.getCacheDirectory(Fabiaoshuoshuo.this, true, "pic") + dateTime);
				if (f.exists()) {
					f.delete();
				}
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				//3.根据路径获取Uri
				Uri uri = Uri.fromFile(f);
				//Log.e("uri", uri + "");
				//4.开启相机
				Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				camera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
				startActivityForResult(camera, REQUEST_CODE_CAMERA);
			}
		});
		//2.返回按钮
		fanhui01.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}

	private void publishWithoutFigure(final String commitContent,final BmobFile figureFile) {		
		final ShuoShuo shuoshuo = new ShuoShuo();
		shuoshuo.setAuthor(currentUser);
		shuoshuo.setContent(commitContent);
		// 图片文件的路径
		if (figureFile != null) {
			shuoshuo.setContentfigureurl(figureFile);
		}
		
		shuoshuo.save(this, new SaveListener() {

			@Override
			public void onSuccess() {
				new SweetAlertDialog(Fabiaoshuoshuo.this, SweetAlertDialog.SUCCESS_TYPE)
	              .setTitleText("发表说说成功")
	              .setContentText("返回主页")
	              .show();
				setResult(RESULT_OK);
				
				finish();
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				new SweetAlertDialog(Fabiaoshuoshuo.this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("发表失败")
                .setContentText("原因"+arg1)
                .show();
			}
		});
		
	}

	/*
	 * 发表带图片
	 */
	private void publish(final String commitContent) {
		
        
		final BmobFile figureFile = new BmobFile(new File(targeturl));
		figureFile.uploadblock(Fabiaoshuoshuo.this, new UploadFileListener() {
			
			

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				publishWithoutFigure(commitContent, figureFile);
			}
			
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				new SweetAlertDialog(Fabiaoshuoshuo.this, SweetAlertDialog.ERROR_TYPE)
	              .setTitleText("上传失败")
	              .setContentText("原因"+arg1)
	              .show();
			}
		});
//		figureFile.upload(this, new UploadFileListener() {
//			public void onSuccess() {			
//				publishWithoutFigure(commitContent, figureFile);
//				
//			}
//			public void onProgress(Integer arg0) {
//			}
//			public void onFailure(int arg0, String arg1) {
//				new SweetAlertDialog(Fabiaoshuoshuo.this, SweetAlertDialog.ERROR_TYPE)
//                .setTitleText("上传失败")
//                .setContentText("原因"+arg1)
//                .show();
//			}
//		});
	}

	// 2.获得图片源
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {	
		super.onActivityResult(requestCode, resultCode, data);		
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case REQUEST_CODE_ALBUM:
				String fileName = null;
				if (data != null) {
					Uri originalUri = data.getData();
					ContentResolver cr = getContentResolver();
					Cursor cursor = cr.query(originalUri, null, null, null,null);
					if (cursor.moveToFirst()) {
						do {
							fileName = cursor.getString(cursor.getColumnIndex("_data"));
							// LogUtils.i(TAG,"get album:"+fileName);
						} while (cursor.moveToNext());
					}
					Bitmap bitmap = compressImageFromFile(fileName);
					targeturl = saveToSdCard(bitmap);
					albumPic.setBackgroundDrawable(new BitmapDrawable(bitmap));
					takeLayout.setVisibility(View.GONE);
				}
				break;
			case REQUEST_CODE_CAMERA:
				String files = CacheUtils.getCacheDirectory(this, true, "pic")+ dateTime;
				File file = new File(files);
				if (file.exists()) {
					Bitmap bitmap = compressImageFromFile(files);
					targeturl = saveToSdCard(bitmap);
					takePic.setBackgroundDrawable(new BitmapDrawable(bitmap));
					openLayout.setVisibility(View.GONE);
				} else {

				}
				break;
			default:
				break;
			}
		}
	}

	private Bitmap compressImageFromFile(String srcPath) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		newOpts.inJustDecodeBounds = true;// 只读边,不读内容
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		float hh = 800f;//
		float ww = 480f;//
		int be = 1;
		if (w > h && w > ww) {
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置采样率

		newOpts.inPreferredConfig = Config.ARGB_8888;// 该模式是默认的,可不设
		newOpts.inPurgeable = true;// 同时设置才会有效
		newOpts.inInputShareable = true;// 。当系统内存不够时候图片自动被回收

		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		// return compressBmpFromBmp(bitmap);//原来的方法调用了这个方法企图进行二次压缩
		// 其实是无效的,大家尽管尝试
		return bitmap;
	}
	//保存获得的位图到sd卡，并返回该图片的路径。
	public String saveToSdCard(Bitmap bitmap) {
		String files = CacheUtils.getCacheDirectory(this, true, "pic")+ dateTime + "_11";
		File file = new File(files);
		try {
			FileOutputStream out = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.JPEG, 50, out)) {
				out.flush();
				out.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// LogUtils.i(TAG, file.getAbsolutePath());
		return file.getAbsolutePath();
	}
}
