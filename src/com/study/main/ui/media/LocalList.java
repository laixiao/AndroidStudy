package com.study.main.ui.media;

import java.io.File;
import java.util.ArrayList;

import com.study.main.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LocalList extends Activity implements OnItemClickListener {
	 private FileAdapter mAdapter;
	private ListView listView;
	private Button repeatScanner;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.locallist);
        
        mAdapter = new FileAdapter(this, null);
       listView = (ListView)this.findViewById(android.R.id.list);
       repeatScanner=(Button) this.findViewById(R.id.repeatScanner);
       listView.setAdapter(mAdapter);
       listView.setOnItemClickListener(this);
       
       repeatScanner.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		    new ScanVideoTask().execute();
		}
	});
   
          
    }
    //1.listView点击事件
    @Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
    	 final File f = mAdapter.getItem(position);
    	
 			 Intent intent = new Intent(LocalList.this, localVideoView.class);
 	         intent.putExtra("path", f.getPath());
 	         startActivity(intent);
 	    	

	}
    /** 2.扫描SD卡 */
    private class ScanVideoTask extends AsyncTask<Void, File, Void> {

        @Override
        protected Void doInBackground(Void... params) {
        	//判断SD卡是否存在
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {

          eachAllMedias(Environment.getExternalStorageDirectory());
           
        }
            return null;
        }

        @Override
        protected void onProgressUpdate(File... values) {
            mAdapter.add(values[0]);
            mAdapter.notifyDataSetChanged();
        }

        /** 遍历所有文件夹，查找出视频文件 */
        public void eachAllMedias(File f) {
            if (f != null && f.exists() && f.isDirectory()) {
                File[] files = f.listFiles();
                if (files != null) {
                    for (File file : f.listFiles()) {
                        if (file.isDirectory()) {
                            eachAllMedias(file);
                        } else if (file.exists() && file.canRead() && FileUtils.isVideoOrAudio(file)) {
                            publishProgress(file);
                        }
                    }
                }
            }
        }
    }
    //3.listView适配器
    private class FileAdapter extends ArrayAdapter<File> {

        public FileAdapter(Context ctx, ArrayList<File> l) {
            super(ctx, l);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final File f = getItem(position);
            if (convertView == null) {
                final LayoutInflater mInflater =getLayoutInflater();
                convertView = mInflater.inflate(R.layout.fragment_file_item, null);
            }
            ((TextView) convertView.findViewById(R.id.title)).setText(f.getName());
            return convertView;
        }
    }
	

}

