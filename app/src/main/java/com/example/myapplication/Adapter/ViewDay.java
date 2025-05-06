package com.example.myapplication.Adapter;

public class ViewDay {

    private String Day;
    private String Month;
    private String DayOfWeek;
    private Float Alpha;

    public ViewDay(String Day, String Month, String DayOfWeek, Float Alpha) {
        this.Day = Day;
        this.Month = Month;
        this.DayOfWeek = DayOfWeek;
        this.Alpha = Alpha;
    }

    public String getDay() {return this.Day;}
    public void setDay(String NewDay) {this.Day = NewDay;}
    public String getMonth() {return this.Month;}
    public void  setMonth(String NewMonth) {this.Month = NewMonth;}
    public String getDayOfWeek() {return this.DayOfWeek;}
    public void setDayOfWeek(String NewDayOfWeek) {this.DayOfWeek = NewDayOfWeek;}
    public Float getAlpha() {return this.Alpha;}
    public void setAlpha(Float NewAlpha) {this.Alpha = NewAlpha;}

}
