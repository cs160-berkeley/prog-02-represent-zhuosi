package com.cs260.zhuosi.zsrepresent;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Created by Zhuosi on 3/9/16.
 */
public class GetRepresGenTask extends AsyncTask<Object, Object, Object> {

    private String urlString = null;
    private DataContainer dc = DataContainer.getInstance();

    public void setData(String Zipcode){
        urlString = "http://congress.api.sunlightfoundation.com/legislators/locate?zip=" + Zipcode + "&apikey=" + dc.getSunlightKey();
    }

    public void setData(Double latitude, Double longitude){
        urlString = "http://congress.api.sunlightfoundation.com/legislators/locate?latitude=" + latitude.toString() + "&longitude=" + longitude.toString()+ "&apikey=" + dc.getSunlightKey();
    }

    public AsyncResponse delegate = null; // call back interface

    @Override
    protected Object doInBackground(Object... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;
        try {
            //http://congress.api.sunlightfoundation.com/legislators/locate?zip=94704&apikey=01a9c938100b40658be95180193e1bd2"
            URL url = new URL(urlString);

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            //urlConnection.setRequestMethod("GET");
            System.out.println(urlConnection);
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }

            forecastJsonStr = buffer.toString();
        }
        catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
            return null;
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }

        return forecastJsonStr;
    }

    public GetRepresGenTask(AsyncResponse asyncResponse){
        delegate = asyncResponse;
    }

    @Override
    public void onPostExecute(Object result){
        delegate.processFilnish(result);
    }

}
