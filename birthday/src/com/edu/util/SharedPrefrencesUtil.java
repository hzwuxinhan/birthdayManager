package com.edu.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPrefrencesUtil {
	//table为SharedPrefrences的文件名，s为键，data为值
	public static void saveToSharedPrefrences(String table, String s,String data,Context context){
		SharedPreferences sharedPreferences = context.getSharedPreferences(table, 0);
		Editor editor = sharedPreferences.edit();
		editor.putString(s, data);
		editor.commit();
	}
	
	//table为SharedPrefrences的文件名，s为键，data为值
	//account --->haveSaved表代表着是否已经保存着账号密码
	public static void saveToSharedPrefrences(String table,String s,Boolean data,Context context){
		SharedPreferences sharedPreferences = context.getSharedPreferences(table, 0);
		Editor editor = sharedPreferences.edit();
		editor.putBoolean(s, data);
		editor.commit();
	}
	
	//table为SharedPrefrences的文件名，s为键，s1表示类型
	public static String getSharedPrefrences(String table,String s,String s1,Context context){
		SharedPreferences sharedPreferences = context.getSharedPreferences(table, 0);
		String result = sharedPreferences.getString(s, "");
		return result;
	}
	
	//table为SharedPrefrences的文件名，s为键，s1位类型
	public static Boolean getSharedPrefrences(String table,String s,Boolean s1,Context context){
		SharedPreferences sharedPreferences = context.getSharedPreferences(table, 0);
		Boolean result = sharedPreferences.getBoolean(s, false);
		return result;
	}
}
