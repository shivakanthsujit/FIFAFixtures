package io.github.shivakanthsujit.fifafixtures;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter {


    private final Activity context;

    private final Team[] teams;
    private final ArrayList<Fixture> fixes;

    public CustomListAdapter(Activity context, ArrayList<Fixture> fixParam,Team[] teamParam){

        super(context,R.layout.listview_row , fixParam);
        this.context=context;
        this.fixes = fixParam;
        this.teams = teamParam;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_row, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView teamAN = rowView.findViewById(R.id.teamA);
        TextView teamBN = rowView.findViewById(R.id.teamB);
        TextView date = rowView.findViewById(R.id.date);
        TextView time = rowView.findViewById(R.id.time);
        TextView venue = rowView.findViewById(R.id.venue);
        ImageView imgA = rowView.findViewById(R.id.imgA);
        ImageView imgB = rowView.findViewById(R.id.imgB);

        //this code sets the values of the objects to values from the arrays
        teamAN.setText(fixes.get(position).retTN('A'));
        teamBN.setText(fixes.get(position).retTN('B'));
        date.setText(fixes.get(position).retTN('D'));
        time.setText(fixes.get(position).retTN('T'));
        venue.setText(fixes.get(position).retTN('V'));

        Team A = fixes.get(position).getTeam(fixes.get(position).retTN('A'),teams);
        Team B = fixes.get(position).getTeam(fixes.get(position).retTN('B'),teams);
        imgA.setImageURI(A.toUri(A.imgid));
        imgB.setImageURI(B.toUri(B.imgid));

        return rowView;

    }

}
