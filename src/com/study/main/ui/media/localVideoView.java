package com.study.main.ui.media;

import com.study.main.R;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnBufferingUpdateListener;
import io.vov.vitamio.MediaPlayer.OnInfoListener;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class localVideoView extends Activity implements OnInfoListener, OnBufferingUpdateListener{
	private View mVolumeBrightnessLayout;
	private ImageView mOperationBg, mOperationPercent;
	private VideoView videoView;
	//private String path = "/storage/sdcard1/新文件夹/0001.优酷网-无游戏 宁愿死 60.flv";
	private Button stop,suofang;
	private String path="";//http://file.bmob.cn/M00/D1/E0/oYYBAFRkZYeAMeo0AGkQUNBZWqs461.flv
	private GestureDetector mGestureDetector;
	/** 声音管理员获取最大音量值 */
	private int mMaxVolume;
	/** 当前声音 */
	private int mVolume = -1;
	/** 当前缩放模式 */
	private int mVideoLayout = 0;
	/** 当前亮度 */
	private float mBrightness = -1f;
	private AudioManager mAudioManager;
	
	  private ProgressBar pb;
	  private TextView downloadRateView, loadRateView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		if (!LibsChecker.checkVitamioLibs(this))
			return;
		setContentView(R.layout.videoviewdemo);
		init();
	}


	private void init() {
		// TODO Auto-generated method stub
		videoView=(VideoView) this.findViewById(R.id.surface_view);
		suofang=(Button) this.findViewById(R.id.suofang);
		mVolumeBrightnessLayout = this.findViewById(R.id.operation_volume_brightness);
		mOperationBg = (ImageView)this.findViewById(R.id.operation_bg);
		mOperationPercent = (ImageView)this. findViewById(R.id.operation_percent);
	    pb = (ProgressBar) findViewById(R.id.probar);
	    downloadRateView = (TextView) findViewById(R.id.download_rate);
	    loadRateView = (TextView) findViewById(R.id.load_rate);
	    
		// 2.声音管理员
		mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		mMaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

		path = getIntent().getStringExtra("path");
		if (path == "") {
			Toast.makeText(localVideoView.this, "暂时无法播放", Toast.LENGTH_LONG).show();
			return;		
		} else {
			if (path.startsWith("http:"))
				videoView.setVideoURI(Uri.parse(path));
			else
				videoView.setVideoPath(path);
			//6.设置媒体控制器
	
			videoView.setMediaController(new MediaController(this));
			mGestureDetector = new GestureDetector(this, new MyGestureListener());
			videoView.requestFocus();
			videoView.setOnInfoListener(this);
			videoView.setOnBufferingUpdateListener(this);
			//设置缓冲大小
			//videoView.setBufferSize(1024*1024*1);
			videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
						@Override
						public void onPrepared(MediaPlayer mediaPlayer) {
							// optional need Vitamio 4.0
							mediaPlayer.setPlaybackSpeed(1.0f);
						}
					});
			
		}

	}

