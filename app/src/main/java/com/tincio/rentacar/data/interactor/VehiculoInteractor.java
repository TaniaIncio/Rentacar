package com.tincio.rentacar.data.interactor;

import android.content.Context;

import com.tincio.rentacar.data.callback.VehiculoCallback;
import com.tincio.rentacar.domain.RestVehiculos;

/**
 * Created by juan on 26/05/2016.
 */
public class VehiculoInteractor {

    VehiculoCallback callback;
    Context context;

    public VehiculoInteractor(VehiculoCallback callback, Context context){
        this.callback = callback;
        this.context = context;
    }

    public void getListVehiculo(){
        RestVehiculos rest = new RestVehiculos();
        callback.onResponseVehiculo(rest.getVehiculos(context));
    }
}
