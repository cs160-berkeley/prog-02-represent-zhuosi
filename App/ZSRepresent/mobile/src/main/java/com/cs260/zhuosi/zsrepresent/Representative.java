package com.cs260.zhuosi.zsrepresent;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Zhuosi on 3/4/16.
 */
public class Representative {
    private String name = null;
    private String picture = null;
    private String party = null;
    private String email = null;
    private String website = null;
    private String lastTweetContent = null;
    private String lastTweetDate = null;
    private String TermEnd = null;
    private String bioguide_id = null;
    private List<String> committeeID = new ArrayList<String>();

    private List<String> committeeList = new ArrayList<String>();
    private List<String> billList = new ArrayList<String>();

    public List<String> getCommitteeID() {
        return committeeID;
    }

    public void setCommitteeID(List<String> committeeID) {
        this.committeeID = committeeID;
    }

    public void setCommitteeList(List<String> committeeList) {
        this.committeeList = committeeList;
    }

    public String getBioguide_id() {
        return bioguide_id;
    }

    public void setBioguide_id(String bioguide_id) {
        this.bioguide_id = bioguide_id;
    }

    public String getLastTweetDate() {
        return lastTweetDate;
    }

    public void setLastTweetDate(String lastTweetDate) {
        this.lastTweetDate = lastTweetDate;
    }

    public String getTermEnd() {
        return TermEnd;
    }

    public void setTermEnd(String termEnd) {
        TermEnd = termEnd;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public String getParty() {
        return party;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public String getLastTweetContent() {
        return lastTweetContent;
    }


    public List<String> getCommitteeList() {
        return committeeList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setLastTweetContent(String lastTweetContent) {
        this.lastTweetContent = lastTweetContent;
    }

    public void clearBillList(){
        billList.clear();
    }


    public void setCommitteeList(String info) {
        System.out.println("ready to setRepresentativeInfo" + info);
        committeeList.clear();
        try {
            JSONObject bigObject = new JSONObject(info);
            JSONArray comJsonArray = bigObject.getJSONArray("results");
            for(int i = 0; i < comJsonArray.length(); i++){
                JSONObject comObject = (JSONObject)comJsonArray.get(i);
                committeeList.add(comObject.get("name").toString());
                committeeID.add(comObject.get("committee_id").toString());
            }
        }catch(Exception e){
            System.out.println("jsonobject initial exception " + e);
        }
        System.out.println("finished parsing json for commity information");
    }

    public Tile toTile(Context context){

        int party = context.getResources().getIdentifier("mipmap/" + this.party, null, context.getPackageName());
        int img = context.getResources().getIdentifier("mipmap/" + this.picture, null, context.getPackageName());
        Tile t = new Tile(name, party,img, this.lastTweetDate, this.lastTweetContent, this.email, this.website);
        return t;
    }

    public String toString(){
        return " name is " + name +"\n" + " party is " + party + "\n" + " end term is " + TermEnd +"\n" + " email is " + email +"\n" + " website is " + website + "\n";
    }

    public void addBillList(String info) {
        System.out.println("ready to setRepresentativeInfo" + info);
        try {
            JSONObject bigObject = new JSONObject(info);
            JSONArray billJsonArray = bigObject.getJSONArray("results");
            for(int i = 0; i < billJsonArray.length(); i++){
                JSONObject billObject = (JSONObject)billJsonArray.get(i);
                billList.add(billObject.get("official_title").toString());
            }
        }catch(Exception e){
            System.out.println("jsonobject initial exception " + e);
        }
        System.out.println("finished parsing json for bill information");
    }

    public List<String> getBillList() {
        return billList;
    }
}
