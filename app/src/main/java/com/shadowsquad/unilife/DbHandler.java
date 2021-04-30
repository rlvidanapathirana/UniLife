package com.shadowsquad.unilife;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {
    private  static  final  int VERSION =1;
    private  static  final String DB_NAME ="unilife";
    private  static final String EVENT_TABLE_NAME ="Event";


    //COLOUM NAMES
    private static final String ID ="id";
    private static final String EVENT_NAME ="eventName";
    private static final String PRESENTER ="presenter";
    private static final String VENUE ="venue";
    private static final String START_TIME ="statTime";
    private static final String END_TIME ="endTime";
    private static final String DATE ="date";
    private static final String NOTE ="note";
    private static final String FINISHED ="finished";
    private static final String STARTED ="started";

    public DbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE_QUERY ="CREATE TABLE " +EVENT_TABLE_NAME+" "+
                "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + EVENT_NAME +" TEXT,"
                + PRESENTER +" TEXT,"
                + VENUE +" TEXT,"
                + START_TIME +" TEXT,"
                + END_TIME +" TEXT,"
                + DATE +" TEXT,"
                + NOTE +" TEXT,"
                + FINISHED +" TEXT,"
                + STARTED + " TEXT " +
                ");";

            db.execSQL(TABLE_CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+EVENT_TABLE_NAME;
        //DROP OLDER TABLE IF EXISTED
        db.execSQL(DROP_TABLE_QUERY);
        //Create tables agin
        onCreate(db);
    }

    //data is added event table
    public void addCreateEvent(EventModle eventModle){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues  contentValues = new ContentValues();

        contentValues.put(EVENT_NAME,eventModle.getEventName());
        contentValues.put(PRESENTER,eventModle.getPresenter());
        contentValues.put(VENUE,eventModle.getEventName());
        contentValues.put(START_TIME,eventModle.getEventName());
        contentValues.put(END_TIME,eventModle.getEndTime());
        contentValues.put(DATE,eventModle.getPresenter());
        contentValues.put(NOTE,eventModle.getEventName());
        contentValues.put(FINISHED,eventModle.getFinished());
        contentValues.put(STARTED,eventModle.getStarted());

        sqLiteDatabase.insert(EVENT_TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();

    }
    //get all inserted data from database

    public List<EventModle> getallInsertedEvents(){
        List<EventModle> eventModles = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query ="SELECT * FROM " +EVENT_TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                EventModle eventModle = new EventModle();

                eventModle.setId(cursor.getInt(0));
                eventModle.setEventName(cursor.getString(1));
                eventModle.setPresenter(cursor.getString(2));
                eventModle.setVenue(cursor.getString(3));
                eventModle.setStatTime(cursor.getString(4));
                eventModle.setEndTime(cursor.getString(5));
                eventModle.setDate(cursor.getString(6));
                eventModle.setNote(cursor.getString(7));
                eventModle.setFinished(cursor.getLong(8));
                eventModle.setStarted(cursor.getLong(9));

                eventModles.add(eventModle);
            }while (cursor.moveToNext());

        }
        return eventModles;

    }


}
