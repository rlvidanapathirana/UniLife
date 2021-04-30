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

    private static final int VERSION = 1;
    private static final String DB_NAME = "unilife";
    private static final String EXAM_TABLE_NAME = "exam";


    //Column names Exam
    private static final String ID = "id";
    private static final String EXAM_NAME = "examName";
    private static final String DATE = "date";
    private static final String TIME = "time";
    private static final String PLACE = "place";
    private static final String TYPE = "type";
    private static final String NOTE = "note";
    private static final String STARTED = "started";
    private static final String FINISHED = "finished";


    public DbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //CREATE SQL Table
        String TABLE_CREATE_QUERY = "CREATE TABLE " + EXAM_TABLE_NAME + "" +
                "("
                + ID + " INTEGER PRIMARY KEY " + "AUTOINCREMENT,"
                + EXAM_NAME + " TEXT,"
                + DATE + " TEXT,"
                + TIME + " TEXT,"
                + PLACE + " TEXT,"
                + TYPE + " TEXT,"
                + NOTE + " TEXT,"
                + STARTED + " TEXT,"
                + FINISHED + " TEXT" +
                ");";

        db.execSQL(TABLE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + EXAM_TABLE_NAME;
        //DROP OLDER TABLE IF EXISTED
        db.execSQL(DROP_TABLE_QUERY);
        //Create tables again
        onCreate(db);
    }

    //Add Exam add
    public void addExam(ExamModel examModel) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase(); //WRITE DATA IN DATABASE

        ContentValues contentValues = new ContentValues(); //Data struct and store in DB

        contentValues.put(EXAM_NAME,examModel.getExamName());
        contentValues.put(DATE,examModel.getDate());
        contentValues.put(TIME,examModel.getTime());
        contentValues.put(PLACE,examModel.getPlace());
        contentValues.put(TYPE,examModel.getType());
        contentValues.put(NOTE,examModel.getNote());
        contentValues.put(STARTED,examModel.getStarted());
        contentValues.put(FINISHED,examModel.getFinished());

        //Save to table
        sqLiteDatabase.insert(EXAM_TABLE_NAME,null,contentValues);
        //close database
        sqLiteDatabase.close();
    }

    //Get All Add Exams
    public List<ExamModel> getAllInsertedExamModel(){

        List<ExamModel> ExamModels = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " +EXAM_TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);


        if (cursor.moveToFirst()){
            do {
                //Create new ExamModel object
                ExamModel examModel = new ExamModel();

                //set values to the object
                examModel.setId(cursor.getInt(0));
                examModel.setExamName(cursor.getString(1));
                examModel.setDate(cursor.getString(2));
                examModel.setTime(cursor.getString(3));
                examModel.setPlace(cursor.getString(4));
                examModel.setType(cursor.getString(5));
                examModel.setNote(cursor.getString(6));
                examModel.setStarted(cursor.getLong(7));
                examModel.setFinished(cursor.getLong(8));

                //examModels = [obj,obj.]
                ExamModels.add(examModel);
                } while (cursor.moveToNext());
        }
        return ExamModels;

    }

}
