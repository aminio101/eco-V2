package com.eco.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.core.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eco.CoustomTextView;
import com.eco.PV;
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
    NestedScrollView root;
    @BindView(R.id.linearBottom)
    LinearLayout linearBottom;
    @BindView(R.id.linearTop)
    LinearLayout linearTop;
    @BindView(R.id.textView4)
    CoustomTextView textViewNumber;

    ArrayList<MainListViewHolder> viewHolders;
    @BindView(R.id.main_fragment_text_score)
    CoustomTextView textViewScore; @BindView(R.id.textView5)
    CoustomTextView textViewPrice;
    @BindView(R.id.textView2)
    CoustomTextView name;


    @OnClick(R.id.button6)public void loadWallet(){
        ((MainActivity) getActivity()).loadFragmentWallet();
    }
    boolean isSelect = false;
    ArrayList<RubbishEntity> rubbishEntityArrayList;
    int position;
    int checkBag=0;
    @OnClick(R.id.imageView19)public void add(){
        if (checkSelectItem()||isSelect) {
            String s = String.valueOf(viewHolders.get(position).addNum());
            textViewNumber.setText(s);
            checkBag++;
            if (textViewNumber.getText().toString().equals("0"))
                isSelect = false;
        }
        else
            Toast.makeText(getContext(),"لطفا یک پسماند را ابتدا انتخاب کنید",Toast.LENGTH_LONG).show();
    }
    @OnClick(R.id.imageView20)public void mines(){
        if (checkSelectItem()||isSelect) {
            textViewNumber.setText(String.valueOf(viewHolders.get(position).mines()));

            if (textViewNumber.getText().toString().equals("0"))
                isSelect = false;
            else checkBag--;

        }else
            Toast.makeText(getContext(),"لطفا یک پسماند را ابتدا انتخاب کنید",Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.nextFragment)
    public void nextFragment() {// todo
//        if((! textViewNumber.getText().toString().equals("0")) && (isSelect) )
        if(checkBag >0)
        {
             presenter.save(viewHolders);
        }
        else{
            Toast.makeText(getContext(),"لطفا یک پسماند را ابتدا انتخاب کنید",Toast.LENGTH_LONG).show();
        }
    }
    public boolean checkSelectItem(){
        boolean haveItem = false;
        for (int i=0;i<viewHolders.size();i++){
            if (viewHolders.get(i).num>0) {
                haveItem = true;
                break;
            }
        }
        return haveItem;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_fragment, container, false);
        hideKeyboard();
        ButterKnife.bind(this,view);
        init();
        checkBag=0;
        presenter.setFCM();
        return view;
    }

    private void init() {
        PV.list.clear();
        ((MainActivity) getActivity()).setTollbarName(" سامانه هوشمند اکو");
        isSelect = false;
        presenter = new MainFragmentPresenter(this,getContext(),progressBar,root,getActivity());
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
            textViewPrice.setText("قیمت پایه هر کیلوگرم "+rubbishEntity.price+" ریال");
            textViewNumber.setText(String.valueOf(viewHolders.get(i).num));
            position = i;
            isSelect = true;

        }
    };
    void addItem() {
        for (int i = 0; i < viewHolders.size(); i++) {
            viewHolders.get(i).itemView.setLayoutParams(new LinearLayout.LayoutParams(
                    (int) getResources().getDimension(R.dimen._100sdp),
                    (int) getResources().getDimension(R.dimen._100sdp)));
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

    @Override
    public void loadMapFragment() {
        ((MainActivity) getActivity()).loadMapFragment();
    }

    @Override
    public void showUserScore(String score) {
        textViewScore.setText(score+" امتیاز ");
    }

    @Override
    public void rGetUser() {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getUser();
            }
        });
    }

    @Override
    public void rSendFCM() {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setFCM();
            }
        });
    }

    void hideKeyboard(){
        View view1 = getActivity().getCurrentFocus();
        if (view1 != null){
            InputMethodManager imm = (InputMethodManager)getActivity(). getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);
        }
    }
}
