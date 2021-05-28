package com.example.asmandroid.control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asmandroid.Database.SQLiteSinhvien;
import com.example.asmandroid.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class SVControl {
    public static final String SQL_TABLE_SV = "CREATE TABLE SinhVien ( idSV text primary key, name text, sdt text)";
    public static final String TABLE_NAME = "SinhVien";
    public SQLiteSinhvien sqLiteSinhvien;
    public SQLiteDatabase sqLiteDatabase;
    private Context context;
    public SVControl(Context context){
        this.context=context;
        sqLiteSinhvien = new SQLiteSinhvien(context);
        sqLiteDatabase = sqLiteSinhvien.getWritableDatabase();
    }

    //insert
    public int insertSV(SinhVien sinhVien){
        ContentValues values = new ContentValues();
        values.put("idSV",sinhVien.getIdSV());
        values.put("name",sinhVien.getName());
        values.put("sdt",sinhVien.getSdt());
        if (sqLiteDatabase.insert(TABLE_NAME, null,values)<0){
            return -1;//ko thành công
        }
        return 1;//thành công
    }

    //doc dữ liệu
    public List<String> getAllSVString(){
        List<String> list = new ArrayList<String>();
        Cursor cursor=sqLiteDatabase.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            SinhVien sinhVien = new SinhVien();
            sinhVien.setIdSV(cursor.getString(0));
            sinhVien.setName(cursor.getString(1));
            sinhVien.setSdt(cursor.getString(2));
            list.add(sinhVien.getIdSV()+" "+sinhVien.getName()+" - "+sinhVien.getSdt()+" . ");
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public int delSV(String idSV){
        if (sqLiteDatabase.delete(TABLE_NAME, "idSV=?",new String[]{idSV})<0){
            return -1;
        }
        return 1;
    }

    public int upDSV(SinhVien sinhVien){
        ContentValues values = new ContentValues();
        values.put("name",sinhVien.getName());
        values.put("sdt",sinhVien.getSdt());
        if(sqLiteDatabase.update(TABLE_NAME,values,"idSV=?",new String[]{sinhVien.getIdSV()})<0){
            return -1;
        }
        return 1;
    }
}
