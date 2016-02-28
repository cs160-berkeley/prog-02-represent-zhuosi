package com.cs260.zhuosi.votewatch;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Zhuosi on 2/27/16.
 */
public class RepresentativeActivity extends Activity {

    TextView maneText = null;
    ImageView repImage = null, partyImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_representative);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                maneText = (TextView) stub.findViewById(R.id.nameText);
                repImage = (ImageView) stub.findViewById(R.id.repImage);
                partyImage = (ImageView) stub.findViewById(R.id.partyImage);
            }
        });
    }


}
