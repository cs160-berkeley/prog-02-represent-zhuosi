package com.cs260.zhuosi.zsrepresent;

import android.content.Context;

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
    private Date lastTweetDate = null;
    private Date endTermDate = null;
    private List<String> committeeList = new ArrayList<String>();
    private List<Bill> billList = new ArrayList<Bill>();

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

    public Date getLastTweetDate() {
        return lastTweetDate;
    }

    public Date getEndTermDate() {
        return endTermDate;
    }

    public List<String> getCommitteeList() {
        return committeeList;
    }

    public List<Bill> getBillList() {
        return billList;
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

    public void setLastTweetDate(Date lastTweetDate) {
        this.lastTweetDate = lastTweetDate;
    }

    public void setEndTermDate(Date endTermDate) {
        this.endTermDate = endTermDate;
    }

    public void setCommitteeList(List<String> committeeList) {
        this.committeeList = committeeList;
    }

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
    }

    public Tile toTile(Context context){

        int party = context.getResources().getIdentifier("mipmap/" + this.party, null, context.getPackageName());
        int img = context.getResources().getIdentifier("mipmap/" + this.picture, null, context.getPackageName());
        Tile t = new Tile(name, party,img, this.lastTweetDate.toString(), this.lastTweetContent, this.email, this.website);
        return t;
    }

}
