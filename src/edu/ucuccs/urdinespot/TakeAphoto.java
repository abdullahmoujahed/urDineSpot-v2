package edu.ucuccs.urdinespot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

public class TakeAphoto extends Activity {

	Camera localCamera;
	FrameLayout fraLayout;
	ShowCamera show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_take_aphoto);
		localCamera = getInstanceCamera();
		show = new ShowCamera(this, localCamera);
		fraLayout = (FrameLayout) findViewById(R.id.fraLayout);
		fraLayout.addView(show);
	}

	private PictureCallback callback = new PictureCallback() {
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			// isave ang picture gamit ang FileOutputStream
			File f = new File(Environment.getExternalStorageDirectory(),
					"sample.jpg");
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(f);
				fos.write(data);
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};

	// pag pipindutin ang volume key down
	public boolean onKeyDown(int keycode, KeyEvent e) {
		if (KeyEvent.KEYCODE_VOLUME_DOWN == keycode) {
			localCamera.takePicture(shutterCallback, null, callback);
		}
		return true;
	}

	// tawagin ang shutter para magpatunog ng click sound
	private final ShutterCallback shutterCallback = new ShutterCallback() {
		public void onShutter() {
			AudioManager mgr = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
			mgr.playSoundEffect(AudioManager.FLAG_PLAY_SOUND);
		}
	};

	// button para magtake ng picture
	public void clickTake(View v) {
		localCamera.takePicture(shutterCallback, null, callback);
	}

	// iopen ang camera
	public static Camera getInstanceCamera() {
		Camera c = null;
		try {
			c = Camera.open();
		} catch (Exception e) {

		}
		return c;
	}
}
