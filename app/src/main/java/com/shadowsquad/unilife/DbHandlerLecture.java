package com.shadowsquad.unilife;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHandlerLecture extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DB_NAME = "unilife";
    private static final String lECTURE_TABLE_NAME = "lecture";

    // Column names of the table
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DATE = "date";
    private static final String TIME = "time";
    private static final String PLACE = "place";
    private static final String CONDUCTEDBY = "conductedBy";
    private static final String SPECIALNOTE = "specialNote";
    private static final String STARTED = "started";
    private static final String FINISHED = "finished";


    public DbHandlerLecture(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        String TABLE_CREATE_QUERY = "CREATE TABLE " + lECTURE_TABLE_NAME + " " +
                "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " TEXT,"
                + DATE + " TEXT,"
                + TIME + " TEXT,"
                + PLACE + " TEXT,"
                + CONDUCTEDBY + " TEXT,"
                + SPECIALNOTE + " TEXT,"
                + STARTED + " TEXT,"
                + FINISHED + " TEXT" +
                ");";

        /*
            CREATE TABLE lecture (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT,
            started TEXT,finished TEXT); */

        db.execSQL(TABLE_CREATE_QUERY); //run the query and create the table
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + lECTURE_TABLE_NAME;
        // Drop older table if existed
        db.execSQL(DROP_TABLE_QUERY);
        // Create tables again
        onCreate(db);

    }
    // Add a single lecture
    public void addLecture(Lecture lectures) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase(); //create an object to write the data

        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, lectures.getName());
        contentValues.put(DATE, lectures.getDate());
        contentValues.put(TIME, lectures.getTime());
        contentValues.put(PLACE, lectures.getPlace());
        contentValues.put(CONDUCTEDBY, lectures.getConductedBy());
        contentValues.put(SPECIALNOTE, lectures.getSpecialNote());
        contentValues.put(STARTED, lectures.getStarted());
        contentValues.put(FINISHED, lectures.getFinished());

        //save to table
        sqLiteDatabase.insert(lECTURE_TABLE_NAME, null, contentValues);
        // close database
        sqLiteDatabase.close();
    }

    //get all inserted data from database
    public List<Lecture> getallInsertedLecture() {
        List<Lecture> lectures = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + lECTURE_TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Lecture lecture = new Lecture();

                lecture.setId(cursor.getInt(0));
                lecture.setName(cursor.getString(1));
                lecture.setDate(cursor.getString(2));
                lecture.setTime(cursor.getString(3));
                lecture.setPlace(cursor.getString(4));
                lecture.setConductedBy(cursor.getString(5));
                lecture.setSpecialNote(cursor.getString(7));
                lecture.setFinished(cursor.getLong(8));
              // lecture.setStarted(cursor.getLong(9));

                lectures.add(lecture);
            } while (cursor.moveToNext());

        }
        return lectures;



    }
    //item delete in listview
    public void deleteLectureTodo(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(lECTURE_TABLE_NAME, ID + " =?", new String[]{String.valueOf(id)});
        db.close();
    }

    public Lecture getsingleLectureTodo(int id) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(lECTURE_TABLE_NAME, new String[]{ID, NAME, DATE, TIME, PLACE, CONDUCTEDBY, SPECIALNOTE, FINISHED, STARTED},
                ID + "= ?", new String[]{String.valueOf(id)},
                null, null, null);

        Lecture lecture;
        if (cursor != null) {
            cursor.moveToFirst();
            lecture = new Lecture(

                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getLong(7),
                    cursor.getLong(8)

            );
            return lecture;
        }
        return null;
    }


    //UPDATE DETAILS ADD DATABASE
    public int updateLectureTodo(Lecture lecture) {
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, lecture.getName());
        contentValues.put(DATE, lecture.getDate());
        contentValues.put(TIME, lecture.getTime());
        contentValues.put(PLACE, lecture.getPlace());
        contentValues.put(CONDUCTEDBY, lecture.getConductedBy());
        contentValues.put(SPECIALNOTE, lecture.getSpecialNote());
        contentValues.put(STARTED, lecture.getStarted());
        contentValues.put(FINISHED, lecture.getFinished());



        int statusLecture = sqLiteDatabase.update(lECTURE_TABLE_NAME, contentValues, ID +"  =?", new String[]{String.valueOf(lecture.getId())});

        Log.i("ABC",String.valueOf(lecture.getId() + " " ));

        if(statusLecture>0) {
            System.out.println("Successfully Updated");
        }
        else{
            System.out.println("faild Updated");

        }

        sqLiteDatabase.close();
        return statusLecture;


    }

        //counter part implementation

    public  int CountLectureTodo(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + lECTURE_TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();


    }
}