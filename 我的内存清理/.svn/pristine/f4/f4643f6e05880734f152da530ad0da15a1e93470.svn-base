package com.shaung.ramclear.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shaung.ramclear.ProcessActivity;
import com.shaung.ramclear.R;
import com.shaung.ramclear.bean.AppInfo;
import com.shaung.ramclear.dao.WhiteProcessDao;
public class BlackProcessFragment extends Fragment implements OnClickListener {

	private ListView lv_black_list;
	private ProgressBar pb_black_loading;
	private TextView tv_process_float;
	private Button bt_fragmentblack_all;
	private Button bt_fragmentblack_notall;
	private Button bt_fragmentblack_add;
	private List<AppInfo> blackList=new ArrayList<AppInfo>();
	private List<AppInfo> userList=new ArrayList<AppInfo>();
	private List<AppInfo> sysList=new ArrayList<AppInfo>();
	private WhiteAdapter white = null;
	//TODO 接收请求
	public Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case ProcessActivity.SEND_DATA_BLACK:
				blackList= (List<AppInfo>) msg.obj;
				userList.clear();
				sysList.clear();
				for(AppInfo appinfo:blackList){
					if(appinfo.isUser()){
						userList.add(appinfo);
					}else{
						sysList.add(appinfo);
					}
				}
				white.notifyDataSetChanged();
				pb_black_loading.setVisibility(View.INVISIBLE);
				break;
				
