package com.shadowsquad.unilife;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static android.icu.text.MessagePattern.ArgType.SELECT;
import static java.time.LocalDateTime.MIN;

public class DbHandlerEvent extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DB_NAME = "unilife";
    private static final String EVENT_TABLE_NAME = "Event";



    //COLOUM NAMES
    private static final String ID = "id";
    private static final String EVENT_NAME = "eventName";
    private static final String PRESENTER = "presenter";
    private static final String VENUE = "venue";
    private static final String START_TIME = "statTime";
    private static final String END_TIME = "endTime";
    private static final String DATE = "date";
    private static final String NOTE = "note";
    private static final String FINISHED = "finished";
    private static final String STARTED = "started";

    public DbHandlerEvent(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE_QUERY = "CREATE TABLE " + EVENT_TABLE_NAME + " " +
                "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + EVENT_NAME + " TEXT,"
                + PRESENTER + " TEXT,"
                + VENUE + " TEXT,"
                + START_TIME + " TEXT,"
                + END_TIME + " TEXT,"
                + DATE + " TEXT,"
                + NOTE + " TEXT,"
                + FINISHED + " TEXT,"
                + STARTED + " TEXT " +
                ");";

        db.execSQL(TABLE_CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + EVENT_TABLE_NAME;
        //DROP OLDER TABLE IF EXISTED
        db.execSQL(DROP_TABLE_QUERY);
        //Create tables agin
        onCreate(db);
    }

    //data is added event table
    public void addCreateEvent(EventModle eventModle) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(EVENT_NAME, eventModle.getEventName());
        contentValues.put(PRESENTER, eventModle.getPresenter());
        contentValues.put(VENUE, eventModle.getVenue());
        contentValues.put(START_TIME, eventModle.getStatTime());
        contentValues.put(END_TIME, eventModle.getEndTime());
        contentValues.put(DATE, eventModle.getDate());
        contentValues.put(NOTE, eventModle.getNote());
        contentValues.put(FINISHED, eventModle.getFinished());
        contentValues.put(STARTED, eventModle.getStarted());

        sqLiteDatabase.insert(EVENT_TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();

    }
    //get all inserted data from database

    public List<EventModle> getallInsertedEvents() {
        List<EventModle> eventModles = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + EVENT_TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
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
            } while (cursor.moveToNext());

        }
        return eventModles;

    }

    //counter (TABLE RAWS)
    public  int CountEventTodo(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + EVENT_TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();

    }






    // get a single todo
    public EventModle getsingleTodo(int id) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(EVENT_TABLE_NAME, new String[]{ID, EVENT_NAME, PRESENTER, VENUE, START_TIME, END_TIME, DATE, NOTE, FINISHED, STARTED},
                ID + "= ?", new String[]{String.valueOf(id)},
                null, null, null);

        EventModle eventModle;
        if (cursor != null) {
            cursor.moveToFirst();
            eventModle = new EventModle(

                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getLong(8),
                    cursor.getLong(9)

            );
            return eventModle;
        }
        return null;
    }

    //UPDATE DETAILS ADD DATABASE

    public int updateEventTodo(EventModle eventModle) {
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(EVENT_NAME, eventModle.getEventName());
        contentValues.put(PRESENTER, eventModle.getPresenter());
        contentValues.put(VENUE, eventModle.getVenue());
        contentValues.put(START_TIME, eventModle.getStatTime());
        contentValues.put(END_TIME, eventModle.getEndTime());
        contentValues.put(DATE, eventModle.getDate());
        contentValues.put(NOTE, eventModle.getNote());
        contentValues.put(FINISHED, eventModle.getFinished());
        contentValues.put(STARTED, eventModle.getStarted());


        int statusEvent = sqLiteDatabase.update(EVENT_TABLE_NAME, contentValues, ID +"  =?",
                new String[]{String.valueOf(eventModle.getId())});
        //  Log.i("ABC",String.valueOf(eventModle.getId() + " " ));
        sqLiteDatabase.close();
        return statusEvent;
    }

    //item delete in listview

    public void deleteEventTodo(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(EVENT_TABLE_NAME, ID + " =?", new String[]{String.valueOf(id)});
        db.close();

    }
}
