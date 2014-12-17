package com.study.main;
//Download by http://www.codefans.net
import android.app.Application;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
public class UILApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

		// This configuration tuning is custom. You can tune every option, you may tune some of them, 
		// or you can create default configuration by
		//  ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
			.threadPoolSize(3)
			.threadPriority(Thread.NORM_PRIORITY - 2)
			.memoryCacheSize(1500000*100) // 1.5*100 Mb
			.denyCacheImageMultipleSizesInMemory()
			.memoryCacheSize(50 * 1024 * 1024)
			.discCacheSize(200 * 1024 * 1024)
			.diskCacheFileCount(300)
			.discCacheFileNameGenerator(new Md5FileNameGenerator())
			
			.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
}