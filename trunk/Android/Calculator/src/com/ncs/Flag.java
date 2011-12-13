package com.ncs;

class Flag {
	private int a0;
	private int a1;
	private int a2;
	private int a3;

	public int a0() {
		return a0;
	}

	public int a1() {
		return a1;
	}

	public int a2() {
		return a2;
	}

	public int a3() {
		return a3;
	}

	public Flag(int a, int b, int c, int d) {
		a0 = a;
		a1 = b;
		a2 = c;
		a3 = d;
	}

	public Flag next;
}

