package com.ncs;

class FlagList {
	private Flag first;

	public FlagList(int a, int b, int c, int d) {
		first = null;
	}

	public boolean isEmpty() {
		return (first == null);
	}

	public void insertFirst(int a, int b, int c, int d) {
		Flag newFlag = new Flag(a, b, c, d);
		newFlag.next = first;
		first = newFlag;
	}

	public Flag deleteFirst() {
		Flag temp = first;
		first = first.next;
		return temp;
	}
}
