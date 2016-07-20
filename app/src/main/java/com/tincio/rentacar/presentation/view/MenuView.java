package com.tincio.rentacar.presentation.view;

import com.tincio.rentacar.data.model.OpcionMenu;

import java.util.List;

/**
 * Created by juan on 25/05/2016.
 */
public interface MenuView extends MvpView{
    void showListOpcionMenu(List<OpcionMenu> listOpcionMenu);
}
