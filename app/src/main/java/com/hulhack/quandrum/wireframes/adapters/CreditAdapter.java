package com.hulhack.quandrum.wireframes.adapters;

/**
 * Created by giris on 27-03-2016.
 */
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devspark.robototextview.widget.RobotoTextView;
import com.hulhack.quandrum.wireframes.R;
import com.hulhack.quandrum.wireframes.data.CreditModel;
import com.hulhack.quandrum.wireframes.data.PromoModel;
import com.hulhack.quandrum.wireframes.fragments.finance.CreditFragment;
import com.hulhack.quandrum.wireframes.fragments.sales.PromosFragment;
import com.hulhack.quandrum.wireframes.libraries.LabelTextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CreditAdapter extends RecyclerView.Adapter<CreditAdapter.MyViewHolder> {

    private ArrayList<CreditModel> dataSet;
    public CreditAdapter(ArrayList<CreditModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.credit_card, parent, false);

        view.setOnClickListener(CreditFragment.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        LabelTextView amount = holder.amount;
        RobotoTextView transactiontype = holder.transactiontype;
        RobotoTextView documentno = holder.documentno;
        RobotoTextView date = holder.date;
        RobotoTextView shortdesc = holder.shortdesc;

        DecimalFormat formatter = new DecimalFormat("#,###.00");

        amount.setText("AMOUNT: Rs. "+formatter.format(Double.parseDouble(dataSet.get(listPosition).amount)));
        if(dataSet.get(listPosition).shortdesc.equals("CREDIT")) {
            amount.setLabelText("CREDIT");
            amount.setLabelBackgroundColor(Color.GREEN);
        }
        else {
            amount.setLabelText("DEBIT");
            amount.setLabelBackgroundColor(Color.RED);
        }
        transactiontype.setText("Date: "+dataSet.get(listPosition).date);
        String secondaryText = "Transaction Type: "+dataSet.get(listPosition).trasactiontype+"\n"+
                                "Document No.: "+dataSet.get(listPosition).documentno;
        documentno.setText(secondaryText);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        LabelTextView amount;
        RobotoTextView transactiontype;
        RobotoTextView documentno;
        RobotoTextView date;
        RobotoTextView shortdesc;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.amount = (LabelTextView) itemView.findViewById(R.id.amount);
            this.transactiontype = (RobotoTextView) itemView.findViewById(R.id.date);
            this.documentno = (RobotoTextView) itemView.findViewById(R.id.secondary);
        }
    }
}
