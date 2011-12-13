package com.ncs;

class SymbolStack {
	private SymbolList theSymbolList;
	private int countSymbol;

	public SymbolStack() {
		theSymbolList = new SymbolList();
		countSymbol = 0;
	}

	public void push(char c1) {
		theSymbolList.insertFirst(c1);
		countSymbol++;
	}

	public Symbol pop() {
		countSymbol = countSymbol - 1;
		return theSymbolList.deleteFirst();
	}

	public boolean isEmpty() {
		return (theSymbolList.isEmpty());
	}

	public int countSymbol() {
		return countSymbol;
	}
}