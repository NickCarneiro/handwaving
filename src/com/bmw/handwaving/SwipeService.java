package com.bmw.handwaving;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.hardware.*;
import android.media.MediaPlayer;
import android.util.Log;

public class SwipeService extends IntentService implements SensorEventListener {
    long lastProximityEventTime = 0;
    ProximityState lastProximityState;


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public SwipeService(String name) {
        super(name);
    }

    public SwipeService() {
        super("SwipeService");
    }

    @Override
    public void onHandleIntent(Intent intent) {
        Log.i("SwipeService", "handling intent");
        SensorManager mSensorManager;
        Sensor proximitySensor;
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        proximitySensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mSensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onSensorChanged(SensorEvent event) {
        final ProximityState proximityState;
        if (event.values[0] == 0) {
            proximityState = ProximityState.NEAR;
        } else {
            proximityState = ProximityState.FAR;
        }
        final long now = System.currentTimeMillis();
        final long eventDeltaMillis = now - this.lastProximityEventTime;
        if (eventDeltaMillis < 1000 && ProximityState.NEAR.equals(lastProximityState) && ProximityState.FAR.equals(proximityState)) {

            Log.i("MainActivity", "SWIPE");
            this.playShutterSound();
            this.sendYo();
        }
        this.lastProximityEventTime = now;
        this.lastProximityState = proximityState;
    }

    public void playShutterSound() {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.shutter);
        mp.start();
    }


    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void sendYo() {
        new YoTask().execute();
    }
}
