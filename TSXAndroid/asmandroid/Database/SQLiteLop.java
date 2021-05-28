package com.example.asmandroid.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.asmandroid.control.LopControl;

public class SQLiteLop  extends SQLiteOpenHelper {
    private Context context;
    private static final String DB_NAME="QLSV";
    private static final int version=1;
    public SQLiteLop(@Nullable Context context) {
        super(context, DB_NAME, null, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(LopControl.SQL_TABLE_LOP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         db.execSQL("DROP TABLE IF EXISTS "+LopControl.TABLE_NAME);
    }

}
