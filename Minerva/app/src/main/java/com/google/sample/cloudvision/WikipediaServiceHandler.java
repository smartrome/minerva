package com.google.sample.cloudvision;

import android.os.AsyncTask;

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

    private String serviceURL;

    private String articleName;

    public WikipediaServiceHandler(String articleName){

        this.articleName = articleName;

        this.serviceURL = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles=";
    }

    public void callService(){

        String url = serviceURL + articleName;

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
                    e.printStackTrace();
                }
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
                    /*** Manage failure cases ***/
                }

            }

        }.execute(url);
    }

    protected void succeeded(String result){
        onMessageReceived(parseWikipediaMessage(result));
    }

    public abstract void onMessageReceived(String wikipediaMessage);

    protected String parseWikipediaMessage(String wikipediaMessage){

        String extract = null;

        try {
            JSONObject wikipediaMessageAsJson = new JSONObject(wikipediaMessage);

            JSONObject pages = wikipediaMessageAsJson.getJSONObject("query").getJSONObject("pages");

            extract = pages.getJSONObject(((Iterator<String>) pages.keys()).next()).getString("extract");

            String firstParagraph = new String(extract.substring(0, extract.indexOf("\n")));

            extract = firstParagraph;

        }catch(Exception e){
            e.printStackTrace();
        }

        return extract;
    }

}
