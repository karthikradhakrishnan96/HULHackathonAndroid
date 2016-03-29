package com.hulhack.quandrum.wireframes.data;

/**
 * Created by Aman on 3/14/2016.
 */
public class ComplaintModel {

    public String status, category, subject, solution, id, priority, date, region;
    public ComplaintModel(String status, String category, String subject, String solution, String id, String priority, String date, String region) {
        this.status = status;
        this.category = "Category: "+category;
        this.solution = "Solution: " + solution;
        this.subject = "Subject: " + subject;
        this.id = "ID: "+id;
        this.priority = "Criticality: "+priority;
        this.date = "Date: "+date;
        this.region = "Region: "+region;
    }

}