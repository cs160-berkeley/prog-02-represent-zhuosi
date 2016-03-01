package com.cs260.zhuosi.votewatch;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Zhuosi on 2/29/16.
 */
public class RepresentativeAdapter extends BaseAdapter {
    private final Context context;
    private final int[] pictures;
    private final int[] party;
    private final String[] person;

    public RepresentativeAdapter(Context context, int[] pictures, int[] party, String[] person){
        this.context = context;
        this.pictures = pictures;
        this.party = party;
        this.person = person;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View representativeView = inflater.inflate(R.layout.activity_representative, parent, false);
        ImageView pictureView = (ImageView) representativeView.findViewById(R.id.repImage);
        ImageView partyView = (ImageView) representativeView.findViewById(R.id.partyImage);
        TextView nameView = (TextView) representativeView.findViewById(R.id.nameText);

        pictureView.setImageResource(pictures[position]);
        partyView.setImageResource(party[position]);
        nameView.setText(person[position]);

        representativeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(RepresentativeActivity.class, RepresentativeActivity.class);
//                startActivity(intent);
                Toast.makeText(context, person[position], Toast.LENGTH_SHORT).show();
            }
        });

        return representativeView;
    }

    public long getItemId(int position){
        return position;
    }

    public Object getItem(int arg0){
        return null;
    }

    public int getCount(){
        return person.length;
    }
}
