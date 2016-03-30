package com.hulhack.quandrum.wireframes.fragments.sales;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class CalendarFragment extends Fragment  {
    public static final String MY_PREFS_NAME = "Login Credentials";

    private MaterialCalendarView calendarView;
    private ArrayList<Date> markedDates;

    ArrayList<Event> events;
    Map<String,Integer> months=new HashMap<>();
    Set<Date> dates=new LinkedHashSet<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_calendar,null);
        calendarView = (MaterialCalendarView) root.findViewById(R.id.calendar_view);
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
        months.put("Jul", 7);
        // Set listener, in this case, the same activity


        // Initialize the RobotoCalendarPicker with the current index and date



        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        String id = prefs.getString("id", "default id");
        String token = prefs.getString("token", "default token");
        String URL="https://77ec4210.ngrok.io/calendar?token="+token+"&id="+ id;
        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                       pDialog.hide();
                       populateFromResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.hide();
                        Toast.makeText(getActivity(), "Error updating.", Toast.LENGTH_SHORT).show();

                    }
                }) {


        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        if(requestQueue.getCache().get(URL)!=null)
        {
            pDialog.hide();
            String response=new String(requestQueue.getCache().get(URL).data);
            populateFromResponse(response);
        }
        requestQueue.add(stringRequest);



        // Gets the calendar from the view


        return root;
    }

    private void populateFromResponse(String response) {
        try {

            events=new ArrayList<>();
            JSONArray netArray = new JSONArray(response);
            List<CalendarDay> list = new ArrayList<CalendarDay>();
            Calendar calendar = Calendar.getInstance();
            for(int i=0; i<netArray.length();i++){
                JSONObject obj = netArray.getJSONObject(i);
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String date=obj.getString("Date");
                String[] attrs=date.split("-");


                Date inputDate = dateFormat.parse(attrs[2] + "-" + attrs[1] + "-" + attrs[0]);
                dates.add(inputDate);
                events.add(new Event(
                        obj.getString("Activity_Type"), obj.getString("Customer_ID"), obj.getString("Business"), obj.getString("Channel"), obj.getString("CUSTOMER_ID"), obj.getString("Region"), obj.getString("Area"), obj.getString("MOC"), obj.getString("token"), obj.getString("Date"), obj.getString("RS_Name"), obj.getString("number"), inputDate));



                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(inputDate);
                int year=cal1.get(Calendar.YEAR);
                int month=cal1.get(Calendar.MONTH);
                int day=cal1.get(Calendar.DATE);
                calendar.set(year, month, day);
                CalendarDay calendarDay = CalendarDay.from(calendar);
                list.add(calendarDay);
            }

            calendarDays = list;
            calendarView.addDecorators(new EventDecorator(Color.parseColor("#ff0099cc"), calendarDays));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
*/

    public void onDateSelected(Date date1) {

        // Mark calendar day
        for(Date date2:dates) {
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(date1);
            cal2.setTime(date2);
            boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                    cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
            if(sameDay)
            {
                //Toast.makeText(getActivity(),"It's same , my dudes",Toast.LENGTH_LONG).show();
            }
        }
    }


    private class EventDecorator implements DayViewDecorator {

        private final int color;
        private final HashSet<CalendarDay> dates;

        public EventDecorator(int color, Collection<CalendarDay> dates) {
            this.color = color;
            this.dates = new HashSet<>(dates);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new DotSpan(5, color));

        }
    }

    private Collection<CalendarDay> calendarDays = new Collection<CalendarDay>() {
        @Override
        public boolean add(CalendarDay object) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends CalendarDay> collection) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public boolean contains(Object object) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            return false;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @NonNull
        @Override
        public Iterator<CalendarDay> iterator() {
            return null;
        }

        @Override
        public boolean remove(Object object) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> collection) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> collection) {
            return false;
        }

        @Override
        public int size() {
            return 0;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public <T> T[] toArray(T[] array) {
            return null;
        }
    };
}
