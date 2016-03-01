package com.cs260.zhuosi.votewatch;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Zhuosi on 2/27/16.
 */
public class RepresentativeActivity extends FragmentActivity implements RepresentativeFragment.OnFragmentInteractionListener {

    TextView maneText = null;
    ViewPager viewPager;
    ImageView repImage = null, partyImage = null;
    String[] NameList, PartyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swip);
        setContentView(R.layout.fragment_representative);

        maneText = (TextView) findViewById(R.id.nameText);
        repImage = (ImageView) findViewById(R.id.repImage);
        partyImage = (ImageView) findViewById(R.id.partyImage);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        final ListView representList = (ListView) findViewById(R.id.representList);
//        int[] pictures = {R.mipmap.nancy_d, R.mipmap.kevin, R.mipmap.angus_king};
//        int[] party = {R.mipmap.d_logo,R.mipmap.r_logo,R.mipmap.i_logo};
//        String[] person = {"Nancy Pelosi", "Kevin McCarthy", "Angus King"};

        if (extras != null) {
            NameList = extras.getString("name_list").split(";");
            PartyList = extras.getString("party_list").split(";");
        }

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        RepresentativeAdapter swipeAdapter = new RepresentativeAdapter(getSupportFragmentManager(), NameList, PartyList);
        viewPager.setAdapter(swipeAdapter);

    }

    public void onFragmentInteraction(Uri uri){

    }

}
