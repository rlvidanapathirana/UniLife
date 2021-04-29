package com.shadowsquad.unilife;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHandler extends SQLiteOpenHelper {
    private  static  final  int VERSION =1;
    private  static  final String DB_NAME ="Unilife";
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
}
