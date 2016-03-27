package com.hulhack.quandrum.wireframes.data;

/**
 * Created by KAI on 26-Mar-16.
 */
import java.util.Date;
public class Event {
    public String Activity_Type;
    public String Customer_ID;
    public String Business;
    public String Channel;
    public String CUSTOMER_ID;
    public String Region;
    public String Area;
    public String MOC;
    public String token;
    public String Date;
    public String RS_Name;
    public String number;
    public Date   mDate;
    public  Event(
            String Activity_Type,String Customer_ID,String Business,String Channel,String CUSTOMER_ID,String Region,String Area,String MOC,String token,String Date,String RS_Name,String number
    ,Date i) {
        this.Activity_Type = Activity_Type;
        this.Customer_ID = Customer_ID;
        this.Business = Business;
        this.Channel = Channel;
        this.CUSTOMER_ID = CUSTOMER_ID;
        this.Region = Region;
        this.Area = Area;
        this.MOC = MOC;
        this.token = token;
        this.Date = Date;
        this.RS_Name = RS_Name;
        this.number = number;
        this.mDate=i;
    }
}
