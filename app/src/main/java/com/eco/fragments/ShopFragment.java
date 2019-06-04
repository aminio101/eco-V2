package com.eco.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.eco.adapter.CatergoryShopAdapter;
import com.eco.R;
import com.eco.entitys.StoreCategoryListEntity;
import com.eco.interfaces.IShopFragmentView;
import com.eco.interfaces.IShopPresenter;
import com.eco.presenters.ShopFragmentPresenter;
import com.eco.views.DialogConnection;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    CatergoryShopAdapter categoryAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shop_fragment, container, false);
        init();
        presenter.getCategory();
        return view;
    }

    private void init() {
        ButterKnife.bind(this, view);
        presenter = new ShopFragmentPresenter(this, getContext(), progressBar, root);
        categoryAdapter = new CatergoryShopAdapter(getContext());
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL,false));
        recyclerViewCategory.setAdapter(categoryAdapter);
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
        categoryAdapter.addItem(result.data);
    }

    @Override
    public void rGetCategory() {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getCategory();
            }
        });
    }
}
