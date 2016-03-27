package com.hulhack.quandrum.wireframes.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SupplyChainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SupplyChainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {

    public static final String MY_PREFS_NAME = "Login Credentials";

    TextView tvName;
    TextView tvPhone;
    TextView tvCity;
    TextView tvRegion;
    TextView tvCID;

    private OnFragmentInteractionListener mListener;

    public DetailsFragment() {
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
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_details, container, false);

        tvName = (TextView)root.findViewById(R.id.name);
        tvPhone = (TextView)root.findViewById(R.id.tvNumber1);
        tvCity = (TextView)root.findViewById(R.id.tvNumber3);
        tvRegion = (TextView)root.findViewById(R.id.tvNumber5);
        tvCID = (TextView)root.findViewById(R.id.tvNumber6);

        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();

        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        String id = prefs.getString("id", "default id");
        String token = prefs.getString("token", "default token");


        String URL="https://77ec4210.ngrok.io/details?token="+token+"&id="+id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("details response", response);
                        pDialog.hide();
                        try {
                            JSONObject json = new JSONObject(response);
                            String name = json.getString("customer_name");
                            String phone = json.getString("number");
                            String city = json.getString("City");
                            String region = json.getString("region");
                            String cid = json.getString("Customer_ID");
                            tvName.setText(name);
                            tvPhone.setText(phone);
                            tvCity.setText(city);
                            tvRegion.setText(region);
                            tvCID.setText(cid);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Error occurred. Try again", Toast.LENGTH_SHORT).show();
                        Log.e("details error response", error.toString());
                        pDialog.hide();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);
        return root;
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

}
