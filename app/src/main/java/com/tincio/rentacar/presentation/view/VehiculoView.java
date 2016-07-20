package com.tincio.rentacar.presentation.view;

import android.content.Context;

import com.tincio.rentacar.domain.model.Vehiculo;

import java.util.List;

/**
 * Created by juan on 26/05/2016.
 */
public interface VehiculoView extends MvpView{


    public void showVehiculos(List<Vehiculo> listVehiculo);
    public void showReservaVehiculos();
}
