package com.eco.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eco.R;
import com.eco.entitys.StoreCategoryListEntity;
import com.eco.interfaces.IShopFragmentView;
import com.eco.interfaces.IShopPresenter;
import com.eco.presenters.ShopFragmentPresenter;

import butterknife.BindView;

public class ShopFragment extends Fragment implements IShopFragmentView {
    View view;
    IShopPresenter presenter;
    @BindView(R.id.progress_circular)
    ProgressBar progressBar;
    @BindView(R.id.root)
    RelativeLayout root;
    @BindView(R.id.textNull)
    TextView textViewNull;
    @BindView(R.id.shop_fragment_recyclerView_header)
    RecyclerView recyclerViewCategory;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shop_fragment, container, false);
        init();
        presenter.getCategory();
        return view;
    }

    private void init() {
        presenter = new ShopFragmentPresenter(this, getContext(), progressBar, root);
    }

    @Override
    public void showNullCategory() {
        textViewNull.setVisibility(View.VISIBLE);
        textViewNull.setText("فروشگاه خالی میباشد");
        recyclerViewCategory.setVisibility(View.GONE);
    }
    public void showCategoryList(){
        textViewNull.setVisibility(View.GONE);
        recyclerViewCategory.setVisibility(View.VISIBLE);
    }
    @Override
    public void showCategory(StoreCategoryListEntity result) {
        showCategoryList();

    }
}
