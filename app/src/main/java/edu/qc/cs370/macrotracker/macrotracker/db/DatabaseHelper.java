package edu.qc.cs370.macrotracker.macrotracker.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
  public static final String TAG = "DatabaseHelper";

  public static final String tableName = "Enter table name here";
  public static String column_1 = "Enter column here; copy paste to the amount of required columns";

  public DatabaseHelper(Context context) {
    super(context, tableName, null, 1);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    String createTable = "CREATE TABLE " + tableName + " remaining SQL query code to create table";
    db.execSQL(createTable);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // May not be the correct query, please check docs! - DV
    db.execSQL("DROP TABLE IF EXISTS " + tableName);
    onCreate(db);
  }

  public boolean addData(String item) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    // the put method is a (String key, DataType value) method
    contentValues.put(column_1, item);

    Log.d(TAG, "addData: Adding " + item + " to " + tableName);

    long result = db.insert(tableName, null, contentValues);

    // If the data is inserted unsuccessfully, it will return -1
    if (result == -1) {
      return false;
    } else {
      return true;
    }
  }
}
