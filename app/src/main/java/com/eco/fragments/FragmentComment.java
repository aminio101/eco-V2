package com.eco.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eco.PV;
import com.eco.R;
import com.eco.activityes.MainActivity;
import com.eco.adapter.CommentAdapter;
import com.eco.entitys.DriverEntity;
import com.eco.entitys.ItemEntity;
import com.eco.interfaces.IFragmentRateDriverPresenter;
import com.eco.interfaces.IFragmentRateDriverView;
import com.eco.presenters.FragmentRateDriverPresenter;
import com.eco.views.DialogConnection;
import com.j256.ormlite.stmt.query.In;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentComment extends Fragment implements IFragmentRateDriverView {
    View view;
    IFragmentRateDriverPresenter presenter;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.root)
    NestedScrollView root;
    @BindView(R.id.image)
    ImageView profile;
    @BindView(R.id.textView_name)
    TextView name;
    int id;
    @BindView(R.id.list)
    RecyclerView list;

    DriverEntity driverEntity;
    @BindView(R.id.rat)
    RatingBar ratingBar;
    CommentAdapter commentAdapter;

    @OnClick(R.id.button_submit)
    public void onClick() {
        assert driverEntity != null;
        presenter.sendComment(ratingBar.getRating(), driverEntity.id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rate_fragment, container, false);
        hideKeyboard();
        ButterKnife.bind(this, view);
        init();
        presenter.getList(id);
        return view;
    }

    private void init() {
        commentAdapter = new CommentAdapter(getContext());
        list.setNestedScrollingEnabled(false);
        list.setAdapter(commentAdapter);
        list.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        assert getArguments() != null;
        id = Integer.valueOf(getArguments().getString("number"));
        presenter = new FragmentRateDriverPresenter(this, getContext(), progress, root);
    }

    @Override
    public void showList(DriverEntity result, ArrayList<ItemEntity> items) {
        name.setText(result.name + " " + result.family);
        commentAdapter.addItem(items);
        this.driverEntity = result;
        assert result.thumbpic != null;
        Glide.with(getContext()).load(PV.getImage(result.thumbpic)).into(profile);
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
    public void rSendComment(float rating, int driverId) {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendComment(rating, driverId);
            }
        });
    }

    @Override
    public void success() {
        ((MainActivity) getActivity()).loadMainFragment();

    }

    void hideKeyboard(){
        View view1 = getActivity().getCurrentFocus();
        if (view1 != null){
            InputMethodManager imm = (InputMethodManager)getActivity(). getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);
        }
    }
}
