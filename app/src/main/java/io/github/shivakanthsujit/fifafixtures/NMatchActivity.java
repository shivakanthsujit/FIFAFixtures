package io.github.shivakanthsujit.fifafixtures;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class NMatchActivity extends AppCompatActivity {

    TextView D, T, V;
    EditText Ve;
    String[] tN = {"Argentina", "Australia", "Belgium", "Brazil", "Colombia", "Costa Rica", "Croatia", "Denmark", "Egypt", "England", "France", "Germany", "Iceland", "Iran", "Japan", "Mexico", "Morocco", "Nigeria", "Panama", "Peru", "Poland", "Portugal", "Russia", "Saudi Arabia", "Senegal", "Serbia", "South Korea", "Spain", "Sweden", "Switzerland", "Tunisia", "Uruguay"};
    ImageView imgA, imgB;
    Fixture fix;
    Team[] teams = new Team[32];
    Button ref;
    AutoCompleteTextView etvA,etvB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nmatch);
        fix = (Fixture) getIntent().getSerializableExtra("obj");
        teams = (Team[]) getIntent().getSerializableExtra("tArray");
        D = findViewById(R.id.date);
        T = findViewById(R.id.time);
        V = findViewById(R.id.venue);
        Ve = findViewById(R.id.editV);
        imgA = findViewById(R.id.imgA);
        imgB = findViewById(R.id.imgB);
        ref = findViewById(R.id.button);

        etvA = findViewById(R.id.HEVta);

        ArrayAdapter<String> adapterA = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, tN);
        etvA.setThreshold(1);
        etvA.setTextColor(Color.BLUE);
        etvA.setAdapter(adapterA);

        etvB = findViewById(R.id.HEVtb);

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
                mTimePicker = new TimePickerDialog(NMatchActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        T.setText(String.format("%02d", selectedHour) + ":" + String.format("%02d", selectedMinute));
                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        imgA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 3);
            }
        });

        imgB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 4);
            }
        });

        D.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(NMatchActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        selectedmonth = selectedmonth + 1;
                        D.setText("" + selectedyear + "-" + String.format("%02d", selectedmonth) + "-" + String.format("%02d", selectedday));
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });

        V.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                V.setVisibility(View.INVISIBLE);
                Ve.setVisibility(View.VISIBLE);
                Ve.setText(V.getText());

            }
        });

        Ve.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String s = Ve.getText().toString();
                    V.setText(s);
                    Ve.setVisibility(View.INVISIBLE);
                    V.setVisibility(View.VISIBLE);
                    fix.updateF('V',s);
                    String data = fix.retTN('V');
                    Toast.makeText(NMatchActivity.this, data,Toast.LENGTH_LONG).show();
                }
            }
        });

        ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TA = etvA.getText().toString();
                String TB = etvB.getText().toString();
                int cA = checkN(TA);
                int cB = checkN(TB);
                if(cA != -1)
                {
                    Team Aa = teams[cA];
                    imgA.setImageURI(Aa.toUri(Aa.imgid));
                    fix.updateF('A',TA);
                }
                if(cB != -1)
                {
                    Team Bb = teams[cB];
                    imgB.setImageURI(Bb.toUri(Bb.imgid));
                    fix.updateF('B',TB);
                }
                if(cA == -1 || cB == -1)
                    Toast.makeText(NMatchActivity.this, "Invalid Team Name. Please enter again",Toast.LENGTH_LONG).show();



            }
        });
    }
    public void onBackPressed() {
        Intent intent = new Intent();
        String d = D.getText().toString();
        String t = T.getText().toString();
        String v = Ve.getText().toString();
        fix.updateF('T',t);
        fix.updateF('D',d);
        fix.updateF('V',v);
        intent.putExtra("obj", fix);
        intent.putExtra("tArray", teams);
        setResult(1, intent);
        String data = fix.retTN('A') + " " + fix.retTN('B') + " " + fix.retTN('D') + " " + fix.retTN('T') + " " + fix.retTN('V');
        Toast.makeText(NMatchActivity.this, data,Toast.LENGTH_LONG).show();
        finish();
    }
    public int checkN(String a){
        String[] tN ={"Argentina","Australia","Belgium","Brazil","Colombia","Costa Rica","Croatia","Denmark","Egypt","England","France","Germany","Iceland","Iran","Japan","Mexico","Morocco","Nigeria","Panama","Peru","Poland","Portugal","Russia","Saudi Arabia","Senegal","Serbia","South Korea","Spain","Sweden","Switzerland","Tunisia","Uruguay"};
        int ch = -1,i;
        for(i = 0; i < 32; ++i)
        {
            if(a.equals(tN[i]))
            {
                ch++;
                break;
            }
        }
        if(ch == -1)
            return ch;
        else
            return i;
    }
    public Team getTeam(String s){
        int l = 0, u = 31;
        int mid = (l + u)/2;
        while(l != u)
        {
            mid = (l + u)/2;
            if(teams[mid].getName().equals(s))
            {
                break;
            }
            else if (teams[mid].getName().compareTo(s) < 0)
            {
                l = mid + 1;
            }
            else if (teams[mid].getName().compareTo(s) > 0)
            {
                u = mid - 1;
            }
        }
        return teams[mid];
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if(requestCode == 3)
        {
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    imgA.setImageURI(selectedImage);
                    String TA = etvA.getText().toString();
                    String TB = etvB.getText().toString();
                    int cA = checkN(TA);
                    int cB = checkN(TB);
                    if(cA != -1)
                        teams[cA].imgid=teams[cA].toString(selectedImage);
                    if(cB != -1)
                        teams[cB].imgid=teams[cB].toString(selectedImage);
                    if( cA == -1 || cB == -1)
                        Toast.makeText(NMatchActivity.this, "Invalid Team Name. Please enter again",Toast.LENGTH_LONG).show();


                }
        }
        else if(requestCode == 4)
        {
            if(resultCode == RESULT_OK){
                Uri selectedImage = imageReturnedIntent.getData();
                imgA.setImageURI(selectedImage);
                String TA = etvA.getText().toString();
                String TB = etvB.getText().toString();
                int cA = checkN(TA);
                int cB = checkN(TB);
                if(cA != -1)
                    teams[cA].imgid=teams[cA].toString(selectedImage);
                if(cB != -1)
                    teams[cB].imgid=teams[cB].toString(selectedImage);
                if( cA == -1 || cB == -1)
                    Toast.makeText(NMatchActivity.this, "Invalid Team Name. Please enter again",Toast.LENGTH_LONG).show();
            }
        }
    }
}
