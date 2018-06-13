package io.github.shivakanthsujit.fifafixtures;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Team[] teams = new Team[32];;
    String[] tN ={"Argentina","Australia","Belgium","Brazil","Colombia","Costa Rica","Croatia","Denmark","Egypt","England","France","Germany","Iceland","Iran","Japan","Mexico","Morocco","Nigeria","Panama","Peru","Poland","Portugal","Russia","Saudi Arabia","Senegal","Serbia","South Korea","Spain","Sweden","Switzerland","Tunisia","Uruguay"};
    String[] tNN = new String[32] ;
    String venues = "Alalal";
    ListView listView;
    ArrayList<Fixture> fix = new ArrayList<Fixture>();
    int ids = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUp();
        Fixture[] fixArray = new Fixture[fix.size()];
        fixArray = fix.toArray(fixArray);
        CustomListAdapter whatever = new CustomListAdapter(this,fixArray ,teams);
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(whatever);

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

    public void setUp(){
        Date trial = new Date();
        fix.add(new Fixture(tN[0],tN[1],venues,trial,ids++));
        Fixture[] fixArray = new Fixture[fix.size()];
        fixArray = fix.toArray(fixArray);
        for(int i = 1; i < 27; ++i)
        {
            tNN[i - 1] = String.valueOf(i);
        }
        tNN[26]="aa";
        tNN[27]="bb";
        tNN[28]="cc";
        tNN[29]="dd";
        tNN[30]="ee";
        tNN[31]="ff";

        for(int i = 1; i < 33; ++i)
        {
            int temp = R.drawable.a;//getResources().getIdentifier(tNN[i-1], "drawable", "io.github.shivakanthsujit.fifafixtures");
            teams[i-1] = new Team(tN[i-1],temp);
        }
    }
}

