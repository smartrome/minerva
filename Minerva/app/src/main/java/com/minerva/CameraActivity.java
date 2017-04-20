package com.minerva;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequest;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.EntityAnnotation;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;
import com.google.api.services.vision.v1.model.LocationInfo;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by razi on 4/8/2017.
 */
public class CameraActivity extends Activity {

    private Camera mCamera;
    private CameraPreview mPreview;

    private static final String CLOUD_VISION_API_KEY = "AIzaSyCZL62RLKn9_XB8EEgSURW0vy_APs4fsic";
    private static final String ANDROID_CERT_HEADER = "X-Android-Cert";
    private static final String ANDROID_PACKAGE_HEADER = "X-Android-Package";
    private static final String TAG = Command.class.getSimpleName();
    public PhotoHandler.dataPass listener;
    public Bitmap bitmap;
    public AlertDialog cameraLoadDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);



        //handling bottom navigation bar
        BottomNavigationView buttomNavigationVew = (BottomNavigationView) findViewById(R.id.navigation);
        buttomNavigationVew.getMenu().getItem(0).setChecked(false);
        buttomNavigationVew.getMenu().getItem(2).setChecked(true);

        //handling item click in bottom navbar
        buttomNavigationVew.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.action_camera:
                        break;
                    case R.id.action_gallery:
                        mCamera.release();
                        Intent galleryIntent = new Intent(CameraActivity.this , GalleryActivity.class);
                        CameraActivity.this.startActivity(galleryIntent);
                        break;
                    case R.id.action_home:
                        mCamera.release();
                        Intent intent = new Intent(CameraActivity.this , MainActivity.class);
                        CameraActivity.this.startActivity(intent);
                        finish();
                        break;
                }

                return false;
            }
        });



        // Create an instance of Camera
        mCamera = getCameraInstance();

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);


        //image button click

        ImageButton button_capture = (ImageButton) findViewById(R.id.button_capture);
        button_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener = new PhotoHandler.dataPass() {
                    @Override
                    public void onDataPass(byte[] data) {
                        uploadImage(data);
                    }
                };

                CameraActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        cameraLoadDialog = new AlertDialog.Builder(CameraActivity.this).create();
                        cameraLoadDialog.setTitle("Loading");
                        cameraLoadDialog.setMessage("Please Wait");
                        cameraLoadDialog.show();
                        cameraLoadDialog.setCancelable(false);
                        cameraLoadDialog.setCanceledOnTouchOutside(false);
                    }
                });
                mCamera.takePicture(null, null, new PhotoHandler(getApplicationContext(),listener));

            }
        });
    }

    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        } catch (Exception e) {
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    public  void uploadImage(byte[] data) {
        if (data != null) {
            try {
                // scale the image to save on bandwidth
                bitmap  = BitmapFactory.decodeByteArray(data, 0, data.length);

                bitmap =  scaleBitmapDown(bitmap,1200);

                callCloudVision(bitmap);
                // mMainImage.setImageBitmap(bitmap);

            } catch (Exception e) {
                System.out.print(e);
                Log.d(TAG, "Image picking failed because " + e.getMessage());
            }
        } else {
            Log.d(TAG, "Image picker gave us a null image.");
        }
    }

// reduce the size of the image which we want to upload
    public Bitmap scaleBitmapDown(Bitmap bitmap, int maxDimension) {

        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        int resizedWidth = maxDimension;
        int resizedHeight = maxDimension;

        if (originalHeight > originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = (int) (resizedHeight * (float) originalWidth / (float) originalHeight);
        } else if (originalWidth > originalHeight) {
            resizedWidth = maxDimension;
            resizedHeight = (int) (resizedWidth * (float) originalHeight / (float) originalWidth);
        } else if (originalHeight == originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = maxDimension;
        }
        return Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);
    }

