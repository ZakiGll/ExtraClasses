package com.example.extraclasses;

public class Cources {
    String Cname;
    String Cyear;
    String module;
    String description;
    String adrs;
    String time;
    String date;
    String teacher;
    String id;
    String price;

    public Cources() { }

    public Cources(String Cname,String Cyear, String module, String description,String adrs,String time,String date,String price,String teacher,String id) {

        this.Cname=Cname;
        this.Cyear=Cyear;
        this.module=module;
        this.description=description;
        this.adrs=adrs;
        this.time=time;
        this.date=date;
        this.description=description;
        this.teacher = teacher;
        this.id=id;
        this.price=price;



    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public String getCyear() {
        return Cyear;
    }

    public void setCyear(String cyear) {
        Cyear = cyear;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdrs() {
        return adrs;
    }

    public void setAdrs(String adrs) {
        this.adrs = adrs;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getId() {
        return id;
    }

    public void setId(String students) {
        id = students;
    }
}

