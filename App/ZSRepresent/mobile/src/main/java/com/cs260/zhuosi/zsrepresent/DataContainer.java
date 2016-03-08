package com.cs260.zhuosi.zsrepresent;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Zhuosi on 3/4/16.
 */
public class DataContainer {
    private static DataContainer mInstance = null;

    public static final String NAME_LIST = "/name_list";
    public static final String PARTY_LIST = "/party_list";
    public static final String detailId = "/DETAILID";
    public static final String Shacksignal = "/SHAKE";

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
}
