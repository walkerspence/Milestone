package com.example.walker.milestone;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Milestone {
    String name;
    Date date;

    Milestone(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    Milestone(String name, String date) {
        this.name = name;
        try {
            this.date = new SimpleDateFormat("MM-dd-yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public Date getDate() {
        return this.date;
    }


}
