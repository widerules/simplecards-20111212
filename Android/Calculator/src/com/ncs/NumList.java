package com.ncs;

class NumList {
	private Num firstNum;

	public NumList() {
		firstNum = null;
	}

	public boolean isEmpty() {
		return (firstNum == null);
	}

	public void insertFirst(String s1) {
		Num newNum = new Num(s1);
		newNum.next = firstNum;
		firstNum = newNum;
	}

	public Num deleteFirst() {
		Num temp = firstNum;
		firstNum = firstNum.next;
		return temp;
	}
}