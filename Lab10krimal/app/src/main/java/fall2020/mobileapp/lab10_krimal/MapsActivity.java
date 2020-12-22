package fall2020.mobileapp.lab10_krimal;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String value = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        value = getIntent().getStringExtra("SOURCE");

        System.out.println(value);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        double latitude = 30;    // Lat/Long for ??
        double longitude= -30;

        List<Address> geocodeMatches = null;

        try {
            geocodeMatches =  new Geocoder(this).getFromLocationName(value, 1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (!geocodeMatches.isEmpty())
        {
            latitude = geocodeMatches.get(0).getLatitude();
            longitude = geocodeMatches.get(0).getLongitude();
        }

        // Add a marker in Sydney and move the camera
//        LatLng fairfax = new LatLng(38.8462, -77.3064);
        LatLng location = new LatLng(latitude,longitude);
        Toast.makeText(this, "Latitude: " + location.latitude + " Longitude: " + location.longitude, Toast.LENGTH_SHORT).show();

        mMap.addMarker(new MarkerOptions().position(location).title("Marker at " + value));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,14));
    }

    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent, 123);
        finish();
    }

}
