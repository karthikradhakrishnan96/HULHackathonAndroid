package com.hulhack.quandrum.wireframes.adapters;

/**
 * Created by Aman on 3/14/2016.
 */

import android.animation.LayoutTransition;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.devspark.robototextview.widget.RobotoTextView;
import com.hulhack.quandrum.wireframes.R;
import com.hulhack.quandrum.wireframes.data.ComplaintModel;
import com.hulhack.quandrum.wireframes.fragments.ComplaintsFragment;
import com.hulhack.quandrum.wireframes.libraries.LabelTextView;

import java.util.ArrayList;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.MyViewHolder> {

    private ArrayList<ComplaintModel> dataSet;
    public ComplaintAdapter(ArrayList<ComplaintModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.complaint_card, parent, false);

        view.setOnClickListener(ComplaintsFragment.myOnClickListener);

        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.toAnimate);
        LayoutTransition lt = new LayoutTransition();
        lt.setAnimator(LayoutTransition.DISAPPEARING, null);
        linearLayout.setLayoutTransition(lt);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        LabelTextView category = holder.category;
        RobotoTextView subject = holder.subject;
        RobotoTextView solution = holder.solution;
        RobotoTextView id = holder.id;
        RobotoTextView priority = holder.priority;
        RobotoTextView date = holder.date;
        RobotoTextView region = holder.region;

        category.setText(dataSet.get(listPosition).category);
        category.setLabelText(dataSet.get(listPosition).status);
        if(dataSet.get(listPosition).status.equals("PENDING"))
            category.setLabelBackgroundColor(Color.parseColor("#CD3A3A"));
        else
            category.setLabelBackgroundColor(Color.parseColor("#6D9B00"));
        subject.setText(dataSet.get(listPosition).subject);
        solution.setText(dataSet.get(listPosition).solution);
        id.setText(dataSet.get(listPosition).id);
        priority.setText(dataSet.get(listPosition).priority);
        if(dataSet.get(listPosition).priority.equals("HIGH"))
            priority.setTextColor(Color.parseColor("#CD3A3A"));
        date.setText(dataSet.get(listPosition).date);
        region.setText(dataSet.get(listPosition).region);

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        LabelTextView category;
        RobotoTextView subject;
        RobotoTextView solution;
        RobotoTextView id;
        RobotoTextView priority;
        RobotoTextView date;
        RobotoTextView region;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.category = (LabelTextView) itemView.findViewById(R.id.com_category);
            this.subject = (RobotoTextView) itemView.findViewById(R.id.com_subject);
            this.solution = (RobotoTextView) itemView.findViewById(R.id.com_solution);
            this.id = (RobotoTextView) itemView.findViewById(R.id.com_id);
            this.priority = (RobotoTextView) itemView.findViewById(R.id.com_priority);
            this.date = (RobotoTextView) itemView.findViewById(R.id.com_date);
            this.region = (RobotoTextView) itemView.findViewById(R.id.com_region);
        }
    }
}
