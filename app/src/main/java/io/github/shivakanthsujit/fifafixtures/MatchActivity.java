package io.github.shivakanthsujit.fifafixtures;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ViewSwitcher;

import java.util.Calendar;

public class MatchActivity extends AppCompatActivity {

    TextView D,T;
    String[] tN ={"Argentina","Australia","Belgium","Brazil","Colombia","Costa Rica","Croatia","Denmark","Egypt","England","France","Germany","Iceland","Iran","Japan","Mexico","Morocco","Nigeria","Panama","Peru","Poland","Portugal","Russia","Saudi Arabia","Senegal","Serbia","South Korea","Spain","Sweden","Switzerland","Tunisia","Uruguay"};
    ImageView imgA,imgB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        Fixture fix = (Fixture)getIntent().getSerializableExtra("obj");
        Team A = (Team)getIntent().getSerializableExtra("tea");
        Team B = (Team)getIntent().getSerializableExtra("teb");
        D = findViewById(R.id.date);
        T = findViewById(R.id.time);
        imgA = findViewById(R.id.imgA);
        imgB = findViewById(R.id.imgB);
        D.setText(fix.retTN('D'));
        T.setText(fix.retTN('T'));
        imgA.setImageResource(A.imgid);
        imgB.setImageResource(B.imgid);


        AutoCompleteTextView etvA = findViewById(R.id.HEVta);
        etvA.setText(A.getName());
        ArrayAdapter<String> adapterA = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, tN);
        etvA.setThreshold(1);
        etvA.setTextColor(Color.BLUE);
        etvA.setAdapter(adapterA);

        AutoCompleteTextView etvB = findViewById(R.id.HEVtb);
        etvB.setText(B.getName());
        ArrayAdapter<String> adapterB = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, tN);
        etvB.setThreshold(1);
        etvB.setTextColor(Color.BLUE);
        etvB.setAdapter(adapterB);

        T.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MatchActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        T.setText( String.format("%02d", selectedHour) + ":" + String.format("%02d", selectedMinute) + ":00");
                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        D.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(MatchActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        D.setText("" + selectedyear + "-" + String.format("%02d", selectedmonth) + "-" + String.format("%02d", selectedday));
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });
    }


}
