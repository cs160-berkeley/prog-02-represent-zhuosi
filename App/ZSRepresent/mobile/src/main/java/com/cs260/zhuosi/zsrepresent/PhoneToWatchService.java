package com.cs260.zhuosi.zsrepresent;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

/**
 * Created by joleary on 2/19/16.
 */
public class PhoneToWatchService extends Service {

    private GoogleApiClient mApiClient;

    @Override
    public void onCreate() {
        super.onCreate();
        //initialize the googleAPIClient for message passing
        mApiClient = new GoogleApiClient.Builder( this )
                .addApi( Wearable.API )
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle connectionHint) {
                    }

                    @Override
                    public void onConnectionSuspended(int cause) {
                    }
                })
                .build();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mApiClient.disconnect();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Which cat do we want to feed? Grab this info from INTENT
        // which was passed over when we called startService

        final DataContainer dc = DataContainer.getInstance();
        final String nameList = dc.getNameList();
        final String partyList = dc.getPartyList();

        System.out.println("nameList got from main activity : " + nameList);
        System.out.println("partyList got from main activity : " + partyList);


        // Send the message with the cat name
        new Thread(new Runnable() {
            @Override
            public void run() {
                //first, connect to the apiclient
                mApiClient.connect();
                //now that you're connected, send a massage with the cat name
                sendMessage(dc.NAME_LIST, nameList);
                try{
                    Thread.sleep(500);
                }
                catch ( java.lang.InterruptedException ie) {
                    System.out.println(ie);
                }
                sendMessage(dc.PARTY_LIST, partyList);
                try{
                    Thread.sleep(500);
                }
                catch ( java.lang.InterruptedException ie) {
                    System.out.println(ie);
                }
                sendMessage(dc.COUNTY, dc.getCounty());
                try{
                    Thread.sleep(500);
                }
                catch ( java.lang.InterruptedException ie) {
                    System.out.println(ie);
                }
                dc.findVoteResult(getApplicationContext());
                sendMessage(dc.VOTEOBAMA, dc.getObama());
                try{
                    Thread.sleep(500);
                }
                catch ( java.lang.InterruptedException ie) {
                    System.out.println(ie);
                }
                sendMessage(dc.VOTEROMNEY, dc.getROMNEY());
                try{
                    Thread.sleep(500);
                }
                catch ( java.lang.InterruptedException ie) {
                    System.out.println(ie);
                }

                sendMessage(dc.START_INTENT, "Time to start the new intent!");
            }
        }).start();

        return START_STICKY;
    }

    @Override //remember, all services need to implement an IBiner
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void sendMessage( final String path, final String text ) {
        //one way to send message: start a new thread and call .await()
        //see watchtophoneservice for another way to send a message
        new Thread( new Runnable() {
            @Override
            public void run() {
                NodeApi.GetConnectedNodesResult nodes = Wearable.NodeApi.getConnectedNodes( mApiClient ).await();
                for(Node node : nodes.getNodes()) {
                    //we find 'nodes', which are nearby bluetooth devices (aka emulators)
                    //send a message for each of these nodes (just one, for an emulator)
                    MessageApi.SendMessageResult result = Wearable.MessageApi.sendMessage(
                            mApiClient, node.getId(), path, text.getBytes() ).await();
                    //4 arguments: api client, the node ID, the path (for the listener to parse),
                    //and the message itself (you need to convert it to bytes.)
                }
            }
        }).start();
    }

}
