package com.shaung.ramclear;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.shaung.ramclear.utils.DensityUtils;
import com.shaung.ramclear.utils.ScreenUtils;

public class JumpActivity extends Activity {

	@ViewInject(R.id.iv_jump_me)
	private ImageView iv_jump_me;
	@ViewInject(R.id.fl_jump_back)
	private FrameLayout fl_jump_back;
	private DisplayMetrics screenWidth;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jump);
		ViewUtils.inject(this);
		screenWidth = ScreenUtils.getScreenWidth(getApplicationContext());
		//设置页面背景
		Bitmap bitmap=Bitmap.createBitmap(screenWidth.widthPixels, screenWidth.heightPixels, Bitmap.Config.ARGB_8888);
		BitmapDrawable drawable = new BitmapDrawable(bitmap);
		fl_jump_back.setBackgroundDrawable(drawable);
		//设置点击事件
		iv_jump_me.setOnTouchListener(new OnTouchListener() {
			private int startx;
			private int starty;
			private int kx;
			private int ky;
			private int prex;
			private int prey;
			private Bitmap bitmap;
			private float asin=0;
			private double atan=0;
			private boolean flagc=false;
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:

					startx = (int) event.getRawX();
					starty = (int) event.getRawY();
					kx=startx;
					ky=starty;
					break;

				case MotionEvent.ACTION_MOVE:

					int endx=(int) event.getRawX();
					int endy=(int) event.getRawY();


					//计算斜率
					try{
						atan=(endy-ky)/(endx-kx);
					}catch (Exception e) {
						atan=-404;
					}
					//判断是否特殊
					boolean flag=false;
					//计算旋转角度
					if(atan==-404){//垂直不旋转
						if(endy>ky){
							asin=180;
						}else{
							asin=0;
						}

					}else if(atan==0){
						if(endx>kx){
							asin=90;
						}else{
							asin=-90;
						}
					}else{
						flag=true;
						asin =(float) Math.toDegrees(Math.atan(atan));
					}
					//定位角度
					if(flag){
						//左右上下
						if(endy>starty){
							if(atan>0){
								asin+=90;
							}else{
								asin-=90;
							}
						}else{
							if(atan>0){
								asin=-asin;
							}else{
								asin=-asin;
							}
						}
					}

					bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
					//1,创建父模板
					Bitmap bitmapFu = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
					//2,为父模板创建画布
					Canvas canvas = new Canvas(bitmapFu);
					//3,设置旋转矩阵
					Matrix mx=new Matrix();
					mx.setRotate(asin, bitmap.getWidth()/2, bitmap.getHeight()/2);
					//4,设置画笔
					Paint p=new Paint();
					canvas.drawBitmap(bitmap, mx, p);
					//5,设置图片
					iv_jump_me.setImageBitmap(bitmapFu);
					/////////////////////////////////////////////////////
					//绳子的高度
					int linehigh=screenWidth.heightPixels-dpTopx(143);
					//设置终点
					int lineEndY=iv_jump_me.getTop()+iv_jump_me.getHeight();//高
					int lineEndX=iv_jump_me.getLeft()+iv_jump_me.getWidth()/2;

					if(endy>screenWidth.heightPixels-dpTopx(160)){

						Bitmap bitmap2=Bitmap.createBitmap(screenWidth.widthPixels, screenWidth.heightPixels, Bitmap.Config.ARGB_8888);
						Canvas canvas2 = new Canvas(bitmap2);
						Paint p2=new Paint();
						p2.setColor(Color.BLUE);
						p2.setStrokeWidth(10f);
						canvas2.drawLine(0, linehigh, lineEndX, lineEndY, p2);
						canvas2.drawLine(screenWidth.widthPixels, linehigh, lineEndX, lineEndY, p2);
						BitmapDrawable drawable = new BitmapDrawable(bitmap2);
						fl_jump_back.setBackgroundDrawable(drawable);
						flagc=true;
					}else{
						flagc=false;
					}
					/////////////////////////////////////////////////////
					//6,移动位置
					float divX=endx-startx;
					float divY=endy-starty;
					//
					int top = iv_jump_me.getTop();
					int left = iv_jump_me.getLeft();
					//4.2移动相应的偏移量
					left+=divX;
					top+=divY;
					int right=left+iv_jump_me.getWidth();
					int bottom=top+iv_jump_me.getHeight();
					iv_jump_me.layout(left,top,right, bottom);
					/////////////////////////////////////////////////////
					//7,变换起始位置
					prex=startx;
					prey=starty;
					kx=endx;
					ky=endy;
					startx=endx;
					starty=endy;
					break;
				case MotionEvent.ACTION_UP:

					if(flagc){
						if(asin==180){//垂直
							asin=0;
							bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
							//1,创建父模板
							Bitmap bitmapFu3= Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
							//2,为父模板创建画布
							Canvas canvas3 = new Canvas(bitmapFu3);
							//3,设置旋转矩阵
							Matrix mx3=new Matrix();
							mx3.setRotate(asin, bitmap.getWidth()/2, bitmap.getHeight()/2);
							//4,设置画笔
							Paint p3=new Paint();
							canvas3.drawBitmap(bitmap, mx3, p3);
							//5,设置图片
							iv_jump_me.setImageBitmap(bitmapFu3);
						}else{//非垂直
							int endx3=(int) event.getRawX();
							if(endx3<startx){//右下
								//改变转向
								asin=180+asin;
							}else{//左下
								asin=asin-180;
							}

							bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
							//1,创建父模板
							Bitmap bitmapFu3= Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
							//2,为父模板创建画布
							Canvas canvas3 = new Canvas(bitmapFu3);
							//3,设置旋转矩阵
							Matrix mx3=new Matrix();
							mx3.setRotate(asin, bitmap.getWidth()/2, bitmap.getHeight()/2);
							//4,设置画笔
							Paint p3=new Paint();
							canvas3.drawBitmap(bitmap, mx3, p3);
							//5,设置图片
							iv_jump_me.setImageBitmap(bitmapFu3);
							//移动
							/**
							 * 1,左上,0,右上,-1是垂直,-2横向
							 */
							int redirect=-2;
							if(asin==180){//垂直
								redirect=-1;
							}else if(asin==90||asin==-90){//横向
								redirect=-2;
							}else{//正常
								if(endx3>prex){//左上
									redirect=1;
								}else if(endx3<prex){//右上
									redirect=0;
								}
							}
							startPosition(atan,redirect);
						}
					}
					break;
				}
				return true;
			}
		});

	}

	/**
	 * 斜率方向
	 * @param atan
	 * @param redirect
	 */
	public void startPosition(final double atan,final int redirect){

		new Thread(new Runnable() {
			int y;
			int x;
			@Override
			public void run() {
				y=iv_jump_me.getTop();
				x=iv_jump_me.getLeft();
				/**
				 * 1,左上,0,右上,-1是垂直,-2横向
				 */
				switch (redirect) {
				case 1://左上
					System.out.println("左伤1");
					moveLeft(atan);
					break;
				case 0://右上
					break;
				case -1://垂直
					moveHigh();
					break;
				case -2://横向
					break;	
				}

			}
			
			private void moveHigh() {
				
				while(y>50){
					//修改图片位置
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							iv_jump_me.layout(x,y,(int) (x+iv_jump_me.getWidth()),y+iv_jump_me.getHeight());
						}
					});
					//睡眠
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//减去坐标
					System.out.println("y=="+y);
					y=y-100;
				}
			}
			//左上
			private void moveLeft(final double atan) {
				while(y>50){
					//修改图片位置
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							double x=(double) (y/atan);
							//计算比例
							double bili=screenWidth.heightPixels/screenWidth.widthPixels;
							x=x/bili;
							System.out.println("x="+x);
							iv_jump_me.layout((int)x,y,(int) (x+iv_jump_me.getWidth()),y+iv_jump_me.getHeight());
						}
					});
					//睡眠
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//减去坐标
					y=y-100;
				}
			}
		}).start();
	}



	public int dpTopx(int dp){
		int dpToPx = DensityUtils.dpToPx(getApplicationContext(), dp);
		return dpToPx;
	}

}
