package com.ncs;

class NumStack {
	private NumList theNumList;
	private int countNum;

	public NumStack() {
		theNumList = new NumList();
		countNum = 0;
	}

	public void push(String s1) {
		theNumList.insertFirst(s1);
		countNum++;
	}

	public Num pop() {
		countNum = countNum - 1;
		return theNumList.deleteFirst();
	}

	public boolean isEmpty() {
		return (theNumList.isEmpty());
	}

	public int countNum() {
		return countNum;
	}
}
