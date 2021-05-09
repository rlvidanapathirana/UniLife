package com.shadowsquad.unilife;

public class EventModle {
    private  int id;
    private String eventName,presenter,venue,statTime,endTime,date,note;
    private long started,finished;



    public  EventModle(){


    }
    public EventModle(int id, String eventName, String presenter, String venue,
                      String statTime, String endTime, String date, String note,
                      long started, long finished) {
        this.id = id;
        this.eventName = eventName;
        this.presenter = presenter;
        this.venue = venue;
        this.statTime = statTime;
        this.endTime = endTime;
        this.date = date;
        this.note = note;
        this.started = started;
        this.finished = finished;
    }

    public EventModle(String eventName, String presenter, String venue,
                      String statTime, String endTime, String date, String note,
                      long started, long finished) {
        this.eventName = eventName;
        this.presenter = presenter;
        this.venue = venue;
        this.statTime = statTime;
        this.endTime = endTime;
        this.date = date;
        this.note = note;
        this.started = started;
        this.finished = finished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getPresenter() {
        return presenter;
    }

    public void setPresenter(String presenter) {
        this.presenter = presenter;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getStatTime() {
        return statTime;
    }

    public void setStatTime(String statTime) {
        this.statTime = statTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
