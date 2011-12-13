package com.ncs;

class Symbol {
	private char c;
	private int degree1;

	public Symbol(char c1) {
		c = c1;
		if (c == '¡Á' || c == '¡Â')
			degree1 = 2;
		else
			degree1 = 1;
	}

	public int getDegree() {
		return degree1;
	}

	public char getSymbol() {
		return c;
	}

	public Symbol next;
}
