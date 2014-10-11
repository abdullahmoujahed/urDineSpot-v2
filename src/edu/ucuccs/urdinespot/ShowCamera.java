package edu.ucuccs.urdinespot;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback {
	Camera cam;
	SurfaceHolder h;
	public ShowCamera(Context context, Camera camera) {
		super(context);
		cam =  camera;
		h = getHolder();
		h.addCallback(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try {
			cam.setPreviewDisplay(holder);
			cam.startPreview();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		cam.stopPreview();
	}

}
