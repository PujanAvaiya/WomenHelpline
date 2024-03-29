package com.example.resque;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.widget.Toast;

import com.google.android.gms.location.LocationResult;

public class Background extends BroadcastReceiver {
    public Background() {
    }
    public static final String ACTION_PROCESS_UPDATE="com.example.resque.UPDATE_LOCATION";
    String lattitude,longitude;

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent!=null)
        {
            final String action=intent.getAction();
            if(ACTION_PROCESS_UPDATE.equals(action))
            {
                LocationResult result=LocationResult.extractResult(intent);
                if(result!=null)
                {
                    Location location=result.getLastLocation();
                    String location_string =new StringBuilder("Lattitude "+location.getLatitude())
                            .append("Longitude")
                            .append(location.getLongitude())
                            .toString();
                    try
                    {
                        Track.getInstance().updatetextview(location_string);
                    }
                    catch(Exception e)
                    {
                        Toast.makeText(context,location_string,Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }


}
