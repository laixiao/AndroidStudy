package com.study.main.adapter;
import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class MyApplication extends Application {
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressWarnings("unused")
	@Override
	public void onCreate() {
		super.onCreate();
		initImageLoader(getApplicationContext());
	}

	public static void initImageLoader(Context context) {
		//1. This configuration tuning is custom. You can tune every option, you may tune some of them,or you can create default configuration by  ImageLoaderConfiguration.createDefault(this); method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.diskCacheSize(50 * 1024 * 1024) // 50 Mb
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		//2. Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
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
