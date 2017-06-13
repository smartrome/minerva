package com.minerva;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by Julio Carrasquel on 4/9/17.
 */

public abstract class WikipediaServiceHandler{

    /*** Logging tag ***/

    protected String LOG_TAG;

    /*** Wikipedia service hander input parameters ***/

    protected String serviceURL;

    protected String articleName;

    public WikipediaServiceHandler(String articleName){

        this.LOG_TAG = "WikipediaServiceHandler";

        this.articleName = articleName;

        this.serviceURL = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles=";
    }

    public void callService(){

        // *** Change spaces by the special character %20 in the landmark name ***
        articleName = articleName.replace(" ", "%20");

        String url = serviceURL + articleName;

        // *** Adding redirect let Wikipedia know to redirect us to the article with more similarity on the name ***
        url = url + "&redirects=1";

        Log.i(LOG_TAG, "Wikipedia service handler calling service in: " + url);

        new AsyncTask<String, Integer, String>() {

            protected Integer responseCode;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String ...params){
                String urlString = params[0];
                InputStream inputStream = null;
                try{
                    URL url = new URL(urlString);
                    HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
                    inputStream = new BufferedInputStream(httpUrlConnection.getInputStream());
                    responseCode = httpUrlConnection.getResponseCode();
                }catch(Exception e){
                    Log.e(LOG_TAG, "There has been an error communicating with the Wikipedia API", e);
                }

                Log.i(LOG_TAG, "Wikipedia Service Handler Response code status: " + responseCode);
                return responseCode >= 200 && responseCode < 300 ? convertStreamToString(inputStream) : null;
            }

            private String convertStreamToString(InputStream is) {
                Scanner s = new Scanner(is).useDelimiter("\\A");
                return s.hasNext() ? s.next() : "";
            }

            @Override
            protected void onPostExecute(String result){
                if (responseCode >= 200 && responseCode < 300) {
                    succeeded(result);
                } else {
                    // Manage failure cases
                }

            }

        }.execute(url);
    }

    protected void succeeded(String result){
        // Only print the raw JSON object for debugging on development
        Log.d(LOG_TAG, "JSON Object arrived from the Wikipedia server: " + result);
        onMessageReceived(parseWikipediaMessage(result));
    }

    protected String parseWikipediaMessage(String wikipediaMessage){

        String extract = null;

        try {
            Log.i(LOG_TAG, "Parsing Wikipedia message response...");

            JSONObject wikipediaMessageAsJson = new JSONObject(wikipediaMessage);

            JSONObject pages = wikipediaMessageAsJson.getJSONObject("query").getJSONObject("pages");

            extract = pages.getJSONObject(((Iterator<String>) pages.keys()).next()).getString("extract");

            if(extract != null && extract.length() > 0){
                /***Check if the extract field is not empty and not null, then we take the first paragraph
                    unless the extract is only composed of one paragraph ***/
                extract = extract.indexOf("\n") == - 1 ? extract : new String(extract.substring(0, extract.indexOf("\n")));
            }else{
                Log.w(LOG_TAG, "Extract field on JSON response message is empty or null. Please check...");
                // If the extract field was empty or null, put again null as a sentinel mark
                extract = null;
            }

        }catch(Exception e){
            Log.e(LOG_TAG, "There has been an error parsing the Wikipedia extract...", e);
            extract = null;
        }

        return extract;
    }

    /*** This method is implemented within the Result Activity in order to carry out the
     * necessary logic after the call done to the wikipedia API ***/
    public abstract void onMessageReceived(String wikipediaMessage);
}
