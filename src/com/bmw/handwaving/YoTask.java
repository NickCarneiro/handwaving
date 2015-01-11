package com.bmw.handwaving;

import android.os.AsyncTask;
import android.util.Log;


/**
 * Created by nickc on 1/10/15.
 */
public class YoTask extends AsyncTask<Void, Void, Void> {
    private static String YO_API_KEY = "";

    protected Void doInBackground(Void... params) {
        Yo yo = new Yo(YO_API_KEY);
        try {
            yo.yoall();
        } catch (Exception e) {
            Log.e(YoTask.class.getName(), e.getMessage());
        }
        return null;
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    protected void onPostExecute(Long result) {
    }
}
