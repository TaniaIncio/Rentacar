package com.tincio.rentacar.presentation.presenter;

import com.tincio.rentacar.data.callback.MenuOpcionCallback;
import com.tincio.rentacar.data.interactor.MenuOpcionInteractor;
import com.tincio.rentacar.data.model.OpcionMenu;
import com.tincio.rentacar.presentation.view.MenuView;

import java.util.List;

/**
 * Created by juan on 25/05/2016.
 */
public class MenuOpcionPresenter implements MvpPresenter<MenuView>, MenuOpcionCallback{

    MenuView menuView;
    MenuOpcionInteractor interactor;

    public MenuOpcionPresenter(){
        interactor = new MenuOpcionInteractor(this);
    }

    public void getListOpcionMenu(){
        interactor.getListOpcionMenu();
    }

    @Override
    public void onResponse(List<OpcionMenu> listMenu) {
        menuView.showListOpcionMenu(listMenu);
    }
    @Override
    public void setView(MenuView view) {
        menuView = view;
    }

    @Override
    public void detachView() {
        menuView = null;
    }
}
