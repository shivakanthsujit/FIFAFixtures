package io.github.shivakanthsujit.fifafixtures;

import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Team implements Serializable {

    String name;
    ArrayList<Fixture> fixtures;
    int noOfFix;
    String imgid;

    public Team(String n, Uri id) {
        name = n;
        noOfFix = 0;
        imgid = this.toString(id);

    }

    public String getName() {
        return name;
    }

    public void newFix(Team B, Date dM, String venue, int fxid) {
        fixtures.add(new Fixture(name, B.getName(), venue, dM, fxid));
        noOfFix++;
    }

    public void delFix(int id)
    {
        int idx = 0;


        while (idx < fixtures.size())
        {
            int foo = Integer.parseInt(fixtures.get(idx).retTN('I'));
            if(id == foo)
            {
                fixtures.remove(idx);
                noOfFix--;
            }
            else
            {
                ++idx;
            }
        }
    }
     public void upTFix(int id, Fixture f)
     {

         int idx = 0;


         while (idx < fixtures.size())
         {
             int foo = Integer.parseInt(fixtures.get(idx).retTN('I'));
             if(id == foo)
             {

             }
             else
             {
                 ++idx;
             }
         }
     }
     public Uri toUri(String s)
     {
         Uri uri;
         uri = Uri.parse(s);
         return uri;
     }

     public String toString(Uri u)
     {

         String stringUri;
         stringUri = u.toString();
         return stringUri;
     }

}





