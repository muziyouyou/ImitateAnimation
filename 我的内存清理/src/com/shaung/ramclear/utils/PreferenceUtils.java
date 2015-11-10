package com.shaung.ramclear.utils;

import java.util.Set;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.widget.Toast;


/**
 * @author lishu_000
 *
 */
public class PreferenceUtils {
	
	
    
   /**
    * �������ֵ  boolean,Float,int,String,Set
    * @param mcontext
    * @param key
    * @param value
    * @return
    */
	@SuppressLint("NewApi")
	public static <Q> boolean  writeData(Context mcontext,String key,Q value){
	  SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mcontext);
	  Editor edit = defaultSharedPreferences.edit();
	  //���Class���ж�
	  if(value instanceof Boolean){
		  
		  edit.putBoolean(key,(Boolean)value); 
		  edit.commit();
		  LogUtils.e("PreferenceUtils", "Boolean-error");
		  return true;
	  }else if(value instanceof Float){
		  edit.putFloat(key,(Float)value);
		  edit.commit();
		  LogUtils.e("PreferenceUtils", "Float-error");
		  return true;
	  }else if(value instanceof Integer){
		  edit.putInt(key,(Integer)value);
		  edit.commit();
		  LogUtils.e("PreferenceUtils", "Integer-error");
		  return true;
	  }else if(value instanceof String){
		  edit.putString(key, (String)value);
		  edit.commit();
		  LogUtils.e("PreferenceUtils", "String-error");
		  return true;
	  }else if(value instanceof Set){
		  edit.putStringSet(key,(Set)value);
		  edit.commit();
		  LogUtils.e("PreferenceUtils", "Set-error");
		  return true;
	  }else{
		  Toast.makeText(mcontext, "δ�ҵ���Ӧ������", Toast.LENGTH_SHORT).show();
		  return false;
	  }
	}
	
	/**
	 * 获取一个SharedPreferences
	 * @param context
	 * @return
	 */
	public static SharedPreferences getPreferences(Context context){
		SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		return defaultSharedPreferences;
	}
	  
	
}
