package io.github.shivakanthsujit.fifafixtures;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.*;

public class Fixture {
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

    public String retTN(char T)
    {
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
        String time = localDateFormat.format(dt);
        SimpleDateFormat L = new SimpleDateFormat("yyyy-MM-dd");
        String date = L.format(dt);
        if(T=='A')
            return teamA;
        else if (T=='B')
            return teamB;
        else if (T=='D')
            return date;
        else if (T=='T')
            return time;

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


}
