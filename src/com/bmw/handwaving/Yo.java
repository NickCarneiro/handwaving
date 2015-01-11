package com.bmw.handwaving;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.net.ssl.HttpsURLConnection;


public class Yo {

    private String apikey;

    public Yo(String apikey) {
        this.apikey = apikey;
    }

    public void yoall() throws Exception {
        String url = "https://api.justyo.co/yoall/";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        con.setRequestMethod("POST");

        String urlParameters = "api_token=" + this.apikey + "&username=HACKTHEDRIVE";

        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
    }
}
