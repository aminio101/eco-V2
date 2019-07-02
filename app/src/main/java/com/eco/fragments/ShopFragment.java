package com.eco.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eco.R;
import com.eco.activityes.MainActivity;
import com.eco.adapter.CategoryShopAdapter;
import com.eco.adapter.ProductListAdapter;
import com.eco.entitys.ProductListEntity;
import com.eco.entitys.StoreCategoryListEntity;
import com.eco.interfaces.IGetShopItem;
import com.eco.interfaces.IShopFragmentView;
import com.eco.interfaces.IShopPresenter;
import com.eco.presenters.ShopFragmentPresenter;
import com.eco.views.DialogConnection;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopFragment extends Fragment implements IShopFragmentView {
    View view;
    IShopPresenter presenter;
    @BindView(R.id.progress_circular)
    ProgressBar progressBar;
    @BindView(R.id.shop_fragment_recyclerView_product)
    RecyclerView mainList;
    @BindView(R.id.textNull)
    TextView textViewNull;
    @BindView(R.id.shop_fragment_recyclerView_header)
    RecyclerView recyclerViewCategory;
    @BindView(R.id.header_card)
    CardView cardView;
    CategoryShopAdapter categoryAdapter;
    LinearLayoutManager linearLayoutManagerCategoryList;
    IGetShopItem iGetShopItem = new IGetShopItem() {
        @Override
        public void getShopItem(int id) {
            presenter.getProduct(id);
        }
    };
    ProductListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shop_fragment, container, false);
        init();
        presenter.getCategory();
        return view;
    }

    private void init() {
        ButterKnife.bind(this, view);
        ArrayList<View> viewArrayList = new ArrayList<>();
        viewArrayList.add(mainList);
        viewArrayList.add(textViewNull);
        presenter = new ShopFragmentPresenter(this, getContext(), progressBar, viewArrayList);
        categoryAdapter = new CategoryShopAdapter(getContext(), iGetShopItem);
        linearLayoutManagerCategoryList = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManagerCategoryList.setReverseLayout(true);
        recyclerViewCategory.setLayoutManager(linearLayoutManagerCategoryList);
        recyclerViewCategory.setAdapter(categoryAdapter);
        adapter = new ProductListAdapter(getContext(), sellOnclickListener);
        mainList.setAdapter(adapter);
        mainList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void showNullCategory() {
        textViewNull.setVisibility(View.VISIBLE);
        textViewNull.setText("فروشگاه خالی میباشد");
        recyclerViewCategory.setVisibility(View.GONE);
    }


    public void showCategoryList() {
        textViewNull.setVisibility(View.GONE);
        cardView.setVisibility(View.VISIBLE);
        recyclerViewCategory.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCategory(StoreCategoryListEntity result) {
        showCategoryList();

        categoryAdapter.addItem(result.data);
    }

    View.OnClickListener sellOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity) getActivity()).loadMapFragment();
        }
    };

    @Override
    public void rGetCategory() {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getCategory();
            }
        });
    }

    @Override
    public void showProductList(ProductListEntity result) {
        showProductList();
        adapter.clearItem();
        adapter.addItem(result.data);
    }

    @Override
    public void rGetProductList(final int id) {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getProduct(id);
            }
        });
    }

    public void showProductList() {
        textViewNull.setText("");
    }

    @Override
    public void showNullProductList() {
        textViewNull.setText("لیست خالی میباشد");
        adapter.clearItem();
    }
}
