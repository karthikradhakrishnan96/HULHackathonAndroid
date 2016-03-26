package com.hulhack.quandrum.wireframes.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.hulhack.quandrum.wireframes.R;

import fr.ganfra.materialspinner.MaterialSpinner;


/**
 * Created by Ratan on 7/29/2015.
 */
public class ComplaintsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_complaints,null);
        final Button button = (Button) rootView.findViewById(R.id.click);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String[] ITEMS={"Sd Sales Order","Authorization Issue","Material Group Not Found","Plant Is Not Maintain","Bill Screen Issues","Sd Billing Issue","Sales Area Not Define","Transfer Order Not Happening","Price Not Matching","Pan Number Not Displaying","Tax Issue","Invoice Issue","Customer Group Not Mapped"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, ITEMS);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.alert_dialog, null);
                MaterialSpinner spinner = (MaterialSpinner) dialogView.findViewById(R.id.spinner);
                spinner.setAdapter(adapter);
                dialogBuilder.setView(dialogView);
                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
            }
        });
        return rootView;
    }


}
