package com.example.rajiv.testdatepicker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.DATE;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

// use Ctrl + Alt + L to reformat code
// never use prefixes other than what they are meant for
public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "MainActivity";
    EditText edt1, edt2;
    int day1, month1, year1, day2, month2, year2;
    private int mYearIni, mMonthIni, mDayIni, sYearIni, sMonthIni, sDayIni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt1 = (EditText) findViewById(R.id.editText);
        edt2 = (EditText) findViewById(R.id.editText2);

        edt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edt1.getWindowToken(), 0);
                showFromCalendar();
            }
        });

        edt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edt2.getWindowToken(), 0);
                showToCalendar();
            }
        });
    }

    private void showFromCalendar() {
        Calendar c1 = Calendar.getInstance();
        DatePickerDialog da = new DatePickerDialog(this, mDateSetListener, sYearIni, sMonthIni, sDayIni);
        c1.add(DATE, 1);
        Date newDate = c1.getTime();
        da.getDatePicker().setMinDate(newDate.getTime());
        da.show();
    }

    private void showToCalendar() {
        Calendar c2 = Calendar.getInstance();
        DatePickerDialog da1 = new DatePickerDialog(this, sDateSetListener, mYearIni, mMonthIni, mDayIni);
        c2.set(YEAR, mYearIni);
        c2.set(MONTH, mMonthIni);
        c2.set(DAY_OF_MONTH, mDayIni);
        c2.add(DATE, 1);
        Date newDate1 = c2.getTime();
        da1.getDatePicker().setMinDate(newDate1.getTime());
        da1.show();
    }

    private DatePickerDialog.OnDateSetListener sDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    sYearIni = year;
                    sMonthIni = monthOfYear;
                    sDayIni = dayOfMonth;

                    colocar_fecha1();
                }
            };

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYearIni = year;
                    mMonthIni = monthOfYear;
                    mDayIni = dayOfMonth;

                    colocar_fecha();
                }
            };

    private void colocar_fecha() {

        day1 = mDayIni;
        month1 = mMonthIni + 1;
        year1 = mYearIni;
        edt1.setText(day1 + "-" + month1 + "-" + year1 + " ");
    }

    private void colocar_fecha1() {
        day2 = sDayIni;
        month2 = sMonthIni + 1;
        year2 = sYearIni;
        edt2.setText(day2 + "-" + month2 + "-" + year2 + " ");
        dateCheck();
    }

    public void dateCheck() {

        if (day1 > day2 || month1 > month2 || year1 > year2) {
            if (day1 + month1 + year1 >= day2 + month2 + year2) {
                Toast.makeText(getApplicationContext(), "From-Date Should not be greater than To-Date", Toast.LENGTH_LONG).show();
                System.out.println("::::::::inside date check");
                edt2.setError("From-Date Should not be greater than To-Date");

            } else {

                edt2.setError(null);
            }
        }
    }

}
