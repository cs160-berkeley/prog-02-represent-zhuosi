package com.cs260.zhuosi.zsrepresent;

/**
 * Created by Zhuosi on 3/7/16.
 */
public class Tile {
    private String name;
    private int party;
    private int img;
    private String lastTwiteTime;
    private String lastTwiteContent;
    private String email;
    private String website;

    public Tile(String name, int party, int img, String lastTwiteTime, String lastTwiteContent, String email, String website) {
        super();
        this.name = name;
        this.party = party;
        this.img = img;
        this.lastTwiteTime = lastTwiteTime;
        this.lastTwiteContent = lastTwiteContent;
        this.email = email;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public int getParty() {
        return party;
    }

    public int getImg() {
        return img;
    }

    public String getLastTwiteTime() {
        return lastTwiteTime;
    }

    public String getLastTwiteContent() {
        return lastTwiteContent;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

}
