package com.bmw.handwaving;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor proximitySensor;
    long lastProximityEventTime = 0;
    ProximityState lastProximityState;


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
        final ProximityState proximityState;
        if (event.values[0] == 0) {
            proximityState = ProximityState.NEAR;
        } else {
            proximityState = ProximityState.FAR;
        }
        Log.i("MainActivity", "CHANGE: " + proximityState);

    }
}
