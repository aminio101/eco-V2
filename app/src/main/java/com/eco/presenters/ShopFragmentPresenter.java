package com.eco.presenters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ProgressBar;

import com.eco.entitys.ErrorEntity;
import com.eco.entitys.StoreCategoryListEntity;
import com.eco.interfaces.IShopFragmentView;
import com.eco.interfaces.IShopPresenter;
import com.eco.rest.IRemoteCallback;
import com.eco.rest.MethodApi;

import java.util.ArrayList;

public class ShopFragmentPresenter extends BasePresenter<IShopFragmentView> implements IShopPresenter {
    public ShopFragmentPresenter(IShopFragmentView view, Context context, ProgressBar progressBars, View views) {
        super(view, context, progressBars, views);
    }

    @Override
    public void getCategory() {
        MethodApi.getInstance().getStoreCategories(new IRemoteCallback<StoreCategoryListEntity>() {
            @Override
            public void onResponse(Boolean answer) {

            }

            @Override
            public void onSuccess(StoreCategoryListEntity result) {
                if (isViewAvailable()) {
                    if (result == null)
                        mView.get().showNullCategory();
                    else if (result.data == null)
                        mView.get().showNullCategory();
                    else if (result.data.size() == 0)
                        mView.get().showNullCategory();
                    else
                        mView.get().showCategory(result);
                }
            }

            @Override
            public void onFail(ErrorEntity errorObject) {

            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {

            }
        });
    }

    @Override
    public void detach() {
        detachView();
    }
}
