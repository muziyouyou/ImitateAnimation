package com.shaung.ramclear.utils;

import android.content.Context;

public class DensityUtils {
	/** 
	 * 根据手机的分辨率从 dip 的单位 转成为 px(像素) 
	 * dpValue 代表dp值
	 */  
	public static int dpToPx(Context context, float dpValue) {  
		final float scale = context.getResources().getDisplayMetrics().density;  
		return (int) (dpValue * scale + 0.5f); 
	}  

	/** 
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp 
	 * pxValue 代表px值
	 */  
	public static int pxToDp(Context context, float pxValue) {  
		final float scale = context.getResources().getDisplayMetrics().density;  
		return (int) (pxValue / scale + 0.5f);  
	}  

}
