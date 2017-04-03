package com.example.vlad.tintagcars;


import android.app.Application;

import dagger.component.DaggerNetComponent;
import dagger.component.NetComponent;
import dagger.module.AppModule;
import dagger.module.NetModule;

/**
 * Created by Vlad on 3/30/2017.
 */

public class App extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("http://terraconnect.northeurope.cloudapp.azure.com:8080"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}