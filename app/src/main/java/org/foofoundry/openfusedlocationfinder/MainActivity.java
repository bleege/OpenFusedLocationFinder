package org.foofoundry.openfusedlocationfinder;

import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mapzen.android.lost.api.LocationServices;
import com.mapzen.android.lost.api.LostApiClient;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";

    // UI elements
    private TextView lblLocation;
    private Button btnShowLocation;
    private Button btnStartLocationUpdates;

    private Location mLastLocation;

    private LostApiClient lostApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lostApiClient = new LostApiClient.Builder(this).build();
        lostApiClient.connect();

        lblLocation = (TextView) findViewById(R.id.lblLocation);
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
        btnStartLocationUpdates = (Button) findViewById(R.id.btnLocationUpdates);

        // Show location button click listener
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                displayLocation();
            }
        });
    }


    /**
     * Method to display the location on UI
     * */
    private void displayLocation() {

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation();

        if (mLastLocation != null) {
            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();
            lblLocation.setText(latitude + ", " + longitude);
        } else {
            lblLocation.setText("(Couldn't get the location. Make sure location is enabled on the device)");
        }
    }

}
