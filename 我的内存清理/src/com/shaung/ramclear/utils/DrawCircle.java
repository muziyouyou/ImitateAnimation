package com.shaung.ramclear.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;

public class DrawCircle {

	/**
	 * 给图片加圆角
	 * @param source
	 * @param pixels
	 * @return
	 */
	 public static Bitmap createMap(Bitmap source,int pixels){
		 
		 //创建模板
		 Bitmap bitmap = Bitmap.createBitmap(source.getWidth(), source.getHeight(), source.getConfig());
		 //给模板布置画布
		 Canvas canvas = new Canvas(bitmap);
		 //设置画笔
		 Paint paint=new Paint();
		 Rect rect = new Rect(0, 0, source.getWidth(), source.getHeight());
		 RectF rectf = new RectF(rect);
		 float roundPX=pixels;
		 paint.setAntiAlias(true);
		  canvas.drawARGB(0,0,0,0);
		 int color=0xff424242;
		 paint.setColor(color);
		 canvas.drawRoundRect(rectf, roundPX, roundPX, paint);
		 paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		 //按照原图画画
		 canvas.drawBitmap(source,rect,rect,paint);
		 return bitmap;
	 }
	
	
}
