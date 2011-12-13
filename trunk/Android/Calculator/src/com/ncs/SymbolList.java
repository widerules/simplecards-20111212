package com.ncs;

class SymbolList {
	private Symbol firstSymbol;

	public SymbolList() {
		firstSymbol = null;
	}

	public boolean isEmpty() {
		return (firstSymbol == null);
	}

	public void insertFirst(char c1) {
		Symbol newSymbol = new Symbol(c1);
		newSymbol.next = firstSymbol;
		firstSymbol = newSymbol;
	}

	public Symbol deleteFirst() {
		Symbol temp = firstSymbol;
		firstSymbol = firstSymbol.next;
		return temp;
	}
}
