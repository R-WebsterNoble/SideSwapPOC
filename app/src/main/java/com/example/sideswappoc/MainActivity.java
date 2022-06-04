package com.example.sideswappoc;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "SideSwapPoc.MainActivity";

    private SensorListener sensorListener;
    private SensorManager sensorManager;
    private DeviceRotation deviceRotation;
    private Sensor rotationVectorSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        deviceRotation = new DeviceRotation();

        sensorListener = new SensorListener(deviceRotation);

        MyGlSurfaceView view = new MyGlSurfaceView(this, deviceRotation);

        setContentView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i(TAG, "onResume");

        deviceRotation.reset();

        rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        sensorManager.registerListener(sensorListener, rotationVectorSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i(TAG, "onPause");

        sensorManager.unregisterListener(sensorListener);
    }
}