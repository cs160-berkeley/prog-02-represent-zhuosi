package com.cs260.zhuosi.zsrepresent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Zhuosi on 2/26/16.
 */
public class ZipcodeActivity extends Activity {
    private Button titleButton = null;
    private Button confirmButton = null;

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

                final DataContainer dc = DataContainer.getInstance();
//                dc.fillDummyData();
                EditText zipText = (EditText)findViewById(R.id.zipText);

                GetRepresGenTask getRepresGenTask = new GetRepresGenTask(new AsyncResponse() {
                    @Override
                    public void processFilnish(Object output) {
                        dc.setRepresentativeInfo((String)output);
                        Intent intent = new Intent(ZipcodeActivity.this, CongressionalActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                        intent = new Intent(ZipcodeActivity.this, PhoneToWatchService.class);
                        startService(intent);
                    }
                });
                getRepresGenTask.setData(zipText.getText().toString());
                getRepresGenTask.execute();

            }
        });

    }

}
