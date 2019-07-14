package com.eco.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eco.CoustomTextView;
import com.eco.PV;
import com.eco.R;
import com.eco.activityes.MainActivity;
import com.eco.adapter.FinalFragmentAdapter;
import com.eco.entitys.RubbishEntity;
import com.eco.interfaces.IFinalFragmentPresenter;
import com.eco.interfaces.IFinalFragmentView;
import com.eco.presenters.FinalFragmentPresenter;
import com.eco.views.DialogConnection;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FinalFragment extends Fragment implements IFinalFragmentView {
    IFinalFragmentPresenter presenter;
    View view;
    @BindView(R.id.progress_circular)
    ProgressBar progressBar;
    @BindView(R.id.root)
    NestedScrollView root;
    @BindView(R.id.list)
    RecyclerView list;
    FinalFragmentAdapter adapter;
    @BindView(R.id.textView6)
    CoustomTextView timeText;
    @BindView(R.id.button)
    Button button;

    @OnClick(R.id.button)
    public void sendRequest() {

        if (button.getText().equals("بازگشت به منو اصلی"))
            ((MainActivity) getActivity()).loadMainFragment();
        else
            presenter.sendRequest();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.final_fragment, container, false);
        hideKeyboard();
        ButterKnife.bind(this, view);
        init();
        presenter.getList();
        return view;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.delete((RubbishEntity) v.getTag());
        }
    };

    @Override
    public void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    private void init() {
        list.setNestedScrollingEnabled(false);
        adapter = new FinalFragmentAdapter(getContext(), onClickListener);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        presenter = new FinalFragmentPresenter(this, getContext(), progressBar, root);
        Log.i("data", PV.requestEntity.toString());

    }

    @Override
    public void showList(ArrayList<RubbishEntity> list) {
        adapter.add(list);
        try {
            presenter.getTime();
        } catch (Exception er) {
            er.printStackTrace();
        }
        if (adapter.getItemCount()==0)
            button.setText("بازگشت به منو اصلی");

    }

    @Override
    public void rSendRequest() {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendRequest();
            }
        });
    }

    @Override
    public void success() {
        adapter.hideDelete();
        button.setText("بازگشت به منو اصلی");
    }

    @Override
    public void showTime(int day, int hour) {
        timeText.setText(hour + " ساعت و " + day + " روز باقی مانده است ");
    }
    void hideKeyboard(){
        View view1 = getActivity().getCurrentFocus();
        if (view1 != null){
            InputMethodManager imm = (InputMethodManager)getActivity(). getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);
        }
    }
}
