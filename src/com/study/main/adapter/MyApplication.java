package com.study.main.adapter;

import java.io.File;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;

import cn.bmob.v3.BmobUser;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.study.main.Entity.QiangYu;
import com.study.main.Entity.User;

public class MyApplication extends Application{

	public static String TAG;
	
	private static MyApplication myApplication = null;
	
	private QiangYu currentQiangYu = null;
	
	public static MyApplication getInstance(){
		return myApplication;
	}
	public User getCurrentUser() {
		User user = BmobUser.getCurrentUser(myApplication, User.class);
		if(user!=null){
			return user;
		}
		return null;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		TAG = this.getClass().getSimpleName();
		//鐢变簬Application绫绘湰韬凡缁忓崟渚嬶紝鎵?浠ョ洿鎺ユ寜浠ヤ笅澶勭悊鍗冲彲銆?
		myApplication = this;
		initImageLoader();
	}

	
	
	public QiangYu getCurrentQiangYu() {
		return currentQiangYu;
	}

	public void setCurrentQiangYu(QiangYu currentQiangYu) {
		this.currentQiangYu = currentQiangYu;
	}

//	public void addActivity(Activity ac){
//		ActivityManagerUtils.getInstance().addActivity(ac);
//	}
//	
//	public void exit(){
//		ActivityManagerUtils.getInstance().removeAllActivity();
//	}
//	
//	public Activity getTopActivity(){
//		return ActivityManagerUtils.getInstance().getTopActivity();
//	}
	

	public void initImageLoader(){
		File cacheDir = StorageUtils.getCacheDirectory(getApplicationContext());
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
										.memoryCache(new LruMemoryCache(5*1024*1024))//设置内存缓存
										.memoryCacheSize(10*1024*1024)
//										.discCache(new UnlimitedDiscCache(cacheDir))
//										.discCacheFileNameGenerator(new HashCodeFileNameGenerator())
										.build();
		ImageLoader.getInstance().init(config);
	}
	
	public DisplayImageOptions getOptions(int drawableId){
		return new DisplayImageOptions.Builder()
		.showImageOnLoading(drawableId)// 设置图片在下载期间显示的图片
		.showImageForEmptyUri(drawableId)// 设置图片Uri为空或是错误的时候显示的图片
		.showImageOnFail(drawableId)// 设置图片加载/解码过程中错误时候显示的图片
		.resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
		.cacheInMemory(true)// 设置下载的图片是否缓存在内存中
		.cacheOnDisc(true)// 设置下载的图片是否缓存在SD卡中
		.imageScaleType(ImageScaleType.NONE)// EXACTLY :图像将完全按比例缩小的目标大小  * EXACTLY_STRETCHED:图片会缩放到目标大小完全 IN_SAMPLE_INT:图像将被二次采样的整数倍  * IN_SAMPLE_POWER_OF_2:图片将降低2倍，直到下一减少步骤，使图像更小的目标大小 *  NONE:图片不会调整 
		.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型,默认值——Bitmap.Config.ARGB_8888
		//.displayer(new RoundedBitmapDisplayer(10)) // RoundedBitmapDisplayer（int roundPixels）设置圆角图片 *            FakeBitmapDisplayer（）这个类什么都没做  *            FadeInBitmapDisplayer（int durationMillis）设置图片渐显的时间  *             SimpleBitmapDisplayer()正常显示一张图片　
		//.postProcessor(null)//设置图片加入缓存前，对bitmap进行设置 BitmapProcessor preProcessor 
		.build();
	
	
			/**
			 *  设置图片的解码配置 android.graphics.BitmapFactory.Options
			 *  注意:选择inSampleSize将不考虑的选项
			 * 会根据imageScaleType(imageScaleType)选项设置大小
			 *  注意:这个选项重叠bitmapConfig()选项。
			 */

		
		
		
	}
}







//ageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)  
//  
//  
///** 
// * 你可以设置你自己实现的内存缓存 
// */  
//.memoryCache(new LruMemoryCache(2 * 1024 * 1024))  
//    /** 
//     * 为位图最大内存缓存大小(以字节为单位),默认值,可用应用程序内存的1/8 
//     * 注意:如果你使用这个方法,那么LruMemoryCache将被用作内存缓存。 
//     * 您可以使用memoryCache(MemoryCacheAware)方法来设置自己的MemoryCacheAware的实现。    
//     */  
//.memoryCacheSize(2 * 1024 * 1024)  
//  
///** 
// * 当同一个Uri获取不同大小的图片，缓存到内存时，只缓存一个。默认会缓存多个不同的大小的相同图片 
// */  
//.denyCacheImageMultipleSizesInMemory()  
//  
///** 
// * 设置本地图片缓存 也可以设置你自己实现 盘缓存必需实现 DiscCacheAware接口 
// * 类型（在com.nostra13.universalimageloader.cache.disc.impl包下能找到如下的类）： 
// * FileCountLimitedDiscCache(File cacheDir, int maxFileCount):设置缓存路径和缓存文件的数量，超过数量后，old将被删除 
// *  
// * FileCountLimitedDiscCache(File cacheDir,FileNameGenerator fileNameGenerator,int maxFileCount):第二个参数是通过图片的url生成的唯一文件名。 
// *  
// * LimitedAgeDiscCache(File cacheDir, FileNameGenerator fileNameGenerator, long maxAge) :第二个参数同上 
// *  
// * LimitedAgeDiscCache(File cacheDir, long maxAge):maxAge为定义的时间，超过时间后，图片将被删除 
// *  
// * TotalSizeLimitedDiscCache(File cacheDir, FileNameGenerator fileNameGenerator, int maxCacheSize) :第二个参数同上 
// *  
// * TotalSizeLimitedDiscCache(File cacheDir, int maxCacheSize) :定义缓存的大小，如超过了，就会删除old图片。 UnlimitedDiscCache(File cacheDir) ：缓存没有限制 
// *  
// * UnlimitedDiscCache(File cacheDir, FileNameGenerator fileNameGenerator)：第二个参数同上 
// */  
//.discCache(new FileCountLimitedDiscCache(new File("/sdcard/cache"), 100))//  
///** 
// * 设置缓存的大小(以字节为单位)默认:本地缓存是不限制大小 
// * 注意:如果你使用这个方法,那么TotalSizeLimitedDiscCache将被用作磁盘缓存 
// * 您可以使用discCache(DiscCacheAware)DiscCacheAware引入自己的实现方法 
// *  
// * @param maxCacheSize大小 
// */  
//.discCacheSize(10*1024*1024)  
///** 
//    * 设置图片保存到本地的参数 
//    * @param maxImageWidthForDiscCache 保存的最大宽度 
//    * @param maxImageHeightForDiscCache 保存的最大高度 
//    * @param compressFormat    保存的压缩格式 
//    * @param compressQuality 提示压缩的程度，有0-100.想png这种图片无损耗，就不必设置了 
//    * @param BitmapProcessor 处理位图,可以更改原来的位图,实现必须是线程安全的。 
//    */  
//  .discCacheExtraOptions(100,10,android.graphics.Bitmap.CompressFormat.JPEG,0, null )  
///** 
// * 设置缓存文件的数量 
// * @param maxFileCount数量 
// */  
//.discCacheFileCount(100)  
///** 
// * .taskExecutor(Executor executor) 添加个线程池，进行下载 
// *  
// * @param executor 
// *            线程池 
// *            如果进行了这个设置，那么threadPoolSize(int)，threadPriority( 
// *            int)，tasksProcessingOrder(QueueProcessingType) 
// *            将不会起作用 
// */  
//  
///** 
// * 设置缓存文件的名字 
// *  
// * @param fileNameGenerator 
// *            discCacheFileNameGenerator(FileNameGenerator 
// *            fileNameGenerator) 参数fileNameGenerator： 
// *            HashCodeFileNameGenerator 
// *            ()：通过HashCode将url生成文件的唯一名字 
// *            Md5FileNameGenerator()：通过Md5将url生产文件的唯一名字 
// */  
//.discCacheFileNameGenerator(new Md5FileNameGenerator())  
//  
///** 
// * 设置用于显示图片的线程池大小 
// * @param threadPoolSize 
// */  
//.threadPoolSize(5)//  
//  
//  
///** 
// * 设置线程的优先级 
// * @param threadPriority 
// */  
//.threadPriority(Thread.MIN_PRIORITY + 3)  
///** 
// * tasksProcessingOrder(QueueProcessingType tasksProcessingType) 
// * 设置图片下载和显示的工作队列排序 
// *  
// * @param tasksProcessingType 
// */  
//.tasksProcessingOrder(QueueProcessingType.LIFO)  
///** 
// * taskExecutorForCachedImages(Executor executorForCachedImages) 
// * 下载缓存图片 
// *  
// * @param executorForCachedImages 
// */  
//// =========================================================//  
//.writeDebugLogs()  
//.build();  
//  
//  
//ImageLoader.getInstance().init(config);  
