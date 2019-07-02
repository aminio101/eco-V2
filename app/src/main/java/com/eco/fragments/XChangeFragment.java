package com.eco.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.eco.R;
import com.eco.adapter.XChangeAdapter;
import com.eco.entitys.ListeEntity;
import com.eco.entitys.XChangeEntity;
import com.eco.interfaces.IXChangePresenter;
import com.eco.interfaces.IXChangeView;
import com.eco.presenters.XChangePresenter;
import com.eco.views.DialogConnection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XChangeFragment extends Fragment implements IXChangeView {
    View view;
    @BindView(R.id.root)
    NestedScrollView root;
    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    XChangeAdapter adapter;
    IXChangePresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.x_change_fragment, container, false);
        ButterKnife.bind(this, view);
        init();
        presenter.getList(1);
        return view;
    }

    @Override
    public void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    private void init() {
        adapter = new XChangeAdapter(getContext());
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL,false));
        presenter = new XChangePresenter(this, getContext(), progressBar, root);
        list.setNestedScrollingEnabled(false);
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
    }
}
