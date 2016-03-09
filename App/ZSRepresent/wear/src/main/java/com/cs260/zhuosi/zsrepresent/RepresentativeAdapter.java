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
    HashMap<String, String> PictureList = new HashMap<String, String> ();
    DataContainer dc = null;
    public RepresentativeAdapter(FragmentManager fm, String[] name_list, String[] party_list){
        super(fm);
        PictureList.put("Nancy Pelosi", "nancy_d");
        PictureList.put("Kevin McCarthy","kevin");
        PictureList.put("Angus King","angus_king");
        dc = DataContainer.getInstance();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new RepresentativeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("Length", dc.nameList.length);
        bundle.putInt("Position", position);

        if(position < dc.nameList.length) {
            bundle.putString("Name", dc.nameList[position]);
            bundle.putString("Party", dc.partyList[position]);
            System.out.println("**********************");
            System.out.println(dc.nameList[position] + "    " + PictureList.get(dc.nameList[position]));
            System.out.println("**********************");
            bundle.putString("Pic", PictureList.get(dc.nameList[position]));
        }
        else if(position == dc.nameList.length){
            bundle.putString("Location", "United States " + dc.county);
            bundle.putString("Result","Obama: " + dc.obama +"% \nRomney: " + dc.romney + "%");
        }

        fragment.setArguments(bundle);
        return fragment;
    }

    public int getCount(){
        return dc.nameList.length + 1;
    }
}
