package com.shaung.ramclear.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MusicItem extends View{

	
	private int mHight=100;
	public MusicItem(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MusicItem(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MusicItem(Context context) {
		super(context);
	}

	/***
	 * 1,测量
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(200, 200);
	}
	
	/**
	 * 2,在控件内部绘制图形
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		canvas.drawRect(0, 200-mHight, 200, 200, paint);
		paint.setColor(Color.BLACK);
		canvas.drawText("你好", 10, 10, paint);
	}
	
	/**
	 * 3,设置控件
	 */
	public void addHight(int hight){
		mHight=mHight+hight;
		invalidate();
	}
	public void reduceHight(int hight){
		mHight=mHight-hight;
		invalidate();
	}
}
