package hk.ust.cse.comp107x.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnClickListener {
    Toolbar toolbar;
    String names[];
    String brands[];
    String carReg;
    String phone;
    Button btnChangeDate;
    EditText displayDate;
    EditText displayTime;
    Button btnChangeTime;
    Spinner dropdownServiceCenter;
    Spinner dropdownBrand;
    Button save;
    CarService service;
    int year,month,day,hour,minute;
    static final int DATE_DIALOG_ID = 999;
    static final int TIME_DIALOG_ID = 888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        names=getResources().getStringArray(R.array.friends);
        brands=getResources().getStringArray(R.array.brand);
        System.out.println("after name "+names+"  and name at first pos="+names[0]);
        setCurrentDateOnView();
        dropdownServiceCenter= (Spinner) findViewById(R.id.spinner);
        dropdownBrand= (Spinner) findViewById(R.id.brand);
        save= (Button) findViewById(R.id.next);
        EditText registration= (EditText) findViewById(R.id.regNumber);
        EditText phoneNumber= (EditText) findViewById(R.id.phone);
        carReg=registration.getText().toString();
        phone=phoneNumber.getText().toString();
        service=(CarService)getApplicationContext();
        System.out.println("after friend view");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, names);
        ArrayAdapter<String> adapterBrand = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, brands);
        System.out.println("After setting adapter");

        dropdownServiceCenter.setAdapter(adapter);
        dropdownBrand.setAdapter(adapterBrand);
        System.out.println("before listner");
        dropdownBrand.setOnItemSelectedListener(this);
        dropdownServiceCenter.setOnItemSelectedListener(this);
        addListenerOnButton();

        save.setOnClickListener(this);

        System.out.println("end of onCreate");
    }

    // display current date
    public void setCurrentDateOnView() {

        displayDate = (EditText) findViewById(R.id.date);
        //dp = (DatePicker) findViewById(R.id.datePicker);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        displayDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

        displayTime = (EditText) findViewById(R.id.time);
        //timePicker1 = (TimePicker) findViewById(R.id.timePicker1);

        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        // set current time into textview
        displayTime.setText(
                new StringBuilder().append(pad(hour))
                        .append(":").append(pad(minute)));

    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    public void addListenerOnButton() {

        btnChangeDate = (Button) findViewById(R.id.dateButton);

        btnChangeDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);

            }

        });

        btnChangeTime = (Button) findViewById(R.id.timeButton);

        btnChangeTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(TIME_DIALOG_ID);

            }

        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month,day);
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,
                        timePickerListener, hour, minute,false);

        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            displayDate.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

        }
    };

    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute) {
                    hour = selectedHour;
                    minute = selectedMinute;

                    // set current time into textview
                    displayTime.setText(new StringBuilder().append(pad(hour))
                            .append(":").append(pad(minute)));


                }
            };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_user) {
            Intent mIntent = new Intent(this,ShowUserProfile.class);

            startActivity(mIntent);
            return true;
        }
        if (id == R.id.action_search) {
            Intent mIntent = new Intent(this,Brands.class);

            startActivity(mIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String selected=parent.getSelectedItem().toString();
        //Spinner fromParam= view;
//        View fromId=findViewById(R.id.spinner);
//        System.out.println("view from parameter="+view + " and view from id-"+fromId);
        if(parent.getId()==(R.id.spinner))
            System.out.println("spinner for service center is selected");
        System.out.println("Spinned Id selected= "+selected);
//        Intent mIntent = new Intent(this,populate.class);
//        System.out.println("after Intent creation");
//        mIntent.putExtra("Friend Name", names[position]);
//        System.out.println("after setting value");
//        startActivity(mIntent);
//        System.out.println("end of on click method");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        service.setBrandName(dropdownBrand.getSelectedItem().toString());
        service.setRegNumber(carReg);
        service.setPhone(phone);
        service.setServiceCenter(dropdownServiceCenter.getSelectedItem().toString());
        Intent mIntent = new Intent(this,populate.class);
        mIntent.putExtra("Friend Name", dropdownBrand.getSelectedItem().toString());
        startActivity(mIntent);


    }
}
