package com.cs260.zhuosi.zsrepresent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Zhuosi on 2/27/16.
 */
public class DetailedActivity extends Activity{

    private Button titleButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_detailed);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);

        Intent intent = getIntent();
        DataContainer dc = DataContainer.getInstance();
        int id = Integer.parseInt(intent.getStringExtra(dc.detailId));

        System.out.println("In the detailed activity, id is " + id);

        ImageView repImage = (ImageView) findViewById(R.id.repImage);
        ImageView partyImage = (ImageView) findViewById(R.id.partyImage);
        TextView nameText = (TextView) findViewById(R.id.repName);

        Context context = getApplicationContext();
        Representative r = dc.getRepresentativeByIndex(id);
        partyImage.setImageResource(context.getResources().getIdentifier("mipmap/" + r.getParty(), null, context.getPackageName()));
        repImage.setImageResource(context.getResources().getIdentifier("mipmap/" + r.getPicture(), null, context.getPackageName()));
        nameText.setText(r.getName());


        titleButton = (Button)this.findViewById(R.id.titleButton);
        titleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailedActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

    }


}
