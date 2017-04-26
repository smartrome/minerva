package com.minerva;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class ResultActivity extends AppCompatActivity {

    /*** Logging tag ***/

    protected String LOG_TAG;

    /*** Parameters passed to this activity ***/

    protected String placeName;

    protected Pair<Double, Double> placeCoordinates;

    protected String wikipediaInformation;

    /*** UI referenced main elements ***/

    protected ImageView userImageView;

    protected TextView placeNameTextView;

    protected Button seeInfoButton;

    protected Button openMapsButton;

    protected Button audioGuideButton;

    protected TextView wikipediaInformationTextView;

    protected Button readMoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        LOG_TAG = ResultActivity.this.getClass().getSimpleName();

        try{
            Log.i(LOG_TAG, "Creating Result Activity... Retrieving elements from the caller activity...");

            //TODO Unify the way in which the action bar is manipulated across all activities
            /*** Change background color and text color of the Action Bar ***/
            android.support.v7.app.ActionBar actionBar = getSupportActionBar();
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.rome)));
            actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Minerva</font>"));

            /*** Get information retrieved from previous activity and fetch the result interface ***/
            Intent intent = getIntent();

            // *** Getting and displaying landmark name ***
            placeName = intent.getStringExtra("PLACE_NAME");
            placeNameTextView = (TextView) findViewById(R.id.placeNameTextView);
            placeNameTextView.setText(placeName);

            // *** Getting landmark coordinates ***
            Double latitude = intent.getDoubleExtra("PLACE_LATITUDE", 0.0);
            Double longitude = intent.getDoubleExtra("PLACE_LONGITUDE", 0.0);
            placeCoordinates = new Pair<>(latitude, longitude);

            // *** Displaying user image on the result activity ***
            userImageView = (ImageView) findViewById(R.id.placeUserImageView);
            String userImageURI = intent.getStringExtra("USER_IMAGE_URI");
            if(userImageURI != null){
                Uri uri = Uri.parse(userImageURI);
                userImageView.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), uri));
            }else{
                Log.w(LOG_TAG, "Image URI came null from the caller activity. Please check...");
            }

            wikipediaInformationTextView = (TextView) findViewById(R.id.wikipediaInformationTextView);
            callWikipediaService();

            audioGuideButton = (Button) findViewById(R.id.audioGuideButton);
            //TODO Implement audio-guides feature

            seeInfoButton = (Button) findViewById(R.id.seeBasicInfoButton);
            //TODO Implement basic utility info section

            openMapsButton = (Button) findViewById(R.id.seePlaceOnMapButton);
            openMapsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // *** Open system-predefined Maps application ***
                    Double latitude = placeCoordinates.first;
                    Double longitude = placeCoordinates.second;
                    Intent intent = new Intent(ResultActivity.this, MapsActivity.class);
                    intent.putExtra("PLACE_LATITUDE", latitude);
                    intent.putExtra("PLACE_LONGITUDE", longitude);
                    intent.putExtra("PLACE_NAME", placeName);
                    startActivity(intent);
                }
            });

            readMoreButton = (Button) findViewById(R.id.readmoreButton);
            readMoreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // *** Access to Wikipedia full article
                    Intent intent = new Intent(ResultActivity.this, FullWikipediaArticleActivity.class);
                    intent.putExtra("PLACE_NAME", placeName);
                    startActivity(intent);
                }
            });

            Log.i(LOG_TAG, "Result Activity configured successfully...");

        }catch(Exception e) {
            Log.e(LOG_TAG, "There has been an error creating the Result Activity. Check stack-trace for details...", e);
            //TODO Handle behavior when onCreate() method on activity fails
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void callWikipediaService(){
        new WikipediaServiceHandler(placeName){
            @Override
            public void onMessageReceived(String wikipediaMessage) {
                // *** Displaying Wikipedia article extract whenever the message is received ***
                wikipediaInformation = wikipediaMessage;

                if(wikipediaInformation != null) {
                    wikipediaInformationTextView.setText(wikipediaInformation);
                    Log.i(ResultActivity.this.LOG_TAG, "Article extract displayed successfully...");
                }else {
                    wikipediaInformationTextView.setText(getString(R.string.error_retrieving_wikipedia_extract));
                    wikipediaInformationTextView.setTypeface(null, Typeface.BOLD_ITALIC);
                    readMoreButton.setVisibility(View.INVISIBLE);
                    Log.i(ResultActivity.this.LOG_TAG, "Article extract could not be displayed...");
                }
            }
        }.callService();
    }
}
