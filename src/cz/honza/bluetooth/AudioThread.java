package cz.honza.bluetooth;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

public class AudioThread extends Thread {

	protected volatile boolean mShouldStop = false;
	
	public void requestStop()
	{
		mShouldStop = true;
	}
	
	@Override
	public void run() {
		int size = AudioTrack.getMinBufferSize (8000, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_8BIT); 
		AudioTrack at = new AudioTrack(AudioManager.STREAM_MUSIC, 8000, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_8BIT, 
				size,
				AudioTrack.MODE_STREAM);
		
		byte[] data = new byte[size];
		for (int i = 0; i < size; i++)
		{
			data[i] = (byte)(Math.sin(i / 10) * 10 + 0.5);
		}

		at.play();
		while (!mShouldStop)
		{
			at.write(data, 0, data.length);
		}
	}

}
