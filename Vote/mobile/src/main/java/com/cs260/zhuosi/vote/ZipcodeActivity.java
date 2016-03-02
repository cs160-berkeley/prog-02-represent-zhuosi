package com.cs260.zhuosi.vote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by Zhuosi on 2/26/16.
 */
public class ZipcodeActivity extends Activity {
    private Button titleButton = null;
    private Button confirmButton = null;
    private static final String NAME_LIST = "/name_list";
    private static final String PARTY_LIST = "/party_list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_zipcode);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);


        titleButton = (Button) this.findViewById(R.id.titleButton);
        titleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZipcodeActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        confirmButton = (Button) this.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ZipcodeActivity.this, CongressionalActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);

                String finalNameList = "Nancy Pelosi;Kevin McCarthy;Angus King";
                String finalPartyList = "d;r;i";

                intent = new Intent(ZipcodeActivity.this, PhoneToWatchService.class);
                intent.putExtra(NAME_LIST, finalNameList);
                intent.putExtra(PARTY_LIST, finalPartyList);
                startService(intent);
            }
        });

    }

}