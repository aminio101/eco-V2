package com.eco.interfaces;

import android.widget.Button;

import com.eco.entitys.FavoriteAddressEntity;
import com.eco.entitys.LocationEntity;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

public interface IMapPresenter extends IBasePresenter{
    void getFavoriteLocation();

    void editLocation(FavoriteAddressEntity locationEntity);

    void addLocation( FavoriteAddressEntity favoriteAddressEntity);

    void sendLocation(Button buttonNextStep, FavoriteAddressEntity favoriteAddressEntity );

    void checkData(String toString, GoogleMap mMap);
}
