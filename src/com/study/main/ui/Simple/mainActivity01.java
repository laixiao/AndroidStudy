package com.study.main.ui.Simple;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.study.main.R;
import com.study.main.Entity.UserMovie;


public class mainActivity01 extends Activity{
	PullToRefreshListView list;
	private ILoadingLayout loadingLayout;
	ListView listview;
	List<UserMovie> userMovieList = new ArrayList<UserMovie>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mainactivity01);
		
		queryData(0, STATE_REFRESH);
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		list=(PullToRefreshListView)findViewById(R.id.list);
		list.setMode(Mode.BOTH);
		loadingLayout = list.getLoadingLayoutProxy();
		loadingLayout.setLastUpdatedLabel("");
		loadingLayout.setPullLabel(getString(R.string.pull_to_refresh_bottom_pull));
		loadingLayout.setRefreshingLabel(getString(R.string.pull_to_refresh_bottom_refreshing));
		loadingLayout.setReleaseLabel(getString(R.string.pull_to_refresh_bottom_release));
		//1.��������
		list.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				//��ʼ��������������
				if (firstVisibleItem == 0) {
					loadingLayout.setLastUpdatedLabel("");
					loadingLayout.setPullLabel(getString(R.string.pull_to_refresh_top_pull));
					loadingLayout.setRefreshingLabel(getString(R.string.pull_to_refresh_top_refreshing));
					loadingLayout.setReleaseLabel(getString(R.string.pull_to_refresh_top_release));
				} else if (firstVisibleItem + visibleItemCount + 1 == totalItemCount) {
					loadingLayout.setLastUpdatedLabel("");
					loadingLayout.setPullLabel(getString(R.string.pull_to_refresh_bottom_pull));
					loadingLayout.setRefreshingLabel(getString(R.string.pull_to_refresh_bottom_refreshing));
					loadingLayout.setReleaseLabel(getString(R.string.pull_to_refresh_bottom_release));
				}
			}
		});
		//2. ����ˢ�¼���
		list.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// 0.����ˢ��(�ӵ�һҳ��ʼװ������)
				queryData(0, STATE_REFRESH);
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// 0.�������ظ���(������һҳ����)
				queryData(curPage, STATE_MORE);
			}
		});
		listview=list.getRefreshableView();
		listview.setAdapter(new firstListAdapter(this));

	}
	
	//3.��ѯ�����ʵ����
	private static final int STATE_REFRESH = 0;// ����ˢ��
	private static final int STATE_MORE = 1;// ���ظ���
	
	private int limit = 10;		// ÿҳ��������10��
	private int curPage = 0;		// ��ǰҳ�ı�ţ���0��ʼ
	
	/**
	 * ��ҳ��ȡ����
	 * @param page	ҳ��
	 * @param actionType	ListView�Ĳ������ͣ�����ˢ�¡��������ظ��ࣩ
	 */
	private void queryData(final int page, final int actionType){
		//Log.i("bmob", "pageN:"+page+" limit:"+limit+" actionType:"+actionType);
		
		BmobQuery<UserMovie> query = new BmobQuery<UserMovie>();
		query.setLimit(limit);			// 1.����ÿҳ����������
		query.setSkip(page*limit);		// 2.�ӵڼ������ݿ�ʼ��
		query.findObjects(this, new FindListener<UserMovie>() {
			
			@Override
			public void onSuccess(List<UserMovie> arg0) {
				// TODO Auto-generated method stub
				
				if(arg0.size()>0){
					//1.��ʼ��	�� ��������ˢ�²���ʱ������ǰҳ�ı������Ϊ0������bankCards��գ�������ӣ�
					if(actionType == STATE_REFRESH){
						curPage = 0;
						userMovieList.clear();
					}
					
					// �����β�ѯ��������ӵ�bankCards��
					for (UserMovie td : arg0) {
						userMovieList.add(td);
					}
					
					// ������ÿ�μ��������ݺ󣬽���ǰҳ��+1������������ˢ�µ�onPullUpToRefresh�����оͲ���Ҫ����curPage��
					curPage++;
				//	showToast("��"+(page+1)+"ҳ���ݼ������");
				}else if(actionType == STATE_MORE){
					//����������û�и���������
					//showToast("û�и���������");
				}else if(actionType == STATE_REFRESH){
					//����������û�и���������
					//showToast("û������");
				}
				list.onRefreshComplete();
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
			//	showToast("��ѯʧ��:"+arg1);
				//���һ������ˢ��
				list.onRefreshComplete();
			}
		});
	}
	
	private class firstListAdapter extends BaseAdapter  {
		
		Context context;
		
		public firstListAdapter(Context context){
			this.context = context;
		}

		@SuppressLint("InflateParams") @Override
		public View getView(final int position, View convertView,ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				
				convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
				holder = new ViewHolder();
				holder.list_item_textView1 = (TextView) convertView.findViewById(R.id.list_item_textView1);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			UserMovie userMovie = (UserMovie) getItem(position);

			holder.list_item_textView1.setText(userMovie.getMoviename());
			return convertView;
		}

		class ViewHolder {
			TextView list_item_textView1;
			
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return userMovieList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return userMovieList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

	}
	
	
	
}
