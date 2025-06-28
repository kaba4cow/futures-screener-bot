package com.kaba4cow.futuresscreenerbot.tool;

public class TimeTracker {

	private long startTimeMillis;
	private boolean started;

	private long finishTimeMillis;
	private boolean finished;

	public TimeTracker() {
		reset();
	}

	public TimeTracker reset() {
		startTimeMillis = 0L;
		started = false;
		finishTimeMillis = 0L;
		finished = false;
		return this;
	}

	public TimeTracker start() {
		if (started)
			throw new IllegalStateException("TimeTracker has already started");
		startTimeMillis = System.currentTimeMillis();
		started = true;
		return this;
	}

	public TimeTracker finish() {
		if (!started)
			throw new IllegalStateException("TimeTracker has not started yet");
		if (finished)
			throw new IllegalStateException("TimeTracker has already finished");
		finishTimeMillis = System.currentTimeMillis();
		finished = true;
		return this;
	}

	public long getDurationMillis() {
		return finishTimeMillis - startTimeMillis;
	}

}
