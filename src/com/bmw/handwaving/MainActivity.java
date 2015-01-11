package com.bmw.handwaving;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.i("MainActivity", "activity created");
        Intent intent = new Intent(this, SwipeService.class);
        startService(intent);
    }

    protected void onResume() {
        super.onResume();

    }

    protected void onPause() {
        super.onPause();
    }




}
