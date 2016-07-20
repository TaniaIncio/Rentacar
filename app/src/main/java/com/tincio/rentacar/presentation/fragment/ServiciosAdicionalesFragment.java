package com.tincio.rentacar.presentation.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tincio.rentacar.R;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import com.google.firebase.analytics.FirebaseAnalytics.Param;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiciosAdicionalesFragment extends Fragment {

    public static String TAG ="ServiciosAdicionalesFragment";
    private FirebaseAnalytics mFirebaseAnalytics;

    public ServiciosAdicionalesFragment() {
        // Required empty public constructor
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
        return inflater.inflate(R.layout.fragment_servicios_adicionales, container, false);
    }

}
