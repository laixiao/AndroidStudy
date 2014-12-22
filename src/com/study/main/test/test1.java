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
        requestWindowFeature(Window.FEATURE_NO_TITLE);//ȡ������  
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
	          
	        /*����ҳ�淽ʽ1. 
	        webResetPwd.setInitialScale(55); 
	        */ 
	        
	        //Webview ����ʵ��������ָ������ҳ
	        websetting.setSupportZoom(true); 
	        websetting.setBuiltInZoomControls(true); 
	         
	        /*��Сҳ�淽ʽ3. 
	        websetting.setDefaultZoom(WebSettings.ZoomDensity.FAR); 
	        */ 
	        /*����ҳ�淽ʽ4. 
	        websetting.setUseWideViewPort(true);  
	        websetting.setLoadWithOverviewMode(true); 
	        */  
	        /*����ҳ�淽ʽ5. �Ὣҳ��Ԫ����һ������ʾ���� 
	        websetting.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN); 
	        */  
	}
	
	
	private void setListener() {
		
	}
	
	
	
}  