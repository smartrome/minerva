package com.minerva;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class ResultActivity extends AppCompatActivity {

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

        /*** Change color of the Action Bar ***/
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.rome)));

        /*** Get information retrieved from previous activity and fetch the result interface ***/

        Intent intent = getIntent();

        placeName = intent.getStringExtra("PLACE_NAME"); // Place here the place name

        Double latitude = intent.getDoubleExtra("PLACE_LATITUDE", 0.0);
        Double longitude = intent.getDoubleExtra("PLACE_LONGITUDE", 0.0);
        placeCoordinates = new Pair<>(latitude, longitude); // Place here the coordinates

        userImageView = (ImageView) findViewById(R.id.placeUserImageView);
        // userImageView.setImageBitmap(); //TODO Place here the image taken by the user

        placeNameTextView = (TextView) findViewById(R.id.placeNameTextView);
        placeNameTextView.setText(placeName);

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
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f?q=%f,%f(%s)", latitude, longitude, latitude, longitude, placeName);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
                ResultActivity.this.startActivity(intent);
            }
        });

        readMoreButton = (Button) findViewById(R.id.readmoreButton);
        readMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // *** Dispatch new activity displaying Wikipedia article site ***
                Intent intent = new Intent(ResultActivity.this, FullWikipediaArticleActivity.class);
                intent.putExtra("PLACE_NAME", placeName);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void callWikipediaService(){
        new WikipediaServiceHandler(placeName){
            @Override
            public void onMessageReceived(String wikipediaMessage) {
                wikipediaInformation = wikipediaMessage;
                wikipediaInformationTextView.setText(wikipediaInformation);
            }
        }.callService();
    }

}
