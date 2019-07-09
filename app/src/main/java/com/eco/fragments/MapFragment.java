package com.eco.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.eco.R;
import com.eco.activityes.MainActivity;
import com.eco.adapter.FavoriteAddressAdapter;
import com.eco.entitys.FavoriteAddressEntity;
import com.eco.entitys.LocationEntity;
import com.eco.enums.MapAdapterMode;
import com.eco.interfaces.ICallBackFavoriteLocationDialog;
import com.eco.interfaces.ILocationClick;
import com.eco.interfaces.IMapPresenter;
import com.eco.interfaces.IMapView;
import com.eco.presenters.MapFragmentPresenter;
import com.eco.views.DialogConnection;
import com.eco.views.DialogSelectAddress;
import com.eco.views.MapUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.eco.enums.MapAdapterMode.EDIT;
import static com.eco.enums.MapAdapterMode.SELECT;

public class MapFragment extends Fragment implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        LocationListener,
        GoogleApiClient.OnConnectionFailedListener,
        IMapView {
    View view;
    private boolean locationFirst;
    private boolean mapLoad;
    FavoriteAddressEntity favoriteAddressEntity;
    LocationManager locationManager;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    LatLng latLng;
    @BindView(R.id.cardView)
    CardView cardView;
    @BindView
            (R.id.mapView)
    com.google.android.gms.maps.MapView mMapView;
    @BindView(R.id.helperView)
    View mHelperView;
    private Location userLocation;
    MapAdapterMode mapAdapterMode;
    @BindView(R.id.list)
    RecyclerView recyclerView;
    FavoriteAddressAdapter adapter;
    IMapPresenter presenter;
    @BindView(R.id.progress_circular)
    ProgressBar progressBar;
    @BindView(R.id.root)
    ConstraintLayout constraintLayout;

    @OnClick(R.id.map_fragment_button_next_step)
    public void timeFragment() {
        if (buttonNextStep.getText().toString().equals("ویرایش")) {
            DialogSelectAddress dialogSelectAddress = new DialogSelectAddress(favoriteAddressEntity, getContext(), callBackFavoriteLocationDialog);
        } else if (buttonNextStep.getText().toString().equals("اضافه کردن")) {
            DialogSelectAddress dialogSelectAddress = new DialogSelectAddress(null, getContext(), callBackFavoriteLocationDialog);
        } else {
            presenter.checkData(des.getText().toString(), mMap);
        }
    }

    ArrayList<FavoriteAddressEntity> list;
    @BindView(R.id.map_fragment_button_next_step)
    Button buttonNextStep;
    @BindView(R.id.mapFragmentRelativeMyLocation)
    ImageView imageViewMyLocation;
    @BindView(R.id.addLocation)
    ImageView imageViewAddLocation;
    @BindView(R.id.btnEditLocation)
    ImageView imageViewEditLocation;    @BindView(R.id.cancel)
    ImageView cancel;
    @BindView(R.id.mapFragmentEditTextDes)
    EditText des;
    @OnClick(R.id.cancel)public void cancel(){
        successAddLocation();
        cancel.setVisibility(View.GONE);
    }
    @OnClick(R.id.addLocation)
    public void addLocation() {
        hideView();
        buttonNextStep.setText("اضافه کردن");
        cancel.setVisibility(View.VISIBLE);
    }

    void editMode() {
        imageViewMyLocation.setVisibility(View.GONE);
        imageViewAddLocation.setVisibility(View.GONE);
        imageViewEditLocation.setVisibility(View.GONE);
    }

    @OnClick(R.id.mapFragmentRelativeMyLocation)
    public void goToMyLocation() {
        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        assert locationManager != null;
        getLastLocation();
        if (userLocation != null) {
            latLng = new LatLng(userLocation.getLongitude(), userLocation.getLatitude());
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14), 100, null);
        }
    }

    @OnClick(R.id.btnEditLocation)
    public void editLocation() {
        adapter.setMode(EDIT);
        mapAdapterMode = EDIT;
        buttonNextStep.setText("ویرایش");
        editMode();
    }

    public void showView() {
        imageViewEditLocation.setVisibility(View.VISIBLE);
        imageViewAddLocation.setVisibility(View.VISIBLE);
        imageViewMyLocation.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        cardView.setVisibility(View.VISIBLE);
    }

    public void hideView() {
        imageViewEditLocation.setVisibility(View.GONE);
        imageViewAddLocation.setVisibility(View.GONE);
        imageViewMyLocation.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        cardView.setVisibility(View.GONE);
    }

    private FusedLocationProviderClient fusedLocationClient;
    Context mContext;
    private boolean gpsCheck = true;
    Activity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.map_fragment, container, false);
        init();
        presenter.getFavoriteLocation();
        return view;
    }

    private void init() {
        list = new ArrayList<>();
        mapAdapterMode = SELECT;
        mContext = getContext();
        mActivity = getActivity();
        ButterKnife.bind(this, view);
        adapter = new FavoriteAddressAdapter(getContext(), onLocationClick);
        presenter = new MapFragmentPresenter(this, getContext(), progressBar, constraintLayout);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mapInit();
        buildGoogleApiClient();
    }


    void mapInit() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext);
        locationManager = (LocationManager) mActivity.getSystemService(Context.LOCATION_SERVICE);
        if (MapUtils.checkPlayServices(mActivity)) {
            if (mMapView != null) {
                mMapView.onCreate(null);
                mMapView.onResume();
                mMapView.getMapAsync(this);
            }
            mHelperView.setOnTouchListener(new View.OnTouchListener() {
                private float scaleFactor = 1f;

                @Override
                public boolean onTouch(final View view, final MotionEvent motionEvent) {
                    if (simpleGestureDetector.onTouchEvent(motionEvent)) { // Double tap
                        mMap.animateCamera(CameraUpdateFactory.zoomIn()); // Fixed zoom in
                    } else if (motionEvent.getPointerCount() == 1) { // Single tap
                        mMapView.dispatchTouchEvent(motionEvent); // Propagate the event to the map (Pan)
                    } else if (scaleGestureDetector.onTouchEvent(motionEvent)) { // Pinch zoom
                        mMap.moveCamera(CameraUpdateFactory.zoomBy( // Zoom the map without panning it
                                (mMap.getCameraPosition().zoom * scaleFactor
                                        - mMap.getCameraPosition().zoom) / 5));
                    }

                    return true;
                    // Consume all the gestures
                }

                // Gesture detector to manage double tap gestures
                private final GestureDetector simpleGestureDetector = new GestureDetector(
                        mActivity, new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onDoubleTap(MotionEvent e) {
                        return true;
                    }
                });

                // Gesture detector to manage scale gestures
                private final ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector(
                        mActivity, new ScaleGestureDetector.SimpleOnScaleGestureListener() {
                    @Override
                    public boolean onScale(ScaleGestureDetector detector) {
                        scaleFactor = detector.getScaleFactor();
                        return true;
                    }
                });
            });
        }
    }

    ILocationClick onLocationClick = new ILocationClick() {
        @Override
        public void onClick(FavoriteAddressEntity favoriteAddress) {
            LatLng latLng = new LatLng(favoriteAddress.getLocation().getLat(), favoriteAddress.getLocation().getLng());
            favoriteAddressEntity = favoriteAddress;
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14), 100, null);
            if (mapAdapterMode == EDIT) {
                showAddressDialog(favoriteAddressEntity);
            } else {
                des.setText(favoriteAddress.getDescription());
            }
        }
    };

    private void showAddressDialog(FavoriteAddressEntity favoriteAddressEntity) {
        if (list.size() == 5)
            Toast.makeText(getContext(), "لیست مکان های منتخب شما پر شده است ", Toast.LENGTH_LONG).show();
        else {
            buttonNextStep.setText("ویرایش");
            hideView();
        }
    }

    ICallBackFavoriteLocationDialog callBackFavoriteLocationDialog = new ICallBackFavoriteLocationDialog() {
        @Override
        public void set(String name, String des) {
            if (favoriteAddressEntity == null)
                favoriteAddressEntity = new FavoriteAddressEntity();
            favoriteAddressEntity.setName(name);
            favoriteAddressEntity.setDescription(des);
            LocationEntity locationEntity = new LocationEntity();
            LatLng latLng = mMap.getCameraPosition().target;
            locationEntity.setLat(latLng.latitude);
            locationEntity.setLng(latLng.longitude);
            favoriteAddressEntity.setLocation(locationEntity);
            presenter.sendLocation(buttonNextStep, favoriteAddressEntity);
            buttonNextStep.setText("مرحله بعد");
        }
    };

    @Override
    public void onConnected(Bundle bundle) {
        if (ContextCompat.checkSelfPermission(mActivity,
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationRequest mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(7000);
            mLocationRequest.setFastestInterval(5000);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
            builder.addLocationRequest(mLocationRequest);
            LocationSettingsRequest locationSettingsRequest = builder.build();

            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        latLng = new LatLng(location.getLatitude(), location.getLongitude());
        if (locationFirst) {
            locationFirst = false;
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15), 2000, null);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private synchronized void buildGoogleApiClient() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(mActivity)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
        }
    }

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.
                ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.
                checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient
                .getLastLocation()
                .addOnCompleteListener(mActivity, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {

                        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                            if (gpsCheck) {
                                MapUtils.showSettingsLocation(mActivity);
                                gpsCheck = false;
                                latLng = new LatLng(36.2873745, 59.6147718);
                                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14), 100, null);
                            } else if (!task.isSuccessful() && task.getResult() == null && !gpsCheck) {
                                latLng = new LatLng(36.2873745, 59.6147718);
                                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14), 100, null);
                            }
                        }
                        if (task.isSuccessful() && task.getResult() != null) {
                            userLocation = task.getResult();
                            latLng = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14), 100, null);
                        }
                    }
                });
    }

    @Override
    public void showFavoriteLocation(ArrayList<FavoriteAddressEntity> result) {
        adapter.addItem(result);
        this.list = result;
    }

    @Override
    public void rGetFavoriteLocation() {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getFavoriteLocation();
            }
        });
    }

    @Override
    public void successChangeLocation() {
        showView();
        adapter.clear();
        presenter.getFavoriteLocation();
        adapter.setMode(SELECT);
        mapAdapterMode = SELECT;
        adapter.unSelectAll();
        favoriteAddressEntity = null;
    }

    @Override
    public void rChangeLocation(FavoriteAddressEntity favoriteAddressEntity) {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.editLocation(favoriteAddressEntity);
            }
        });
    }

    @Override
    public void successAddLocation() {
        showView();
        adapter.unSelectAll();
        buttonNextStep.setText("مرحله بعد");
        favoriteAddressEntity = null;
        presenter.getFavoriteLocation();
    }

    @Override
    public void rAddLocation(FavoriteAddressEntity favoriteAddressEntity) {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addLocation(favoriteAddressEntity);
            }
        });
    }

    @Override
    public void rCheckLocation(String des, GoogleMap googleMap) {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.checkData(des, googleMap);
            }
        });
    }

    @Override
    public void successSaveLocation() {
        ((MainActivity) getActivity()).loadTimeFragment();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        buildGoogleApiClient();
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        boolean success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                        mActivity, R.raw.map_style));
        if (!success) {
//            Log.d(TAG, "Style parsing failed.");
        }
        mMap.setOnMapLoadedCallback(() -> {
            if (mMap != null) {
//                Log.d(TAG, "onMapLoaded: ");
                mapLoad = true;
                getLastLocation();
            }
        });
        if (mMap != null) {
            mMap.setOnCameraIdleListener(() -> {
                if (mMap != null) {
                    LatLng point = new LatLng(mMap.getCameraPosition().target.latitude, mMap.getCameraPosition().target.longitude);
                }

            });
        }
    }
}
