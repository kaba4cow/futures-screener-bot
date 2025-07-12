package com.kaba4cow.futuresscreenerbot.domain.barseries;

import java.util.LinkedList;

import org.json.JSONArray;

public class BarSeries {

	private final LinkedList<Bar> bars;

	private final int maxBarCount;

	public BarSeries(int maxBarCount) {
		this.bars = new LinkedList<>();
		this.maxBarCount = maxBarCount;
	}

	public BarSeries(JSONArray jsonArray) {
		this.bars = new LinkedList<>();
		this.maxBarCount = jsonArray.length();
		for (int i = 0; i < jsonArray.length(); i++)
			addBar(new Bar(jsonArray.getJSONArray(i)));
	}

	public boolean addBar(Bar newBar) {
		if (isEmpty()) {
			bars.add(newBar);
			return true;
		} else {
			Bar lastBar = getLast();
			if (newBar.getOpenTime().isEqual(lastBar.getOpenTime())) {
				bars.removeLast();
				bars.addLast(newBar);
			} else if (newBar.getOpenTime().isAfter(lastBar.getOpenTime())) {
				bars.addLast(newBar);
				if (getBarCount() > maxBarCount)
					bars.removeFirst();
				return true;
			}
		}
		return false;
	}

	public Bar getBar(int index) {
		return bars.get(index);
	}

	public Bar getLast() {
		return bars.getLast();
	}

	public Bar getFirst() {
		return bars.getFirst();
	}

	public int getLastIndex() {
		return getBarCount() - 1;
	}

	public int getBarCount() {
		return bars.size();
	}

	public int getMaxBarCount() {
		return maxBarCount;
	}

	public boolean isEmpty() {
		return bars.isEmpty();
	}

}
