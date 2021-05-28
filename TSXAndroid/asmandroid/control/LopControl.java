package com.example.asmandroid.control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.asmandroid.Database.SQLiteLop;
import com.example.asmandroid.model.Lop;

import java.util.ArrayList;
import java.util.List;

public class LopControl  {
    public static final String SQL_TABLE_LOP = "CREATE TABLE lop ( maLop text primary key, tenLop text)";
    public static final String TABLE_NAME = "lop";
    public SQLiteLop sqLiteLop;
    public SQLiteDatabase sqLiteDatabase;
    private Context context;
    public LopControl(Context context){
        this.context=context;
        sqLiteLop = new SQLiteLop(context);
        sqLiteDatabase = sqLiteLop.getWritableDatabase();
    }

    //insert
    public int insertLop(Lop lop){
        ContentValues values = new ContentValues();
        values.put("maLop",lop.getMaLop());
        values.put("tenLop",lop.getTenLop());
        if (sqLiteDatabase.insert(TABLE_NAME, null,values)<0){
            return -1;//ko thành công
        }
        return 1;//thành công
    }

    //doc dữ liệu
    public List<String> getAllLopString(){
        List<String> list = new ArrayList<String>();
        Cursor cursor=sqLiteDatabase.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            Lop lop = new Lop();
            lop.setMaLop(cursor.getString(0));
            lop.setTenLop(cursor.getString(1));
            list.add(lop.getMaLop()+" "+lop.getTenLop()+" . ");
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    //doc dữ liệu
    public List<String> getAllMaLop(){
        List<String> list = new ArrayList<String>();
        Cursor cursor=sqLiteDatabase.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            Lop lop = new Lop();
            lop.setMaLop(cursor.getString(0));
            cursor.moveToNext();
            list.add(lop.getMaLop());
        }
        cursor.close();
        return list;
    }

    public int Dellop(String maLop){
        if (sqLiteDatabase.delete(TABLE_NAME, "maLop=?",new String[]{maLop})<0){
            return -1;
        }
        return 1;
    }

    public int upDlop(Lop lop){
        ContentValues values = new ContentValues();
        values.put("tenLop",lop.getTenLop());
        if (sqLiteDatabase.update(TABLE_NAME,values,"maLop=?",new String[]{lop.getMaLop()})<0){
            return -1;
        }
        return 1;
    }
}
