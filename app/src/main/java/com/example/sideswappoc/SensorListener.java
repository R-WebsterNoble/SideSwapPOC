package com.example.sideswappoc;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class SensorListener implements SensorEventListener {

    private static final String TAG = "SideSwapPoc.SensorListener";

    private final float[] rotationFloatBuffer = new float[3];

    private final DeviceRotation deviceRotation;

    public SensorListener(DeviceRotation deviceRotation) {
        this.deviceRotation = deviceRotation;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] mRotationMatrix = new float[16];
        SensorManager.getRotationMatrixFromVector(mRotationMatrix, event.values);
        SensorManager.remapCoordinateSystem(mRotationMatrix,
                        SensorManager.AXIS_X, SensorManager.AXIS_Z,
                        mRotationMatrix);
        SensorManager.getOrientation(mRotationMatrix, rotationFloatBuffer);

        float deviceAzimuthRotation = (float) Math.toDegrees(rotationFloatBuffer[0]);
        deviceRotation.setRotation(deviceAzimuthRotation);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}