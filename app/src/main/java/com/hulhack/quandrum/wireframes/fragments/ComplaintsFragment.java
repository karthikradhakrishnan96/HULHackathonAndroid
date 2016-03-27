package com.hulhack.quandrum.wireframes.fragments;

import android.animation.LayoutTransition;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dd.CircularProgressButton;
import com.hulhack.quandrum.wireframes.R;
import com.hulhack.quandrum.wireframes.adapters.ComplaintAdapter;
import com.hulhack.quandrum.wireframes.data.ComplaintModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fr.ganfra.materialspinner.MaterialSpinner;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ComplaintsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ComplaintsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComplaintsFragment extends Fragment {

    public static View.OnClickListener myOnClickListener;
    private static RecyclerView recyclerView;
    private static ArrayList<ComplaintModel> data;
    private static RecyclerView.Adapter adapter;
    private static ArrayList<Integer> removedItems;
    private RecyclerView.LayoutManager layoutManager;
    private OnFragmentInteractionListener mListener;
    private View dialogView;
    private RadioGroup mRadioGroup;
    private MaterialSpinner spinner;
    private CircularProgressButton circularProgressButton;

    public ComplaintsFragment() {
        // Required empty public constructor
    }

    public static ComplaintsFragment newInstance() {
        ComplaintsFragment fragment = new ComplaintsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_complaints, container, false);
        // Inflate the layout for this fragment


        myOnClickListener = new MyOnClickListener(getActivity());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(false);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        String token = "Aasd12197";
        String id = "0000101008";
        String URL = "https://77ec4210.ngrok.io/complaint?token=" + token + "&id=" + id;

        final ArrayList<ComplaintModel> networkData = new ArrayList<ComplaintModel>();

        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {

                    @Override

                    public void onResponse(String response) {
                        try {
                            pDialog.hide();
                            JSONArray netArray = new JSONArray(response);
                            for (int i = 0; i < netArray.length(); i++) {
                                JSONObject obj = netArray.getJSONObject(i);
                                networkData.add(new ComplaintModel(
                                        obj.getString("Status"),
                                        obj.getString("Category"),
                                        obj.getString("Subject"),
                                        obj.getString("Solution_Details"),
                                        obj.getString("Incident_ID"),
                                        obj.getString("Incident_type"),
                                        obj.getString("Created_On"),
                                        obj.getString("Region")));
                            }

                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.hide();
                        Toast.makeText(getActivity(), "Error occurred. Try again", Toast.LENGTH_SHORT).show();

                    }
                }) {


        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);

        data = networkData;
        adapter = new ComplaintAdapter(data);
        recyclerView.setAdapter(adapter);
        final FloatingActionButton button = (FloatingActionButton) rootView.findViewById(R.id.fab);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String[] ITEMS = {"Sd Sales Order", "Authorization Issue", "Material Group Not Found", "Plant Is Not Maintain", "Bill Screen Issues", "Sd Billing Issue", "Sales Area Not Define", "Transfer Order Not Happening", "Price Not Matching", "Pan Number Not Displaying", "Tax Issue", "Invoice Issue", "Customer Group Not Mapped"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, ITEMS);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                dialogView = inflater.inflate(R.layout.alert_dialog, null);
                spinner = (MaterialSpinner) dialogView.findViewById(R.id.spinner);
                spinner.setAdapter(adapter);
                mRadioGroup = (RadioGroup) dialogView.findViewById(R.id.radioGroup1);
                dialogBuilder.setView(dialogView);
                final AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
                circularProgressButton = (CircularProgressButton) dialogView.findViewById(R.id.submit);
                circularProgressButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String subject = spinner.getSelectedItem().toString();
                        int selectedId = mRadioGroup.getCheckedRadioButtonId();

                        // find the radiobutton by returned id
                        RadioButton mRadioButton = (RadioButton) dialogView.findViewById(selectedId);
                        final String criticality = mRadioButton.getText().toString();


                        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://77ec4210.ngrok.io/complaint",
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Toast.makeText(getActivity(), "Your complaint has been successfully submitted", Toast.LENGTH_SHORT).show();
                                        circularProgressButton.setProgress(100);
                                        alertDialog.dismiss();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        circularProgressButton.setProgress(-1);
                                        Toast.makeText(getActivity(), "Error occurred. Try again", Toast.LENGTH_SHORT).show();
                                        alertDialog.dismiss();
                                    }
                                }) {
                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<>();
                                params.put("token", "Aasd12197");
                                params.put("id", "0000101008");
                                params.put("subject", subject);
                                params.put("level", criticality);
                                return params;
                            }

                        };

                        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                        requestQueue.add(stringRequest);


                    }
                });
            }
        });
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other com.hulhack.quandrum.wireframes.fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        public MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            int selectedItemPosition = recyclerView.getChildLayoutPosition(v);
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForLayoutPosition(selectedItemPosition);
            LinearLayout hiddenLayout
                    = (LinearLayout) viewHolder.itemView.findViewById(R.id.frame_expand);
            if (hiddenLayout.getVisibility() == View.VISIBLE) {
                hiddenLayout.setVisibility(View.GONE);
            } else {
                hiddenLayout.setVisibility(View.VISIBLE);
            }
        }

    }

}
