package com.hulhack.quandrum.wireframes.invoicemodels.data;

/**
 * Created by Aman on 3/14/2016.
 */
public class InvoiceModel {
    public String Pricing_procedure;
    public String Billing_Document;
    public String Credit_account;
    public String Warehouse_Number;
    public String Sold_toparty;
    public String Sales_Organization;
    public String Payment_Method;
    public String Distribution_Channel;
    public String Company_Code;
    public String VAT_Registration_No;
    public String Billing_Date;
    public  InvoiceModel(
            String Pricing_procedure,String Billing_Document,String Credit_account,String Warehouse_Number,String Sold_toparty,String Sales_Organization,String Payment_Method,String Distribution_Channel,String Company_Code,String VAT_Registration_No,String Billing_Date
    ){
        this.Pricing_procedure = Pricing_procedure;
        this.Billing_Document = Billing_Document;
        this.Credit_account = Credit_account;
        this.Warehouse_Number = Warehouse_Number;
        this.Sold_toparty = Sold_toparty;
        this.Sales_Organization = Sales_Organization;
        this.Payment_Method = Payment_Method;
        this.Distribution_Channel = Distribution_Channel;
        this.Company_Code = Company_Code;
        this.VAT_Registration_No = VAT_Registration_No;
        this.Billing_Date = Billing_Date;
    }


}