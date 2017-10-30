package tree.utils;

public class Statistics {
	
	private long mTime = 0;
	private long mIteration = 0;
	
	public void startTimer() {
		mTime = System.currentTimeMillis();
	}
	
	public void endTimer() {
		mTime = System.currentTimeMillis() - mTime;
	}
	
	public void incrementIteration() {
		mIteration++;
	}
	
	public long getTime() {
		return mTime;
	}
	
	public long getNumberOfIterations() {
		return mIteration;
	}
}
