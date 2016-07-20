package com.tincio.rentacar.presentation.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.tincio.rentacar.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactoFragment extends Fragment {

    public static String TAG ="ContactoFragment";
    private FirebaseAnalytics mFirebaseAnalytics;
    @Bind(R.id.ic_phone)
    ImageView icPhone;
    public ContactoFragment() {
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
        View view= inflater.inflate(R.layout.fragment_contacto, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        icPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "969867777"));
                    startActivity(intent);
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        });
    }
}
