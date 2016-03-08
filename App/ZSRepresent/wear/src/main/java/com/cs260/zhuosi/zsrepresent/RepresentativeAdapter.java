package com.cs260.zhuosi.zsrepresent;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by Zhuosi on 2/29/16.
 */
public class RepresentativeAdapter extends FragmentStatePagerAdapter {
    String[] NameList;
    String[] PartyList;
    HashMap<String, String> PictureList = new HashMap<String, String> ();

    public RepresentativeAdapter(FragmentManager fm, String[] name_list, String[] party_list){
        super(fm);
        NameList = name_list;
        PartyList = party_list;
        PictureList.put("Nancy Pelosi","nancy_d");
        PictureList.put("Kevin McCarthy","kevin");
        PictureList.put("Angus King","angus_king");
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new RepresentativeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("Length", NameList.length);
        bundle.putInt("Position", position);

        if(position < NameList.length) {
            bundle.putString("Name", NameList[position]);
            bundle.putString("Party", PartyList[position]);
            System.out.println("**********************");
            System.out.println(NameList[position] + "    " + PictureList.get(NameList[position]));
            System.out.println("**********************");
            bundle.putString("Pic", PictureList.get(NameList[position]));
        }
        else if(position == NameList.length){
            bundle.putString("Location", "United States California");
            bundle.putString("Result","Nancy: 33% \nKevin: 67%");
        }

        fragment.setArguments(bundle);
        return fragment;
    }

    public int getCount(){
        return NameList.length + 1;
    }
}
