package com.hulhack.quandrum.wireframes.supplychainmodels.adapters;

/**
 * Created by Aman on 3/14/2016.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hulhack.quandrum.wireframes.R;
import com.hulhack.quandrum.wireframes.supplychainmodels.data.DataModel;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataSet;

    public CustomAdapter(ArrayList<DataModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_card3, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.title;
        TextView textViewVersion = holder.description;
        //ImageView imageView = holder.imageViewIcon;

        textViewName.setText("DELIVERY ID: "+dataSet.get(listPosition).getDeliveryID());
        String secondaryText = "Transport Date: "+dataSet.get(listPosition).getTransportDate()+"\n"+
                                "Shipping Point: "+dataSet.get(listPosition).getShippingPoint()+"\n"+
                                "Total Weight: "+dataSet.get(listPosition).getTotalWeight()+"\n"+
                                "Volume: "+dataSet.get(listPosition).getVolume()+"\n"+
                                "Invoice Amount: "+dataSet.get(listPosition).getInvoiceAmount()+"\n"+
                                "Sales Org: "+dataSet.get(listPosition).getSalesOrg();
        textViewVersion.setText(secondaryText);
        //imageView.setImageResource(dataSet.get(listPosition).getImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;
        //ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.description = (TextView) itemView.findViewById(R.id.mydesc);
            //this.imageViewIcon = (ImageView) itemView.findViewById(R.id.thumbnail);
        }
    }
}
