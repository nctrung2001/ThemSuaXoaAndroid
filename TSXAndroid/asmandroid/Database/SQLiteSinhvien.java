package com.example.asmandroid.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.asmandroid.control.SVControl;

public class SQLiteSinhvien extends SQLiteOpenHelper {
    private static final String DB_NAME="QLSinhVien";
    private static final int version=1;
    public SQLiteSinhvien(@Nullable Context context) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL(SVControl.SQL_TABLE_SV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS " +SVControl.TABLE_NAME);
    }
}
