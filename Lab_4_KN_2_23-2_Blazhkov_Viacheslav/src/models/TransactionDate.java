package models;

public class TransactionDate {
    private int _day;
    private int _month;
    private int _year;

    public TransactionDate(int day, int month, int year) {
        _day = day;
        _month = month;
        _year = year;
    }

    @Override
    public String toString() {
        return _day + "-" + _month + "-" + _year;
    }

    public int getDay() {
        return _day;
    }

    public int getMonth() {
        return _month;
    }

    public int getYear() {
        return _year;
    }
}
