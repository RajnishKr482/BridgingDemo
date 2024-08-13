package com.bridgingdemo;

import android.app.Activity;
import android.app.DirectAction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Promise promise;
    private Uri photoURI;
    CameraModule (ReactApplicationContext context){
        super(context);
    }
    @NonNull
    @Override
    public String getName() {
        return "CameraModule";
    }

    @ReactMethod
    public void openCamera(Promise promise) {
        Activity currentActivity = getCurrentActivity();

        if (currentActivity == null) {
            promise.reject("E_NO_ACTIVITY", "No activity");
            return;
        }

        this.promise = promise;

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getReactApplicationContext().getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                promise.reject("E_IO_EXCEPTION", ex.getMessage());
                return;
            }


                currentActivity.startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

        } else {
            promise.reject("E_NO_CAMERA", "No camera available");
        }
    }

    @Override
    public void onActivityResult(Activity activity,  int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (resultCode == Activity.RESULT_OK) {

                Bitmap photo = (Bitmap) data.getExtras().get("data");


                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                String encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

                WritableMap resultMap = Arguments.createMap();
                resultMap.putString("uri", encodedImage);

                Log.d("encoded", encodedImage );
                promise.resolve(resultMap);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                promise.reject("E_USER_CANCELED", "User canceled");
            } else {
                promise.reject("E_UNKNOWN", "Unknown error");
            }
        }
    }
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getReactApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }
    @Override
    public void onNewIntent(Intent intent) {

    }
}
