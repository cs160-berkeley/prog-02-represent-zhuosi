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

/**
 * Created by Zhuosi on 2/29/16.
 */
public class RepresentativeAdapter extends FragmentStatePagerAdapter {
    String[] NameList;
    String[] PartyList;

    public RepresentativeAdapter(FragmentManager fm, String[] name_list, String[] party_list){
        super(fm);
        NameList = name_list;
        PartyList = party_list;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new RepresentativeFragment();
        Bundle bundle = new Bundle();

        bundle.putInt("Length", NameList.length);

        if(position < NameList.length) {
            bundle.putString("Name", NameList[position]);
            bundle.putString("Party", PartyList[position]);
        }
        else if(position == NameList.length){
            bundle.putString("Location", "United States");
        }

        fragment.setArguments(bundle);
        return fragment;
    }

    public int getCount(){
        return NameList.length + 1;
    }
}
