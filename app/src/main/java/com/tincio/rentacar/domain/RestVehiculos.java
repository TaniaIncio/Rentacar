package com.tincio.rentacar.domain;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.tincio.rentacar.domain.model.ResponseVehiculo;
import com.tincio.rentacar.domain.model.Vehiculo;
import com.tincio.rentacar.presentation.app.RentacarApplication;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import retrofit.Response;

/**
 * Created by juan on 26/05/2016.
 */
public class RestVehiculos {

    public List<Vehiculo> getVehiculos(Context context){
        List<Vehiculo> listVehiculos=null;
        try{
            AssetManager aManager = context.getAssets();
            InputStream input= (RentacarApplication.getApplication().getSucursal().equals("Piura")?
                    aManager.open("vehiculos.json"):RentacarApplication.getApplication().getSucursal().equals("Tumbes")?
                    aManager.open("vehiculos_tumbes.json"):aManager.open("vehiculos_talara.json"));
            JsonReader jsonReader = new JsonReader(new InputStreamReader(input,"iso-8859-1"));
            listVehiculos = new ArrayList<>();
            Gson gson = new Gson();
            ResponseVehiculo response = gson.fromJson(jsonReader, ResponseVehiculo.class);
            if(response.getTotalObjects()>0){
                listVehiculos=  response.getData();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listVehiculos;
    }
}
