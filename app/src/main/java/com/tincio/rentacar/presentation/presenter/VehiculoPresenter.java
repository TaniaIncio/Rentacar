package com.tincio.rentacar.presentation.presenter;

import android.content.Context;

import com.tincio.rentacar.data.callback.VehiculoCallback;
import com.tincio.rentacar.data.interactor.VehiculoInteractor;
import com.tincio.rentacar.domain.model.Vehiculo;
import com.tincio.rentacar.presentation.view.VehiculoView;

import java.util.List;

/**
 * Created by juan on 26/05/2016.
 */
public class VehiculoPresenter implements MvpPresenter<VehiculoView>, VehiculoCallback {

    VehiculoView view;
    VehiculoInteractor interactor;
    Context context;
    public VehiculoPresenter (Context context){
        this.context = context;
    }
    @Override
    public void setView(VehiculoView view) {
        this.view = view;
        interactor = new VehiculoInteractor(this,context);
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public void getListVehiculos(){
        interactor.getListVehiculo();
    }

    @Override
    public void onResponseVehiculo(List<Vehiculo> listVehiculo) {
        view.showVehiculos(listVehiculo);
    }
}
