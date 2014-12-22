package com.study.main.test;
import com.study.main.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

public class test1 extends Activity {  
    private WebView webResetPwd;  
    String url;
 
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        requestWindowFeature(Window.FEATURE_NO_TITLE);//取消标题  
        setContentView(R.layout.test1);  
         
        init1();
       
    }
    
	private void init1() {
	
	        
	        
	        setListener();
	        url=getIntent().getStringExtra("url");
	        webResetPwd = (WebView)findViewById(R.id.webView1);  
	        webResetPwd.setWebViewClient(new WebViewClient(){});  
	        webResetPwd.loadUrl(url);  
	        WebSettings websetting = webResetPwd.getSettings();   
	        websetting.setJavaScriptEnabled(true);  
	          
	        /*缩放页面方式1. 
	        webResetPwd.setInitialScale(55); 
	        */ 
	        
	        //Webview 设置实现两个手指缩放网页
	        websetting.setSupportZoom(true); 
	        websetting.setBuiltInZoomControls(true); 
	         
	        /*缩小页面方式3. 
	        websetting.setDefaultZoom(WebSettings.ZoomDensity.FAR); 
	        */ 
	        /*缩放页面方式4. 
	        websetting.setUseWideViewPort(true);  
	        websetting.setLoadWithOverviewMode(true); 
	        */  
	        /*缩放页面方式5. 会将页面元素在一列中显示出来 
	        websetting.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN); 
	        */  
	}
	
	
	private void setListener() {
		
	}
	
	
	
}  