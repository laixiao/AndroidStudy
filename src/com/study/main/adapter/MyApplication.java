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
		//由于Application类本身已经单例，�?以直接按以下处理即可�?
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
										.memoryCache(new LruMemoryCache(5*1024*1024))//�����ڴ滺��
										.memoryCacheSize(10*1024*1024)
//										.discCache(new UnlimitedDiscCache(cacheDir))
//										.discCacheFileNameGenerator(new HashCodeFileNameGenerator())
										.build();
		ImageLoader.getInstance().init(config);
	}
	
	public DisplayImageOptions getOptions(int drawableId){
		return new DisplayImageOptions.Builder()
		.showImageOnLoading(drawableId)// ����ͼƬ�������ڼ���ʾ��ͼƬ
		.showImageForEmptyUri(drawableId)// ����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ
		.showImageOnFail(drawableId)// ����ͼƬ����/��������д���ʱ����ʾ��ͼƬ
		.resetViewBeforeLoading(true)// ����ͼƬ������ǰ�Ƿ����ã���λ
		.cacheInMemory(true)// �������ص�ͼƬ�Ƿ񻺴����ڴ���
		.cacheOnDisc(true)// �������ص�ͼƬ�Ƿ񻺴���SD����
		.imageScaleType(ImageScaleType.NONE)// EXACTLY :ͼ����ȫ��������С��Ŀ���С  * EXACTLY_STRETCHED:ͼƬ�����ŵ�Ŀ���С��ȫ IN_SAMPLE_INT:ͼ�񽫱����β�����������  * IN_SAMPLE_POWER_OF_2:ͼƬ������2����ֱ����һ���ٲ��裬ʹͼ���С��Ŀ���С *  NONE:ͼƬ������� 
		.bitmapConfig(Bitmap.Config.RGB_565)// ����ͼƬ�Ľ�������,Ĭ��ֵ����Bitmap.Config.ARGB_8888
		//.displayer(new RoundedBitmapDisplayer(10)) // RoundedBitmapDisplayer��int roundPixels������Բ��ͼƬ *            FakeBitmapDisplayer���������ʲô��û��  *            FadeInBitmapDisplayer��int durationMillis������ͼƬ���Ե�ʱ��  *             SimpleBitmapDisplayer()������ʾһ��ͼƬ��
		//.postProcessor(null)//����ͼƬ���뻺��ǰ����bitmap�������� BitmapProcessor preProcessor 
		.build();
	
	
			/**
			 *  ����ͼƬ�Ľ������� android.graphics.BitmapFactory.Options
			 *  ע��:ѡ��inSampleSize�������ǵ�ѡ��
			 * �����imageScaleType(imageScaleType)ѡ�����ô�С
			 *  ע��:���ѡ���ص�bitmapConfig()ѡ�
			 */

		
		
		
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
