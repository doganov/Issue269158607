# Issue 269158607

This example project is adapted from [Aron Yu](https://github.com/aronyu79)'s
https://github.com/aronyu79/Issue263307511.

To reproduce Issue [269158607](https://issuetracker.google.com/issues/269158607):


# Scenario 1

1) Make sure `MainActivity.TRIGGER` is set to `IssueTrigger.ON_CREATE`
2) Make sure `BasicGLSurfaceView.WORKAROUND` is set to `false`
3) Start the application
4) Try using the Back button or the back gesture.

You'll likely notice that the MainActivity can't be properly displayed on the
screen and the user input is ignored.  Although the application is not
responding, and the MainActivity is frozen forever, the main thread is working
fine under the hood.

To get an ANR popup, try it on an Emulator or a device where you can actually
press the Back button (instead of using the back gesture).

See scenario_1.webm for demo.


# Scenario 2

1) Make sure `MainActivity.TRIGGER` is set to `IssueTrigger.ON_CLICK`
2) Make sure `BasicGLSurfaceView.WORKAROUND` is set to `false`
3) Start the application
4) Tap somewhere on the screen
5) Background the app
6) Bring back the app to the foreground
7) Try using the Back button or the back gesture.

You'll notice that the MainActivity is frozen on some older frame and the user
input is ignored.  Although the application is not responding, and the
MainActivity is frozen forever, the main thread is working fine under the hood.

To get an ANR popup, try it on an Emulator or a device where you can actually
press the Back button (instead of using the back gesture).

See scenario_2.webm for a demo.


# Workaround

Those scenarios are NOT reproducible when `BasicGLSurfaceView.WORKAROUND` is set
to `true.
