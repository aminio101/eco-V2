package com.eco.interfaces;

import com.eco.entitys.FavoriteAddressEntity;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

public interface IMapView {
    void showFavoriteLocation(ArrayList<FavoriteAddressEntity> result);

    void rGetFavoriteLocation();

    void successChangeLocation();

    void rChangeLocation(FavoriteAddressEntity favoriteAddressEntity);


    void successAddLocation();

    void rAddLocation( FavoriteAddressEntity favoriteAddressEntity);

    void rCheckLocation(String des, GoogleMap googleMap);

    void successSaveLocation();

}
