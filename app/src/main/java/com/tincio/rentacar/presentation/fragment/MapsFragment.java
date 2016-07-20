package com.tincio.rentacar.presentation.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tincio.rentacar.R;
import android.support.v4.app.FragmentManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapsFragment extends Fragment {


  //  MapView mMapView;
    private GoogleMap googleMap;
    public static String TAG ="MapsFragment";
    private FirebaseAnalytics mFirebaseAnalytics;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_map, container, false);
        } catch (InflateException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics =  FirebaseAnalytics.getInstance(getActivity());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentManager fmanager = getActivity().getSupportFragmentManager();
        Fragment fragment = getChildFragmentManager().findFragmentById(R.id.map);
        SupportMapFragment supportmapfragment = (SupportMapFragment)fragment;
        googleMap = supportmapfragment.getMap();


     /*   googleMap = ((MapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map)).getMap();*/
        // latitude and longitude
        if(checkPlayServices()) {

            double latitude = -5.171733;
            double longitude = -80.631626;

            // create marker

            MarkerOptions marker1 = new MarkerOptions().position(
                    new LatLng(latitude, longitude)).title("Rent a car Piura")
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_car));
            latitude = -5.171750;
            longitude = -80.631636;
            MarkerOptions marker2 = new MarkerOptions().position(
                    new LatLng(latitude, longitude)).title("Rent a car Talara")
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_car));
            latitude = -3.556044;
            longitude = -80.428213;
            //-3.556044, -80.428213
            MarkerOptions marker3 = new MarkerOptions().position(
                    new LatLng(latitude, longitude)).title("Rent a car Tumbes")
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_car));
            // adding marker
            googleMap.addMarker(marker1);
            googleMap.addMarker(marker2);
            googleMap.addMarker(marker3);
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(-4.530499, -80.546973)).zoom(8).build();
            googleMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));
        }
    }

    private final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(getActivity());
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(getActivity(), resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
                //  ctx.finish();
            }else {
                Log.i("Error", "This device is not supported.");
               // getActivity().finish();
            }
            return false;
        }
        return true;
    }
}
