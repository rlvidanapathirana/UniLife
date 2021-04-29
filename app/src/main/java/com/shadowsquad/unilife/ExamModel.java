package com.shadowsquad.unilife;

public class ExamModel {
//Exam
    private int id;
    private  String examName, date , time , place, type , note;
    private long started, finished;



    public ExamModel(){




    }

    //constructor
    public ExamModel(int id, String examName, String date, String time , String place, String type, String note, long started, long finished) {
        this.id = id;
        this.examName = examName;
        this.date = date;
        this.time = time;
        this.place = place;
        this.type = type;
        this.note = note;
        this.started = started;
        this.finished = finished;
    }

    public ExamModel(String examName, String date, String time, String place, String type, String note, long started, long finished) {
        this.examName = examName;
        this.date = date;
        this.time = time;
        this.place = place;
        this.type = type;
        this.note = note;
        this.started = started;
        this.finished = finished;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getStarted() {
        return started;
    }

    public void setStarted(long started) {
        this.started = started;
    }

    public long getFinished() {
        return finished;
    }

    public void setFinished(long finished) {
        this.finished = finished;
    }


}
