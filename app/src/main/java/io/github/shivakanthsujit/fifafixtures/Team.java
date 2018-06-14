package io.github.shivakanthsujit.fifafixtures;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Team implements Serializable {

    String name;
    ArrayList<Fixture> fixtures;
    int noOfFix;
    int imgid;

    public Team(String n, int id) {
        name = n;
        noOfFix = 0;
        imgid = id;

    }

    public String getName() {
        return name;
    }

    public void newFix(Team B, Date dM, String venue, int fxid) {
        fixtures.add(new Fixture(name, B.getName(), venue, dM, fxid));
        noOfFix++;
    }

}





