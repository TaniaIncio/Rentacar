package com.tincio.rentacar.presentation.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.tincio.rentacar.R;
import com.tincio.rentacar.presentation.app.RentacarApplication;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.linear_opc_piura)
    LinearLayout linearPiura;
    @Bind(R.id.linear_opc_talara)
    LinearLayout linearTalara;
    @Bind(R.id.linear_opc_tumbes)
    LinearLayout linearTumbes;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    //apply firebase
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        preferences= getSharedPreferences("preferences", MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        linearPiura.setOnClickListener(this);
        linearTalara.setOnClickListener(this);
        linearTumbes.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        editor = preferences.edit();
        if(v==linearPiura){
            editor.putString("sucursal","Piura");
            RentacarApplication.getApplication().setSucursal("Piura");
        }else{
            if(v==linearTalara){
                editor.putString("sucursal","Talara");
                RentacarApplication.getApplication().setSucursal("Talara");
            }else {
                editor.putString("sucursal", "Tumbes");
                RentacarApplication.getApplication().setSucursal("Tumbes");
            }
        }
        editor.commit();
        Intent intent=null;
        intent = new Intent(this, NavigationMenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        exit();
    }

    public void exit(){
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        }else{
            ActivityCompat.finishAffinity(this);
        }
    }
}
