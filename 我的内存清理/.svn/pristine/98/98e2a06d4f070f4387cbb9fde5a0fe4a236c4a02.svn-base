package com.shaung.ramclear.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class ScreenUtils {
	
	public static DisplayMetrics getScreenWidth(Context context){
		WindowManager windowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
		//windowManager.getDefaultDisplay().getWidth();//获取屏幕的宽度
		DisplayMetrics outMetrics = new DisplayMetrics();//创建一张白纸
		windowManager.getDefaultDisplay().getMetrics(outMetrics);//给白纸设置宽高
		return outMetrics;
	}
}
