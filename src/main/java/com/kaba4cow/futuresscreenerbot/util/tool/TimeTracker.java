package com.kaba4cow.futuresscreenerbot.util.tool;

public class TimeTracker {

	private final long startTimeMillis;

	private long finishTimeMillis;

	private boolean finished;

	public TimeTracker() {
		this.startTimeMillis = System.currentTimeMillis();
		this.finishTimeMillis = 0L;
		this.finished = false;
	}

	public TimeTracker finish() {
		if (finished)
			throw new IllegalStateException("Timer has already finished");
		finished = true;
		finishTimeMillis = System.currentTimeMillis();
		return this;
	}

	public long getElapsedTimeMillis() {
		return finishTimeMillis - startTimeMillis;
	}

}
