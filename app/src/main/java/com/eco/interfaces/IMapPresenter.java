package com.eco.interfaces;

import android.widget.Button;

import com.eco.entitys.FavoriteAddressEntity;
import com.eco.entitys.LocationEntity;

import java.util.ArrayList;

public interface IMapPresenter extends IBasePresenter{
    void getFavoriteLocation();

    void editLocation(FavoriteAddressEntity locationEntity);

    void addLocation( FavoriteAddressEntity favoriteAddressEntity);

    void sendLocation(Button buttonNextStep, FavoriteAddressEntity favoriteAddressEntity );
}
