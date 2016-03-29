package com.hulhack.quandrum.wireframes.adapters;

/**
 * Created by Aman on 3/14/2016.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devspark.robototextview.widget.RobotoTextView;
import com.hulhack.quandrum.wireframes.R;
import com.hulhack.quandrum.wireframes.data.SalesModel;

import com.hulhack.quandrum.wireframes.fragments.sales.MembershipFragment;
import com.hulhack.quandrum.wireframes.libraries.LabelTextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SalesAdapter extends RecyclerView.Adapter<SalesAdapter.MyViewHolder> {

    private ArrayList<SalesModel> dataSet;
    public SalesAdapter(ArrayList<SalesModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sales_card, parent, false);

        view.setOnClickListener(MembershipFragment.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {


        RobotoTextView Region = holder.Region;
        RobotoTextView RS_Name = holder.RS_Name;
        RobotoTextView Area = holder.Area;
        RobotoTextView RS_Sales_forecast = holder.RS_Sales_forecast;
        RobotoTextView RS_Sales = holder.RS_Sales;
        LabelTextView CUSTOMER_ID = holder.CUSTOMER_ID;

        DecimalFormat formatter = new DecimalFormat("#,###.00");

        CUSTOMER_ID.setText("MOC: " + dataSet.get(listPosition).CUSTOMER_ID);
        CUSTOMER_ID.setLabelEnable(false);
        RS_Sales.setText("RS Sales: Rs. "+formatter.format(Double.parseDouble(dataSet.get(listPosition).RS_Sales)));
        String secondaryText = "Region: "+dataSet.get(listPosition).Region+"\n"+
                                "RS Name: "+dataSet.get(listPosition).RS_Name+"\n"+
                                "Area: "+dataSet.get(listPosition).Area+"\n"+
                                "RS Sales Forecast: Rs. "+formatter.format(Double.parseDouble(dataSet.get(listPosition).RS_Sales_forecast));

        Area.setText(secondaryText);
/*
        Region.setText(dataSet.get(listPosition).Region);
        RS_Name.setText(dataSet.get(listPosition).RS_Name);
        Area.setText(dataSet.get(listPosition).Area);
        RS_Sales_forecast.setText(dataSet.get(listPosition).RS_Sales_forecast);
        RS_Sales.setText(dataSet.get(listPosition).RS_Sales);
        CUSTOMER_ID.setText(dataSet.get(listPosition).CUSTOMER_ID);
*/
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public RobotoTextView Region;
        public RobotoTextView RS_Name;
        public RobotoTextView Area;
        public RobotoTextView RS_Sales_forecast;
        public RobotoTextView RS_Sales;
        public LabelTextView CUSTOMER_ID;

        public MyViewHolder(View itemView) {
            super(itemView);
            //this.Region= (RobotoTextView) itemView.findViewById(R.id.Region);
            //this.RS_Name= (RobotoTextView) itemView.findViewById(R.id.RS_Name);
            this.Area= (RobotoTextView) itemView.findViewById(R.id.Area);
            //this.RS_Sales_forecast= (RobotoTextView) itemView.findViewById(R.id.RS_Sales_forecast);
            this.RS_Sales= (RobotoTextView) itemView.findViewById(R.id.RS_Sales);
            this.CUSTOMER_ID= (LabelTextView) itemView.findViewById(R.id.CUSTOMER_ID);
        }
    }
}
