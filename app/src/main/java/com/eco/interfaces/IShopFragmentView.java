package com.eco.interfaces;

import com.eco.entitys.ProductListEntity;
import com.eco.entitys.StoreCategoryListEntity;

public interface IShopFragmentView {
    void showNullCategory();

    void showCategory(StoreCategoryListEntity result);

    void rGetCategory();

    void showProductList(ProductListEntity result);

    void rGetProductList(int id);
}
