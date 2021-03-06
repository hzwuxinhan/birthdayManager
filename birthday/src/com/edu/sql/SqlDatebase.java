package com.edu.sql;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.edu.birthday.R;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class SqlDatebase {
	
	private static SQLiteDatabase sqLiteDatabase;
	
	public SqlDatebase(Context context){
	}
	
	public static synchronized SQLiteDatabase getInstanceDatabase(Context context){
		if(sqLiteDatabase == null){
			sqLiteDatabase = fromOutCopyDate(context);
		}
		return sqLiteDatabase;
	}
	
	public static SQLiteDatabase fromOutCopyDate(Context context){
		String DATEBASE_PATH = "/data/data/com.edu.birthday/databases";
		String DATEBASE_FILENAME = "Birthday_sms.db";
		
//		数据的绝对路径
		String datebasePath = DATEBASE_PATH + "/" + DATEBASE_FILENAME;
		File path = new File(DATEBASE_PATH);
		//建立文件夹
		if(!path.exists()){
			path.mkdir();
		}
		//建立一个文件
		if(!(new File(datebasePath)).exists()){
			InputStream inputStream = context.getResources().openRawResource(R.raw.sms_info);
			
			try{
				FileOutputStream fileOutputStream = new FileOutputStream(datebasePath);
				byte data[] = new byte[2048];
				
				int index = 0;
				while((index = inputStream.read(data)) != -1){
					fileOutputStream.write(data, 0, index);
				}
				inputStream.close();
				fileOutputStream.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(datebasePath, null);
		return sqLiteDatabase;
	}
}
