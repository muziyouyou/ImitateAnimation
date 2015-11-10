package com.shaung.ramclear.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

public class StreamUtils {
	
	/**
	 * 解析读取流
	 * @param input
	 * @return
	 * @throws IOException 
	 */
	public static String parserStream(InputStream input) throws IOException{
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		StringWriter sw=new StringWriter();
		String line=null;
		while((line=reader.readLine())!=null){
			sw.write(line);
		}
		sw.close();
		reader.close();
		return sw.toString();
	}

}
