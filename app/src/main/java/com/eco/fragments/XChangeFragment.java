package com.eco.fragments;

import android.app.Dialog;
import android.opengl.Visibility;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eco.CoustomTextView;
import com.eco.R;
import com.eco.adapter.XChangeAdapter;
import com.eco.entitys.ListeEntity;
import com.eco.entitys.XChangeEntity;
import com.eco.interfaces.IXChangePresenter;
import com.eco.interfaces.IXChangeView;
import com.eco.presenters.XChangePresenter;
import com.eco.viewHolder.XChangeViewHolder;
import com.eco.views.DialogConnection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XChangeFragment extends Fragment implements IXChangeView {
    View view;
    @BindView(R.id.root)
    RelativeLayout root;
    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.textNull)
    CoustomTextView textNull;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    XChangeAdapter adapter;
    IXChangePresenter presenter;
    boolean isLoading = true;
    int visibleThreshold = 2;
    int totalItemCount, lastVisibleItem;
    int nextPage = 1;
    LinearLayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.x_change_fragment, container, false);
        ButterKnife.bind(this, view);
        init();isLoading = true;
        presenter.getList(1);
        return view;
    }

    @Override
    public void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    private void init() {
        presenter = new XChangePresenter(this, getContext(), progressBar, root);
        list.setHasFixedSize(false);
        adapter = new XChangeAdapter(getContext(), presenter);
        list.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        list.setLayoutManager(layoutManager);
        list.setNestedScrollingEnabled(false);

        list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition();
                if (
                        !isLoading &&
                        totalItemCount >= 10 && totalItemCount <= (lastVisibleItem + visibleThreshold) && nextPage != 1) {
                    isLoading = true;
                    presenter.getList(nextPage);
                }
            }
        });


    }

    @Override
    public void rGetList(int id) {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getList(id);
            }
        });
    }

    @Override
    public void showList(ListeEntity<XChangeEntity> result) {

        adapter.addItem(result);
        isLoading = false;
        if (result.data.size()==10&&nextPage!=Integer.valueOf(result.pageNumber))
            nextPage++;
        else if (nextPage == Integer.valueOf(result.pageNumber))
            nextPage = 1;

    }

    @Override
    public void rDelete(XChangeEntity xChangeEntity, XChangeViewHolder xChangeEntity1) {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.delete(xChangeEntity, xChangeEntity1);
            }
        });
    }

    @Override
    public void delete(XChangeEntity xChangeEntity) {
        adapter.removeItem(xChangeEntity);
    }

    @Override
    public void showNull() {
        textNull.setVisibility(View.VISIBLE);
        list.setVisibility(View.GONE);
    }

}
