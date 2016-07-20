package com.tincio.rentacar.presentation.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.tincio.rentacar.R;
import com.tincio.rentacar.data.model.OpcionMenu;
import com.tincio.rentacar.domain.model.Vehiculo;
import com.tincio.rentacar.presentation.activity.NavigationMenuActivity;
import com.tincio.rentacar.presentation.adapter.VehiculosAdapter;
import com.tincio.rentacar.presentation.app.RentacarApplication;
import com.tincio.rentacar.presentation.presenter.VehiculoPresenter;
import com.tincio.rentacar.presentation.view.VehiculoView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import com.google.firebase.analytics.FirebaseAnalytics.Param;

public class VehiculoFragment extends Fragment implements VehiculoView{

    @Bind(R.id.rcv_vehiculos)
    RecyclerView rcvVehiculos;

    VehiculoPresenter presenter;
    VehiculosAdapter adapter;
    public static String TAG ="VehiculoFragment";
    //add firebase analytics
    private FirebaseAnalytics mFirebaseAnalytics;
    public VehiculoFragment() {
        // Required empty public constructor
    }

    public static VehiculoFragment newInstance(String param1, String param2) {
        VehiculoFragment fragment = new VehiculoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics =  FirebaseAnalytics.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_vehiculo, container, false);
        ButterKnife.bind(this, view);
        presenter = new VehiculoPresenter(getActivity());
        presenter.setView(this);
        presenter.getListVehiculos();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getListVehiculos();
        showReservaVehiculos();
    }

    @Override
    public void showVehiculos(List<Vehiculo> listVehiculo) {
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getActivity());
        rcvVehiculos.setLayoutManager(layoutManager1);
        adapter = new VehiculosAdapter(listVehiculo);
        rcvVehiculos.setAdapter(adapter);
    }

    @Override
    public void showReservaVehiculos() {
        adapter.setOnItemButtonClick(new VehiculosAdapter.OnItemButtonClick() {
            @Override
            public void setOnItemButtonClick(Vehiculo itemVehiculo) {
                ReservacionesFragment fragRes = new ReservacionesFragment().newInstance(itemVehiculo.getNombre());
                ((NavigationMenuActivity) getActivity()).changeFragment(fragRes, ReservacionesFragment.TAG);
                //analytics al reservar vehiculo
                analyticsUserClickReserva(itemVehiculo);
            }
        });
    }

    private void analyticsUserClickReserva(Vehiculo itemVehiculo){
        try{
            Bundle params = new Bundle();
            params.putString( Param.ITEM_ID, String.valueOf(itemVehiculo.getId()));
            params.putString( Param.ITEM_CATEGORY, itemVehiculo.getNombre() );
            params.putString(Param.VALUE, RentacarApplication.getApplication().getSucursal());
            mFirebaseAnalytics.logEvent(Event.ADD_TO_CART, params);
            //que tipo de contenido clickea
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID,  String.valueOf(itemVehiculo.getId()));
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, itemVehiculo.getNombre());
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
