package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.CalendarAdapter;
import com.example.myapplication.Adapter.GridSpacingItemDecoration;
import com.example.myapplication.Adapter.ViewDay;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        selectedDate = LocalDate.now();
        setMonthView();
    }

    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.RecyclerCalendar);
        monthYearText = findViewById(R.id.monthYearTV);
        calendarRecyclerView.addItemDecoration(new GridSpacingItemDecoration(12, 7));
    }

    private void setMonthView() {
        monthYearText.setText(MonthFromDate(selectedDate));
        ArrayList<ViewDay> daysInMonth = daysInMonthArray(selectedDate);
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);

        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    private ArrayList<ViewDay> daysInMonthArray(LocalDate date) {
        ArrayList<ViewDay> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate LastMonth = selectedDate.minusMonths(1);
        LocalDate NextMonth = selectedDate.plusMonths(1);
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        LocalDate firstOfNextMonth = NextMonth.withDayOfMonth(1);
        LocalDate endOfMonth = LastMonth.withDayOfMonth(LastMonth.lengthOfMonth());
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d");

        for(int i = 1; i <= 42; i++)
        {
            if(i < dayOfWeek)
            {
                int dayNumber = i - dayOfWeek + 1;
                LocalDate currentDay = endOfMonth.plusDays(dayNumber);
                String dayOfWeekName = currentDay.getDayOfWeek().getDisplayName(TextStyle.SHORT,
                        Locale.getDefault());
                daysInMonthArray.add(new ViewDay(String.valueOf(currentDay.format(format)),
                        MonthFromDate(LastMonth), dayOfWeekName, (float) 0.3));
            }
            else if (i > daysInMonth + dayOfWeek - 1) {

                LocalDate currentDay = firstOfNextMonth.plusDays(i - dayOfWeek
                        - firstOfMonth.lengthOfMonth());
                String otchet1 = "CurrentDay = " + currentDay.format(format);
                Log.d("NextMonth", otchet1);
                Log.d("NextMonth", "i = " + i);
                Log.d("NextMonth", "dayOfWeek = " + dayOfWeek);
                Log.d("NextMonth", String.valueOf(firstOfNextMonth.lengthOfMonth()));
                String dayOfWeekName = currentDay.getDayOfWeek().getDisplayName(TextStyle.SHORT,
                        Locale.getDefault());
                daysInMonthArray.add(new ViewDay(String.valueOf(currentDay.format(format)),
                        MonthFromDate(NextMonth), dayOfWeekName, (float) 0.3));
            }
            else
            {
                int dayNumber = i - dayOfWeek + 1;
                LocalDate currentDay = firstOfMonth.plusDays(dayNumber - 1);
                String dayOfWeekName = currentDay.getDayOfWeek().getDisplayName(TextStyle.SHORT,
                        Locale.getDefault());
                daysInMonthArray.add(new ViewDay(String.valueOf(dayNumber),
                        MonthFromDate(selectedDate), dayOfWeekName, (float) 1.0));
            }
        }
        return  daysInMonthArray;
    }

    private String MonthFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM");
        return date.format(formatter);
    }

    public void previousMonthAction(View view)
    {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view)
    {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText)
    {
        if(!dayText.isEmpty())
        {
            String message = "Selected Date " + dayText + " " + MonthFromDate(selectedDate);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }

}