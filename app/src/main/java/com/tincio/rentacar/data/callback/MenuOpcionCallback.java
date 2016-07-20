package com.tincio.rentacar.data.callback;

import com.tincio.rentacar.data.model.OpcionMenu;

import java.util.List;

/**
 * Created by juan on 25/05/2016.
 */
public interface MenuOpcionCallback {
    public void onResponse(List<OpcionMenu> listMenu);
}
