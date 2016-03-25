package com.hulhack.quandrum.wireframes.fragments.sales;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hulhack.quandrum.wireframes.R;
import com.marcohc.robotocalendar.RobotoCalendarView;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Ratan on 7/29/2015.
 */
public class CalendarFragment extends Fragment implements RobotoCalendarView.RobotoCalendarListener {

    private RobotoCalendarView robotoCalendarView;
    private int currentMonthIndex;
    private Calendar currentCalendar;

    public String test = "[\n" +
            "    {\n" +
            "        \"token\": \"Aasd12197\",\n" +
            "        \"Area\": \"NORTH GUJARAT SALES AREA\",\n" +
            "        \"CUSTOMER_ID\": \"0000101008\",\n" +
            "        \"MOC\": 4,\n" +
            "        \"number\": \"8050199000\",\n" +
            "        \"Customer_ID\": \"0000101008\",\n" +
            "        \"Region\": \"GUJARAT \",\n" +
            "        \"Date\": \"2016-03-21\",\n" +
            "        \"RS_Name\": \"BHUPENDRA STORES\",\n" +
            "        \"Channel\": \"GT\",\n" +
            "        \"Business\": \"U1\",\n" +
            "        \"Activity_Type\": \"SALES TARGET\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"token\": \"Aasd12197\",\n" +
            "        \"Area\": \"NORTH GUJARAT SALES AREA\",\n" +
            "        \"CUSTOMER_ID\": \"0000101008\",\n" +
            "        \"MOC\": 4,\n" +
            "        \"number\": \"8050199000\",\n" +
            "        \"Customer_ID\": \"0000101008\",\n" +
            "        \"Region\": \"GUJARAT \",\n" +
            "        \"Date\": \"2016-03-21\",\n" +
            "        \"RS_Name\": \"BHUPENDRA STORES\",\n" +
            "        \"Channel\": \"GT\",\n" +
            "        \"Business\": \"U1\",\n" +
            "        \"Activity_Type\": \"SALES TARGET\"\n" +
            "    }\n" +
            "]\n";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_calendar,null);
        // Gets the calendar from the view
        robotoCalendarView = (RobotoCalendarView) root.findViewById(R.id.robotoCalendarPicker);

        // Set listener, in this case, the same activity
        robotoCalendarView.setRobotoCalendarListener(this);

        // Initialize the RobotoCalendarPicker with the current index and date
        currentMonthIndex = 0;
        currentCalendar = Calendar.getInstance(Locale.getDefault());

        // Mark current day
        robotoCalendarView.markDayAsCurrentDay(currentCalendar.getTime());

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
        robotoCalendarView.markDayAsSelectedDay(date);

        // Mark that day with random colors
        final Random random = new Random(System.currentTimeMillis());
        final int style = random.nextInt(3);
        switch (style) {
            case 0:
                robotoCalendarView.markFirstUnderlineWithStyle(RobotoCalendarView.BLUE_COLOR, date);
                break;
            case 1:
                robotoCalendarView.markSecondUnderlineWithStyle(RobotoCalendarView.GREEN_COLOR, date);
                break;
            case 2:
                robotoCalendarView.markFirstUnderlineWithStyle(RobotoCalendarView.RED_COLOR, date);
                break;
            default:
                break;
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
