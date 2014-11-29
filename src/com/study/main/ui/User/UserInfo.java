package com.study.main.ui.User;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.study.main.R;
import com.study.main.Entity.User;
import com.study.main.utils.CacheUtils;
import com.study.main.utils.DateTimePickDialogUtil;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobQuery.CachePolicy;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
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
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class UserInfo extends Activity{
	//private FinalBitmap fb;
	private Button choicepicture,takephotos,submit01,userinfo_quit;

	private String dateTime;
	private String iconUrl;
	
	private ImageView personico;
	User currentUser;
	private EditText signatureEdit,sex_EditText,userinfo_phonenumber,userinfo_nickname;
	private TextView userinfo_birthday;
	
	private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			Bundle bundle=msg.getData();
			sex_EditText.setText(bundle.getString("Sex"));
			signatureEdit.setText(bundle.getString("Signature"));
			
			userinfo_phonenumber.setText(bundle.getString("PhoneNumber"));
			userinfo_nickname.setText(bundle.getString("Nickname"));
			userinfo_birthday.setText(bundle.getString("Birthday"));
			
		}
		
		
	};
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.userinfo);
		//fb=FinalBitmap.create(UserInfo.this);
		init();
		
	}



	private void init() {
		// TODO Auto-generated method stub
		choicepicture=(Button) this.findViewById(R.id.choicepicture);
		takephotos=(Button) this.findViewById(R.id.takephotos);
		userinfo_quit=(Button) this.findViewById(R.id.userinfo_quit);
		submit01=(Button) this.findViewById(R.id.submit01);
		personico=(ImageView) this.findViewById(R.id.personico);
		signatureEdit=(EditText) this.findViewById(R.id.signatureEdit);
		sex_EditText=(EditText) this.findViewById(R.id.sex_EditText);
		
		userinfo_phonenumber=(EditText) this.findViewById(R.id.userinfo_phonenumber);
		userinfo_nickname=(EditText) this.findViewById(R.id.userinfo_nickname);
		userinfo_birthday=(TextView) this.findViewById(R.id.userinfo_birthday);
		
		
		currentUser = BmobUser.getCurrentUser(this, User.class);
		 if (currentUser != null) {
            //鎵ц涓�绯诲垪鎿嶄綔鍟�
			 setListener();
			 initView();
        } else {
            Toast.makeText(UserInfo.this, "璇峰厛鐧诲綍鍟�",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(UserInfo.this, LoginAndRegister.class);
            startActivity(intent);
            finish();
        }
		
	}




	//鍒濆鍖栭〉闈㈠唴瀹�
	private void initView() {
		// TODO Auto-generated method stub
		BmobQuery<User> userQuery=new BmobQuery<User>();
		userQuery.addWhereEqualTo("objectId", currentUser.getObjectId());
		userQuery.setCachePolicy(CachePolicy.CACHE_THEN_NETWORK);
//		userQuery.setMaxCacheAge(100000L);
		userQuery.findObjects(UserInfo.this, new FindListener<User>() {
			
			@Override
			public void onSuccess(List<User> arg0) {
				// TODO Auto-generated method stub
				if(arg0.size()>0){
					
					// 濡傛灉鏌ヨ缁撴灉澶т簬0锛屽彇绗竴鏉℃暟鎹殑icon鍥捐繘琛屾樉绀�
				//	fb.display(personico,  arg0.get(0).getAvatar().getFileUrl());
					Message msg=Message.obtain(handler);//33鏄爣璇嗙
					Bundle bundle=new Bundle();
					bundle.putString("AvatarUrl", arg0.get(0).getAvatar().getFileUrl());
					bundle.putString("Sex", arg0.get(0).getSex());
					bundle.putString("Signature", arg0.get(0).getSignature());
					
					bundle.putString("Birthday", arg0.get(0).getBirthday());
					bundle.putString("PhoneNumber", arg0.get(0).getPhonenumber());
					bundle.putString("Nickname", arg0.get(0).getNickname());
					msg.setData(bundle);
					msg.sendToTarget();		
					 
				}
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
	}



	//浜嬩欢鐩戝惉
	private void setListener() {
		// TODO Auto-generated method stub
		//1.鐩稿唽閫夊彇
		choicepicture.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Date date1 = new Date(System.currentTimeMillis());
				dateTime = date1.getTime() + "";
				getAvataFromAlbum();//浠庣浉鍐岄�夊彇
			}
		});
		//2.鐩告満鎷嶇収
		takephotos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Date date = new Date(System.currentTimeMillis());
				dateTime = date.getTime() + "";
				getAvataFromCamera();
			}
		});
		
		
		
		
		//3.鎻愪氦淇敼
		submit01.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setAvata(iconUrl);
			}
		});

		//4.閫�鍑虹敤鎴�
		userinfo_quit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				BmobUser.logOut(UserInfo.this);   //娓呴櫎缂撳瓨鐢ㄦ埛瀵硅薄
				 Toast.makeText(UserInfo.this, "鎴愬姛閫�鍑�",Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
	            intent.setClass(UserInfo.this, LoginAndRegister.class);
	            startActivity(intent);
	            finish();
				
			}
		});
		
		//5.
		userinfo_birthday.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DateTimePickDialogUtil dateutil=new DateTimePickDialogUtil(UserInfo.this, "2000骞�5鏈�20鏃� 05:20");
				dateutil.dateTimePicKDialog(userinfo_birthday);
			}
		});
		
	}
	
	private void setAvata(String avataPath){
		//currentUser = BmobUser.getCurrentUser(this, User.class);
		if(avataPath!=null){
			final BmobFile file = new BmobFile(new File(iconUrl));
			file.upload(this, new UploadFileListener() {
				
				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					Toast.makeText(UserInfo.this, "鍥剧墖鏂囦欢涓婁紶--鎴愬姛", Toast.LENGTH_LONG).show();
				
					currentUser.setAvatar(file);
					//1.璁剧疆涓�х鍚�
					if(!TextUtils.isEmpty(signatureEdit.getText().toString().trim())){
						currentUser.setSignature(signatureEdit.getText().toString().trim());
					}
					//2.璁剧疆鎬у埆
					currentUser.setSex("鐢�");
					//3.璁剧疆鎵嬫満鍙风爜
					if(userinfo_phonenumber.getText().toString().trim().length()==11){
					currentUser.setPhonenumber(userinfo_phonenumber.getText().toString().trim());
					}else{
						Toast.makeText(UserInfo.this, "璇锋鏌ユ墜鏈哄彿鐮佺殑闀垮害", Toast.LENGTH_LONG).show();
					}
					//4.璁剧疆鏄电О
					currentUser.setNickname(userinfo_nickname.getText().toString().trim());
					//5.璁剧疆鐢熸棩
					currentUser.setBirthday(userinfo_birthday.getText().toString().trim());
					
					currentUser.update(UserInfo.this, new UpdateListener() {
						
						@Override
						public void onSuccess() {
							
							currentUser = BmobUser.getCurrentUser(UserInfo.this,User.class);
						
						
							setResult(RESULT_OK);
							Toast.makeText(UserInfo.this,"淇濆瓨鎴愬姛", Toast.LENGTH_LONG).show();
						}

						@Override
						public void onFailure(int arg0, String arg1) {
							// TODO Auto-generated method stub
							
							Toast.makeText(UserInfo.this,"淇濆瓨澶辫触", Toast.LENGTH_LONG).show();
						}
					});
				}

				@Override
				public void onProgress(Integer arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO Auto-generated method stub
					//LogUtils.i(TAG, "涓婁紶鏂囦欢澶辫触銆�"+arg1);
					Toast.makeText(UserInfo.this, arg0+"鍥剧墖鏂囦欢涓婁紶--澶辫触"+arg1, Toast.LENGTH_LONG).show();
				}
			});
		}else{
		
			//1.璁剧疆涓�х鍚�
			if(!TextUtils.isEmpty(signatureEdit.getText().toString().trim())){
				currentUser.setSignature(signatureEdit.getText().toString().trim());
			}
			//2.璁剧疆鎬у埆
			currentUser.setSex(sex_EditText.getText().toString().trim());
			//3.璁剧疆鎵嬫満鍙风爜
			if(userinfo_phonenumber.getText().toString().trim().length()==11){
			currentUser.setPhonenumber(userinfo_phonenumber.getText().toString().trim());
			}else{
				Toast.makeText(UserInfo.this, "璇锋鏌ユ墜鏈哄彿鐮佺殑闀垮害", Toast.LENGTH_LONG).show();
			}
			//4.璁剧疆鏄电О
			currentUser.setNickname(userinfo_nickname.getText().toString().trim());
			//5.璁剧疆鐢熸棩
			currentUser.setBirthday(userinfo_birthday.getText().toString().trim());
			
			
			currentUser.update(this, new UpdateListener() {
					
					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
					
						setResult(RESULT_OK);
						Toast.makeText(UserInfo.this,"淇濆瓨鎴愬姛", Toast.LENGTH_LONG).show();
					}
	
				
					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO Auto-generated method stub
						Toast.makeText(UserInfo.this,"淇濆瓨澶辫触"+arg1, Toast.LENGTH_LONG).show();
					}
				});
		}
	}
	
	
	
	private void getAvataFromAlbum(){
		Intent intent2 = new Intent(Intent.ACTION_GET_CONTENT);
		intent2.setType("image/*");
		startActivityForResult(intent2, 2);
	}
	private void getAvataFromCamera(){
		File f = new File(CacheUtils.getCacheDirectory(this, true, "icon") + dateTime);
		if (f.exists()) {
			f.delete();
		}
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Uri uri = Uri.fromFile(f);
		//Log.e("uri", uri + "");
		//鎰忓浘寮�鍚浉鏈�
		Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		camera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		startActivityForResult(camera, 1);
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			switch (requestCode) {
			case 1://寮�鍚浉鏈哄緱鍒扮浉鐗�
				String files =CacheUtils.getCacheDirectory(this, true, "icon") + dateTime;
				File file = new File(files);
				if(file.exists()&&file.length() > 0){
					Uri uri = Uri.fromFile(file);
					startPhotoZoom(uri);
				}else{
					
				}
				break;
			case 2:
				if (data == null) {
					return;
				}
				//寮�鍚浉鍐�
				startPhotoZoom(data.getData());
				break;
			case 3:
				if (data != null) {
					Bundle extras = data.getExtras();
					if (extras != null) {
						Bitmap bitmap = extras.getParcelable("data");
						// 閿熸枻鎷烽敓鏂ゆ嫹鍥剧墖
						iconUrl = saveToSdCard(bitmap);
						personico.setImageBitmap(bitmap);
					}
				}
				break;
			default:
				break;
			}
		}
	}
	
	
	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓绲歳op=true閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鑺傚尅鎷烽敓鏂ゆ嫹閿熸枻鎷稩ntent閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹绀洪敓鏂ゆ嫹VIEW閿熺即瑁佺》鎷�
		// aspectX aspectY 閿熻鍖℃嫹鍙╄皨閿熸枻鎷烽敓锟�
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 閿熻瑁佺》鎷峰浘鐗囬敓鏂ゆ嫹閿燂拷
		intent.putExtra("outputX", 120);
		intent.putExtra("outputY", 120);
		intent.putExtra("crop", "true");
		intent.putExtra("scale", true);// 鍘婚敓鏂ゆ嫹閿熻妭鎲嬫嫹
		intent.putExtra("scaleUpIfNeeded", true);// 鍘婚敓鏂ゆ嫹閿熻妭鎲嬫嫹
		// intent.putExtra("noFaceDetection", true);//閿熸枻鎷烽敓鏂ゆ嫹璇嗛敓鏂ゆ嫹
		intent.putExtra("return-data", true);
		//鎰忓浘鐮佽繑鍥炲鐞�
		startActivityForResult(intent, 3);

	}
	public String saveToSdCard(Bitmap bitmap){
		String files =CacheUtils.getCacheDirectory(this, true, "icon") + dateTime+"_12";
		File file=new File(files);
        try {
            FileOutputStream out=new FileOutputStream(file);
            if(bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)){
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
	
	private Bitmap compressImageFromFile(String srcPath) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		newOpts.inJustDecodeBounds = true;//鍙杈�,涓嶈鍐呭
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
		newOpts.inSampleSize = be;//璁剧疆閲囨牱鐜�
		
		newOpts.inPreferredConfig = Config.ARGB_8888;//璇ユā寮忔槸榛樿鐨�,鍙笉璁�
		newOpts.inPurgeable = true;// 鍚屾椂璁剧疆鎵嶄細鏈夋晥
		newOpts.inInputShareable = true;//銆傚綋绯荤粺鍐呭瓨涓嶅鏃跺�欏浘鐗囪嚜鍔ㄨ鍥炴敹
		
		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
//		return compressBmpFromBmp(bitmap);//鍘熸潵鐨勬柟娉曡皟鐢ㄤ簡杩欎釜鏂规硶浼佸浘杩涜浜屾鍘嬬缉
									//鍏跺疄鏄棤鏁堢殑,澶у灏界灏濊瘯
		return bitmap;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
