package com.shaung.ramclear.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.shaung.ramclear.bean.Bean;
import com.shaung.ramclear.db.WhiteProcessSqllHelper;

public class WhiteProcessDao {

	private WhiteProcessSqllHelper whiteProcessSqllHelper;
    private Context mContext;
	
	public WhiteProcessDao(Context context){
		mContext=context;
		whiteProcessSqllHelper = new WhiteProcessSqllHelper(context);
	}
	
	public long addWhite(String packName){
		SQLiteDatabase database = whiteProcessSqllHelper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("whitename", packName);
		long insert = database.insert(WhiteProcessSqllHelper.TABLE_Name, null, contentValues);
		database.close();
		//TODO 内容观察者
		 ContentResolver contentResolver = mContext.getContentResolver();   
		 Uri uri = Uri.parse("content://com.myclear.change");      
		 contentResolver.notifyChange(uri, null);//通知内容观察者更新数据
		return insert;
	}
	
	public long deleteWhite(String packagename){
		SQLiteDatabase database = whiteProcessSqllHelper.getWritableDatabase();
		long delete = database.delete(whiteProcessSqllHelper.TABLE_Name, "whitename=?", new String[]{packagename});
		database.close();
		//TODO 内容观察者
		 ContentResolver contentResolver = mContext.getContentResolver();   
		 Uri uri = Uri.parse("content://com.myclear.change");      
		 contentResolver.notifyChange(uri, null);//通知内容观察者更新数据
		return delete;
	}
	/**
	 * 获取所有的白名单
	 * @return
	 */
	public List<Bean> getWhiteList(){
		
		List<Bean> beanList=new ArrayList<Bean>();
		SQLiteDatabase database = whiteProcessSqllHelper.getWritableDatabase();
		String sql="select * from "+WhiteProcessSqllHelper.TABLE_Name+"";
		Cursor rawQuery = database.rawQuery(sql, null);
		while(rawQuery.moveToNext()){
			Bean bean = new Bean();
			bean.setId(Integer.parseInt(rawQuery.getString(0)));
			bean.setPackagerName(rawQuery.getString(1));
			beanList.add(bean);
		}
		rawQuery.close();
		database.close();
		return beanList;
	}
	
	/**
	 * 判断是否在白名单
	 * @param whitename
	 * @return
	 */
	public boolean isWhite(String whitename){
		boolean falg=false;
		SQLiteDatabase database = whiteProcessSqllHelper.getWritableDatabase();
		String sql="select * from "+WhiteProcessSqllHelper.TABLE_Name+" where whitename=?";
		Cursor rawQuery = database.rawQuery(sql, new String[]{whitename});
		if(rawQuery.moveToNext()){
			falg=true;
		}
		rawQuery.close();
		database.close();
		return falg;
	}
}
