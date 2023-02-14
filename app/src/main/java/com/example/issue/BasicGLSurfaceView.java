package com.example.issue;

import android.opengl.GLSurfaceView;
import android.view.SurfaceHolder;

class BasicGLSurfaceView extends GLSurfaceView {

    private static final boolean WORKAROUND_ENABLED = false;

    private final SurfaceRedrawNeededAsyncWorkaround mRedrawWorkaround =
            new SurfaceRedrawNeededAsyncWorkaround();

    public BasicGLSurfaceView(GLES20TriangleRenderer renderer) {
        super(renderer.getContext());
        setEGLContextClientVersion(2);
        setRenderer(renderer);
    }

    @Override
    public void surfaceRedrawNeededAsync(SurfaceHolder holder, Runnable finishDrawing) {
        if (WORKAROUND_ENABLED) {
            super.surfaceRedrawNeededAsync(holder, mRedrawWorkaround.register(finishDrawing));
        } else {
            super.surfaceRedrawNeededAsync(holder, finishDrawing);
        }
    }
}

