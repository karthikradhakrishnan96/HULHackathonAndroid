package com.hulhack.quandrum.wireframes.data;

/**
 * Created by KAI on 27-Mar-16.
 */
public class SalesModel {
    public String Region;
    public String RS_Name;
    public String Area;
    public String RS_Sales_forecast;
    public String RS_Sales;
    public String CUSTOMER_ID;
    public SalesModel(
            String Region,String RS_Name,String Area,String RS_Sales_forecast,String RS_Sales,String CUSTOMER_ID
    ) {
        this.Region = Region;
        this.RS_Name = RS_Name;
        this.Area = Area;
        this.RS_Sales_forecast = RS_Sales_forecast;
        this.RS_Sales = RS_Sales;
        this.CUSTOMER_ID = CUSTOMER_ID;
    }
}
