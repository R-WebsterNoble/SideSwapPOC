package com.example.sideswappoc;


import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Seker on 2/23/2016.
 */
public class MyGlSurfaceView extends GLSurfaceView {

    private static final String TAG = "SideSwapPoc.myGlSurfaceView";

    private final DeviceRotation deviceRotation;

    public MyGlSurfaceView(Context context, DeviceRotation deviceRotation) {
        super(context);
        // Create an OpenGL ES 3.0 context.
        setEGLContextClientVersion(3);

        super.setEGLConfigChooser(8, 8, 8, 8, 16, 0);

        // Set the Renderer for drawing on the GLSurfaceView
        this.deviceRotation = deviceRotation;
        setRenderer(new MyRenderer(this.deviceRotation));

        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {

        Log.i(TAG, "onTouchEvent");

        deviceRotation.reset();
        return true;
    }

}
