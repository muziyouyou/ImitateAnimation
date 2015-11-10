package com.shaung.ramclear.dao;

import java.util.ArrayList;
import java.util.List;

import com.shaung.ramclear.bean.AppInfo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

public class PackageDao {

	
	public List<AppInfo> getAppInfos(Context context){
		
		List<AppInfo> list=new ArrayList<AppInfo>();
		PackageManager manager = context.getPackageManager();
		List<PackageInfo> packages = manager.getInstalledPackages(0);
		for (PackageInfo packageInfo : packages) {
	/*		 3.1获取包名:packageInfo.packageName
			 3.2~版本号:packageInfo.versionName
			 3.3~应用信息packageInfo.applicationInfo
			 3.4~应用的图标 applicationInfo.loadIcon(packageManager);
			 3.5~应用的名称applicationInfo.loadLabel(packageManager).toString();
			 3.7~应用的标签 applicationInfo.flags;
			 3.8~判断是够安装在SD卡和是否为系统程序*/
			   //系统应用
			   //(flags & applicationInfo.FLAG_SYSTEM)
			   //安装在SD卡上
			   //flags & applicationInfo.FLAG_EXTERNAL_STORAGE)
			String packageName = packageInfo.packageName;
			String versionName = packageInfo.versionName;
			ApplicationInfo applicationInfo = packageInfo.applicationInfo;
			Drawable icon = applicationInfo.loadIcon(manager);
			String name = applicationInfo.loadLabel(manager).toString();
			int flags = applicationInfo.flags;
			boolean isUser=true;
			if((flags&applicationInfo.FLAG_SYSTEM)==applicationInfo.FLAG_SYSTEM){
				isUser=false;
			}
			boolean isSD=false;
			if ((flags & applicationInfo.FLAG_EXTERNAL_STORAGE) == applicationInfo.FLAG_EXTERNAL_STORAGE) {
			   isSD=true; 
			}
			AppInfo appInfo = new AppInfo(name, icon, packageName, versionName, isSD, isUser);
			list.add(appInfo);
		}
		return list;
	}
}
