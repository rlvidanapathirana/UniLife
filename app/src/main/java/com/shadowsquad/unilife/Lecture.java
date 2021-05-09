package com.shadowsquad.unilife;
//model class
//when passing the data to the data base this keeps temporally and when data getting also

public class Lecture {
    //variables
    private int id;
    private String name, date, time, place, conductedBy, specialNote;
    private long started, finished;

    public Lecture() {//empty constructor

    }

    public Lecture(int id, String name, String date, String time, String place, String conductedBy, String specialNote, long started, long finished) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.place = place;
        this.conductedBy = conductedBy;
        this.specialNote = specialNote;
        this.started = started;
        this.finished = finished;
    }

    public Lecture(String name, String date, String time, String place, String conductedBy, String specialNote, long started, long finished) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.place = place;
        this.conductedBy = conductedBy;
        this.specialNote = specialNote;
        this.started = started;
        this.finished = finished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getConductedBy() {
        return conductedBy;
    }

    public void setConductedBy(String conductedBy) {
        this.conductedBy = conductedBy;
    }

    public String getSpecialNote() {
        return specialNote;
    }

    public void setSpecialNote(String specialNote) {
        this.specialNote = specialNote;
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


