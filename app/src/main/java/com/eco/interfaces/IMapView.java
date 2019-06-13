package com.eco.interfaces;

import com.eco.entitys.FavoriteAddressEntity;

import java.util.ArrayList;

public interface IMapView {
    void showFavoriteLocation(ArrayList<FavoriteAddressEntity> result);

    void rGetFavoriteLocation();

}
