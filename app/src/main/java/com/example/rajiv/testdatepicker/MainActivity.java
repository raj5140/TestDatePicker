package com.example.rajiv.testdatepicker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText edt1,edt2;
    static final int DATE_ID = 0;
    static final int DATE_ID1 = 1;
    int day1,month1,year1,day2,month2,year2;
    private int mYearIni, mMonthIni, mDayIni, sYearIni, sMonthIni, sDayIni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt1=(EditText)findViewById(R.id.editText);
        edt2=(EditText)findViewById(R.id.editText2);

        edt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edt1.getWindowToken(), 0);

                showDialog(DATE_ID);

            }
        });

        edt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edt2.getWindowToken(), 0);

                showDialog(DATE_ID1);

            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {



       Calendar c1= Calendar.getInstance();

       Calendar c2 = Calendar.getInstance();

        switch (id) {
            case DATE_ID:

                DatePickerDialog da = new DatePickerDialog(this, mDateSetListener, mYearIni, mMonthIni, mDayIni);

                c1.add(Calendar.DATE, 1);
                Date newDate = c1.getTime();
                da.getDatePicker().setMinDate(newDate.getTime());
                return da;


            case DATE_ID1:

                DatePickerDialog da1 = new DatePickerDialog(this, sDateSetListener, sYearIni, sMonthIni, sDayIni);

                c2.add(Calendar.DATE, 1);
                Date newDate1 = c2.getTime();
                da1.getDatePicker().setMinDate(newDate1.getTime());
                return da1;

        }
        return null;
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
        month1 = mMonthIni+1;
        year1 = mYearIni;
        edt1.setText(day1 + "-" + month1 + "-" + year1 + " ");
    }

    private void colocar_fecha1() {
        day2 = sDayIni;
        month2 = sMonthIni+1;
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
