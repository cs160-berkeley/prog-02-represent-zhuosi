package com.cs260.zhuosi.zsrepresent;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Zhuosi on 3/4/16.
 */
public class DataContainer {
    private static DataContainer mInstance = null;
    private JSONObject voteJason = null;
    public static final String NAME_LIST = "/NAMELIST";
    public static final String PARTY_LIST = "/PARTYLIST";
    public static final String START_INTENT = "/STARTINTENT";
    public static final String detailId = "/DETAILID";
    public static final String Shacksignal = "/SHAKE";
    public static final String COUNTY = "/COUNTY";
    public static final String VOTEOBAMA = "/VOTEREOVAMA";
    public static final String VOTEROMNEY = "/VOTEREROMNEY";
    private int obama, romney;
    private static String sunlightKey = null;
    private static JSONArray jarray = null;

    private DataContainer(){}
    private static List<Representative> representativeList = new ArrayList<Representative>();
    private String ZipCode = null;
    private String county = null;

    public static List<Representative> getRepresentativeList(){
        return representativeList;
    }


    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }


    public static DataContainer getInstance(){
        if(mInstance == null){
            mInstance = new DataContainer();
        }
        return mInstance;
    }

    public void addRepresentative(Representative r){
        representativeList.add(r);
    }

    public Representative getRepresentativeByIndex(int index){
        return representativeList.get(index);
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public int getRepresentCount(){
       return representativeList.size();
    }

    public Tile getTileByIndex(int index, Context context) {
        Representative r = representativeList.get(index);
        Tile t = r.toTile(context);
        return t;
    }

    public String getNameList() {
        StringBuilder namelist = new StringBuilder();
        for(int i = 0; i < getRepresentCount(); i++){
            namelist.append(getRepresentativeByIndex(i).getName());
            namelist.append(";");
        }
        return namelist.toString();
    }

    public String getPartyList() {
        StringBuilder partyList = new StringBuilder();
        for(int i = 0; i < getRepresentCount(); i++){
            partyList.append(getRepresentativeByIndex(i).getParty());
            partyList.append(";");
        }
        return partyList.toString();
    }

    public void findVoteResult(Context context) {
        String json = null;

        try {
            InputStream stream = context.getAssets().open("election-county-2012.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            json = new String(buffer,"UTF-8");
            System.out.println("read in file successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            jarray = new JSONArray(json);
            System.out.println("created json array successfully");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            for(int i = 0; i < jarray.length(); i++){
                JSONObject j = (JSONObject) jarray.get(i);
                if(j.get("county-name").equals(county)){
                    String obamaVote = j.get("obama-percentage").toString();
                    if(obamaVote.indexOf(".") == -1){
                        obama = Integer.parseInt(obamaVote);
                    }else{
                        obama = Integer.parseInt(obamaVote.substring(0, obamaVote.indexOf(".")));
                    }
                    String romneyVote = j.get("romney-percentage").toString();
                    if(romneyVote.indexOf(".") == -1){
                        romney = Integer.parseInt(romneyVote);
                    }else{
                        romney = Integer.parseInt(romneyVote.substring(0, romneyVote.indexOf(".")));
                    }
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("finished searching, obama vote result is " + obama);
        System.out.println("finished searching, romney vote result is " + romney);
    }

    public String getObama() {
        return Integer.toString(obama);
    }

    public String getROMNEY() {
        return Integer.toString(romney);
    }

    public void randomZIP(Context context){
        BufferedReader br = null;
        try {
            InputStream inputStream = context.getAssets().open("us_postal_codes.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            br = new BufferedReader(inputStreamReader);
            Random r = new Random();
            int index = r.nextInt(43582) + 1;
            String line = "";
            for(int i = 0; i < index; i++){
                line = br.readLine();
            }
            ZipCode = line.split(",")[0];

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void setSunlightKey(String sunlightKey) {
        this.sunlightKey = sunlightKey;
    }

    public String getSunlightKey(){
        return sunlightKey;
    }

    public void setRepresentativeInfo(String forecastJsonStr) {
       // System.out.println("ready to setRepresentativeInfo" + forecastJsonStr);
        representativeList.clear();
        try {
            JSONObject bigObject = new JSONObject(forecastJsonStr);
            JSONArray repJsonArray = bigObject.getJSONArray("results");
            for(int i = 0; i < repJsonArray.length(); i++){
                JSONObject repObject = (JSONObject)repJsonArray.get(i);
                final Representative r = new Representative();
                r.setName(repObject.get("first_name").toString() + " " + repObject.get("last_name").toString());
                r.setEmail(repObject.get("oc_email").toString());
                r.setWebsite(repObject.get("website").toString());
                county = repObject.get("state_name").toString();
                r.setParty(convertParty(repObject.get("party").toString()));
                r.setTermEnd(repObject.get("term_end").toString());
                r.setBioguide_id(repObject.get("bioguide_id").toString());
                r.setLastTweetContent(repObject.get("twitter_id").toString());
                representativeList.add(r);
            }
        }catch(Exception e){
            System.out.println("jsonobject initial exception " + e);
        }

        GetRepresComTask getReprescomTask = new GetRepresComTask(new AsyncResponse() {
            @Override
            public void processFilnish(Object output) {
               GetRepresBillTask getRepresBillTask = new GetRepresBillTask();
                getRepresBillTask.execute();
            }
        });
        getReprescomTask.execute();

      //  System.out.println("finished parsing json for basic information");
    }

    private String convertParty(String party) {
        return party.toLowerCase()+"_logo";
    }
}
