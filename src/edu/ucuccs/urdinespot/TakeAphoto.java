package edu.ucuccs.urdinespot;

import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
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
	Bitmap imageToSave;
	
	String sample;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_take_aphoto);
		localCamera = getInstanceCamera();
		show = new ShowCamera(this, localCamera);
		fraLayout = (FrameLayout) findViewById(R.id.fraLayout);
		fraLayout.addView(show);
	}


	
	// pag pipindutin ang volume key down
	public boolean onKeyDown(int keycode, KeyEvent e) {
		if (KeyEvent.KEYCODE_VOLUME_DOWN == keycode) {
			localCamera.takePicture(shutterCallback, null, null);
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
		localCamera.takePicture(shutterCallback, null, null);

		Intent take = new Intent(this, SubmitPhoto.class);
		startActivity(take);
		
		File direct = new File(Environment.getExternalStorageDirectory()
				+ "/DCIM");

		if (!direct.exists()) {
			File wallpaperDirectory = new File("/PIC");
			wallpaperDirectory.mkdirs();
		}

		File file = new File(new File("/PIC"), sample);
		if (file.exists()) {
			file.delete();
		}
		try {
			FileOutputStream out = new FileOutputStream(file);
			imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
