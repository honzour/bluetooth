package cz.honza.bluetooth;

public class AudioThread extends Thread {

	protected volatile boolean mShouldStop = false;
	
	public void requestStop()
	{
		mShouldStop = true;
	}
	
	@Override
	public void run() {
		while (!mShouldStop)
		{
			
		}
	}

}
