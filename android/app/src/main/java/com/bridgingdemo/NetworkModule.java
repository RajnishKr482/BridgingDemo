package com.bridgingdemo;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.NonNull;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class NetworkModule extends ReactContextBaseJavaModule {

    public NetworkModule(ReactApplicationContext context) {
        super(context);
    }



    @Override
    public String getName() {
        return "NetworkModule";
    }

    @ReactMethod
    public void getNetworkInfo(Promise promise) {
        ConnectivityManager cm = (ConnectivityManager) getReactApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        String networkType = activeNetwork != null && activeNetwork.isConnected() ? activeNetwork.getTypeName() : "NONE";
        promise.resolve(networkType);
    }


}



//package com.bridgingdemo;
//
//import android.content.Context;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//
//import com.facebook.react.bridge.Promise;
//import com.facebook.react.bridge.ReactApplicationContext;
//import com.facebook.react.bridge.ReactContextBaseJavaModule;
//import com.facebook.react.bridge.ReactMethod;
//
//
//public class NetworkInfoModule extends ReactContextBaseJavaModule {
//
//    public NetworkInfoModule(ReactApplicationContext reactContext) {
//        super(reactContext);
//    }
//
//    @Override
//    public String getName() {
//        return "NetworkInfoModule";
//    }
//
//    @ReactMethod
//    public void getNetworkInfo(Promise promise) {
//        ConnectivityManager cm = (ConnectivityManager) getReactApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        String networkType = activeNetwork != null && activeNetwork.isConnected() ? activeNetwork.getTypeName() : "NONE";
//        promise.resolve(networkType);
//    }
//}



