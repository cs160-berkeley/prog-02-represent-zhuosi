package com.cs260.zhuosi.zsrepresent;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by joleary and noon on 2/19/16 at very late in the night. (early in the morning?)
 */
public class PhoneListenerService extends WearableListenerService {

//   WearableListenerServices don't need an iBinder or an onStartCommand: they just need an onMessageReceieved.

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in PhoneListenerService, got: " + messageEvent.getPath());
        final DataContainer dc = DataContainer.getInstance();

        if( messageEvent.getPath().equalsIgnoreCase( dc.detailId ) ) {
            String message = new String(messageEvent.getData(), StandardCharsets.UTF_8);

            Intent intent = new Intent(this, DetailedActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra(dc.detailId , message);

            System.out.println("About to start the detailed page for representative at position " + message);
            startActivity(intent);
        }else {
            if (messageEvent.getPath().equalsIgnoreCase(dc.Shacksignal)) {

                dc.randomZIP(getApplicationContext());
                GetRepresGenTask getRepresGenTask = new GetRepresGenTask(new AsyncResponse() {
                    @Override
                    public void processFilnish(Object output) {
                        dc.setRepresentativeInfo((String) output);
                        Intent intent = new Intent(PhoneListenerService.this, CongressionalActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                        intent = new Intent(PhoneListenerService.this, PhoneToWatchService.class);
                        startService(intent);
                    }
                });
                getRepresGenTask.setData(dc.getZipCode());
                getRepresGenTask.execute();
            }
        }
    }
}
