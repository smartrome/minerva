package com.minerva;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
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

    protected ImageView saveImageButton;

    /*** This flag let us know if we come either from the gallery or the camera ***/

    protected Boolean fromCamera;

    /*** User Image URI and bitmap ***/

    protected String userImageURI;

    protected Bitmap bitmap;

    protected Boolean imageAlreadySaved;

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

            /*** Setting image already saved flag for avoiding multiple saves ***/
            imageAlreadySaved = false;

            fromCamera = intent.getBooleanExtra("FROM_CAMERA", true);

            // *** Displaying user image on the result activity ***
            userImageView = (ImageView) findViewById(R.id.placeUserImageView);
            userImageURI = intent.getStringExtra("USER_IMAGE_URI");
            if(userImageURI != null){
                Uri uri = Uri.parse(userImageURI);
                //bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                userImageView.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), uri));

                // *** Save image on-long click for the case in which we come from the camera activity ***
                if(fromCamera) {
                    userImageView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            saveImage();
                            return true;
                        }
                    });
                }
            }else{
                Log.w(LOG_TAG, "Image URI came null from the caller activity. Please check...");
            }

            // *** Provide Save Image functionality only if we come from the Camera Activity ***
            saveImageButton = (ImageView) findViewById(R.id.savePictureButton);
            if(fromCamera){
                // *** Allow save function of image ***
                saveImageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saveImage();
                    }
                });

            }else{
                // *** Hide save image button ***
                saveImageButton.setVisibility(View.INVISIBLE);
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

            // *** if automatic image saving enabled, automatically save the image when we come from the camera activity***
            if(fromCamera) {
                final String autoSavePreferenceKey = "pref_photo_autosave";
                final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Boolean autosave = preferences.getBoolean(autoSavePreferenceKey, false);
                Log.i(LOG_TAG, "Auto image saving option: " + String.valueOf(autosave));
                if (autosave) {
                    saveImage();
                }
            }

            // *** Displaying welcoming message to the user ***
            showActivityWelcomeMessage();

            Log.i(LOG_TAG, "Result Activity configured & launched successfully... :-)");

        }catch(Exception e) {
            Log.e(LOG_TAG, "There has been an error creating the Result Activity. Check stack-trace for details...", e);
            //TODO Handle behavior when onCreate() method on activity fails
        }
    }

    protected void showActivityWelcomeMessage(){

        String welcomeMessage = fromCamera ? getString(R.string.message_landmark_welcome_camera, placeName) : getString(R.string.message_landmark_welcome_gallery, placeName);

        Toast welcomeMessageToast = Toast.makeText(this, welcomeMessage, Toast.LENGTH_LONG);
        ViewGroup group = (ViewGroup) welcomeMessageToast.getView();
        TextView messageTextView = (TextView) group.getChildAt(0);
        messageTextView.setTextSize(20);
        welcomeMessageToast.setGravity(Gravity.CENTER, 0, 20);
        welcomeMessageToast.show();

        // *** Show indication for saving image to the user for the case in which we come from the camera activity ***
        if (fromCamera && imageAlreadySaved == false) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // *** Show indication to the user that s/he can save the image ***
                    Toast saveImageToast = Toast.makeText(ResultActivity.this, getString(R.string.message_save_image_indication), Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) saveImageToast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(20);
                    saveImageToast.setGravity(Gravity.TOP, 0, 20);
                    saveImageToast.show();
                }
            }, 3500);
        }
    }

    protected void saveImage(){

        if(imageAlreadySaved == false) {

            try {
                Log.i(LOG_TAG, "Saving image...");

                // *** Create file on the images Directory ***

                File imagesDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Minerva");

                if (!imagesDirectory.exists() && !imagesDirectory.mkdirs()) {
                    Log.e(LOG_TAG, "Cannot create directory to save image...");
                    return;
                }

                String imageIdentifier = String.valueOf(new Date().getTime());
                String imageFilename = "picture_" + imageIdentifier + ".jpeg";

                String completeFilename = imagesDirectory.getPath() + File.separator + imageFilename;

                Log.i(LOG_TAG, "Attempting to save image at: " + completeFilename);

                File pictureFile = new File(completeFilename);

                // *** Copy image from temporary file ***

                OutputStream outputStream = new FileOutputStream(pictureFile);

                MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(userImageURI)).compress(Bitmap.CompressFormat.JPEG, 85, outputStream);

                outputStream.close();

                // *** Reference image on the gallery ***

                ContentValues values = new ContentValues();

                values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                values.put(MediaStore.MediaColumns.DATA, completeFilename);

                getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                // *** Delete temporary file ***
                //TODO Delete temporary file

                Log.i(LOG_TAG, "Image saved...");

                Toast.makeText(this, "Your image has been saved", Toast.LENGTH_SHORT).show();

                imageAlreadySaved = true;

            } catch (Exception e) {
                Toast.makeText(this, "Oops!... Image could not be automatically saved...", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }else{
            Log.i(LOG_TAG, "Image has been already saved...");
            Toast.makeText(this, "We already have saved your image!", Toast.LENGTH_LONG).show();
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
