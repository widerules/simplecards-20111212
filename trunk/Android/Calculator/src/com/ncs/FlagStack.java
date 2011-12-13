package com.ncs;

class FlagStack {
	private FlagList theFlagList;
	private int count;

	public FlagStack() {
		theFlagList = new FlagList(0, 0, 0, 0);
		count = 0;
	}

	public void push(int a, int b, int c, int d) {
		theFlagList.insertFirst(a, b, c, d);
		count++;
	}

	public Flag pop() {
		count = count - 1;
		return theFlagList.deleteFirst();
	}

	public boolean isEmpty() {
		return (theFlagList.isEmpty());
	}

	public int count() {
		return count;
	}
}