package com.cs260.zhuosi.zsrepresent;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Zhuosi on 3/4/16.
 */
public class DataContainer {
    private static DataContainer mInstance = null;
    private JSONObject voteJason = null;
    public static final String NAME_LIST = "/name_list";
    public static final String PARTY_LIST = "/party_list";
    public static final String detailId = "/DETAILID";
    public static final String Shacksignal = "/SHAKE";
    public static final String START_INTENT = "/start_intent";
    private Double obama, romney;

    private static JSONArray jarray = null;

    private DataContainer(){}
    private static List<Representative> representativeList = new ArrayList<Representative>();
    private String ZipCode = null;
    private String location = null;
    private String voteResult = null;

    public static List<Representative> getRepresentativeList(){
        return representativeList;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVoteResult() {
        return voteResult;
    }

    public void setVoteResult(String voteResult) {
        this.voteResult = voteResult;
    }

    public int getRepresentCount(){
       return representativeList.size();
    }

    public void fillDummyData() {
        Date date = new Date();

        Representative r = new Representative();
        r.setName("Nancy Pelosi");
        r.setParty("d_logo");
        r.setPicture("nancy_d");
        r.setLastTweetDate(date);
        r.setLastTweetContent("twit content 1");
        r.setWebsite("http://www.mikulski.senate.gov/");
        representativeList.add(r);
        r = new Representative();
        r.setName("Kevin McCarthy");
        r.setParty("r_logo");
        r.setPicture("kevin");
        r.setLastTweetDate(date);
        r.setLastTweetContent("twit content 2");
        representativeList.add(r);
        r = new Representative();
        r.setName("Angus King");
        r.setParty("i_logo");
        r.setPicture("angus_king");
        r.setLastTweetDate(date);
        r.setLastTweetContent("twit content 3");
        representativeList.add(r);
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


    public void findVoteResult(Context context, String county) {
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
                    obama = (Double)j.get("obama-percentage");
                    romney = (Double)j.get("romney-percentage");
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("finished searching, obama vote result is " + obama);
        System.out.println("finished searching, romney vote result is " + romney);
    }
}
