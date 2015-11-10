package com.shaung.ramclear.async;

import android.os.Handler;
import android.os.Message;

public abstract class MyAsyncTask{
    //操作后
	private Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			afterTask();
		}		
	};
	//操作前
	public abstract void preTask();
	//操作中
	public abstract void onTask();
	//操作后
	public abstract void afterTask();
	
	public void execute(){
		//操作前
		preTask();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				//操作中
				onTask();
				handler.sendEmptyMessage(0);
			}
		}).start();
	}
}
