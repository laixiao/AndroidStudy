package com.study.main.ResideMenu;

import cn.bmob.v3.BmobUser;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.study.main.R;
import com.study.main.Entity.User;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResideMenuInfo extends LinearLayout {

	/** menu item icon */
	private ImageView iv_icon;
	/** menu item title */
	private TextView tv_username;

	private TextView tv_dengji;

	private DisplayImageOptions options;
	
	public ResideMenuInfo(Context context) {
		super(context);
		initViews(context);
	}

	public ResideMenuInfo(Context context, int icon, String title, String dengji) {
		super(context);
		initViews(context);
		iv_icon.setImageResource(icon);
		tv_username.setText(title);
		tv_dengji.setText(dengji);
	}

	private void initViews(final Context context) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.residemenu_info, this);
		iv_icon = (ImageView) findViewById(R.id.image_icon);
		tv_username = (TextView) findViewById(R.id.tv_username);
		tv_dengji = (TextView) findViewById(R.id.tv_dengji);
		
		//1.set	 iv_icon,tv_username
		User currentUser=BmobUser.getCurrentUser(context, User.class);
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.bg_pic_loading)
		.showImageForEmptyUri(R.drawable.bg_pic_loading)
		.showImageOnFail(R.drawable.bg_pic_loading)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.displayer(new RoundedBitmapDisplayer(90))
		.build();	
		
		if(currentUser.getAvatar()!=null){
			ImageLoader.getInstance().displayImage(currentUser.getAvatar().getFileUrl(context),iv_icon, options,null);	
		}
		tv_username.setText(currentUser.getNickname());
		tv_dengji.setText(""+currentUser.getSignature());
		
	}

	/**
	 * set the icon color;
	 * 
	 * @param icon
	 */
	public void setIcon(int icon) {
		iv_icon.setImageResource(icon);
	}

	/**
	 * set the title with string;
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		tv_username.setText(title);
	}

	/**
	 * set the title with string;
	 * 
	 * @param dengji
	 */
	public void setDengJi(String dengji) {
		tv_dengji.setText(dengji);
	}
}