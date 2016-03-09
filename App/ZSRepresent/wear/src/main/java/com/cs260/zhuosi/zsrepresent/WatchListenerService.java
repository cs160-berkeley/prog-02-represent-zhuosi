package com.cs260.zhuosi.zsrepresent;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by joleary and noon on 2/19/16 at very late in the night. (early in the morning?)
 */
public class WatchListenerService extends WearableListenerService {

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in WatchListenerService, got: " + messageEvent.getPath());

        DataContainer dc = DataContainer.getInstance();

        if( messageEvent.getPath().equalsIgnoreCase( dc.NAME_LIST ) ) {
            String name_list = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            dc.nameList = name_list.split(";");
        } else if (messageEvent.getPath().equalsIgnoreCase( dc.PARTY_LIST )) {
            String party_list = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            dc.partyList = party_list.split(";");
        } else if (messageEvent.getPath().equalsIgnoreCase( dc.COUNTY )) {
            String county = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            dc.county = county;
        } else if (messageEvent.getPath().equalsIgnoreCase( dc.VOTEOBAMA )) {
            String result = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            dc.obama = Integer.parseInt(result);
        } else if (messageEvent.getPath().equalsIgnoreCase( dc.VOTEROMNEY )) {
            String result = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            dc.romney = Integer.parseInt(result);
        } else if (messageEvent.getPath().equalsIgnoreCase( dc.START_INTENT)) {
            Intent intent = new Intent(this, RepresentativeActivity.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else {
            super.onMessageReceived( messageEvent );
        }

    }
}