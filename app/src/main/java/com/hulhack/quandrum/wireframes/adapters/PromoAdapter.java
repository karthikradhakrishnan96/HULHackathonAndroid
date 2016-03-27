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
import com.hulhack.quandrum.wireframes.data.PromoModel;
import com.hulhack.quandrum.wireframes.fragments.sales.PromosFragment;
import com.hulhack.quandrum.wireframes.libraries.LabelTextView;

import java.util.ArrayList;

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.MyViewHolder> {

    private ArrayList<PromoModel> dataSet;
    public PromoAdapter(ArrayList<PromoModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.promo_card, parent, false);

        view.setOnClickListener(PromosFragment.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        LabelTextView product = holder.product;
        RobotoTextView business = holder.business;
        RobotoTextView brand = holder.brand;
        RobotoTextView name = holder.name;
        RobotoTextView id = holder.id;
        RobotoTextView investment = holder.investment;
        RobotoTextView start = holder.start;
        RobotoTextView end = holder.end;

        product.setText(dataSet.get(listPosition).product);
        product.setLabelText("2 Days");
        business.setText(dataSet.get(listPosition).business);
        brand.setText(dataSet.get(listPosition).brand);
        name.setText(dataSet.get(listPosition).name+"\n\n"+dataSet.get(listPosition).id+"\n\n"+dataSet.get(listPosition).investment+"\n\n"+dataSet.get(listPosition).start+"\n"+dataSet.get(listPosition).end);
        //id.setText(dataSet.get(listPosition).id);
        //investment.setText(dataSet.get(listPosition).investment);
        //start.setText(dataSet.get(listPosition).start);
        //end.setText(dataSet.get(listPosition).end);
        id.setVisibility(View.GONE);
        investment.setVisibility(View.GONE);
        start.setVisibility(View.GONE);
        end.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        LabelTextView product;
        RobotoTextView business;
        RobotoTextView brand;
        RobotoTextView name;
        RobotoTextView id;
        RobotoTextView investment;
        RobotoTextView start;
        RobotoTextView end;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.product = (LabelTextView) itemView.findViewById(R.id.promo_product);
            this.business = (RobotoTextView) itemView.findViewById(R.id.promo_business);
            this.brand = (RobotoTextView) itemView.findViewById(R.id.promo_brand);
            this.name = (RobotoTextView) itemView.findViewById(R.id.promo_name);
            this.id = (RobotoTextView) itemView.findViewById(R.id.promo_id);
            this.investment = (RobotoTextView) itemView.findViewById(R.id.promo_investment);
            this.start = (RobotoTextView) itemView.findViewById(R.id.promo_start);
            this.end = (RobotoTextView) itemView.findViewById(R.id.promo_end);
        }
    }
}
