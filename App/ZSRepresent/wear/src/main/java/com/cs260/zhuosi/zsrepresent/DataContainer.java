package com.cs260.zhuosi.zsrepresent;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Zhuosi on 3/8/16.
 */
public class DataContainer {

    public static final String NAME_LIST = "/NAMELIST";
    public static final String PARTY_LIST = "/PARTYLIST";
    public static final String START_INTENT = "/STARTINTENT";
    public static final String COUNTY = "/COUNTY";
    public static final String VOTEOBAMA = "/VOTEREOVAMA";
    public static final String VOTEROMNEY = "/VOTEREROMNEY";


    private static DataContainer mInstance = null;
    public String[] nameList = null;
    public String[] partyList = null;
    public String county = null;
    public int obama = 0, romney = 0;

    private DataContainer(){}

    public static DataContainer getInstance(){
        if(mInstance == null){
            mInstance = new DataContainer();
        }
        return mInstance;
    }


}
