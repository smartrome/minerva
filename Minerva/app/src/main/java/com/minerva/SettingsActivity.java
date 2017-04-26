package com.minerva;

import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.Window;

public class SettingsActivity extends AppCompatActivity {

    /*** Logging tag ***/

    protected String LOG_TAG;

    /*** General settings attributes ***/

    // Place here other variables to be use by this Activity

    @Override
    protected void onCreate(Bundle savedInstanceState){
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        LOG_TAG = SettingsActivity.this.getClass().getSimpleName();

        /*** Change background color and text color of the Action Bar ***/
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.rome)));
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Settings</font>"));

        Log.i(LOG_TAG, "Accessing to the Settings Activity...");
    }

    /*** The Settings Activity has within its layout file an instance of the SettingsFragment class,
     * in which are placed all the preferences for the application (language, photo auto-save, etc) ***/
    public static class SettingsFragment extends PreferenceFragment{
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // *** Load the preferences from an XML resource ***
            addPreferencesFromResource(R.xml.preferences);

            // *** Set default value of the language ***
            // TODO Set value of the language for the first time that the application runs according to the user locale
        }

    }
}
