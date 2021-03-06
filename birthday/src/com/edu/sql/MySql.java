package com.edu.sql;

import com.edu.value.Sqlvalue;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySql extends SQLiteOpenHelper {

	private static MySql mInstance = null;
	
	//id,姓名，年龄，性别，头像，公历年，公历月，公历日，电话，生肖，星座，备注
	private static final String create = "Create Table " + Sqlvalue.TABLE_birth_name + "(id integer primary key,"
			+ Sqlvalue.birthPer_name + " text," + Sqlvalue.birthPer_age + " integer," + Sqlvalue.birthPer_sex + " integer,"
			+ Sqlvalue.birthPer_photo + " text," + Sqlvalue.birthPer_Greyear + " integer," + Sqlvalue.birthPer_Gremonth + " integer,"
			+ Sqlvalue.birthPer_Greday + " integer," + Sqlvalue.birthPer_phone + " text," + Sqlvalue.birthPer_animals + " text,"
			+ Sqlvalue.birthPer_constellation + " text," + Sqlvalue.birthPer_beizhuInfo + " text)";
	
	private MySql(Context context) {
		super(context, "birthday.db", null, 1);
		// TODO Auto-generated constructor stub
	}
	
	//单例模式
	static synchronized MySql getInstance(Context context){
		if(mInstance == null){
			mInstance = new MySql(context);
		}
		return mInstance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(create);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
