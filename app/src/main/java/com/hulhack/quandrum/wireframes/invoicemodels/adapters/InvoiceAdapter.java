package com.hulhack.quandrum.wireframes.invoicemodels.adapters;

/**
 * Created by Aman on 3/14/2016.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devspark.robototextview.widget.RobotoTextView;
import com.hulhack.quandrum.wireframes.R;
import com.hulhack.quandrum.wireframes.fragments.finance.InvoicesFragment;
import com.hulhack.quandrum.wireframes.invoicemodels.data.InvoiceModel;

import java.util.ArrayList;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.MyViewHolder> {

    private ArrayList<InvoiceModel> dataSet;
    public InvoiceAdapter(ArrayList<InvoiceModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.invoice_card, parent, false);

        view.setOnClickListener(InvoicesFragment.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {


        RobotoTextView Pricing_procedure = holder.Pricing_procedure;
        RobotoTextView Billing_Document = holder.Billing_Document;
        RobotoTextView Credit_account = holder.Credit_account;
        RobotoTextView Warehouse_Number = holder.Warehouse_Number;
        RobotoTextView Sold_toparty = holder.Sold_toparty;
        RobotoTextView Sales_Organization = holder.Sales_Organization;
        RobotoTextView Payment_Method = holder.Payment_Method;
        RobotoTextView Distribution_Channel = holder.Distribution_Channel;
        RobotoTextView Company_Code = holder.Company_Code;
        RobotoTextView VAT_Registration_No = holder.VAT_Registration_No;
        RobotoTextView Billing_Date = holder.Billing_Date;
        //Pricing_procedure.setText(dataSet.get(listPosition).Pricing_procedure);
        Billing_Document.setText("BILLING DOCUMENT: "+dataSet.get(listPosition).Billing_Document);
        //Credit_account.setText(dataSet.get(listPosition).Credit_account);
        //Warehouse_Number.setText(dataSet.get(listPosition).Warehouse_Number);
        //Sold_toparty.setText(dataSet.get(listPosition).Sold_toparty);
        //Sales_Organization.setText(dataSet.get(listPosition).Sales_Organization);
        String secondaryText = "Payment Method: "+dataSet.get(listPosition).Payment_Method+"\n"+
                                "Pricing Procedure: "+dataSet.get(listPosition).Pricing_procedure+"\n"+
                                "Credit Account: "+dataSet.get(listPosition).Credit_account+"\n"+
                                "Warehouse Number: "+dataSet.get(listPosition).Warehouse_Number+"\n"+
                                "Sold To: "+dataSet.get(listPosition).Sold_toparty+"\n"+
                                "Distribution Channel: "+dataSet.get(listPosition).Distribution_Channel+"\n"+
                                "Company Code: "+dataSet.get(listPosition).Company_Code+"\n"+
                                "VAT Reg. No.: "+dataSet.get(listPosition).VAT_Registration_No+"\n"+
                                "Billing Date: "+dataSet.get(listPosition).Billing_Date;
        Payment_Method.setText(secondaryText);
        //Distribution_Channel.setText(dataSet.get(listPosition).Distribution_Channel);
        //Company_Code.setText(dataSet.get(listPosition).Company_Code);
        //VAT_Registration_No.setText(dataSet.get(listPosition).VAT_Registration_No);
        //Billing_Date.setText(dataSet.get(listPosition).Billing_Date);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        RobotoTextView Pricing_procedure;
        RobotoTextView Billing_Document;
        RobotoTextView Credit_account;
        RobotoTextView Warehouse_Number;
        RobotoTextView Sold_toparty;
        RobotoTextView Sales_Organization;
        RobotoTextView Payment_Method;
        RobotoTextView Distribution_Channel;
        RobotoTextView Company_Code;
        RobotoTextView VAT_Registration_No;
        RobotoTextView Billing_Date;

        public MyViewHolder(View itemView) {
            super(itemView);

            //this.Pricing_procedure= (RobotoTextView) itemView.findViewById(R.id.Pricing_procedure);
            this.Billing_Document= (RobotoTextView) itemView.findViewById(R.id.Billing_Document);
            //this.Credit_account= (RobotoTextView) itemView.findViewById(R.id.Credit_account);
            //this.Warehouse_Number= (RobotoTextView) itemView.findViewById(R.id.Warehouse_Number);
            //this.Sold_toparty= (RobotoTextView) itemView.findViewById(R.id.Sold_toparty);
            //this.Sales_Organization= (RobotoTextView) itemView.findViewById(R.id.Sales_Organization);
            this.Payment_Method= (RobotoTextView) itemView.findViewById(R.id.Payment_Method);
            //this.Distribution_Channel= (RobotoTextView) itemView.findViewById(R.id.Distribution_Channel);
            //this.Company_Code= (RobotoTextView) itemView.findViewById(R.id.Company_Code);
            //this.VAT_Registration_No= (RobotoTextView) itemView.findViewById(R.id.VAT_Registration_No);
            //this.Billing_Date= (RobotoTextView) itemView.findViewById(R.id.Billing_Date);
        }
    }
}
