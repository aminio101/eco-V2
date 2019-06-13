package com.eco.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.VolumeShaper;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eco.adapter.FavoriteAddressAdapter;
import com.eco.entitys.FavoriteAddressEntity;
import com.eco.interfaces.IMapPresenter;
import com.eco.interfaces.IMapView;
import com.eco.presenters.MapFragmentPresenter;
import com.eco.views.DialogConnection;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.osmdroid.config.Configuration;
import org.osmdroid.events.DelayedMapListener;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.compass.CompassOverlay;

import com.eco.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, IMapView {
    View view;
    @BindView
            (R.id.mapView)
    MapView mapView;
    FavoriteAddressAdapter adapter;
    private Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    IMapPresenter presenter;
    @BindView(R.id.progress_circular)
    ProgressBar progressBar;
    @BindView(R.id.root)
    ConstraintLayout constraintLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.map_fragment, container, false);
        init();
        presenter.getFavoriteLocation();
        return view;
    }

    private void init() {
        ButterKnife.bind(this, view);
        mapCheck();
        adapter = new FavoriteAddressAdapter(getContext(), onLocationClick);
        presenter = new MapFragmentPresenter(this, getContext(), progressBar, constraintLayout);
        setupMap();
    }

    View.OnClickListener onLocationClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FavoriteAddressEntity favoriteAddressEntity = (FavoriteAddressEntity) v.getTag();
            mapView.getController().setCenter(new GeoPoint(favoriteAddressEntity.getLocation().getLat(), mLastLocation.getLongitude()));
        }
    };

    void mapCheck() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    private void setupMap() {
        Context ctx = getActivity().getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        mapView.setClickable(true);
        mapView.setBuiltInZoomControls(true);

        mapView.getController().setZoom(15); //set initial zoom-level, depends on your need

        mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);


        CompassOverlay compassOverlay = new CompassOverlay(getContext(), mapView);
        compassOverlay.enableCompass();
        mapView.getOverlays().add(compassOverlay);

        mapView.setMapListener(new DelayedMapListener(new MapListener() {
            public boolean onZoom(final ZoomEvent e) {

                String latitudeStr = "" + mapView.getMapCenter().getLatitude();
                String longitudeStr = "" + mapView.getMapCenter().getLongitude();

                String latitudeFormattedStr = latitudeStr.substring(0, Math.min(latitudeStr.length(), 7));
                String longitudeFormattedStr = longitudeStr.substring(0, Math.min(longitudeStr.length(), 7));

                Log.i("zoom", "" + mapView.getMapCenter().getLatitude() + ", " + mapView.getMapCenter().getLongitude());
                return true;
            }

            public boolean onScroll(final ScrollEvent e) {

                String latitudeStr = "" + mapView.getMapCenter().getLatitude();
                String longitudeStr = "" + mapView.getMapCenter().getLongitude();

                String latitudeFormattedStr = latitudeStr.substring(0, Math.min(latitudeStr.length(), 7));
                String longitudeFormattedStr = longitudeStr.substring(0, Math.min(longitudeStr.length(), 7));

                Log.i("scroll", "" + mapView.getMapCenter().getLatitude() + ", " + mapView.getMapCenter().getLongitude());
                return true;
            }
        }, 1000));
    }

    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    private void setCenterInMyCurrentLocation() {
        if (mLastLocation != null) {
            mapView.getController().setCenter(new GeoPoint(mLastLocation.getLatitude(), mLastLocation.getLongitude()));
        } else {
            Toast.makeText(getContext(), "Getting current location", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void showFavoriteLocation(ArrayList<FavoriteAddressEntity> result) {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getFavoriteLocation();
            }
        });
    }

    @Override
    public void rGetFavoriteLocation() {

    }
}
