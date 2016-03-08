package com.cs260.zhuosi.zsrepresent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.net.Uri;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhuosi on 2/26/16.
 */
public class CongressionalActivity extends Activity {
    private Button titleButton = null;
    private List<Tile> tiles = new ArrayList<Tile>();
    private  DataContainer dc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dc = DataContainer.getInstance();
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_congressional);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);

        titleButton = (Button)this.findViewById(R.id.titleButton);
        titleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CongressionalActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        populateTileList();
        populateViewList();

    }

    private void populateViewList() {
        ArrayAdapter<Tile> adapter = new TileAdapter();
        ListView list = (ListView) findViewById(R.id.congre_listView);
        list.setAdapter(adapter);
    }

    private void populateTileList() {
        for(int i = 0; i < dc.getRepresentCount(); i++){
            tiles.add(dc.getTileByIndex(i, getApplicationContext()));
        }
    }

    private class TileAdapter extends ArrayAdapter<Tile> {
        public TileAdapter() {
            super(CongressionalActivity.this, R.layout.tile_view, tiles);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.tile_view, parent, false);
            }

            LinearLayout tileLayout = (LinearLayout) itemView.findViewById(R.id.tileLayout);
            tileLayout.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CongressionalActivity.this, DetailedActivity.class);
                    intent.putExtra(dc.detailId, Integer.toString(position));
                    startActivity(intent);
                }
            });


            final Tile currentTile = tiles.get(position);
            Context context = getContext();

            ImageView repImage = (ImageView) itemView.findViewById(R.id.repImage);
            repImage.setImageResource(currentTile.getImg());

            TextView nameText = (TextView) itemView.findViewById(R.id.nameText);
            nameText.setText(currentTile.getName());

            ImageView partyImage = (ImageView) itemView.findViewById(R.id.partyImage);
            partyImage.setImageResource(currentTile.getParty());

            TextView twitterDate = (TextView) itemView.findViewById(R.id.twitterDate);
            twitterDate.setText(currentTile.getLastTwiteTime().toString());

            TextView twitterContent = (TextView) itemView.findViewById(R.id.twitterContent);
            twitterContent.setText(currentTile.getLastTwiteContent());

            Button emailButton = (Button) itemView.findViewById(R.id.emailButton);
            emailButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(currentTile.getEmail()));
                    startActivity(intent);
                }
            });

            Button webButton = (Button) itemView.findViewById(R.id.webButton);
            webButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(currentTile.getWebsite()));
                    startActivity(intent);
                }
            });

            return itemView;
        }
    }


}
