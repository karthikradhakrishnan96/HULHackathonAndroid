package com.hulhack.quandrum.wireframes.data;

/**
 * Created by giris on 27-03-2016.
 */
public class CreditModel {

    public String trasactiontype, documentno, amount, date, shortdesc;

    public CreditModel(String t, String doc, String amount, String date, String sd) {
        this.trasactiontype = t;
        this.documentno = doc;
        this.amount = amount;
        this.date = date;
        this.shortdesc = sd;
    }

    public String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1).toLowerCase();
    }

}