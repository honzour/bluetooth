package cz.honza.bluetooth;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class BluetoothActivity extends Activity {

	protected AudioThread mThread = null;
	protected Spinner mRoute;
	protected AudioManager mAm;
	
	protected void trySetBluetooth(boolean on)
	{
		if (on)
			mAm.startBluetoothSco();
		else
			mAm.stopBluetoothSco();
	}
	
	protected void setRoute()
	{
		switch(mRoute.getSelectedItemPosition())
		{
		case 0:
			trySetBluetooth(false);
			mAm.setSpeakerphoneOn(false);
			break;
		case 1:
			trySetBluetooth(false);
			mAm.setSpeakerphoneOn(true);
			break;
		case 2:
			mAm.setSpeakerphoneOn(false);
			trySetBluetooth(true);
			break;
		}
	}
	
	protected void start()
	{
		mAm.setMode(AudioManager.MODE_IN_COMMUNICATION);
		if (mThread != null)
			mThread.requestStop();
		setRoute();
		mThread = new AudioThread();
		mThread.start();
	}
	
	protected void stop()
	{
		if (mThread != null)
			mThread.requestStop();		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bluetooth);
		findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				start();
			}
		});
		
		findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				stop();
			}
		});
		mAm = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		mRoute = (Spinner)findViewById(R.id.route);
		mRoute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				setRoute();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// NOOP
			}
		
		});
		
	}

	@Override
	protected void onDestroy() {
		stop();
		super.onDestroy();
	}

	
	

}
