package cz.honza.bluetooth;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class BluetoothActivity extends Activity {

	protected AudioThread mThread = null;
	
	protected void start()
	{
		if (mThread != null)
			mThread.requestStop();
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
		
	}

	@Override
	protected void onDestroy() {
		stop();
		super.onDestroy();
	}

	
	

}
