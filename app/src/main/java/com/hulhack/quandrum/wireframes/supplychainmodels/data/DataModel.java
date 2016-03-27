package com.hulhack.quandrum.wireframes.supplychainmodels.data;

/**
 * Created by Aman on 3/14/2016.
 */
public class DataModel {

    String deliveryID;
    String transportDate;
    String shippingPoint;
    String totalWeight;
    String volume;
    String invoiceAmount;
    String salesOrg;
    //int image;

    public DataModel(String deliveryID, String transportDate, String shippingPoint, String totalWeight, String volume, String invoiceAmount, String salesOrg){ //int image) {
        this.deliveryID = deliveryID;
        this.transportDate = transportDate;
        this.shippingPoint = shippingPoint;
        this.totalWeight = totalWeight;
        this.volume = volume;
        this.invoiceAmount = invoiceAmount;
        this.salesOrg = salesOrg;
    }

    public String getDeliveryID() {
        return deliveryID;
    }

    public String getShippingPoint() {
        return shippingPoint;
    }
    public String getTotalWeight() {
        return totalWeight;
    }
    public String getVolume() {
        return volume;
    }
    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public String getSalesOrg() {
        return salesOrg;
    }
    public String getTransportDate() {
        return transportDate;
    }
}