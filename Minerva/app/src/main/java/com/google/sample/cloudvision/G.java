package com.google.sample.cloudvision;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * Created by razi on 4/7/2017.
 */


public class G extends Activity {

    public static Context context;

    public static Activity currentActivity;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        context = getApplication().getApplicationContext();
    }
}
