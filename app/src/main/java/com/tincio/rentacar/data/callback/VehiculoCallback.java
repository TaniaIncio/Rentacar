package com.tincio.rentacar.data.callback;

import android.content.Context;

import com.tincio.rentacar.domain.model.Vehiculo;

import java.util.List;

/**
 * Created by juan on 26/05/2016.
 */
public interface VehiculoCallback {
    public void onResponseVehiculo(List<Vehiculo> listVehiculo);
}
