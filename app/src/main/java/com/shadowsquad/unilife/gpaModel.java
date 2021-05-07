package com.shadowsquad.unilife;

public class gpaModel {

    private int id;
    private String cgpa, target;
//    private long started, finished;

    public gpaModel() {

    }

    public gpaModel(int id, String cgpa, String target) {
        this.id = id;
        this.cgpa = cgpa;
        this.target = target;
    }

    public gpaModel(String cgpa, String target) {
        this.cgpa = cgpa;
        this.target = target;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCgpa() {
        return cgpa;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

}
