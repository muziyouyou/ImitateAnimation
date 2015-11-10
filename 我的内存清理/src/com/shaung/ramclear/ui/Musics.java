package com.shaung.ramclear.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class Musics extends ViewGroup {

	public Musics(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public Musics(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public Musics(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 测量子控件
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		View left = getChildAt(0);
		View center = getChildAt(1);
		View right = getChildAt(2);
		left.measure(left.getLayoutParams().width, left.getLayoutParams().height);
		center.measure(center.getLayoutParams().width, center.getLayoutParams().height);
		right.measure(right.getLayoutParams().width, right.getLayoutParams().height);
	}


	/***
	 * 为子控件分配位置
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

		View left = getChildAt(0);
		View center = getChildAt(1);
		View right = getChildAt(2);
		left.layout(0, 10, left.getMeasuredWidth(), 10+left.getMeasuredHeight());
		center.layout(left.getMeasuredWidth(), 10, center.getMeasuredWidth()+left.getMeasuredWidth(), 10+left.getMeasuredHeight());
		right.layout(left.getMeasuredWidth()*2, 10, center.getMeasuredWidth()*2+left.getMeasuredWidth(), 10+left.getMeasuredHeight());
	}



}
