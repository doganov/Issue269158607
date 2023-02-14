package com.example.issue;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    private enum IssueTrigger {
        ON_CREATE,
        ON_CLICK,
    }

    private static final IssueTrigger TRIGGER = IssueTrigger.ON_CREATE;
    private static final long ON_SURFACE_CREATED_MIN_DURATION_MS = 300;
    private static final long OBJECT_ANIMATION_DURATION_MS = 450;

    private BasicGLSurfaceView mView;

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        initView();
        if (TRIGGER == IssueTrigger.ON_CREATE) {
            animate(mView);
        }
    }

    private void initView() {
        GLES20TriangleRenderer renderer =
                new GLES20TriangleRenderer(getApplication(), ON_SURFACE_CREATED_MIN_DURATION_MS);
        mView = new BasicGLSurfaceView(renderer);
        mView.setOnClickListener(this::onClick);
        setContentView(mView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mView.onResume();
    }

    private void onClick(View view) {
        if (TRIGGER == IssueTrigger.ON_CLICK) {
            // We need a fresh new GLSurfaceView that hasn't rendered a single frame yet
            initView();
            // ...and then animate it while it's struggles to render its first frame
            animate(mView);
        }
    }

    private static void animate(View view) {
        // Animate `view.translationX`. Not all properties cause the problem (for example, `alpha`
        // doesn't), but `translationX` and `translationY` do.
        ObjectAnimator
                .ofFloat(view, "translationX", 0, 100, -100, 50, -50, 25, -25, 0)
                .setDuration(OBJECT_ANIMATION_DURATION_MS)
                .start();
    }
}