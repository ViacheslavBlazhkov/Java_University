package com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Models;

public class TransactionDate {
    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }
    public TransactionDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
    @Override
    public String toString() {
        return new String(day + "-" + month + "-" + year);
    }
    private int day; //день
    private int month; //місяць
    private int year; //рік
}
