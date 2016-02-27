package com.cs260.zhuosi.vote;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.*;

public class MainActivity extends Activity {

    private Button titleButton = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);


        titleButton = (Button)this.findViewById(R.id.titleButton);
        titleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Hola",Toast.LENGTH_SHORT).show();
            }
        });

//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#27AE60")));
//        getSupportActionBar().setIcon(R.mipmap.vote_title);
    }
}
