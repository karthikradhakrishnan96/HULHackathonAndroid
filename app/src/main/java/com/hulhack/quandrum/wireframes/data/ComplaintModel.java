package com.hulhack.quandrum.wireframes.data;

/**
 * Created by Aman on 3/14/2016.
 */
public class ComplaintModel {

    public String status, category, subject, solution, id, priority, date, region;
    public ComplaintModel(String status, String category, String subject, String solution, String id, String priority, String date, String region) {
        this.status = status;
        this.category = category;
        this.solution = solution;
        this.subject = subject;
        this.id = id;
        this.priority = priority;
        this.date = date;
        this.region = region;
    }

}