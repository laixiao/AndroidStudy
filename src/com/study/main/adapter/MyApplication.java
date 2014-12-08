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
// * ������������Լ�ʵ�ֵ��ڴ滺�� 
// */  
//.memoryCache(new LruMemoryCache(2 * 1024 * 1024))  
//    /** 
//     * Ϊλͼ����ڴ滺���С(���ֽ�Ϊ��λ),Ĭ��ֵ,����Ӧ�ó����ڴ��1/8 
//     * ע��:�����ʹ���������,��ôLruMemoryCache���������ڴ滺�档 
//     * ������ʹ��memoryCache(MemoryCacheAware)�����������Լ���MemoryCacheAware��ʵ�֡�    
//     */  
//.memoryCacheSize(2 * 1024 * 1024)  
//  
///** 
// * ��ͬһ��Uri��ȡ��ͬ��С��ͼƬ�����浽�ڴ�ʱ��ֻ����һ����Ĭ�ϻỺ������ͬ�Ĵ�С����ͬͼƬ 
// */  
//.denyCacheImageMultipleSizesInMemory()  
//  
///** 
// * ���ñ���ͼƬ���� Ҳ�����������Լ�ʵ�� �̻������ʵ�� DiscCacheAware�ӿ� 
// * ���ͣ���com.nostra13.universalimageloader.cache.disc.impl�������ҵ����µ��ࣩ�� 
// * FileCountLimitedDiscCache(File cacheDir, int maxFileCount):���û���·���ͻ����ļ�������������������old����ɾ�� 
// *  
// * FileCountLimitedDiscCache(File cacheDir,FileNameGenerator fileNameGenerator,int maxFileCount):�ڶ���������ͨ��ͼƬ��url���ɵ�Ψһ�ļ����� 
// *  
// * LimitedAgeDiscCache(File cacheDir, FileNameGenerator fileNameGenerator, long maxAge) :�ڶ�������ͬ�� 
// *  
// * LimitedAgeDiscCache(File cacheDir, long maxAge):maxAgeΪ�����ʱ�䣬����ʱ���ͼƬ����ɾ�� 
// *  
// * TotalSizeLimitedDiscCache(File cacheDir, FileNameGenerator fileNameGenerator, int maxCacheSize) :�ڶ�������ͬ�� 
// *  
// * TotalSizeLimitedDiscCache(File cacheDir, int maxCacheSize) :���建��Ĵ�С���糬���ˣ��ͻ�ɾ��oldͼƬ�� UnlimitedDiscCache(File cacheDir) ������û������ 
// *  
// * UnlimitedDiscCache(File cacheDir, FileNameGenerator fileNameGenerator)���ڶ�������ͬ�� 
// */  
//.discCache(new FileCountLimitedDiscCache(new File("/sdcard/cache"), 100))//  
///** 
// * ���û���Ĵ�С(���ֽ�Ϊ��λ)Ĭ��:���ػ����ǲ����ƴ�С 
// * ע��:�����ʹ���������,��ôTotalSizeLimitedDiscCache�����������̻��� 
// * ������ʹ��discCache(DiscCacheAware)DiscCacheAware�����Լ���ʵ�ַ��� 
// *  
// * @param maxCacheSize��С 
// */  
//.discCacheSize(10*1024*1024)  
///** 
//    * ����ͼƬ���浽���صĲ��� 
//    * @param maxImageWidthForDiscCache ���������� 
//    * @param maxImageHeightForDiscCache ��������߶� 
//    * @param compressFormat    �����ѹ����ʽ 
//    * @param compressQuality ��ʾѹ���ĳ̶ȣ���0-100.��png����ͼƬ����ģ��Ͳ��������� 
//    * @param BitmapProcessor ����λͼ,���Ը���ԭ����λͼ,ʵ�ֱ������̰߳�ȫ�ġ� 
//    */  
//  .discCacheExtraOptions(100,10,android.graphics.Bitmap.CompressFormat.JPEG,0, null )  
///** 
// * ���û����ļ������� 
// * @param maxFileCount���� 
// */  
//.discCacheFileCount(100)  
///** 
// * .taskExecutor(Executor executor) ��Ӹ��̳߳أ��������� 
// *  
// * @param executor 
// *            �̳߳� 
// *            ���������������ã���ôthreadPoolSize(int)��threadPriority( 
// *            int)��tasksProcessingOrder(QueueProcessingType) 
// *            ������������ 
// */  
//  
///** 
// * ���û����ļ������� 
// *  
// * @param fileNameGenerator 
// *            discCacheFileNameGenerator(FileNameGenerator 
// *            fileNameGenerator) ����fileNameGenerator�� 
// *            HashCodeFileNameGenerator 
// *            ()��ͨ��HashCode��url�����ļ���Ψһ���� 
// *            Md5FileNameGenerator()��ͨ��Md5��url�����ļ���Ψһ���� 
// */  
//.discCacheFileNameGenerator(new Md5FileNameGenerator())  
//  
///** 
// * ����������ʾͼƬ���̳߳ش�С 
// * @param threadPoolSize 
// */  
//.threadPoolSize(5)//  
//  
//  
///** 
// * �����̵߳����ȼ� 
// * @param threadPriority 
// */  
//.threadPriority(Thread.MIN_PRIORITY + 3)  
///** 
// * tasksProcessingOrder(QueueProcessingType tasksProcessingType) 
// * ����ͼƬ���غ���ʾ�Ĺ����������� 
// *  
// * @param tasksProcessingType 
// */  
//.tasksProcessingOrder(QueueProcessingType.LIFO)  
///** 
// * taskExecutorForCachedImages(Executor executorForCachedImages) 
// * ���ػ���ͼƬ 
// *  
// * @param executorForCachedImages 
// */  
//// =========================================================//  
//.writeDebugLogs()  
//.build();  
//  
//  
//ImageLoader.getInstance().init(config);  
