package com.tincio.rentacar.presentation.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.MapFragment;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tincio.rentacar.R;
import com.tincio.rentacar.data.model.OpcionMenu;
import com.tincio.rentacar.presentation.adapter.OpcionMenuAdapter;
import com.tincio.rentacar.presentation.fragment.ContactoFragment;
import com.tincio.rentacar.presentation.fragment.MapsFragment;
import com.tincio.rentacar.presentation.fragment.PreguntasFrecuentesFragment;
import com.tincio.rentacar.presentation.fragment.ReservacionesFragment;
import com.tincio.rentacar.presentation.fragment.ServiciosAdicionalesFragment;
import com.tincio.rentacar.presentation.fragment.VehiculoFragment;
import com.tincio.rentacar.presentation.presenter.MenuOpcionPresenter;
import com.tincio.rentacar.presentation.view.MenuView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import com.google.firebase.analytics.FirebaseAnalytics.Param;


public class NavigationMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,MenuView {

    @Bind(R.id.rcv_opc_menu)
    RecyclerView rcvMenu;
    RecyclerView.LayoutManager layoutManager;

    OpcionMenuAdapter adapterMenu;
    MenuOpcionPresenter presenter;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    SharedPreferences prefs;
    //uso de firebase
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
       /* Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);*/
        //
        setContentView(R.layout.activity_navigation_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ButterKnife.bind(this);
        presenter = new MenuOpcionPresenter();
        presenter.setView(this);
        changeFragment(new VehiculoFragment(),"NOTAG");
        prefs= getSharedPreferences("preferences", MODE_PRIVATE);
        setTitle("Rent a car "+prefs.getString("sucursal",""));
    }

    @Override
    protected void onResume() {
        super.onResume();
        try{
            layoutManager = new LinearLayoutManager(getContext());
            rcvMenu.setLayoutManager(layoutManager);
            ///llamar a opciones de menu
            presenter.getListOpcionMenu();

            adapterMenu.setOnItemClickLIstener(new OpcionMenuAdapter.OnItemClickListener() {
                @Override
                public void setOnItemClickListener(OpcionMenu opcionMenu) {
                    getCaseFragment(opcionMenu.getId());
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START);
                    }
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    void getCaseFragment(int idFragment){
        try{
            switch (idFragment){
                case 1:
                    changeFragment(new VehiculoFragment(), VehiculoFragment.TAG);
                    break;
                case 2:
                    changeFragment(new ReservacionesFragment(), ReservacionesFragment.TAG);
                    break;
                case 3:
                    changeFragment(new ServiciosAdicionalesFragment(), ServiciosAdicionalesFragment.TAG);
                    break;
                case 4:
                    changeFragment(new PreguntasFrecuentesFragment(), PreguntasFrecuentesFragment.TAG);
                    break;
                case 5:
                    changeFragment(new ContactoFragment(), ContactoFragment.TAG);
                    break;
                case 6:
                    changeFragment(new MapsFragment(), MapsFragment.TAG);
                    break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void changeFragment(Fragment fragment, String TAG){
        try{
            mFirebaseAnalytics.setUserProperty("opcionSeleccionada", TAG);
            FragmentTransaction Ft= getSupportFragmentManager().beginTransaction();
            Ft.replace(R.id.frame_base, fragment, TAG);
            if(!TAG.equals("NOTAG"))
                Ft.addToBackStack(TAG);
            Ft.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_inicio) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showListOpcionMenu(List<OpcionMenu> listOpcionMenu) {
        adapterMenu = new OpcionMenuAdapter(listOpcionMenu);
        rcvMenu.setAdapter(adapterMenu);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