			case 1:
				white.notifyDataSetChanged();
				break;
			}

		}

	};



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		View view =inflater.inflate(R.layout.fragment_blackprocess, null);
		lv_black_list = (ListView) view.findViewById(R.id.lv_black_list);
		pb_black_loading = (ProgressBar) view.findViewById(R.id.pb_black_loading);
		tv_process_float = (TextView) view.findViewById(R.id.tv_process_float);
		bt_fragmentblack_all = (Button) view.findViewById(R.id.bt_fragmentblack_all);
		bt_fragmentblack_notall = (Button) view.findViewById(R.id.bt_fragmentblack_notall);
		bt_fragmentblack_add = (Button) view.findViewById(R.id.bt_fragmentblack_add);
		//设置等待条
		pb_black_loading.setVisibility(View.VISIBLE);
		//TODO 设置ListView
		setListView();
		//TODO listView点击事件
		setItemClick();
		//TODO 点击按钮
		bt_fragmentblack_all.setOnClickListener(this);
		bt_fragmentblack_notall.setOnClickListener(this);
		bt_fragmentblack_add.setOnClickListener(this);
		return view;
	}

	/**
	 * TODO 条目点击事件
	 */
	private void setItemClick() {
		lv_black_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

				if(position==0||position==userList.size()+1){
					return;
				}
				//获取条目的数据
				AppInfo appInfo = null;
				if(position<=userList.size()){
					appInfo=userList.get(position-1);
				}else{
					appInfo=sysList.get(position-userList.size()-2);
				}
				//
				ViewHolder viewHolder=null;
				viewHolder = (ViewHolder) view.getTag();

				if(appInfo.isCheck()){

					appInfo.setCheck(false);
					viewHolder.cb_itemprocess_check.setChecked(false);
				}else{
					System.out.println(""+appInfo.getPackageName()+":true");
					appInfo.setCheck(true);
					viewHolder.cb_itemprocess_check.setChecked(true);
				}
			}
		});
	}

	//TODO 设置ListView
	private void setListView() {
		//设置Adapter
		white=new WhiteAdapter();
		lv_black_list.setAdapter(white);
		//设置ListView滚动事件
		lv_black_list.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

				tv_process_float.setBackgroundColor(Color.GRAY);
				if(firstVisibleItem==0){
					tv_process_float.setText("用户程序:"+userList.size());
				}
				if(firstVisibleItem==userList.size()+1){
					tv_process_float.setText("系统程序:"+sysList.size());
				}
				if(firstVisibleItem<=userList.size()){
					tv_process_float.setText("用户程序:"+userList.size());
				}else{
					tv_process_float.setText("系统程序:"+sysList.size());
				}
			}
		});
	}



	/**
	 * TODO  adapter
	 * @author lishu_000
	 *
	 */
	private class WhiteAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return userList.size()+1+sysList.size()+1;
		}

		@Override
		public Object getItem(int position) {

			return blackList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if(position==0){
				TextView textView = new TextView(getActivity());
				textView.setText("用户程序:"+userList.size());
				textView.setBackgroundColor(Color.GRAY);
				return textView;
			}
			if(position==userList.size()+1){
				TextView textView = new TextView(getActivity());
				textView.setText("系统程序:"+sysList.size());
				textView.setBackgroundColor(Color.GRAY);
				return textView;
			}

			//初始化控件
			View view=null;
			ViewHolder viewHolder=null;
			if(convertView!=null && convertView instanceof RelativeLayout){
				view=convertView;
				viewHolder=(ViewHolder) view.getTag();
			}else{
				view =View.inflate(getActivity(), R.layout.item_process, null);
				viewHolder=new ViewHolder();
				viewHolder.iv_itemprocess_ic=(ImageView) view.findViewById(R.id.iv_itemprocess_ic);
				viewHolder.tv_itemprocess_loaction=(TextView) view.findViewById(R.id.tv_itemprocess_loaction);
				viewHolder.tv_itemprocess_name=(TextView) view.findViewById(R.id.tv_itemprocess_name);
				viewHolder.cb_itemprocess_check=(CheckBox) view.findViewById(R.id.cb_itemprocess_check);
				view.setTag(viewHolder);
			}
			//获取数据

			AppInfo appInfo = null;
			if(position<=userList.size()){
				appInfo=userList.get(position-1);
			}else{
				appInfo=sysList.get(position-userList.size()-2);
			}
			//设置控件
			viewHolder.iv_itemprocess_ic.setImageDrawable(appInfo.getIcon());
			if(appInfo.isSD()){
				viewHolder.tv_itemprocess_loaction.setText("SD");
			}else{
				viewHolder.tv_itemprocess_loaction.setText("手机");
			}
			viewHolder.tv_itemprocess_name.setText(appInfo.getName());

			System.out.println(""+appInfo.getPackageName()+":"+appInfo.isCheck()+"");
			if(appInfo.isCheck()){
				viewHolder.cb_itemprocess_check.setChecked(true);
			}else{
				viewHolder.cb_itemprocess_check.setChecked(false);
			}
			return view;
		}
	}

	static class ViewHolder{

		ImageView iv_itemprocess_ic;
		TextView tv_itemprocess_name,tv_itemprocess_loaction;
		CheckBox cb_itemprocess_check;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.bt_fragmentblack_all:

			for(AppInfo appin:userList){
				appin.setCheck(true);
			}
			for(AppInfo appin:sysList){
				appin.setCheck(true);
			}
			white.notifyDataSetChanged();
			break;

		case R.id.bt_fragmentblack_notall:
			for(AppInfo appin:userList){
				if(appin.isCheck()){
					appin.setCheck(false);
				}else{
					appin.setCheck(true);
				}
			}
			for(AppInfo appin:sysList){
				if(appin.isCheck()){
					appin.setCheck(false);
				}else{
					appin.setCheck(true);
				}
			}
			white.notifyDataSetChanged();

			break;
		case R.id.bt_fragmentblack_add:

			new Thread(new Runnable() {

				@Override
				public void run() {


					List<AppInfo> deleteList=new ArrayList<AppInfo>();
					//添加操作
					WhiteProcessDao processDao = new WhiteProcessDao(getActivity());
					for(AppInfo appin:userList){
						if(appin.isCheck()){
							deleteList.add(appin);
							processDao.addWhite(appin.getPackageName());
						}
					}
					for(AppInfo appin:sysList){
						if(appin.isCheck()){
							deleteList.add(appin);
							processDao.addWhite(appin.getPackageName());
						}
					}
					//删除操作
					for(AppInfo appin:deleteList){

						if(userList.contains(appin)){
							userList.remove(appin);
						}
						if(sysList.contains(appin)){
							sysList.remove(appin);
						}
					}
					handler.sendEmptyMessage(1);
				}
			}).start();
			break;
		}

	}

}
