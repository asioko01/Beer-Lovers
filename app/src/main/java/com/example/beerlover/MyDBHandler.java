package com.example.beerlover;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;

import android.content.ContentValues;

import android.database.Cursor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BeerDB.db";
    private static String DATABASE_PATH="/data/user/0/com.example.beerlover/databases/";
    SQLiteDatabase myDB;
    private static Context mContext;


    public static final String TABLE_NAME = "Beer";
    public static final String COLUMN_CODE = "BeerCode";
    public static final String COLUMN_NAME = "BeerName";
    public static final String COLUMN_CHECK = "BeerCheck";
    public static final String COLUMN_PIC = "BeerPic";
    public static final String COLUMN_DET = "BeerDet";
    //initialize the database
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        this.mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {   }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}

    private boolean checkDatabase(){
        try{
            final String mPath=DATABASE_PATH+DATABASE_NAME;
            final File file=new File(mPath);
            if(file.exists())
                return true;
            else
                return false;
        }catch(SQLiteException e){
            e.printStackTrace();
            return false;
        }
    }

    private void copyDatabase() throws IOException{
        try{
            InputStream mInputStream= mContext.getAssets().open(DATABASE_NAME);
            String outFileName=DATABASE_PATH+DATABASE_NAME;
            OutputStream mOutputStream=new FileOutputStream(outFileName);
            byte[] buffer=new byte[1024];
            int length;
            while((length=mInputStream.read(buffer))>0){
                mOutputStream.write(buffer,0,length);
            }
            mOutputStream.flush();
            mOutputStream.close();
            mInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createDatabase() throws IOException{
        boolean mDatabaseExist = checkDatabase();
        if(!mDatabaseExist){
            this.getReadableDatabase();
            this.close();
            try{
                copyDatabase();
            }catch(IOException mIOException){
                mIOException.printStackTrace();
                throw new Error("Error copying Database");
            }finally {
                this.close();
            }
        }
    }

    @Override
    public synchronized void close(){
        if(myDB != null)
            myDB.close();
        SQLiteDatabase.releaseMemory();
        super.close();
    }


    public ArrayList<BeerDB> loadHandler() {
        try{
            createDatabase();

        }catch(IOException e){
            e.printStackTrace();
        }

        ArrayList<BeerDB> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("select * from Beer",null);
        while(c.moveToNext()){

            String result_Code=c.getString(0);
            String result_Name=c.getString(1);
            String result_Det=c.getString(2);
            int result_Check=c.getInt(3);
            String result_Pic=c.getString(4);

            if (result_Check == 1) {
                BeerDB beer = new BeerDB(result_Code, result_Name,  result_Det ,result_Check, result_Pic );
                list.add(beer);
            }
        }
        c.close();
        db.close();
        return list;
    }

    public void addHandler(BeerDB beer) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CODE, beer.getCode());
        values.put(COLUMN_NAME, beer.getName());
        values.put(COLUMN_DET,beer.getDet());
        values.put(COLUMN_CHECK,beer.getCheck());
        values.put(COLUMN_PIC,beer.getPic());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public BeerDB findHandler(String beerCode) {
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_CODE + " = " + "'" + beerCode + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        BeerDB beer = new BeerDB();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            beer.setCode(cursor.getString(0));
            beer.setName(cursor.getString(1));
            beer.setDet(cursor.getString(2));
            beer.setCheck(cursor.getInt(3));
            beer.setPic(cursor.getString(4));
            cursor.close();
        } else {
            beer = null;
        }
        db.close();
        return beer;
    }

    public boolean deleteHandler(String code) {
        boolean result = false;
        String query = "Select*FROM" + TABLE_NAME + "WHERE" + COLUMN_CODE + "= '" + code + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        BeerDB beer = new BeerDB();
        if (cursor.moveToFirst()) {
            beer.setCode(cursor.getString(0));
            db.delete(TABLE_NAME, COLUMN_CODE + "=?",
                    new String[] {
                            code
                    });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updateHandler(String beerCode, String beerName,String beerDet,int beerCheck,String beerPic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_CODE, beerCode);
        args.put(COLUMN_NAME, beerName);
        args.put(COLUMN_CHECK,beerCheck);
        args.put(COLUMN_PIC,beerPic);
        args.put(COLUMN_DET,beerDet);
        return db.update(TABLE_NAME, args, COLUMN_CODE + "=" + beerCode, null) > 0;
    }
}
