package io.github.shivakanthsujit.fifafixtures;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Team[] teams = new Team[32];
    String[] tN ={"Argentina","Australia","Belgium","Brazil","Colombia","Costa Rica","Croatia","Denmark","Egypt","England","France","Germany","Iceland","Iran","Japan","Mexico","Morocco","Nigeria","Panama","Peru","Poland","Portugal","Russia","Saudi Arabia","Senegal","Serbia","South Korea","Spain","Sweden","Switzerland","Tunisia","Uruguay"};
    String[] tNN = new String[32] ;
    String venues = "Alalal";
    ListView listView;
    int posclick;
    ArrayList<Fixture> fix = new ArrayList<Fixture>();
    int ids = 1000;
    Button btn;
    int nF=0;
    Date trial = new Date();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fix.add(new Fixture(tN[15],tN[28],venues,trial,ids++));
        nF++;
        btn = findViewById(R.id.add);

        for(int i = 0; i < 26; ++i)
        {
            int temp = i+97;
            tNN[i] = Character.toString ((char)temp);
        }
        tNN[26]="aa";
        tNN[27]="bb";
        tNN[28]="cc";
        tNN[29]="dd";
        tNN[30]="ee";
        tNN[31]="ff";

        for(int i = 0; i < 32; ++i)
        {
            int drawableResourceId = this.getResources().getIdentifier(tNN[i], "drawable", this.getPackageName());

            teams[i] = new Team(tN[i],getUriToDrawable(this,drawableResourceId));
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
                intent.putExtra("tArray",teams);
                posclick = position;
                startActivityForResult(intent,1);
            }
        });

    }
        public void addFix(View v)
        {
            fix.add(new Fixture(ids++));
            nF++;
            Intent intent = new Intent(MainActivity.this, NMatchActivity.class);
            intent.putExtra("obj", fix.get(nF-1));
            intent.putExtra("tArray",teams);
            startActivityForResult(intent,2);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == 1) {
                Serializable tt = data.getSerializableExtra("obj");
                Serializable te = data.getSerializableExtra("tArray");
                fix.set(posclick,(Fixture)tt);
                teams = (Team[])te;
                CustomListAdapter whatever = new CustomListAdapter(this,fix,teams);
                listView = findViewById(R.id.list);
                listView.setAdapter(whatever);
            }
        }
        else if(requestCode == 2){
            if(resultCode == 1) {
                Serializable tt = data.getSerializableExtra("obj");
                Serializable te = data.getSerializableExtra("tArray");
                fix.set(nF-1,(Fixture)tt);
                teams = (Team[])te;
                CustomListAdapter whatever = new CustomListAdapter(this,fix,teams);
                listView = findViewById(R.id.list);
                listView.setAdapter(whatever);
            }
        }
    }
    public static final Uri getUriToDrawable(@NonNull Context context,
                                             @AnyRes int drawableId) {
        Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + context.getResources().getResourcePackageName(drawableId)
                + '/' + context.getResources().getResourceTypeName(drawableId)
                + '/' + context.getResources().getResourceEntryName(drawableId) );
        return imageUri;
    }

}

