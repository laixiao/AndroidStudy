package com.study.main.ui.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.youmi.android.offers.OffersManager;
import net.youmi.android.offers.PointsManager;
import net.youmi.android.spot.SpotManager;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.study.main.R;
import com.study.main.Entity.User;
import com.study.main.ui.ResideMenuItemUi.HeimaJavaweb;
import com.study.main.ui.ResideMenuItemUi.SettingActivity;
import com.study.main.ui.media.DownloadListActivity;
import com.study.main.utils.CacheUtils;
import com.study.main.utils.DateTimePickDialogUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class UserInfo1 extends Activity{
	User currenUser;
	List<Map<String, Object>> list;  
	private ListView userinfo_listView1;
	private TextView userinfo_nickname,signatureEdit,user_birthday;
	private ImageView user_info_imageView5,user_infosex,personico,userinfo_birthday;
	String dateTime,iconUrl;
	DisplayImageOptions options,options2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.userinfo1);
		currenUser=BmobUser.getCurrentUser(UserInfo1.this, User.class);
		
		
		
		init();
		setListener();
		
	}
	
	 private void init() {
		// TODO Auto-generated method stub
		userinfo_listView1=(ListView) this.findViewById(R.id.userinfo_listView1);
		userinfo_nickname=(TextView) this.findViewById(R.id.userinfo_nickname);
		signatureEdit=(TextView) this.findViewById(R.id.signatureEdit);
		user_birthday=(TextView) this.findViewById(R.id.user_birthday);
		user_info_imageView5=(ImageView) this.findViewById(R.id.user_info_imageView5);
		user_infosex=(ImageView) this.findViewById(R.id.user_infosex);
		personico=(ImageView) this.findViewById(R.id.personico);
		userinfo_birthday=(ImageView) this.findViewById(R.id.userinfo_birthday);
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.icon_profile)
		.showImageForEmptyUri(R.drawable.icon_profile)
		.showImageOnFail(R.drawable.icon_profile)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.displayer(new RoundedBitmapDisplayer(90))
		.build();
		options2= new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.icon_profile)
		.showImageForEmptyUri(R.drawable.icon_profile)
		.showImageOnFail(R.drawable.icon_profile)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		//.displayer(new RoundedBitmapDisplayer(90))
		.build();
		
		list=getListForSimpleAdapter();
		SimpleAdapter simpleAdapter=new SimpleAdapter(UserInfo1.this, list, R.layout.user_item1,
				new String[]{"user_list_imageView1","user_list_textView1","user_textView2"},
				new int[]{R.id.user_list_imageView1,R.id.user_list_textView1,R.id.user_textView2});
		userinfo_listView1.setAdapter(simpleAdapter);
		
		showUser();
	
		
		
	}

	private void showUser() {
		// TODO Auto-generated method stub
		if(currenUser.getAvatar()!=null){
			ImageLoader.getInstance().displayImage(currenUser.getAvatar().getFileUrl(UserInfo1.this), personico, options,null);
		}
		userinfo_nickname.setText(currenUser.getNickname());
		user_birthday.setText(currenUser.getBirthday());
		signatureEdit.setText(currenUser.getSignature());
		if (currenUser.isSex()) {
				user_infosex.setImageResource(R.drawable.user_infosex2);
			}
		else {
				user_infosex.setImageResource(R.drawable.user_infosex1);
			}
		
	}

	private void setListener() {
		//1.listView点击
		 userinfo_listView1.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					switch (position) {
					
					//1.设置昵称
					case 0:
						LayoutInflater inflater = getLayoutInflater();
						View layout = inflater.inflate(R.layout.dialog01,(ViewGroup) findViewById(R.id.dialog));
						final EditText dialog_qianming=(EditText) layout.findViewById(R.id.dialog_qianming);
						 new AlertDialog.Builder(UserInfo1.this)
						 .setTitle("设置昵称")
						 .setView(layout)
						 .setPositiveButton("确定", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								final SweetAlertDialog sweetAlertDialog =new SweetAlertDialog(UserInfo1.this).setTitleText("亲，正在修改昵称").setContentText("请稍后...");
								sweetAlertDialog.show();
								String msg=dialog_qianming.getText().toString().trim();							
								userinfo_nickname.setText(msg);
								
								currenUser.setNickname(msg);
								currenUser.update(UserInfo1.this, new UpdateListener() {
									
									@Override
									public void onSuccess() {
										sweetAlertDialog.dismiss();
										Toast.makeText(UserInfo1.this, "修改成功啦", Toast.LENGTH_LONG).show();
									}
									
									@Override
									public void onFailure(int arg0, String arg1) {
										
										sweetAlertDialog.dismiss();
										new SweetAlertDialog(UserInfo1.this, SweetAlertDialog.ERROR_TYPE).setTitleText("昵称修改失败").setContentText(""+arg1).show();
									}
								});
								
							}
						 	})
					     .setNegativeButton("取消",null)
						 .show();
						break;
						
					case 1:	//修改手机
						LayoutInflater inflater1 = getLayoutInflater();
						View layout1 = inflater1.inflate(R.layout.dialog01,(ViewGroup) findViewById(R.id.dialog));
						final EditText dialog_qianming1=(EditText) layout1.findViewById(R.id.dialog_qianming);
						 new AlertDialog.Builder(UserInfo1.this)
						 .setTitle("设置手机号码")
						 .setView(layout1)
						 .setPositiveButton("确定", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								String msg=dialog_qianming1.getText().toString().trim();
								currenUser.setPhonenumber(msg);
								currenUser.update(UserInfo1.this, new UpdateListener() {
									
									@Override
									public void onSuccess() {
										// TODO Auto-generated method stub
										Toast.makeText(UserInfo1.this, "手机号码修改成功",Toast.LENGTH_SHORT).show();
									}
									
									@Override
									public void onFailure(int arg0, String arg1) {
										// TODO Auto-generated method stub
										new SweetAlertDialog(UserInfo1.this, SweetAlertDialog.ERROR_TYPE).setTitleText("昵称修改失败").setContentText(""+arg1).show();
									}
								});
								
								
							}
						 	})
					     .setNegativeButton("取消",null)
						 .show();
						
						break;
						
					case 2://保存生日			
						final SweetAlertDialog sweetAlertDialog =new SweetAlertDialog(UserInfo1.this).setTitleText("亲，正在修改生日").setContentText("请稍后...");
						sweetAlertDialog.show();
						currenUser.setBirthday(user_birthday.getText().toString());
						currenUser.update(UserInfo1.this, new UpdateListener() {
							
							@Override
							public void onSuccess() {
								// TODO Auto-generated method stub
								sweetAlertDialog.dismiss();
								Toast.makeText(UserInfo1.this, "修改成功啦", Toast.LENGTH_LONG).show();
							}
							
							@Override
							public void onFailure(int arg0, String arg1) {
								// TODO Auto-generated method stub
								sweetAlertDialog.dismiss();
								new SweetAlertDialog(UserInfo1.this, SweetAlertDialog.ERROR_TYPE).setTitleText("生日修改失败").setContentText(""+arg1).show();
							}
						});
						break;

					case 3://保存头像			
						final SweetAlertDialog sweetAlertDialog1 =new SweetAlertDialog(UserInfo1.this).setTitleText("亲，正在修改头像").setContentText("请稍后...");
						sweetAlertDialog1.show();
						//上传图像文件
						
						if(iconUrl!=null){
							BmobFile bmobFile=new BmobFile(new File(iconUrl));
						bmobFile.uploadblock(UserInfo1.this, new UploadFileListener() {
							
							@Override
							public void onSuccess() {
								// TODO Auto-generated method stub
								sweetAlertDialog1.dismiss();
								Toast.makeText(UserInfo1.this, "修改成功啦", Toast.LENGTH_LONG).show();
							}
							
							@Override
							public void onFailure(int arg0, String arg1) {
								// TODO Auto-generated method stub
								sweetAlertDialog1.dismiss();
								new SweetAlertDialog(UserInfo1.this, SweetAlertDialog.ERROR_TYPE).setTitleText("头像修改失败").setContentText(""+arg1).show();
							}
						});
						}else {
							Toast.makeText(UserInfo1.this, "亲得头像还没有修改喔", Toast.LENGTH_LONG).show();
						}
						
						break;
					case 4://退出登录				
						BmobUser.logOut(UserInfo1.this);
						Toast.makeText(UserInfo1.this, "退出成功", Toast.LENGTH_SHORT).show();
						Intent intent = new Intent();
						intent.setClass(UserInfo1.this, LoginAndRegister.class);
						startActivity(intent);
						finish();
						break;
						
						
					default:
						break;
					}
				}
			});
		 
		 //2.设置个性签名
		 user_info_imageView5.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					LayoutInflater inflater = getLayoutInflater();
					View layout = inflater.inflate(R.layout.dialog01,(ViewGroup) findViewById(R.id.dialog));
					final EditText dialog_qianming=(EditText) layout.findViewById(R.id.dialog_qianming);
					 new AlertDialog.Builder(UserInfo1.this)
					 .setTitle("设置个性签名")
					 .setView(layout)
					 .setPositiveButton("确定", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							final SweetAlertDialog sweetAlertDialog =new SweetAlertDialog(UserInfo1.this).setTitleText("亲，正在修改昵称").setContentText("请稍后...");
							sweetAlertDialog.show();
							String Signature=dialog_qianming.getText().toString().trim();
							signatureEdit.setText(Signature);
							
							currenUser.setSignature(Signature);
							currenUser.update(UserInfo1.this, new UpdateListener() {
								
								@Override
								public void onSuccess() {
									sweetAlertDialog.dismiss();
									Toast.makeText(UserInfo1.this, "修改成功啦", Toast.LENGTH_LONG).show();
								}
								
								@Override
								public void onFailure(int arg0, String arg1) {
									sweetAlertDialog.dismiss();
									new SweetAlertDialog(UserInfo1.this, SweetAlertDialog.ERROR_TYPE).setTitleText("个性签名修改失败").setContentText(""+arg1).show();
								}
							});
							
						
						}
					 	})
				     .setNegativeButton("取消",null)
					 .show();

				}
			});
		 
		 //3.性别
		 user_infosex.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new SweetAlertDialog(UserInfo1.this,SweetAlertDialog.CUSTOM_IMAGE_TYPE)
				.setTitleText("选择性别").setContentText("")
				.setCustomImage(R.drawable.user_infosex)
				.showCancelButton(true).setCancelText("女")
				.setConfirmText("男")
				.setCancelClickListener(new OnSweetClickListener() {
					public void onClick(SweetAlertDialog sweetAlertDialog) {
						sweetAlertDialog.dismiss();
						final SweetAlertDialog sweetAlertDialog1 =new SweetAlertDialog(UserInfo1.this).setTitleText("亲，正在修改性别").setContentText("请稍后...");
						sweetAlertDialog1.show();
						user_infosex.setImageResource(R.drawable.user_infosex1);																		
						currenUser.setSex(false);
						currenUser.update(UserInfo1.this, new UpdateListener() {
							
							@Override
							public void onSuccess() {
								// TODO Auto-generated method stub
								sweetAlertDialog1.dismiss();
								Toast.makeText(UserInfo1.this, "修改成功啦", Toast.LENGTH_LONG).show();
							}
							
							@Override
							public void onFailure(int arg0, String arg1) {
								// TODO Auto-generated method stub
								sweetAlertDialog1.dismiss();
								new SweetAlertDialog(UserInfo1.this, SweetAlertDialog.ERROR_TYPE).setTitleText("性别修改失败").setContentText(""+arg1).show();
							}
						});
					}
				}).setConfirmClickListener(new OnSweetClickListener() {

					@Override
					public void onClick(SweetAlertDialog sweetAlertDialog) {
						sweetAlertDialog.dismiss();
						final SweetAlertDialog sweetAlertDialog1 =new SweetAlertDialog(UserInfo1.this).setTitleText("亲，正在修改性别").setContentText("请稍后...");
						sweetAlertDialog1.show();
						user_infosex.setImageResource(R.drawable.user_infosex2);	
						currenUser.setSex(true);
						currenUser.update(UserInfo1.this, new UpdateListener() {
							
							@Override
							public void onSuccess() {
								// TODO Auto-generated method stub
								sweetAlertDialog1.dismiss();
								Toast.makeText(UserInfo1.this, "修改成功啦", Toast.LENGTH_LONG).show();
							}
							
							@Override
							public void onFailure(int arg0, String arg1) {
								// TODO Auto-generated method stub
								sweetAlertDialog1.dismiss();
								new SweetAlertDialog(UserInfo1.this, SweetAlertDialog.ERROR_TYPE).setTitleText("性别修改失败").setContentText(""+arg1).show();
							}
						});
					}
				}).show();
			}
		});
		 
		//4.
		 userinfo_birthday.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DateTimePickDialogUtil dateutil = new DateTimePickDialogUtil(UserInfo1.this, "2000年05月20日 05:60");
				dateutil.dateTimePicKDialog(user_birthday);
			}
		});
		 
		//5.
		 personico.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new SweetAlertDialog(UserInfo1.this,SweetAlertDialog.CUSTOM_IMAGE_TYPE)
						.setTitleText("选择相片").setContentText("")
						.setCustomImage(R.drawable.titlebutton01)
						.showCancelButton(true).setCancelText("相机拍照")
						.setConfirmText("本地相册")
						.setCancelClickListener(new OnSweetClickListener() {

							@Override
							public void onClick(SweetAlertDialog sweetAlertDialog) {
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
								Date date1 = new Date(System.currentTimeMillis());
								dateTime = date1.getTime() + "";
								getAvataFromAlbum();
								sweetAlertDialog.dismiss();
								
							}
						}).show();
			}
		});
		 
		 
		 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 





















	private List<Map<String, Object>> getListForSimpleAdapter() {  
		  
		  	  
	    	List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();  
	           Map<String, Object> map = new HashMap<String, Object>(); 
	           map.put("user_list_imageView2", R.drawable.residebutton2);  
	           map.put("user_list_textView1", "昵称" );  
	           map.put("user_textView2",""+currenUser.getNickname());  
	           list.add(map);  
	           
	           map = new HashMap<String, Object>();         
	           map.put("user_list_imageView2", R.drawable.residebutton2); 
	           map.put("user_list_textView1", "手机");  
	           map.put("user_textView2", ""+currenUser.getPhonenumber());  
	           list.add(map);  
	   
	           map = new HashMap<String, Object>();        
	           map.put("user_list_imageView2", R.drawable.residebutton5);  
	           map.put("user_list_textView1","保存生日");  
	           map.put("user_textView2",  ""+currenUser.getBirthday());  
	           list.add(map);  
	           
	           map = new HashMap<String, Object>();        
	           map.put("user_list_imageView2", R.drawable.residebutton5);  
	           map.put("user_list_textView1", "保存头像");  
	           map.put("user_textView2", ""); 
	           list.add(map);  
	           map = new HashMap<String, Object>();        
	           map.put("user_list_imageView2", R.drawable.residebutton5);  
	           map.put("user_list_textView1", "退出");  
	           map.put("user_textView2", "");  
	           list.add(map);  
	       return list;  
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
		
		Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		camera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		startActivityForResult(camera, 1);
	}
	
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case 1:
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
				
				startPhotoZoom(data.getData());
				break;
			case 3:
				if (data != null) {
					Bundle extras = data.getExtras();
					if (extras != null) {
						Bitmap bitmap = extras.getParcelable("data");
						
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
