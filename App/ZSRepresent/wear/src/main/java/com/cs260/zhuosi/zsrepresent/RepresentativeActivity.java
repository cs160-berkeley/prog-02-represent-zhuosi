package com.cs260.zhuosi.zsrepresent;

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

    TextView nameText = null;
    ViewPager viewPager;
    ImageView repImage = null, partyImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swip);
        DataContainer dc = DataContainer.getInstance();

        nameText = (TextView) findViewById(R.id.nameText);
        repImage = (ImageView) findViewById(R.id.repImage);
        partyImage = (ImageView) findViewById(R.id.partyImage);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        RepresentativeAdapter swipeAdapter = new RepresentativeAdapter(getSupportFragmentManager(), dc.nameList, dc.partyList);
        viewPager.setAdapter(swipeAdapter);

    }

    public void onFragmentInteraction(Uri uri){

    }

}
