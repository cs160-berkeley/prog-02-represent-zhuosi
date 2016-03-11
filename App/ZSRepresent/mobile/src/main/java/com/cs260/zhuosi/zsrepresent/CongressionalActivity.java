package com.cs260.zhuosi.zsrepresent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Zhuosi on 2/26/16.
 */
public class CongressionalActivity extends Activity {
    private Button titleButton = null;
    private List<Tile> tiles = new ArrayList<Tile>();
    private  DataContainer dc;
    List<Long> tweetIds;
    private static final String TWITTER_KEY = "dDOBCUjHCTCz2o0OVfEeEa8Ye";
    private static final String TWITTER_SECRET = "tgYCQ6UNJP5Wnn4u601dZryowc8T1NX6Zm2S7PBqQlmr4C7FUt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        System.out.println("within CongressoinalActivity");

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
        System.out.println("within populate Tile list" + dc.getRepresentCount());
        for(int i = 0; i < dc.getRepresentCount(); i++){
            tiles.add(dc.getTileByIndex(i, getApplicationContext()));
            System.out.println("Tile " + i + " down");
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
            tileLayout.setOnClickListener(new View.OnClickListener() {

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

            final ImageView partyImage = (ImageView) itemView.findViewById(R.id.partyImage);
            partyImage.setImageResource(currentTile.getParty());

            final TextView twitterContent = (TextView) itemView.findViewById(R.id.twitterText);

            final TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
            final com.twitter.sdk.android.core.services.StatusesService statusesService = twitterApiClient.getStatusesService();
            statusesService.userTimeline(null,currentTile.getLastTwiteContent() , null, null, null, null, null, null, null, new Callback<List<Tweet>>() {
                @Override
                public void success(Result<List<Tweet>> result) {
                    Tweet t = result.data.get(0);
                    Log.d("last tweet", String.valueOf(t.text));
                    Log.d("imgURL", String.valueOf(t.user.profileImageUrl));
                    twitterContent.setText(t.text);
                }

                public void failure(TwitterException exception) {
                    System.out.println(exception.getLocalizedMessage());
                }
            });

            final Intent intent = new Intent(Intent.ACTION_SEND);
            Button emailButton = (Button) itemView.findViewById(R.id.emailButton);
            emailButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + currentTile.getEmail()));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Hello"); // put in subject
                    intent.putExtra(Intent.EXTRA_TEXT, "Vote for " + currentTile.getName()); // put in text
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
