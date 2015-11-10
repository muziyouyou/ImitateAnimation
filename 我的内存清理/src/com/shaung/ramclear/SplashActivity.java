package com.shaung.ramclear;

import java.util.Timer;
import java.util.TimerTask;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.shaung.ramclear.utils.DrawCircle;
import com.shaung.ramclear.utils.ScreenUtils;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable;

public class SplashActivity extends Activity {

	@ViewInject(R.id.tv_splash_load)
	private TextView textView;
	@ViewInject(R.id.ll_splash_show)
	private LinearLayout ll_splash_show;
	@ViewInject(R.id.iv_splash_back)
	private ImageView iv_splash_back;
	@ViewInject(R.id.iv_splash_close)
	private ImageView iv_splash_close;
	private Timer timer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		ViewUtils.inject(this);
		//设计桌面背景
		//1,手机分辨率
		setbackground();
		//2,设置倒计时
		setTime();
		//3,关闭界面
		iv_splash_close.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				timer.cancel();
				Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	/**
	 * TODO设计桌面
	 */
	private void setbackground() {
		DisplayMetrics screenWidth = ScreenUtils.getScreenWidth(getApplicationContext());
		int heightPixels = screenWidth.heightPixels;
		int widthPixels = screenWidth.widthPixels;
		//2，获取图片分辨率
		Options options = new Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(getResources(), R.drawable.splash_back, options);
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
		//3，计算比例
        int widthScale = outWidth/widthPixels;
    	int heightScale = outHeight/heightPixels ; 
    	int scale = widthScale > heightScale ? widthScale : heightScale;
   
		//4，给图片设置缩减
    	options.inJustDecodeBounds = false; // 加载器就会返回图片了
    	options.inSampleSize=scale;
    	Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.splash_back, options);
    	Bitmap map = DrawCircle.createMap(bitmap,20);
    	iv_splash_back.setImageBitmap(map);
	}
	
	/**
	 * TODO 倒计时
	 */
	private void setTime() {
		timer = new Timer();
		timer.schedule(new TimerTask() {
			private int number=5;
			@Override
			public void run() {
				if(number>0){
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							textView.setText(number+"");
						}
					});
					number--;
				}else{
					timer.cancel();
					Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
					startActivity(intent);
					finish();
				}
			}
		}, 0, 1000);
	}
}
