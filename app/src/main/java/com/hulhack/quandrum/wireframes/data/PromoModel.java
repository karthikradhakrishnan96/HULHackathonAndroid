package com.hulhack.quandrum.wireframes.data;

/**
 * Created by Aman on 3/14/2016.
 */
public class PromoModel {

    public String product, business, brand, name, id, investment, start, end;

    public PromoModel(String product, String business, String brand, String name, String id, String investment, String start, String end) {
        this.product = product;
        this.business = "BUSINESS: "+capitalizeFirstLetter(business);
        this.brand = "BRAND: "+capitalizeFirstLetter(brand);
        this.name = "PROMO: "+name;
        this.id = "ID: "+id;
        this.investment = "INVESTMENT TYPE: "+investment;
        this.start = "START: "+start;
        this.end = "END: "+end;

    }

    public String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1).toLowerCase();
    }

}