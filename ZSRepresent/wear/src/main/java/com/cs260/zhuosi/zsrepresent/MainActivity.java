package com.cs260.zhuosi.zsrepresent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {

    private TextView mTextView;
    private String Shacksignal = "/SHAKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, WatchToPhoneService.class);
        intent.putExtra(Shacksignal,"shacked the watch");
        startService(intent);
    }

}
