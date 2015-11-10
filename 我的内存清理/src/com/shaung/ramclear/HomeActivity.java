package com.shaung.ramclear;

import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.shaung.ramclear.adapter.GridViewHomeAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class HomeActivity extends Activity {

	@ViewInject(R.id.gl_home_back)
	private GridView gv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		ViewUtils.inject(this);
		//获取GridView
		List<String> nameList=new ArrayList<String>();
		//数据源
		nameList.add("黑白名单");
		nameList.add("设置");
		nameList.add("弹跳");
		nameList.add("musicItem");
		nameList.add("music");
		GridViewHomeAdapter homeAdapter = new GridViewHomeAdapter(nameList, getApplicationContext());
		gv.setAdapter(homeAdapter);
		//设置点击事件
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

				switch (position) {
				case 0://进程
					Intent intent0 = new Intent(HomeActivity.this,ProcessActivity.class);
					startActivity(intent0);
					break;
				case 1://设置
					Intent intent1 = new Intent(getApplicationContext(), SettingActivity.class);
					startActivity(intent1);
					break;
				case 2://弹射页面
					Intent intent2 = new Intent(getApplicationContext(), JumpActivity.class);
					startActivity(intent2);
					break;
				case 3://音乐条目
					Intent intent3 = new Intent(getApplicationContext(), MusicItemActivity.class);
					startActivity(intent3);
				case 4://音乐
					Intent intent5 = new Intent(getApplicationContext(), MusicActivity.class);
					startActivity(intent5);
					break;
				}

			}
		});
	}

}
