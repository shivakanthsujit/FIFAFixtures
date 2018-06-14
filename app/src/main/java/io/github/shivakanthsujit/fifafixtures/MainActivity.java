package io.github.shivakanthsujit.fifafixtures;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Team[] teams = new Team[32];
    String[] tN ={"Argentina","Australia","Belgium","Brazil","Colombia","Costa Rica","Croatia","Denmark","Egypt","England","France","Germany","Iceland","Iran","Japan","Mexico","Morocco","Nigeria","Panama","Peru","Poland","Portugal","Russia","Saudi Arabia","Senegal","Serbia","South Korea","Spain","Sweden","Switzerland","Tunisia","Uruguay"};
    String[] tNN = new String[32] ;
    String venues = "Alalal";
    ListView listView;
    ArrayList<Fixture> fix = new ArrayList<Fixture>();
    int ids = 1000;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Date trial = new Date();
        fix.add(new Fixture(tN[0],tN[1],venues,trial,ids++));
        btn = findViewById(R.id.add);

        for(int i = 0; i < 26; ++i)
        {
            int temp = i+65;
            tNN[i] = Character.toString ((char)temp);
        }
        tNN[26]="aa";
        tNN[27]="bb";
        tNN[28]="cc";
        tNN[29]="dd";
        tNN[30]="ee";
        tNN[31]="ff";

        for(int i = 1; i < 33; ++i)
        {
            int drawableResourceId = this.getResources().getIdentifier(tNN[i-1], "drawable", this.getPackageName());

            teams[i-1] = new Team(tN[i-1],drawableResourceId);
        }

        CustomListAdapter whatever = new CustomListAdapter(this,fix,teams);
        listView = findViewById(R.id.list);
        listView.setAdapter(whatever);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long id)
            {
                Intent intent = new Intent(MainActivity.this, MatchActivity.class);
                Fixture clickFix = fix.get(position);
                Team A = fix.get(position).getTeam(fix.get(position).retTN('A'),teams);
                Team B = fix.get(position).getTeam(fix.get(position).retTN('B'),teams);
                intent.putExtra("obj", clickFix);
                intent.putExtra("tea", A);
                intent.putExtra("teb", B);
                startActivity(intent);
            }
        });

    }

    public void updateFix(int id, Team old, Team change)
    {

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


}

