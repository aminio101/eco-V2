package com.eco.presenters;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.eco.entitys.ErrorEntity;
import com.eco.entitys.FavoriteAddressEntity;
import com.eco.interfaces.IMapPresenter;
import com.eco.interfaces.IMapView;
import com.eco.rest.IRemoteCallback;
import com.eco.rest.MethodApi;

import java.util.ArrayList;

public class MapFragmentPresenter extends BasePresenter<IMapView> implements IMapPresenter {
    public MapFragmentPresenter(IMapView view, Context context, ProgressBar progressBars, View views) {
        super(view, context, progressBars, views);
    }

    @Override
    public void detach() {
        detachView();
    }

    @Override
    public void getFavoriteLocation() {
        startProgress();
        MethodApi.getInstance().getFavoriteLocation(new IRemoteCallback<ArrayList<FavoriteAddressEntity>>() {
            @Override
            public void onResponse(Boolean answer) {

            }

            @Override
            public void onSuccess(ArrayList<FavoriteAddressEntity> result) {
                mView.get().showFavoriteLocation(result);
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                showMsg(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if (isViewAvailable()) {
                    stopProgress();
                    if (!answer)
                        mView.get().rGetFavoriteLocation();
                }
            }
        });
    }
}
