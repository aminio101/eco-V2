package com.eco.fragments;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eco.R;
import com.eco.activityes.MainActivity;
import com.eco.entitys.RubbishEntity;
import com.eco.interfaces.IMainFragmentPresenter;
import com.eco.interfaces.IMainFragmentView;
import com.eco.interfaces.MainAdapterListener;
import com.eco.presenters.MainFragmentPresenter;
import com.eco.viewHolder.MainListViewHolder;
import com.eco.views.DialogConnection;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends Fragment implements IMainFragmentView {
    View view;
    IMainFragmentPresenter presenter;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.linearBottom)
    LinearLayout linearBottom;
    @BindView(R.id.linearTop)
    LinearLayout linearTop;
    @BindView(R.id.textView4)TextView textViewNumber;
    ArrayList<MainListViewHolder> viewHolders;
    @BindView(R.id.textView2)
    TextView name;
    ArrayList<RubbishEntity> rubbishEntityArrayList;
    int position;
    @OnClick(R.id.imageView19)public void add(){
        textViewNumber.setText(String.valueOf(viewHolders.get(position).addNum()));
    }
    @OnClick(R.id.imageView20)public void mines(){
        textViewNumber.setText(String.valueOf(viewHolders.get(position).mines()));
    }

    @OnClick(R.id.nextFragment)
    public void nextFragment() {
        presenter.save(viewHolders);
        ((MainActivity) getActivity()).loadMapFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this,view);
        init();
        presenter.getList();
        return view;
    }

    private void init() {
        presenter = new MainFragmentPresenter(this,getContext(),progressBar,root);
        viewHolders = new ArrayList<>();
        rubbishEntityArrayList = new ArrayList<>();
    }

    @Override
    public void showList(ArrayList<RubbishEntity> result) {
        ArrayList<MainListViewHolder> mainListViewHolders = new ArrayList<>();
        for (int i=0 ; i <result.size();i++)
        {
            MainListViewHolder mainListViewHolder = new MainListViewHolder(getContext(), result.get(i), i,mainAdapterListener);
            mainListViewHolders.add(mainListViewHolder);
        }
        viewHolders.addAll(mainListViewHolders);
        addItem();
    }
    MainAdapterListener mainAdapterListener = new MainAdapterListener() {
        @Override
        public void onClick(int i, RubbishEntity rubbishEntity) {
            for (int j = 0;j<viewHolders.size();j++){
                viewHolders.get(j).isSelected  = false;
            }
            viewHolders.get(i).isSelected = true;
            viewHolders.get(i).haveNumber = true;
            for (int j = 0;j<viewHolders.size();j++){
                if (viewHolders.get(j).haveNumber && !viewHolders.get(j).isSelected)
                    viewHolders.get(j).setHaveNumber();
            }
            name.setText(rubbishEntity.type);
            textViewNumber.setText(String.valueOf(viewHolders.get(i).num));
            position = i;

        }
    };
    void addItem() {
        for (int i = 0; i < viewHolders.size(); i++) {
            viewHolders.get(i).itemView.setLayoutParams(new LinearLayout.LayoutParams(
                    (int) getResources().getDimension(R.dimen._100ssp),
                    (int) getResources().getDimension(R.dimen._100ssp)));
            if (i == 0 || i % 2 == 0)
                linearTop.addView(viewHolders.get(i).itemView);
            else
                linearBottom.addView(viewHolders.get(i).itemView);
        }
    }
    @Override
    public void rGetList() {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getList();
            }
        });
    }
}
