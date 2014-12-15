package com.study.main.ui.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.study.main.R;
import com.study.main.Entity.User;
import com.study.main.utils.CacheUtils;
import com.study.main.utils.DateTimePickDialogUtil;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobQuery.CachePolicy;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UserInfo extends Activity {
	private Button submit01, userinfo_quit;
	private String dateTime,iconUrl,Signature;
	private ImageView personico, user_infosex,user_info_imageView5,user_info_back;
	User currentUser;
	private TextView userinfo_birthday, signatureEdit,userinfo_phonenumber, userinfo_nickname,user_info_birthday,user_info_name,user_info_phonenumber;
	boolean isSex;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			
			Bundle bundle = msg.getData();			
			signatureEdit.setText(bundle.getString("Signature"));
			userinfo_phonenumber.setText(bundle.getString("PhoneNumber"));
			userinfo_nickname.setText(bundle.getString("Nickname"));
			userinfo_birthday.setText(bundle.getString("Birthday"));

		}

	};
	DisplayImageOptions options;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.user_info);
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.icon_profile)
		.showImageForEmptyUri(R.drawable.icon_profile)
		.showImageOnFail(R.drawable.icon_profile)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.displayer(new RoundedBitmapDisplayer(90))
		.build();
		// TODO Auto-generated constructor stub
		init();

	}

	private void init() {
		userinfo_quit = (Button) this.findViewById(R.id.userinfo_quit);
		submit01 = (Button) this.findViewById(R.id.submit01);
		personico = (ImageView) this.findViewById(R.id.personico);
		user_infosex = (ImageView) this.findViewById(R.id.user_infosex);
		user_info_back= (ImageView) this.findViewById(R.id.user_info_back);
		user_info_imageView5 = (ImageView) this.findViewById(R.id.user_info_imageView5);
		user_info_birthday=(TextView) this.findViewById(R.id.user_info_birthday);
		signatureEdit = (TextView) this.findViewById(R.id.signatureEdit);
		userinfo_phonenumber = (TextView) this.findViewById(R.id.userinfo_phonenumber);
		user_info_phonenumber = (TextView) this.findViewById(R.id.user_info_phonenumber);
		userinfo_nickname = (TextView) this.findViewById(R.id.userinfo_nickname);
		userinfo_birthday = (TextView) this.findViewById(R.id.userinfo_birthday);
		user_info_name= (TextView) this.findViewById(R.id.user_info_name);
		

		currentUser = BmobUser.getCurrentUser(this, User.class);
		if (currentUser != null) {			
			setListener();
			initView();
		} else {
			Toast.makeText(UserInfo.this, "please login", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent();
			intent.setClass(UserInfo.this, LoginAndRegister.class);
			startActivity(intent);
			finish();
		}
	}

	
	private void initView() {
		// TODO Auto-generated method stub
		BmobQuery<User> userQuery = new BmobQuery<User>();
		userQuery.setCachePolicy(CachePolicy.CACHE_THEN_NETWORK);
		// userQuery.setMaxCacheAge(100000L);
		userQuery.getObject(UserInfo.this,currentUser.getObjectId(), new GetListener<User>() {			
			public void onSuccess(User arg0) {					
				if(arg0.getAvatar()!=null){
					ImageLoader.getInstance().displayImage(arg0.getAvatar().getFileUrl(UserInfo.this), personico, options,
							new SimpleImageLoadingListener(){
								@Override
								public void onLoadingComplete(String imageUri, View view,
										Bitmap loadedImage) {
									// TODO Auto-generated method stub
									super.onLoadingComplete(imageUri, view, loadedImage);
								}						
					});
				}
					isSex = arg0.isSex();
					if (isSex) {
						user_infosex.setImageResource(R.drawable.user_infosex2);
					} else {
						user_infosex.setImageResource(R.drawable.user_infosex1);
					}
					Message msg = Message.obtain(handler);
					Bundle bundle = new Bundle();
					bundle.putString("Signature", arg0.getSignature());
					bundle.putString("Birthday", arg0.getBirthday());
					bundle.putString("PhoneNumber", arg0.getPhonenumber());
					bundle.putString("Nickname", arg0.getNickname());
					msg.setData(bundle);
					msg.sendToTarget();

				
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
		});
		

	}

	
	private void setListener() {
		
		//1.set name
		user_info_name.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LayoutInflater inflater = getLayoutInflater();
				View layout = inflater.inflate(R.layout.dialog01,(ViewGroup) findViewById(R.id.dialog));
				final EditText dialog_qianming=(EditText) layout.findViewById(R.id.dialog_qianming);
				 new AlertDialog.Builder(UserInfo.this)
				 .setTitle("设置昵称")
				 .setView(layout)
				 .setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Signature=dialog_qianming.getText().toString().trim();
						userinfo_nickname.setText(Signature);
						Toast.makeText(UserInfo.this, "昵称修改成功",Toast.LENGTH_SHORT).show();
					}
				 	})
			     .setNegativeButton("取消",null)
				 .show();
				
			}
		});
		
		
		//2.set  signature
		user_info_imageView5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				LayoutInflater inflater = getLayoutInflater();
				View layout = inflater.inflate(R.layout.dialog01,(ViewGroup) findViewById(R.id.dialog));
				final EditText dialog_qianming=(EditText) layout.findViewById(R.id.dialog_qianming);
				 new AlertDialog.Builder(UserInfo.this)
				 .setTitle("设置个性签名")
				 .setView(layout)
				 .setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Signature=dialog_qianming.getText().toString().trim();
						signatureEdit.setText(Signature);
						Toast.makeText(UserInfo.this, "个性签名修改成功",Toast.LENGTH_SHORT).show();
					}
				 	})
			     .setNegativeButton("取消",null)
				 .show();

			}
		});

		//3.set sex
		user_infosex.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new SweetAlertDialog(UserInfo.this,
						SweetAlertDialog.CUSTOM_IMAGE_TYPE)
						.setTitleText("选择性别").setContentText("")
						.setCustomImage(R.drawable.user_infosex)
						.showCancelButton(true).setCancelText("女")
						.setConfirmText("男")
						.setCancelClickListener(new OnSweetClickListener() {

							@Override
							public void onClick(
									SweetAlertDialog sweetAlertDialog) {
								// TODO Auto-generated method stub
								isSex = false;
								user_infosex
										.setImageResource(R.drawable.user_infosex1);
								Toast.makeText(UserInfo.this, "修改成功",
										Toast.LENGTH_SHORT).show();
								sweetAlertDialog.dismiss();
							}
						}).setConfirmClickListener(new OnSweetClickListener() {

							@Override
							public void onClick(
									SweetAlertDialog sweetAlertDialog) {
								// TODO Auto-generated method stub
								isSex = true;
								user_infosex
										.setImageResource(R.drawable.user_infosex2);
								Toast.makeText(UserInfo.this, "修改成功",
										Toast.LENGTH_SHORT).show();
								sweetAlertDialog.dismiss();
							}
						}).show();
			}
		});

		// 3.submit
		submit01.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setAvata(iconUrl);
			}
		});

		// 4.quit login
		userinfo_quit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				BmobUser.logOut(UserInfo.this); // 娓呴櫎缂撳瓨鐢ㄦ埛瀵硅薄
				Toast.makeText(UserInfo.this, "success", Toast.LENGTH_SHORT)
						.show();
				Intent intent = new Intent();
				intent.setClass(UserInfo.this, LoginAndRegister.class);
				startActivity(intent);
				finish();

			}
		});

		// 5.birthday
		user_info_birthday.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DateTimePickDialogUtil dateutil = new DateTimePickDialogUtil(
						UserInfo.this, "2000年05月20日 05:60");
				dateutil.dateTimePicKDialog(userinfo_birthday);
			}
		});

		// 6.persion_ico
		personico.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new SweetAlertDialog(UserInfo.this,
						SweetAlertDialog.CUSTOM_IMAGE_TYPE)
						.setTitleText("选择相片").setContentText("")
						.setCustomImage(R.drawable.titlebutton01)
						.showCancelButton(true).setCancelText("相机拍照")
						.setConfirmText("本地相册")
						.setCancelClickListener(new OnSweetClickListener() {

							@Override
							public void onClick(
									SweetAlertDialog sweetAlertDialog) {
								// TODO Auto-generated method stub
								Date date = new Date(System.currentTimeMillis());
								dateTime = date.getTime() + "";
								getAvataFromCamera();
								sweetAlertDialog.dismiss();
							}
						}).setConfirmClickListener(new OnSweetClickListener() {

							@Override
							public void onClick(
									SweetAlertDialog sweetAlertDialog) {
								Date date1 = new Date(System
										.currentTimeMillis());
								dateTime = date1.getTime() + "";
								getAvataFromAlbum();
								sweetAlertDialog.dismiss();
							}
						}).show();
			}
		});
		
		//7.set phonenumber
		user_info_phonenumber.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LayoutInflater inflater = getLayoutInflater();
				View layout = inflater.inflate(R.layout.dialog01,(ViewGroup) findViewById(R.id.dialog));
				final EditText dialog_qianming=(EditText) layout.findViewById(R.id.dialog_qianming);
				 new AlertDialog.Builder(UserInfo.this)
				 .setTitle("设置手机号码")
				 .setView(layout)
				 .setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Signature=dialog_qianming.getText().toString().trim();
						userinfo_phonenumber.setText(Signature);
						Toast.makeText(UserInfo.this, "手机号码修改成功",Toast.LENGTH_SHORT).show();
					}
				 	})
			     .setNegativeButton("取消",null)
				 .show();
			}
		});
		
		//8.back
		user_info_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}

	private void setAvata(String avataPath) {
		// currentUser = BmobUser.getCurrentUser(this, User.class);
		if (avataPath != null) {
			final BmobFile file = new BmobFile(new File(iconUrl));
			file.upload(this, new UploadFileListener() {

				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					Toast.makeText(UserInfo.this, "成功",Toast.LENGTH_LONG).show();

					currentUser.setAvatar(file);
					// 1.signature
					if (!TextUtils.isEmpty(Signature)) {
						currentUser.setSignature(Signature);
					}
					// 2.sex
					currentUser.setSex(isSex);
					// 3.phonenumber
					if (userinfo_phonenumber.getText().toString().trim().length() == 11) {
						currentUser.setPhonenumber(userinfo_phonenumber.getText().toString().trim());
					} else {
						Toast.makeText(UserInfo.this, "please check phonenumber！",Toast.LENGTH_LONG).show();
					}
					// 4.璁剧疆鏄电О
					currentUser.setNickname(userinfo_nickname.getText()
							.toString().trim());
					// 5.璁剧疆鐢熸棩
					currentUser.setBirthday(userinfo_birthday.getText()
							.toString().trim());

					currentUser.update(UserInfo.this, new UpdateListener() {

						@Override
						public void onSuccess() {

							currentUser = BmobUser.getCurrentUser(
									UserInfo.this, User.class);

							setResult(RESULT_OK);
							Toast.makeText(UserInfo.this, "Success",
									Toast.LENGTH_LONG).show();
						}

						@Override
						public void onFailure(int arg0, String arg1) {
							// TODO Auto-generated method stub

							Toast.makeText(UserInfo.this, "erro"+arg1,
									Toast.LENGTH_LONG).show();
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
					// LogUtils.i(TAG, "涓婁紶鏂囦欢澶辫触銆�"+arg1);
					Toast.makeText(UserInfo.this,
							arg0 + "Failure" + arg1, Toast.LENGTH_LONG)
							.show();
				}
			});
		} else {

			// 1.signature
			if (!TextUtils.isEmpty(Signature)) {
				currentUser.setSignature(Signature);
			}
			// 2.sex

			currentUser.setSex(isSex);
			// 3.
			if (userinfo_phonenumber.getText().toString().trim().length() == 11) {
				currentUser.setPhonenumber(userinfo_phonenumber.getText()
						.toString().trim());
			} else {
				Toast.makeText(UserInfo.this, "please check phonenumber！",
						Toast.LENGTH_LONG).show();
			}
			// 4.璁剧疆鏄电О
			currentUser.setNickname(userinfo_nickname.getText().toString()
					.trim());
			// 5.璁剧疆鐢熸棩
			currentUser.setBirthday(userinfo_birthday.getText().toString()
					.trim());

			currentUser.update(this, new UpdateListener() {

				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub

					setResult(RESULT_OK);
					Toast.makeText(UserInfo.this, "seccess", Toast.LENGTH_LONG)
							.show();
				}

				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO Auto-generated method stub
					Toast.makeText(UserInfo.this, "failure" + arg1,
							Toast.LENGTH_LONG).show();
				}
			});
		}
	}

	private void getAvataFromAlbum() {
		Intent intent2 = new Intent(Intent.ACTION_GET_CONTENT);
		intent2.setType("image/*");
		startActivityForResult(intent2, 2);
	}

	private void getAvataFromCamera() {
		File f = new File(CacheUtils.getCacheDirectory(this, true, "icon")
				+ dateTime);
		if (f.exists()) {
			f.delete();
		}
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Uri uri = Uri.fromFile(f);
		// Log.e("uri", uri + "");
		// 鎰忓浘寮�鍚浉鏈�
		Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		camera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		startActivityForResult(camera, 1);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case 1:// 寮�鍚浉鏈哄緱鍒扮浉鐗�
				String files = CacheUtils.getCacheDirectory(this, true, "icon")
						+ dateTime;
				File file = new File(files);
				if (file.exists() && file.length() > 0) {
					Uri uri = Uri.fromFile(file);
					startPhotoZoom(uri);
				} else {

				}
				break;
			case 2:
				if (data == null) {
					return;
				}
				// 寮�鍚浉鍐�
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
		// 鎰忓浘鐮佽繑鍥炲鐞�
		startActivityForResult(intent, 3);

	}

	public String saveToSdCard(Bitmap bitmap) {
		String files = CacheUtils.getCacheDirectory(this, true, "icon")
				+ dateTime + "_12";
		File file = new File(files);
		try {
			FileOutputStream out = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
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
		newOpts.inJustDecodeBounds = true;// 鍙杈�,涓嶈鍐呭
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
		newOpts.inSampleSize = be;// 璁剧疆閲囨牱鐜�

		newOpts.inPreferredConfig = Config.ARGB_8888;// 璇ユā寮忔槸榛樿鐨�,鍙笉璁�
		newOpts.inPurgeable = true;// 鍚屾椂璁剧疆鎵嶄細鏈夋晥
		newOpts.inInputShareable = true;// 銆傚綋绯荤粺鍐呭瓨涓嶅鏃跺�欏浘鐗囪嚜鍔ㄨ鍥炴敹

		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		// return compressBmpFromBmp(bitmap);//鍘熸潵鐨勬柟娉曡皟鐢ㄤ簡杩欎釜鏂规硶浼佸浘杩涜浜屾鍘嬬缉
		// 鍏跺疄鏄棤鏁堢殑,澶у灏界灏濊瘯
		return bitmap;
	}

}
