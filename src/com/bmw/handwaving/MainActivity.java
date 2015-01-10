package com.bmw.handwaving;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor proximitySensor;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.i("MainActivity", "activity created");
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        proximitySensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        Log.i("MainActivity", "CHANGE: " + String.valueOf(event.values[0]));

    }
}
