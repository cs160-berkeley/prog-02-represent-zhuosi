package com.cs260.zhuosi.vote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Zhuosi on 2/26/16.
 */
public class CongressionalActivity extends Activity {
    private Button titleButton = null;
    private LinearLayout tire1 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_congressional);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);


        titleButton = (Button)this.findViewById(R.id.titleButton);
        titleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CongressionalActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        tire1 = (LinearLayout) findViewById(R.id.tire1);
        tire1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CongressionalActivity.this, DetailedActivity.class);
                startActivity(intent);
            }
        });


//        TextView textView =(TextView)findViewById(R.id.textView);
//        textView.setClickable(true);
//        textView.setMovementMethod(LinkMovementMethod.getInstance());
//        String text = "<a href='http://www.google.com'> Google </a>";
//        textView.setText(Html.fromHtml(text));



    }

}
