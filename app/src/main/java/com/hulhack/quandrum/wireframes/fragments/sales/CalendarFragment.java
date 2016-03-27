package com.hulhack.quandrum.wireframes.fragments.sales;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hulhack.quandrum.wireframes.R;
import com.hulhack.quandrum.wireframes.data.Event;
import com.hulhack.quandrum.wireframes.data.PromoModel;
import com.marcohc.robotocalendar.RobotoCalendarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Ratan on 7/29/2015.
 */
public class CalendarFragment extends Fragment implements RobotoCalendarView.RobotoCalendarListener {

    private RobotoCalendarView robotoCalendarView;
    private int currentMonthIndex;
    private Calendar currentCalendar;
    ArrayList<Event> events;
    Map<String,Integer> months=new HashMap<>();
    Set<Date> dates=new LinkedHashSet<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_calendar,null);
        robotoCalendarView = (RobotoCalendarView) root.findViewById(R.id.robotoCalendarPicker);
        months.put("Aug",8);
        months.put("Jan",1);
        months.put("Dec",12);
        months.put("Jun",6);
        months.put("Mar",3);
        months.put("Sep",9);
        months.put("Feb",2);
        months.put("Oct",10);
        months.put("Apr",4);
        months.put("Nov",11);
        months.put("May",5);
        months.put("Jul",7);
        // Set listener, in this case, the same activity
        robotoCalendarView.setRobotoCalendarListener(this);

        // Initialize the RobotoCalendarPicker with the current index and date
        currentMonthIndex = 0;
        currentCalendar = Calendar.getInstance(Locale.getDefault());


        String token = "Aasd12197";
        String id = "0000101008";
        String URL="https://77ec4210.ngrok.io/calendar?token="+token+"&id="+ id;
        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
        events=new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            pDialog.hide();
                            JSONArray netArray = new JSONArray(response);
                            for(int i=0; i<netArray.length();i++){
                                JSONObject obj = netArray.getJSONObject(i);
                                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                                String date=obj.getString("Date");
                                String[] attrs=date.split("-");


                                Date inputDate = dateFormat.parse(attrs[2] + "-" + attrs[1] + "-" + attrs[0]);
                                dates.add(inputDate);
                                events.add(new Event(
                                        obj.getString("Activity_Type"),obj.getString("Customer_ID"),obj.getString("Business"),obj.getString("Channel"),obj.getString("CUSTOMER_ID"),obj.getString("Region"),obj.getString("Area"),obj.getString("MOC"),obj.getString("token"),obj.getString("Date"),obj.getString("RS_Name"),obj.getString("number"),inputDate));



                                robotoCalendarView.markDayAsSelectedDay(inputDate);

                            }



                        } catch (Exception e) {
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

        // Gets the calendar from the view


        return root;
    }
/*
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
*/
    @Override
    public void onDateSelected(Date date) {

        // Mark calendar day
        if(dates.contains(date))
        {

        }
    }

    @Override
    public void onRightButtonClick() {
        currentMonthIndex++;
        updateCalendar();
    }

    @Override
    public void onLeftButtonClick() {
        currentMonthIndex--;
        updateCalendar();
    }

    private void updateCalendar() {
        currentCalendar = Calendar.getInstance(Locale.getDefault());
        currentCalendar.add(Calendar.MONTH, currentMonthIndex);
        robotoCalendarView.initializeCalendar(currentCalendar);
    }

}
