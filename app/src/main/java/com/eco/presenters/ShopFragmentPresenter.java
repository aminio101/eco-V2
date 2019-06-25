package com.eco.presenters;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.eco.entitys.ErrorEntity;
import com.eco.entitys.ProductListEntity;
import com.eco.entitys.StoreCategoryListEntity;
import com.eco.interfaces.IShopFragmentView;
import com.eco.interfaces.IShopPresenter;
import com.eco.rest.IRemoteCallback;
import com.eco.rest.MethodApi;

import java.util.ArrayList;

public class ShopFragmentPresenter extends BasePresenter<IShopFragmentView> implements IShopPresenter {
    public ShopFragmentPresenter(IShopFragmentView view, Context context, ProgressBar progressBars, ArrayList<View> views) {
        super(view, context, progressBars, views);
    }

    @Override
    public void getCategory() {
        startProgress();
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
                if (isViewAvailable()) showMsg(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if (isViewAvailable()) {
                    stopProgress();
                    if (!answer) {
                        mView.get().rGetCategory();
                    }
                }
            }
        });
    }

    @Override
    public void getProduct(final int id) {
        startProgress();
        MethodApi.getInstance().getStoreItems(String.valueOf(id), new IRemoteCallback<ProductListEntity>() {
            @Override
            public void onResponse(Boolean answer) {

            }

            @Override
            public void onSuccess(ProductListEntity result) {
                if (isViewAvailable()){
                    if(result.data==null)
                        mView.get().showNullProductList();
                    else if (result.data.size()==0)
                        mView.get().showNullProductList();
                    else
                        mView.get().showProductList(result);
                }
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                if (isViewAvailable()) showMsg(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if (isViewAvailable()) {
                    if (!connection)
                        mView.get().rGetProductList(id);
                    stopProgress();
                }
            }
        });
    }

    @Override
    public void detach() {
        detachView();
    }
}
