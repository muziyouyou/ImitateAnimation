package com.shaung.ramclear;

import com.shaung.ramclear.service.RocketService;
import com.shaung.ramclear.ui.SetView;
import com.shaung.ramclear.utils.ActivityUtils;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class SettingActivity extends Activity {

	private SetView sv_set_item;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);


		sv_set_item = (SetView) findViewById(R.id.sv_set_item);
		setTable();
		sv_set_item.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean status = sv_set_item.getStatus();
				sv_set_item.setStatus(!status);
				Intent intent=new Intent(SettingActivity.this,RocketService.class);
				if(sv_set_item.getStatus()==true){
					startService(intent);
				}else{
					stopService(intent);
				}
			}
		});
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		 setTable();
	}

	private void setTable() {
		boolean runningService = ActivityUtils.isRunningService("com.shaung.ramclear.service.RocketService", this);
		if(runningService){
			sv_set_item.setStatus(true);
		}else{
			sv_set_item.setStatus(false);
		}
	}
}
