package io.github.shivakanthsujit.fifafixtures;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.*;

public class Fixture implements Serializable {
    String teamA, teamB,venue;
    Date dt;
    int fxid;

    public Fixture(String A, String B, String v, Date d, int id)
    {
        teamA = A;
        teamB = B;
        venue = v;
        dt = d;
        fxid = id;
    }

    public Fixture(Team A, Team B, String v, Date d, int id)
    {
        teamA = A.getName();
        teamB = B.getName();
        venue = v;
        dt = d;
        fxid = id;

        A.newFix(B,d,v,id);
        B.newFix(A,d,v,id);
    }
    public Fixture(int id)
    {
        fxid = id;
    }
    public String retTN(char T)
    {
        SimpleDateFormat localDateFormat ;
        String time ="" ;
        try{
            localDateFormat = new SimpleDateFormat("HH:mm");
            time = localDateFormat.format(dt);
        } catch (Exception e){
            e.printStackTrace();
        }
        SimpleDateFormat L;
        String date="";
        try {
            L = new SimpleDateFormat("yyyy-MM-dd");
            date= L.format(dt);

        } catch (Exception e) {
            e.printStackTrace();

        }
        String id = String.valueOf(fxid);
        if(T=='A')
            return teamA;
        else if (T=='B')
            return teamB;
        else if (T=='D')
            return date;
        else if (T=='T')
            return time;
        else if (T == 'I')
            return id;
        else if(T == 'V')
            return venue;

        return "";
    }
    public Team getTeam(String s, Team teams[]){
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

    public void updateF(char T,String t)
    {
        if(T == 'A')
            teamA = t;
        else if(T == 'B')
            teamB = t;
        else if(T == 'V')
            venue = t;
        else if (T=='D')
        {
            SimpleDateFormat localDateFormat ;
            String time ="" ;
            try{
                localDateFormat = new SimpleDateFormat("HH:mm");
                time = localDateFormat.format(dt);
            } catch (Exception e){
                e.printStackTrace();
            }
            t+=" " + time;
            try {
                dt=new SimpleDateFormat("yyyy-mm-dd HH:mm").parse(t);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        else if (T=='T')
        {
            SimpleDateFormat L;
            String date="";
            try {
            L = new SimpleDateFormat("yyyy-MM-dd");
            date= L.format(dt);

            } catch (Exception e) {
                e.printStackTrace();

            }

            t=date + " " + t;
            try {
                dt=new SimpleDateFormat("yyyy-mm-dd HH:mm").parse(t);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }



}
