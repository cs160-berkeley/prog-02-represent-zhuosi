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
    // In PhoneToWatchService, we passed in a path, either "/FRED" or "/LEXY"
    // These paths serve to differentiate different phone-to-watch messages
    private static final String NAME_LIST = "/name_list";
    private static final String PARTY_LIST = "/party_list";
    private static final String START_INTENT = "/start_intent";
    private static String name_list = null;
    private static String party_list = null;


    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in WatchListenerService, got: " + messageEvent.getPath());
        //use the 'path' field in sendmessage to differentiate use cases
        //(here, fred vs lexy)

        if( messageEvent.getPath().equalsIgnoreCase( NAME_LIST ) ) {
            String name_list = new String(messageEvent.getData(), StandardCharsets.UTF_8);
        } else if (messageEvent.getPath().equalsIgnoreCase( PARTY_LIST )) {
            String party_list = new String(messageEvent.getData(), StandardCharsets.UTF_8);
        } else if (messageEvent.getPath().equalsIgnoreCase( START_INTENT )) {
            Intent intent = new Intent(this, RepresentativeActivity.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("name_list", name_list);
            intent.putExtra("party_list", party_list);
            System.out.println(name_list);
            System.out.println(party_list);
            startActivity(intent);
            System.out.println("**************************");
        }else {
            super.onMessageReceived( messageEvent );
        }

    }
}