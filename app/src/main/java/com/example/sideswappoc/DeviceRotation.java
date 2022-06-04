package com.example.sideswappoc;

import android.util.Log;

public class DeviceRotation {

    private static final String TAG = "SideSwapPoc.DeviceRotation";

    private float initialRotation = Float.NaN;
    private float rotation;

    private static float angleDiff(float a, float b) {
        return ((((b-a) % 360F) + 540F) % 360F) - 180F;
    }

    public float getRotation() {
        if (Float.isNaN(initialRotation))
            return 0f;
        return angleDiff(initialRotation, rotation);
    }

    public void setRotation(float rotation) {

        if (Float.isNaN(initialRotation))
            this.initialRotation = rotation;

        this.rotation = rotation;
    }

    public void reset() {
        Log.i(TAG, "reset");
        initialRotation = Float.NaN;
    }
}
