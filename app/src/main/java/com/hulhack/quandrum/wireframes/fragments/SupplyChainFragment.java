package com.hulhack.quandrum.wireframes.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hulhack.quandrum.wireframes.R;
import com.hulhack.quandrum.wireframes.supplychainmodels.adapters.CustomAdapter;
import com.hulhack.quandrum.wireframes.supplychainmodels.data.DataModel;
import com.hulhack.quandrum.wireframes.supplychainmodels.data.MyData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SupplyChainFragment extends Fragment {

    public static final String MY_PREFS_NAME = "Login Credentials";

    static View.OnClickListener myOnClickListener;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    private static RecyclerView.Adapter adapter;
    private static ArrayList<Integer> removedItems;
    private RecyclerView.LayoutManager layoutManager;
    private OnFragmentInteractionListener mListener;

    public SupplyChainFragment() {
        // Required empty public constructor
    }

    public static SupplyChainFragment newInstance() {
        SupplyChainFragment fragment = new SupplyChainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_supply_chain, container, false);
        // Inflate the layout for this fragment

        //myOnClickListener = new MyOnClickListener(getActivity());

        recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();

        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        String id = prefs.getString("id", "default id");
        String token = prefs.getString("token", "default token");


        String URL="https://77ec4210.ngrok.io/delivery?token="+token+"&id="+id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("delivery response", response);
                        pDialog.hide();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            String [] deliveryID = new String[jsonArray.length()];
                            String [] transportDate = new String[jsonArray.length()];
                            String [] shippingPoint = new String[jsonArray.length()];
                            String [] totalWeight = new String[jsonArray.length()];
                            String [] volume = new String[jsonArray.length()];
                            String [] invoiceAmount = new String[jsonArray.length()];
                            String [] salesOrg = new String[jsonArray.length()];
                            for(int i=0; i<jsonArray.length(); i++)
                            {
                                JSONObject json = jsonArray.getJSONObject(i);
                                deliveryID[i] = json.getString("DELIVERY_ID");
                                transportDate[i] = json.getString("TRANSPORT_DATE");
                                shippingPoint[i] = json.getString("SHIPPING_POINT");
                                totalWeight[i] = json.getString("TOTAL_WEIGHT");
                                volume[i] = json.getString("VOLUME");
                                invoiceAmount[i] = json.getString("INVOICE_AMOUNT");
                                salesOrg[i] = json.getString("SALES_ORG");

                            }

                            data = new ArrayList<DataModel>();
                            for (int i = 0; i < MyData.nameArray.length; i++) {
                                data.add(new DataModel(
                                        deliveryID[i],
                                        transportDate[i],
                                        shippingPoint[i],
                                        totalWeight[i],
                                        volume[i],
                                        invoiceAmount[i],
                                        salesOrg[i]
                                ));
                            }

                            removedItems = new ArrayList<Integer>();

                            adapter = new CustomAdapter(data);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Error occurred. Try again", Toast.LENGTH_SHORT).show();
                        Log.e("delivery error response", error.toString());
                        pDialog.hide();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);



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

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            removeItem(v);
        }

        private void removeItem(View v) {
            int selectedItemPosition = recyclerView.getChildLayoutPosition(v);
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForLayoutPosition(selectedItemPosition);
            TextView textViewName
                    = (TextView) viewHolder.itemView.findViewById(R.id.title);
            String selectedName = (String) textViewName.getText();
            int selectedItemId = -1;
            for (int i = 0; i < MyData.nameArray.length; i++) {
                if (selectedName.equals(MyData.nameArray[i])) {
                    selectedItemId = MyData.id_[i];
                }
            }
            removedItems.add(selectedItemId);
            data.remove(selectedItemPosition);
            adapter.notifyItemRemoved(selectedItemPosition);
        }
    }

}
