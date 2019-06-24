package com.eco.fragments;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.eco.PV;
import com.eco.R;
import com.eco.entitys.LocationEntity;
import com.eco.entitys.UserNumberEntity;
import com.eco.interfaces.ITimeFragmentView;
import com.eco.presenters.TimeFragmentPresenter;
import com.eco.views.DialogConnection;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimeFragment extends Fragment implements ITimeFragmentView {
    View view;
    @BindView(R.id.progress_circular)
    ProgressBar progressBar;
    @BindView(R.id.root)
    ScrollView root;
    TimeFragmentPresenter presenter;
    @BindView(R.id.textView9)
    TextView textViewRequestNumber;
    @BindView(R.id.videoViewProgressBar)
    ProgressBar videoViewProgressBar;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.videoView)
    VideoView videoView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.time_fragment, container, false);
        init();
        presenter.getRequestNumber(PV.locationEntity);
        return view;
    }

    private void init() {
        ButterKnife.bind(this, view);
        presenter = new TimeFragmentPresenter(this, getContext(), progressBar, root);
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
                if (i == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                    videoViewProgressBar.setVisibility(View.GONE);
                }
                return false;
            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

    }

    @Override
    public void showRequestNumber(ArrayList<UserNumberEntity> result) {
        textViewRequestNumber.setText("در شعاع 100 متری شما برای " + result.get(0).countRequest + " نفر درخواست مشابه ای از طریق سامانه اکو ثبت شده است");
        presenter.getAdvertising();
    }

    @Override
    public void rGetRequestNumber(final LocationEntity locationEntity) {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getRequestNumber(locationEntity);
            }
        });
    }

    @Override
    public void showVideo(String url) {
        Uri uri = Uri.parse(url);
        MediaController mediaController = new MediaController(getContext(), false);
        mediaController.setAnchorView(videoView);
        videoView.setVideoURI(uri);
        videoViewProgressBar.setVisibility(View.VISIBLE);
        videoView.start();
        imageView.setVisibility(View.INVISIBLE);
        videoView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showGif(String link) {
        Glide.with(this)
                .load(link)
                .into(imageView);
        videoViewProgressBar.setVisibility(View.INVISIBLE);
        videoView.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showGif(int s) {
        Glide.with(this)
                .load(s)
                .into(imageView);
    }

    @Override
    public void rGetAdvertising() {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getAdvertising();
            }
        });
    }
}
