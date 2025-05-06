package com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class DepositRequest {
    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @NotNull(message = "Amount should have value")
    @Min(value = 1, message = "Amount should be more than 0")
    @Max(value = 100000, message = "Amount should be less than 100001")
    private long amount;
    @NotNull(message = "Day should have value")
    @Min(value = 1, message = "Day should be more than 0")
    @Max(value = 31, message = "Day should be less than 31")
    private int day;
    @NotNull(message = "Month should have value")
    @Min(value = 1, message = "Month should be more than 0")
    @Max(value = 12, message = "Month should be less than 13")
    private int month;
    @NotNull(message = "Year should have value")
    @Min(value = 1999, message = "Year should be more than 1998")
    @Max(value = 2055, message = "Year should be less than 2056")
    private int year;
}
