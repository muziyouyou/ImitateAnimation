package com.shaung.ramclear;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.shaung.ramclear.async.MyAsyncTask;
import com.shaung.ramclear.bean.AppInfo;
import com.shaung.ramclear.dao.PackageDao;
import com.shaung.ramclear.dao.WhiteProcessDao;
import com.shaung.ramclear.fragment.BlackProcessFragment;
import com.shaung.ramclear.fragment.WhiteProcessFragment;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.database.ContentObserver;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProcessActivity extends FragmentActivity  {

	private List<AppInfo> appInfos;
	private List<AppInfo> whiteList;
	private List<AppInfo> blackList;
	public  final static int SEND_DATA_BLACK=0;
	public  final static int SEND_DATA_WHITE=0;
	@ViewInject(R.id.tv_process_title)
	private TextView title;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_process);
		ViewUtils.inject(this);
		//读取数据并分配给Fragment
		sendData();
		//内容监听者监听数据源
		Uri uri = Uri.parse("content://com.myclear.change");  
		getContentResolver().registerContentObserver(uri, true, new ContentObserver(null) {     
			public void onChange(boolean selfChange) {  
				/*	new Thread(new Runnable() {
					@Override
					public synchronized void run() {*/
				appInfos.clear();
				PackageDao packageDao = new PackageDao();
				appInfos.addAll(packageDao.getAppInfos(getApplicationContext()));
				WhiteProcessDao processDao = new WhiteProcessDao(getApplicationContext());
				whiteList.clear();
				blackList.clear();
				System.out.println();
				for (AppInfo app : appInfos) {
					if(processDao.isWhite(app.getPackageName())){
						whiteList.add(app);
					}else{
						blackList.add(app);
					}
				}
			}
			/*	}).start();
			};   */      
		});
	}

	/*
	 *  TODO 填充数据
	 */
	private void sendData() {
		new MyAsyncTask() {
			@Override
			public void preTask() {
				whiteList=new ArrayList<AppInfo>();
				blackList=new ArrayList<AppInfo>();
			}
			@Override
			public void onTask() {
				PackageDao packageDao = new PackageDao();
				appInfos = packageDao.getAppInfos(getApplicationContext());
				WhiteProcessDao processDao = new WhiteProcessDao(getApplicationContext());
				//取出白名单
				for (AppInfo app : appInfos) {
					if(processDao.isWhite(app.getPackageName())){
						whiteList.add(app);
					}else{
						blackList.add(app);
					}
				}
			}
			@Override
			public void afterTask() {
				openBlackFragment();
			}

		}.execute();
	}

	/*
	 * TODO 打开Black
	 */
	private void openBlackFragment() {
		title.setText("黑名单");
		//获取Fagment
		FragmentManager supportFragmentManager = getSupportFragmentManager();
		//开启一个事务 
		FragmentTransaction transaction = supportFragmentManager.beginTransaction();
		// 替换
		transaction.setCustomAnimations(R.anim.translate, R.anim.alpha);
		BlackProcessFragment balckfragment=new BlackProcessFragment();
		transaction.replace(R.id.ll_process_replace, balckfragment);
		//提交
		transaction.commit();
		Message message = Message.obtain();
		message.what=SEND_DATA_BLACK;
		message.obj=blackList;
		balckfragment.handler.sendMessage(message);
	}
	/*
	 * TODO 打开White
	 */
	private void openWhiteFragment() {

		title.setText("白名单");
		//获取Fagment
		FragmentManager supportFragmentManager = getSupportFragmentManager();
		//开启一个事务 
		FragmentTransaction transaction = supportFragmentManager.beginTransaction();
		// 替换
		WhiteProcessFragment whitefragment=new WhiteProcessFragment();
		transaction.setCustomAnimations(R.anim.rotate, R.anim.alpha);
		transaction.replace(R.id.ll_process_replace, whitefragment);
		//提交
		transaction.commit();
		Message message = Message.obtain();
		message.what=SEND_DATA_WHITE;
		message.obj=whiteList;
		whitefragment.handler.sendMessage(message);
	}

	/**
	 * TODO 进入黑名单
	 * @param view
	 */
	public void whiteClick(View view){
		openWhiteFragment();
	}

	/**
	 * 进入白名单
	 * @param view
	 */
	public void blackClick(View view){
		openBlackFragment();
	}

}
