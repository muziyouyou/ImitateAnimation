package com.shaung.ramclear.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;

public class ActivityUtils {


	/**
	 * 判断某个进程是否存在
	 * @param classname
	 * @param context
	 * @return
	 */
	public static boolean isRunningService(String classname,Context context){

		//获取进程管理员
		ActivityManager activity = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
		//获取所有的进程,尽可能的
		List<RunningServiceInfo> services = activity.getRunningServices(1000);
		for (RunningServiceInfo runningServiceInfo : services) {
			ComponentName service = runningServiceInfo.service;
			if(service.getClassName().equals(classname)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取正在运行的进程个数
	 * @param context
	 * @return
	 */
	public static int getRunningProcessNumber(Context context){
		ActivityManager am = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
		return runningAppProcesses.size();
	}

	/**
	 * 16版本取进程的总的空间
	 * @param context
	 * @return
	 */
	public static long getRunningProcess(Context context){

		int sdkInt= android.os.Build.VERSION.SDK_INT;
		if(sdkInt>=16){
			ActivityManager am = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
			MemoryInfo outInfo=new MemoryInfo();
			am.getMemoryInfo(outInfo);
			return  outInfo.totalMem;
		}else{
			return getRunningProcess();
		}
	}

	/**
	 * 兼容版本获取进程的总的空间
	 * @return
	 */
	public static long getRunningProcess(){

		File file = new File("/proc/meminfo");
		BufferedReader bufferedReader =null;
		String total=null;
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			String line=bufferedReader.readLine();
			char[] charArray = line.toCharArray();
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<charArray.length;i++){
				char c=charArray[i];
				if(c>='0'&&c<='9'){
					sb.append(c);
				}
			}
			total=sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		long number=Long.parseLong(total)*1024;
		return number;
	}

	/**
	 * 获取可用内存的空间
	 * @param context
	 * @return
	 */
	public static long getAvailableROM(Context context){

		ActivityManager am = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
		MemoryInfo outInfo=new MemoryInfo();
		am.getMemoryInfo(outInfo);
		return outInfo.availMem;
	}
}
