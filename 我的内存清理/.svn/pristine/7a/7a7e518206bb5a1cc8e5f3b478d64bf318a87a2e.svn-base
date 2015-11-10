package com.shaung.ramclear.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Utils {
	/**
	 * md5加密
	 * @param password
	 * @return
	 */
	public static String digestPassWord(String password){
		StringBuilder sb = new StringBuilder();
		//MD5加密
		try {
			//1.获取数据摘要器
			//arg0 : 加密方式
			MessageDigest digest = MessageDigest.getInstance("MD5");
			//2.将一个进行加密，返回一个加密过的数组，二进制的哈希计算   
			//arg0 : 要加密的byte数组    
			byte[] digests = digest.digest(password.getBytes());
			//3.遍历这个数组
			for (int i = 0; i < digests.length; i++) {
				//4.进行md5计算
				//  -128-127
				int result = digests[i] & 0xff;
				//将获取的值转化成十六进制的字符串
//				String hexString = Integer.toHexString(result)+1;//不规则加密，加盐
				String hexString = Integer.toHexString(result);
				if (hexString.length() < 2) {
//					System.out.print("0");
					sb.append("0");
				}
//				System.out.println(hexString);
				sb.append(hexString);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			//找不到加密方式的异常
			e.printStackTrace();
			return null;
		}
	}
	
	
}
