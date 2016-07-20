package com.tincio.rentacar.presentation.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * Created by tania on 24/02/16.
 */
//MultiDexApplication
public class RentacarApplication extends MultiDexApplication {

    public static final String networkTAG = "NetworkRequest";

    private static RentacarApplication sApplication;
    private static String sucursal;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
      //  Firebase.setAndroidContext(this);
        sApplication = this;
    }

    public static RentacarApplication getApplication(){
        try{
            return sApplication;
        }catch(Exception e){
            throw e;
        }
    }

    public static void setSucursal(String nombre){
        sucursal= nombre;
    }

    public String getSucursal(){
        return sucursal;
    }

}
