package com.financebookprogram.models;

public class Date {
   public int date;
   public String month;
   public int year;

    public Date (int date, String month, int year) {
        this.date = date;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + date;
    }
}