//package com.bridgingdemo;
//
//import androidx.annotation.NonNull;
//
//import com.facebook.react.ReactPackage;
//import com.facebook.react.bridge.NativeModule;
//import com.facebook.react.bridge.ReactApplicationContext;
//import com.facebook.react.uimanager.ViewManager;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class NetworkPackage implements ReactPackage {
//
//    @Override
//    public List<NativeModule> createNativeModules( ReactApplicationContext reactApplicationContext) {
//        List<NativeModule> modules = new ArrayList<>();
//        modules.add(new NetworkModule(reactApplicationContext));
//        return modules;
//    }
//
//    @NonNull
//    @Override
//    public List<ViewManager> createViewManagers(@NonNull ReactApplicationContext reactApplicationContext) {
//        return Collections.emptyList();
//    }
//}

package com.bridgingdemo;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NetworkPackage implements ReactPackage {
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();
        modules.add(new NetworkModule(reactContext));
        return modules;
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
}