// calling to cloud vision API
    public  void callCloudVision(final Bitmap bitmap) throws Exception {
        // Switch text to loading
        // mImageDetails.setText(R.string.loading_message);

        // Do the real work in an async task, because we need to use the network anyway
        new AsyncTask<Object, Void, String>() {
            @Override
            protected String doInBackground(Object... params) {
                try {
                    HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
                    JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

                    VisionRequestInitializer requestInitializer =
                            new VisionRequestInitializer(CLOUD_VISION_API_KEY) {
                                /**
                                 * We override this so we can inject important identifying fields into the HTTP
                                 * headers. This enables use of a restricted cloud platform API key.
                                 */
                                @Override
                                protected void initializeVisionRequest(VisionRequest<?> visionRequest)
                                        throws IOException {
                                    super.initializeVisionRequest(visionRequest);

                                    String packageName = getPackageName();
                                    visionRequest.getRequestHeaders().set(ANDROID_PACKAGE_HEADER, packageName);

                                    String sig = PackageManagerUtils.getSignature(getPackageManager(), packageName);

                                    visionRequest.getRequestHeaders().set(ANDROID_CERT_HEADER, sig);
                                }
                            };

                    Vision.Builder builder = new Vision.Builder(httpTransport, jsonFactory, null);
                    builder.setVisionRequestInitializer(requestInitializer);

                    Vision vision = builder.build();

                    BatchAnnotateImagesRequest batchAnnotateImagesRequest =
                            new BatchAnnotateImagesRequest();
                    batchAnnotateImagesRequest.setRequests(new ArrayList<AnnotateImageRequest>() {{
                        AnnotateImageRequest annotateImageRequest = new AnnotateImageRequest();

                        // Add the image
                        Image base64EncodedImage = new Image();
                        // Convert the bitmap to a JPEG
                        // Just in case it's a format that Android understands but Cloud Vision
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
                        byte[] imageBytes = byteArrayOutputStream.toByteArray();

                        // Base64 encode the JPEG
                        base64EncodedImage.encodeContent(imageBytes);
                        annotateImageRequest.setImage(base64EncodedImage);

                        // add the features we want
                        annotateImageRequest.setFeatures(new ArrayList<Feature>() {{
                            Feature labelDetection = new Feature();
                            labelDetection.setType("LANDMARK_DETECTION");
                            labelDetection.setMaxResults(10);
                            add(labelDetection);
                        }});

                        // Add the list of one thing to the request
                        add(annotateImageRequest);
                    }});

                    Vision.Images.Annotate annotateRequest =
                            vision.images().annotate(batchAnnotateImagesRequest);
                    // Due to a bug: requests to Vision API containing large images fail when GZipped.
                    annotateRequest.setDisableGZipContent(true);
                    Log.d(TAG, "created Cloud Vision request object, sending request");

                    BatchAnnotateImagesResponse response = annotateRequest.execute();
                    convertResponseToString(response);

                } catch (GoogleJsonResponseException e) {
                    Log.d(TAG, "failed to make API request because " + e.getContent());
                } catch (IOException e) {
                    Log.d(TAG, "failed to make API request because of other IOException " +
                            e.getMessage());
                }
                return "Cloud Vision API request failed. Check logs for details.";
            }

            protected void onPostExecute(String result) {
                //mImageDetails.setText(result);
            }
        }.execute();
    }

    public void convertResponseToString(BatchAnnotateImagesResponse response) {

        cameraLoadDialog.dismiss();
        List<String> placeName = new ArrayList<String>();
        List<Double> latitude = new ArrayList<Double>();
        List<Double> longitude = new ArrayList<Double>();

        //need to check the availability of data, need to get coordinates
        if(response.getResponses().get(0).getLandmarkAnnotations()!= null)
        {
            for (EntityAnnotation annotation : response.getResponses().get(0).getLandmarkAnnotations()) {
                LocationInfo info = annotation.getLocations().listIterator().next();

                if(info !=null && !(annotation.getDescription().isEmpty()))
                {
                    placeName.add(annotation.getDescription());
                    latitude.add(info.getLatLng().getLatitude());
                    longitude.add(info.getLatLng().getLongitude());
                }

                if(!(placeName.get(0).isEmpty()) && latitude.get(0)!= 0.1010 && longitude.get(0)!= 0.1010){ //TODO find sth to replace 0.1010
                    Intent intent = new Intent(CameraActivity.this, ResultActivity.class);
                    intent.putExtra("PLACE_NAME", placeName.get(0));
                    intent.putExtra("PLACE_LATITUDE", latitude.get(0));
                    intent.putExtra("PLACE_LONGITUDE", longitude.get(0));

                    //converting bitmap to byte stream
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    intent.putExtra("USER_IMAGE", byteArray);
                    finish();
                    CameraActivity.this.startActivity(intent);

                }
                else
                {
                    CameraActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            AlertDialog alertDialog = new AlertDialog.Builder(CameraActivity.this).create();
                            alertDialog.setTitle("Alert");
                            alertDialog.setMessage("Please Try Again");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            Intent intent = new Intent(CameraActivity.this , MainActivity.class);
                                            finish();
                                            startActivity(intent);
                                        }
                                    });
                            alertDialog.show();

                        }
                    });

                }

            }
        }

        else{
            CameraActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AlertDialog alertDialog = new AlertDialog.Builder(CameraActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Please Try Again");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(CameraActivity.this , MainActivity.class);
                                    finish();
                                    startActivity(intent);
                                }
                            });
                    alertDialog.show();
                }
            });

        }


    }



}
