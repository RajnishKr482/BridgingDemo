package com.bridgingdemo;

import android.app.Activity;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class BrightnessModule extends ReactContextBaseJavaModule {
    private  final ReactApplicationContext reactApplicationContext;

    public BrightnessModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactApplicationContext = reactApplicationContext;
    }

    @NonNull
    @Override
    public String getName() {
        return "BrightnessModule";
    }

    @ReactMethod
    public void setBrightness (float brightness, Promise promise){
        Activity activity = getCurrentActivity();
      try {
          if (activity !=null){
              Window window = activity.getWindow();
              WindowManager.LayoutParams layoutParams = window.getAttributes();
              layoutParams.screenBrightness= brightness;
              window.setAttributes(layoutParams);
              promise.resolve(true);
          } else {
              promise.reject("Activity is null");
          }
      } catch ( Exception e){
          promise.reject("Error setting brightness",e);
      }
    }

    @ReactMethod
    public  void getBrightness (Promise promise){
        try {
            int brightness = Settings.System.getInt(reactApplicationContext.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
            promise.resolve(brightness/255.0f);

        } catch ( Settings.SettingNotFoundException e){
            promise.reject("Error getting brightness");
        }
    }
}
