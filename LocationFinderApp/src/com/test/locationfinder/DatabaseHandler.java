package com.test.locationfinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler {

	private static final String CREATE_TABLE = "create table location_info (_id integer primary key autoincrement, "
			+ "place text not null, caption text not null, latitude text, longitude text);";
	private static final String DROP_TABLE = "drop table if exists location_info";

	private static final String TABLE_NAME = "location_info";
	private static final String DB_NAME = "location_db.db";
	private static final int DB_VERSION = 1;

	private DBHelper mDBHelper;

	private static DatabaseHandler dbHandlerInstance = null;

	public DatabaseHandler(Context context) {
		// TODO Auto-generated constructor stub
		mDBHelper = new DBHelper(context);
	}

	public static DatabaseHandler getDBHandlerInstance(Context c) {
		if (dbHandlerInstance == null) {
			dbHandlerInstance = new DatabaseHandler(c);
		}
		return dbHandlerInstance;
	}

	// Insert
	public boolean insertDataOperation(String place, String caption,
			String lat, String longitude) {

		SQLiteDatabase db = mDBHelper.getWritableDatabase();

		ContentValues cv = new ContentValues();
		cv.put("place", place);
		cv.put("caption", caption);
		cv.put("latitude", lat);
		cv.put("longitude", longitude);

		boolean insertQueryStaus = db.insert(TABLE_NAME, null, cv) > 0;
		return insertQueryStaus;
	}

	public String[] getData(String place) {
		// TODO Auto-generated method stub
		SQLiteDatabase newDB = mDBHelper.getWritableDatabase();
		String[] output = new String[2];

		try {
			Cursor cursor = newDB.query(TABLE_NAME, new String[] { "latitude",
					"longitude" }, "place=?", new String[] { place }, null,
					null, null);
			int resultRowsCount = cursor.getCount();
			if (resultRowsCount > 0) {
				cursor.moveToFirst();
				output[0] = cursor.getString(0);
				output[1] = cursor.getString(1);
			}
			cursor.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			newDB.close();
		}

		return output;
	}

	private static class DBHelper extends SQLiteOpenHelper {
		public DBHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
			// TODO Auto-generated constructor stub

		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			try {
				db.execSQL(CREATE_TABLE);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL(DROP_TABLE);
			onCreate(db);
		}
	}
}
