package com.ncs;

import java.math.BigDecimal;
import java.math.RoundingMode;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CalculatorActivity extends Activity {
	/** Called when the activity is first created. */
	private EditText text;
	private EditText text1;
	private Button[] button = new Button[20];
	private String str = "";
	private int flag0 = 0; // 处理0的输出 为1则表示数字的开头有一个0
	private int flag1 = 0; // 处理0的输出 每个数开头的标志 数值为0表示为数字开头
	private int flag2 = 0; // 处理小数点
	private int flag3 = 0; // 处理运算符的输出
	private int flag4 = 0; // 处理ON/OFF问题
	private int flag5 = 0; // 处理等号问题
	private FlagStack flagstack = new FlagStack();
	private NumStack numStack = new NumStack();
	private NumStack numStack1 = new NumStack();
	private SymbolStack symbolStack = new SymbolStack();
	private SymbolStack symbolStack1 = new SymbolStack();
	private String[] fuhao = new String[4];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		fuhao[0] = "＋";
		fuhao[1] = "－";
		fuhao[2] = "×";
		fuhao[3] = "÷";
		text = (EditText) findViewById(R.id.editText);
		text1 = (EditText) findViewById(R.id.editText1);
		button[0] = (Button) findViewById(R.id.button0);
		button[1] = (Button) findViewById(R.id.button1);
		button[2] = (Button) findViewById(R.id.button2);
		button[3] = (Button) findViewById(R.id.button3);
		button[4] = (Button) findViewById(R.id.button4);
		button[5] = (Button) findViewById(R.id.button5);
		button[6] = (Button) findViewById(R.id.button6);
		button[7] = (Button) findViewById(R.id.button7);
		button[8] = (Button) findViewById(R.id.button8);
		button[9] = (Button) findViewById(R.id.button9);
		button[10] = (Button) findViewById(R.id.button10);
		button[11] = (Button) findViewById(R.id.button11);
		button[12] = (Button) findViewById(R.id.button12);
		button[13] = (Button) findViewById(R.id.button13);
		button[14] = (Button) findViewById(R.id.button14);
		button[15] = (Button) findViewById(R.id.button15);
		button[16] = (Button) findViewById(R.id.button16);
		button[17] = (Button) findViewById(R.id.button17);
		button[18] = (Button) findViewById(R.id.button18);
		button[19] = (Button) findViewById(R.id.buttonback);
		for (int i = 0; i <= 19; i++) {
			button[i].setOnClickListener(new ButtonListener());
			button[i].setEnabled(false);
		}
		button[10].setEnabled(true);
		flag4 = 1;
	}

	class ButtonListener implements OnClickListener {
		public BigDecimal calculator(NumStack numStack, SymbolStack symbolStack) {
			if (numStack.countNum() == 1)
				return new BigDecimal(numStack.pop().getNum());
			if (symbolStack.countSymbol() == 1) {
				BigDecimal num11 = new BigDecimal(numStack.pop().getNum());
				BigDecimal num22 = new BigDecimal(numStack.pop().getNum());
				Symbol symbol11 = symbolStack.pop();
				char fuhao11 = symbol11.getSymbol();
				switch (fuhao11) {
				case '＋':
					BigDecimal sum11 = num11.add(num22);
					return sum11;
				case '－':
					BigDecimal sum22 = num11.subtract(num22);
					return sum22;
				case '×':
					BigDecimal sum33 = num11.multiply(num22);
					return sum33;
				case '÷':
					BigDecimal sum44 = num11.divide(num22, 20,
							RoundingMode.HALF_UP);
					return sum44;
				}
				return new BigDecimal(0.0);
			} else {
				Symbol symbol1 = symbolStack.pop();
				Symbol symbol2 = symbolStack.pop();
				char fuhao1 = symbol1.getSymbol();
				char fuhao2 = symbol2.getSymbol();
				int symbolFlag1 = symbol1.getDegree();
				int symbolFlag2 = symbol2.getDegree();
				if (symbolFlag1 >= symbolFlag2) {
					BigDecimal num1 = new BigDecimal(numStack.pop().getNum());
					BigDecimal num2 = new BigDecimal(numStack.pop().getNum());
					switch (fuhao1) {
					case '＋':
						BigDecimal sum = num1.add(num2);
						numStack.push(sum.toString());
						break;
					case '－':
						BigDecimal sum1 = num1.subtract(num2);
						numStack.push(sum1.toString());
						break;
					case '×':
						BigDecimal sum3 = num1.multiply(num2);
						numStack.push(sum3.toString());
						break;
					case '÷':
						BigDecimal sum4 = num1.divide(num2, 20,
								RoundingMode.HALF_UP);
						numStack.push(sum4.toString());
						break;
					}
					symbolStack.push(fuhao2);
					return calculator(numStack, symbolStack);
				} else {
					BigDecimal num1 = new BigDecimal(numStack.pop().getNum());
					BigDecimal num2 = new BigDecimal(numStack.pop().getNum());
					BigDecimal num3 = new BigDecimal(numStack.pop().getNum());
					switch (fuhao2) {
					case '＋':
						BigDecimal sum = num2.add(num3);
						numStack.push(sum.toString());
						break;
					case '－':
						BigDecimal sum1 = num2.subtract(num3);
						numStack.push(sum1.toString());
						break;
					case '×':
						BigDecimal sum3 = num2.multiply(num3);
						;
						numStack.push(sum3.toString());
						break;
					case '÷':
						BigDecimal sum4 = num2.divide(num3, 20,
								RoundingMode.HALF_UP);
						numStack.push(sum4.toString());
						break;
					}
					numStack.push(num1.toString());
					symbolStack.push(fuhao1);
					return calculator(numStack, symbolStack);
				}
			}
		}

		public String outprocess(String s) {
			char[] mass = s.toCharArray();
			String s1 = "";
			String s2 = "";
			int flag = 0;
			for (int i = 0; i < mass.length; i++) {
				if (mass[i] == 46)
					flag = i;
			}
			if (flag == 0) {
				if (mass.length >= 80)
					return "error!";
				else {
					if (mass.length >= 20) {
						int i;
						s1 = s1 + mass[0] + '.';
						if (mass[14] >= 5 + '0')
							mass[13]++;
						for (i = 1; i <= 13; i++)
							s1 = s1 + mass[i];
						char[] mass1 = s1.toCharArray();
						for (i = mass1.length - 1; i >= 0; i--) {
							if (mass1[i] != '0')
								break;
						}
						if (mass1[i] == 46)
							i--;
						for (int j = 0; j <= i; j++)
							s2 = s2 + mass1[j];
						s2 = s2 + 'E' + (mass.length - 1);
						return s2;
					} else
						return s;
				}
			}
			if (flag != 0 && mass.length < 20) {
				int i;
				for (i = mass.length - 1; i >= 0; i--) {
					if (mass[i] != '0')
						break;
				}
				if (mass[i] == 46)
					i--;
				for (int j = 0; j <= i; j++)
					s1 = s1 + mass[j];
				return s1;
			}

			if (flag != 0 && mass.length >= 20) {
				if (flag >= 18)
					return "error!";
				else {
					int i;
					if (mass[19] >= 5 + '0')
						mass[18]++;
					for (i = 0; i <= 18; i++)
						s1 = s1 + mass[i];
					char[] mass1 = s1.toCharArray();
					for (i = mass1.length - 1; i >= 0; i--) {
						if (mass1[i] != '0')
							break;
					}
					if (mass1[i] == 46)
						i--;
					for (int j = 0; j <= i; j++)
						s2 = s2 + mass1[j];
					return s2;
				}
			}
			return s;
		}

		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (flag5 == 1) {
				str = "";
				text.setText(str);
				flag0 = 0;
				flag1 = 0;
				flag2 = 0;
				flag3 = 0;
				flag4 = 0;
				FlagStack flagstack1 = new FlagStack();
				flagstack = flagstack1;
				text1.setText("");
				flag5 = 0;
			}
			if ((Button) v == button[0]) {
				flag3 = 0;
				if (flag1 == 1) {
					str = str + 0;
					text.setText(str);
					flagstack.push(flag0, flag1, flag2, flag3);
				}
				if (flag0 == 0 && flag1 == 0) {
					str = str + 0;
					text.setText(str);
					flag0 = 1;
					flagstack.push(flag0, flag1, flag2, flag3);
				}
			}
			for (int i = 1; i <= 9; i++) {
				if ((Button) v == button[i]) {
					if (flag1 == 0 && flag0 == 1) {
						char[] a = str.toCharArray();
						str = "";
						for (int j = 0; j < a.length - 1; j++) {
							str = str + a[j];
						}
						if (flagstack.count() > 0)
							flagstack.pop();
					}
					str = str + i;
					flag0 = 0;
					flag1 = 1;
					text.setText(str);
					flag3 = 0;
					flagstack.push(flag0, flag1, flag2, flag3);
				}
			}
			if ((Button) v == button[10]) {
				if (flag4 == 1) {
					str = "0";
					text.setText(str);
					flag0 = 1;
					flag1 = 0;
					flag2 = 0;
					flag3 = 0;
					flag4 = 0;
					for (int i = 0; i <= 19; i++) {
						button[i].setEnabled(true);
					}
					button[10].setEnabled(false);
					FlagStack flagstack1 = new FlagStack();
					flagstack = flagstack1;
				}
			}
			if ((Button) v == button[11]) {
				str = "";
				text.setText(str);
				flag4 = 1;
				for (int i = 0; i <= 19; i++) {
					button[i].setEnabled(false);
				}
				button[10].setEnabled(true);
				text1.setText("");
			}
			for (int k = 0; k <= 3; k++) {
				if ((Button) v == button[k + 12] && str.length() != 0) {
					if (flag3 == 0) {
						str = str + fuhao[k];
						flag0 = 0;
						text.setText(str);
						flag1 = 0;
						flag2 = 0;
						flag3 = 1;
						flagstack.push(flag0, flag1, flag2, flag3);
					}
				}
			}
			if ((Button) v == button[16]) {
				if ((flag2 == 0 && flag1 == 1) || flag0 == 1) {
					flag2 = 1;
					str = str + ".";
					text.setText(str);
					flag1 = 1;
					flag0 = 0;
					flag3 = 1;
					flagstack.push(flag0, flag1, flag2, flag3);
				}
			}
			if ((Button) v == button[17]) {
				if (flag3 != 1) {
					String n = new String("");
					numStack = new NumStack();
					symbolStack = new SymbolStack();
					numStack1 = new NumStack();
					symbolStack1 = new SymbolStack();
					char[] mass = str.toCharArray();
					for (int j = 0; j < mass.length; j++) // for循环是对字符串中的数字和运算符进行提取
					{
						if (Character.isDigit(mass[j]) || mass[j] == 46)
							n = n + mass[j];
						else {
							if (n.length() != 0) {
								numStack.push(n);
								n = new String("");
							}
							symbolStack.push(mass[j]);
						}
						if (j == mass.length - 1 && n.length() != 0) {
							numStack.push(n);
							n = new String("");
						}
					}
					while (numStack.countNum() > 0)
						// 在这使用了栈，需要使用这两个循环改变计算顺序
						numStack1.push(numStack.pop().getNum());
					while (symbolStack.countSymbol() > 0)
						symbolStack1.push(symbolStack.pop().getSymbol());
					BigDecimal result = calculator(numStack1, symbolStack1);
					String resultString = result.toString();
					String s = outprocess(resultString);
					text1.setText(s);
					flag5 = 1;
				}
			}
			if ((Button) v == button[18]) {
				str = "0";
				text.setText(str);
				flag0 = 1;
				flag1 = 0;
				flag2 = 0;
				flag3 = 0;
				FlagStack flagstack1 = new FlagStack();
				flagstack = flagstack1;
				text1.setText("");
			}
			if ((Button) v == button[19]) {
				String str1 = "";
				if (flagstack.count() > 1) {
					flagstack.pop();
					Flag flagnow = flagstack.pop();
					flag0 = flagnow.a0();
					flag1 = flagnow.a1();
					flag2 = flagnow.a2();
					flag3 = flagnow.a3();
					char[] b = str.toCharArray();
					for (int i = 0; i < str.length() - 1; i++) {
						str1 = str1 + b[i];
					}
					str = str1;
					text.setText(str);
					flagstack.push(flag0, flag1, flag2, flag3);
				}

				if (flagstack.count() <= 1) {
					flag0 = 0;
					flag1 = 0;
					flag2 = 0;
					flag3 = 0;
					FlagStack flagstack1 = new FlagStack();
					flagstack = flagstack1;
				}
				str = str1;
				text.setText(str);
			}
			if (text.getText().length() >= 2) {
				Editable ea = text.getText(); // 这两句的意思是设置光标位置为字符串最后一位
				Selection.setSelection(ea, ea.length() - 1);
			}

		}
	}
}
