package com.google.myapplication;
import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;


public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(titleno TEXT primary key, title TEXT, dogtype TEXT, writeairticle TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists Userdetails");
    }


    public Boolean insertdata(String titleno, String title, String dogtype, String writeairticle)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("titleno", titleno);
        contentValues.put("title", title);
        contentValues.put("dogtype", dogtype);
        contentValues.put("writeairticle", writeairticle);
        long result=DB.insert("Userdetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updatedata(String titleno, String title, String dogtype, String writeairticle)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("dogtype", dogtype);
        contentValues.put("writeairticle", writeairticle);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where titleno = ?", new String[]{titleno});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "titleno=?", new String[]{titleno});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Boolean deletedata (String titleno)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where titleno = ?", new String[]{titleno});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Userdetails", "titleno=?", new String[]{titleno});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;
    }
}