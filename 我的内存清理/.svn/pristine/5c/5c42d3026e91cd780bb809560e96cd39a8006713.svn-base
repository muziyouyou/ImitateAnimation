package com.shaung.ramclear.ui;

import com.shaung.ramclear.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SetView extends RelativeLayout {

	private TextView tv_itemsetview_title;
	private TextView tv_itemsetview_detail;
	private String title;
	private String des_on;
	private String des_off;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDes_on() {
		return des_on;
	}

	public void setDes_on(String des_on) {
		this.des_on = des_on;
	}

	public String getDes_off() {
		return des_off;
	}

	public void setDes_off(String des_off) {
		this.des_off = des_off;
	}

	private CheckBox cb_itemsetview_status;

	public SetView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		 init();
	}

	public SetView(Context context, AttributeSet attrs) {
		super(context, attrs);
		title = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.shaung.ramclear", "title");
		des_on = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.shaung.ramclear", "des_on");
		des_off = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.shaung.ramclear", "des_off");
		// TODO Auto-generated constructor stub
		 init();
	}

	public SetView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		 init();
	}
	
	public void init(){
		View view = View.inflate(getContext(), R.layout.item_setview, this);
		tv_itemsetview_title = (TextView) view.findViewById(R.id.tv_itemsetview_title);
		tv_itemsetview_detail = (TextView) view.findViewById(R.id.tv_itemsetview_detail);
		cb_itemsetview_status = (CheckBox) view.findViewById(R.id.cb_itemsetview_status);
		tv_itemsetview_title.setText(title);
	}
	
	
	public void setStatus(boolean status){
		if(status){
			cb_itemsetview_status.setChecked(true);
			tv_itemsetview_detail.setText(des_on);
		}else{
			cb_itemsetview_status.setChecked(false);
			tv_itemsetview_detail.setText(des_off);
		}
	}
	
	public boolean getStatus(){
		return cb_itemsetview_status.isChecked();
	}

	
}
