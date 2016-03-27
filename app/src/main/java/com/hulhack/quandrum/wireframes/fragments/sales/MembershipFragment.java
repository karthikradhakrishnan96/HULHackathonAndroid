package com.hulhack.quandrum.wireframes.fragments.sales;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hulhack.quandrum.wireframes.R;
import com.hulhack.quandrum.wireframes.adapters.SalesAdapter;
import com.hulhack.quandrum.wireframes.data.SalesModel;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MembershipFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MembershipFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MembershipFragment extends Fragment {

    public static View.OnClickListener myOnClickListener;
    private static RecyclerView recyclerView;
    private static ArrayList<SalesModel> data;
    private static RecyclerView.Adapter adapter;
    private static ArrayList<Integer> removedItems;
    private RecyclerView.LayoutManager layoutManager;
    private OnFragmentInteractionListener mListener;

    public MembershipFragment() {
        // Required empty public constructor
    }

    public static MembershipFragment newInstance() {
        MembershipFragment fragment = new MembershipFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_memberstatus, container, false);
        // Inflate the layout for this fragment

        myOnClickListener = new MyOnClickListener(getActivity());

        recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(false);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        String token = "Aasd12197";
        String id = "0000101008";
        String URL="https://77ec4210.ngrok.io/sales?token="+token+"&id="+ id;

        final ArrayList<SalesModel> networkData = new ArrayList<SalesModel>();

        Toast.makeText(getActivity(), URL, Toast.LENGTH_LONG).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray netArray = new JSONArray(response);
                            for(int i=0; i<netArray.length();i++){
                                JSONObject obj = netArray.getJSONObject(i);
                                networkData.add(new SalesModel(
                                        obj.getString("Region"),obj.getString("RS_Name"),obj.getString("Area"),obj.getString("RS_Sales_forecast"),obj.getString("RS_Sales"),obj.getString("MOC")));  }

                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getActivity(), "Error occurred. Try again", Toast.LENGTH_SHORT).show();

                    }
                }) {


        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);

        data = networkData;
        adapter = new SalesAdapter(data);
        recyclerView.setAdapter(adapter);

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
            if(hiddenLayout.getVisibility() == View.VISIBLE) {
                hiddenLayout.setVisibility(View.GONE);
            }
            else {
                hiddenLayout.setVisibility(View.VISIBLE);
            }
        }

    }

}