//	@Override
//	public void onConfigurationChanged(Configuration newConfig) {
//		if (videoView != null)
//			videoView.setVideoLayout(mLayout, 0);
//		super.onConfigurationChanged(newConfig);
//	}
	
	   @Override
	    public boolean onTouchEvent(MotionEvent event) {
	        if (mGestureDetector.onTouchEvent(event))
	            return true;

	        // 处理手势结束
	        switch (event.getAction() & MotionEvent.ACTION_MASK) {
	        case MotionEvent.ACTION_UP:
	            endGesture();
	            break;
	        }

	        return super.onTouchEvent(event);
	    }
	    /** 手势结束 */
	    private void endGesture() {
	        mVolume = -1;
	        mBrightness = -1f;

	        // 隐藏
	        mDismissHandler.removeMessages(0);
	        mDismissHandler.sendEmptyMessageDelayed(0, 500);
	    }
	    
		/** 定时隐藏 */
		private Handler mDismissHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				mVolumeBrightnessLayout.setVisibility(View.GONE);
				suofang.setVisibility(View.GONE);
			}
		};
	    
	// 10.设置手势监听
	private class MyGestureListener extends SimpleOnGestureListener {

		/** 双击 */
		@Override
		public boolean onDoubleTap(MotionEvent e) {
			suofang.setVisibility(View.VISIBLE);
			mVideoLayout++;
			if (mVideoLayout == 4) {
				mVideoLayout = 0;
			}
			switch (mVideoLayout) {
			case 0:
				mVideoLayout = VideoView.VIDEO_LAYOUT_ORIGIN;
				suofang.setBackgroundResource(R.drawable.mediacontroller_sreen_size_100);
				break;
			case 1:
				mVideoLayout = VideoView.VIDEO_LAYOUT_SCALE;
				suofang.setBackgroundResource(R.drawable.mediacontroller_screen_fit);
				break;
			case 2:
				mVideoLayout = VideoView.VIDEO_LAYOUT_STRETCH;
				suofang.setBackgroundResource(R.drawable.mediacontroller_screen_size);
				break;
			case 3:
				mVideoLayout = VideoView.VIDEO_LAYOUT_ZOOM;
				suofang.setBackgroundResource(R.drawable.mediacontroller_sreen_size_crop);

				break;
			}
			videoView.setVideoLayout(mVideoLayout, 0);
			
			
			return true;
		}

		/** 滑动 */
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			float mOldX = e1.getX(), mOldY = e1.getY();
			int y = (int) e2.getRawY();
			Display disp = getWindowManager().getDefaultDisplay();
			int windowWidth = disp.getWidth();
			int windowHeight = disp.getHeight();

			if (mOldX > windowWidth * 4.0 / 5)// 右边滑动
				onVolumeSlide((mOldY - y) / windowHeight);
			else if (mOldX < windowWidth / 5.0)// 左边滑动
				onBrightnessSlide((mOldY - y) / windowHeight);

			return super.onScroll(e1, e2, distanceX, distanceY);
		}
	}
	
	/**
	 * 滑动改变声音大小
	 * 
	 * @param percent
	 */
	private void onVolumeSlide(float percent) {
		if (mVolume == -1) {
			mVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
			if (mVolume < 0)
				mVolume = 0;

			// 显示
			mOperationBg.setImageResource(R.drawable.video_volumn_bg);
			mVolumeBrightnessLayout.setVisibility(View.VISIBLE);
		}

		int index = (int) (percent * mMaxVolume) + mVolume;
		if (index > mMaxVolume)
			index = mMaxVolume;
		else if (index < 0)
			index = 0;

		// 变更声音
		mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);

		// 变更进度条
		ViewGroup.LayoutParams lp = mOperationPercent.getLayoutParams();
		lp.width = findViewById(R.id.operation_full).getLayoutParams().width
				* index / mMaxVolume;
		mOperationPercent.setLayoutParams(lp);
	}

	/**
	 * 滑动改变亮度
	 * 
	 * @param percent
	 */
	private void onBrightnessSlide(float percent) {
		if (mBrightness < 0) {
			mBrightness = getWindow().getAttributes().screenBrightness;
			if (mBrightness <= 0.00f)
				mBrightness = 0.50f;
			if (mBrightness < 0.01f)
				mBrightness = 0.01f;

			// 显示
			mOperationBg.setImageResource(R.drawable.video_brightness_bg);
			mVolumeBrightnessLayout.setVisibility(View.VISIBLE);
		}
		WindowManager.LayoutParams lpa = getWindow().getAttributes();
		lpa.screenBrightness = mBrightness + percent;
		if (lpa.screenBrightness > 1.0f)
			lpa.screenBrightness = 1.0f;
		else if (lpa.screenBrightness < 0.01f)
			lpa.screenBrightness = 0.01f;
		getWindow().setAttributes(lpa);

		ViewGroup.LayoutParams lp = mOperationPercent.getLayoutParams();
		lp.width = (int) (findViewById(R.id.operation_full).getLayoutParams().width * lpa.screenBrightness);
		mOperationPercent.setLayoutParams(lp);
	}


	
	
	
	  @Override
	  public boolean onInfo(MediaPlayer mp, int what, int extra) {
	    switch (what) {
	    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
	      if (videoView.isPlaying()) {
	    	  videoView.pause();
	        pb.setVisibility(View.VISIBLE);
	        downloadRateView.setText("");
	        loadRateView.setText("");
	        downloadRateView.setVisibility(View.VISIBLE);
	        loadRateView.setVisibility(View.VISIBLE);
	      }
	      break;
	    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
	    	videoView.start();
	      pb.setVisibility(View.GONE);
	      downloadRateView.setVisibility(View.GONE);
	      loadRateView.setVisibility(View.GONE);
	      break;
	    case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
	      downloadRateView.setText("" + extra + "kb/s" + "  ");
	      break;
	    }
	    return true;
	  }

	  @Override
	  public void onBufferingUpdate(MediaPlayer mp, int percent) {
	    loadRateView.setText(percent + "%");
	  }

}
