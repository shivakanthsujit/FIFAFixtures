package io.github.shivakanthsujit.fifafixtures;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter {


    private final Activity context;

    private final Team[] teams;
    private final Fixture[] fixes;

    public CustomListAdapter(Activity context, Fixture[] fixParam,Team[] teamParam){

        super(context,R.layout.listview_row , fixParam);
        this.context=context;
        this.fixes = fixParam;
        this.teams = teamParam;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_row, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView teamAN = (TextView) rowView.findViewById(R.id.teamA);
        TextView teamBN = (TextView) rowView.findViewById(R.id.teamB);
        TextView date = (TextView) rowView.findViewById(R.id.date);
        TextView time = (TextView) rowView.findViewById(R.id.time);
        ImageView imgA = (ImageView) rowView.findViewById(R.id.imgA);
        ImageView imgB = (ImageView) rowView.findViewById(R.id.imgB);

        //this code sets the values of the objects to values from the arrays
        teamAN.setText(fixes[position].retTN('A'));
        teamBN.setText(fixes[position].retTN('B'));
        date.setText(fixes[position].retTN('D'));
        time.setText(fixes[position].retTN('T'));

        Team A = fixes[position].getTeam(fixes[position].retTN('A'),teams);
        Team B = fixes[position].getTeam(fixes[position].retTN('B'),teams);
        imgA.setImageResource(A.imgid);
        imgB.setImageResource(B.imgid);

        return rowView;

    }

}
