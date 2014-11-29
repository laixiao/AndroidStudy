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
		//1.滑动监听
		list.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				//初始化：上拉和下拉
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
		//2. 下拉刷新监听
		list.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// 0.下拉刷新(从第一页开始装载数据)
				queryData(0, STATE_REFRESH);
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// 0.上拉加载更多(加载下一页数据)
				queryData(curPage, STATE_MORE);
			}
		});
		listview=list.getRefreshableView();
		listview.setAdapter(new firstListAdapter(this));

	}
	
	//3.查询获得真实数据
	private static final int STATE_REFRESH = 0;// 下拉刷新
	private static final int STATE_MORE = 1;// 加载更多
	
	private int limit = 10;		// 每页的数据是10条
	private int curPage = 0;		// 当前页的编号，从0开始
	
	/**
	 * 分页获取数据
	 * @param page	页码
	 * @param actionType	ListView的操作类型（下拉刷新、上拉加载更多）
	 */
	private void queryData(final int page, final int actionType){
		//Log.i("bmob", "pageN:"+page+" limit:"+limit+" actionType:"+actionType);
		
		BmobQuery<UserMovie> query = new BmobQuery<UserMovie>();
		query.setLimit(limit);			// 1.设置每页多少条数据
		query.setSkip(page*limit);		// 2.从第几条数据开始，
		query.findObjects(this, new FindListener<UserMovie>() {
			
			@Override
			public void onSuccess(List<UserMovie> arg0) {
				// TODO Auto-generated method stub
				
				if(arg0.size()>0){
					//1.初始化	（ 当是下拉刷新操作时，将当前页的编号重置为0，并把bankCards清空，重新添加）
					if(actionType == STATE_REFRESH){
						curPage = 0;
						userMovieList.clear();
					}
					
					// 将本次查询的数据添加到bankCards中
					for (UserMovie td : arg0) {
						userMovieList.add(td);
					}
					
					// 这里在每次加载完数据后，将当前页码+1，这样在上拉刷新的onPullUpToRefresh方法中就不需要操作curPage了
					curPage++;
				//	showToast("第"+(page+1)+"页数据加载完成");
				}else if(actionType == STATE_MORE){
					//上拉操作，没有更多数据了
					//showToast("没有更多数据了");
				}else if(actionType == STATE_REFRESH){
					//下拉操作，没有更多数据了
					//showToast("没有数据");
				}
				list.onRefreshComplete();
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
			//	showToast("查询失败:"+arg1);
				//完成一次下拉刷新
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
