package com.shaung.ramclear.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WhiteProcessSqllHelper extends SQLiteOpenHelper {

	public WhiteProcessSqllHelper(Context context) {
		super(context, "whiteprocess.db", null, 1);
	}
	public final static String TABLE_Name="white";
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql="create table "+TABLE_Name+" (id integer primary key autoincrement,whitename varchar(20))";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
