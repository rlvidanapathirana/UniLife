package com.shadowsquad.unilife;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHandler extends SQLiteOpenHelper {
        private static final int VERSION = 1;
        private static final String DB_NAME = "unilife";
        private static final String GPA_TABLE_NAME = "gpa";

        //COLUMN NAMES
        private static final String ID = "id";
        private static final String CGPA = "cgpa";
        private static final String TARGET = "target_cgpa";

        public DbHandler(@Nullable Context context) {
            super(context, DB_NAME, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String TABLE_CREATE_QUERY = "CREATE TABLE " + GPA_TABLE_NAME + " " +
                    "("
                    + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + CGPA + " TEXT,"
                    + TARGET + " TEXT" +
                    ");";
            db.execSQL(TABLE_CREATE_QUERY);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + GPA_TABLE_NAME;
           //DROP OLDER TABLE IF EXISTED
            db.execSQL(DROP_TABLE_QUERY);
            //Create tables again
            onCreate(db);

        }

        public boolean insertgpa(int i1, String s1, String s2) {
            SQLiteDatabase dbb = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(ID, i1);
            contentValues.put(CGPA, s1);
            contentValues.put(TARGET, s2);
            long result = dbb.insert(GPA_TABLE_NAME, null, contentValues);
            if(result==-1) {
                return false;
            }
            else {
                return true;
            }
        }

        public gpaModel getSingle(int id) {
            SQLiteDatabase db = getWritableDatabase();
            Cursor cursor = db.query(GPA_TABLE_NAME, new String[]{ID, CGPA, TARGET},
                    ID + "= ?", new String[]{String.valueOf(id)},
                    null, null, null);
            gpaModel gpamodel;
            if (cursor != null) {
                cursor.moveToFirst();
                gpamodel = new gpaModel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                );
                return gpamodel;
            }
            return null;
        }

        public int updateCgpa(gpaModel gpamodel) {

            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put(CGPA, gpamodel.getCgpa());
            contentValues.put(TARGET, gpamodel.getTarget());

            int ret = sqLiteDatabase.update(GPA_TABLE_NAME, contentValues, ID +" =?", new String[]{String.valueOf(1)});
            sqLiteDatabase.close();

            return ret;

        }

}
