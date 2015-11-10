package com.shaung.ramclear.utils;

import java.io.File;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.text.format.Formatter;
@SuppressLint("NewApi")
public class ExternalStorageUtils {

	private static File sdcard_dir;
	static{
	    sdcard_dir = Environment.getExternalStorageDirectory();//获取Sdcard目录的一个文件对象
	}
	
	/**
	 * 判断sd卡是否可用
	 * @return
	 */
	public static boolean getExternalStorageStatus(){
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}
	/**
	 * 获取总的SD卡空间
	 * @return
	 */
	public static long getExternalStorageTotal(){
		/*String usableSpace_str = Formatter.formatFileSize(mContext, usableSpace);
		String totalSpace_str = Formatter.formatFileSize(mContext, totalSpace);*/
		return sdcard_dir.getTotalSpace();//获取剩余空间
	}
	/***
	 * 获取sd卡的剩余空间
	 * @return
	 */
	public static long getExternalStorageUsableSpace(){
		return sdcard_dir.getUsableSpace();//获取剩余空间
	}

}
