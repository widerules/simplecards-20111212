package com.ncs.hj;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GuessNumberActivity extends Activity {
	/** Called when the activity is first created. */
	EditText edit1 = null;
	Button btn1 = null;
	Button btn2 = null;
	Button btnCls = null;
	TextView textShowInput = null;
	TextView textShowResult = null;
	TextView textShowRemainTimes = null;

	TextView textShowReg = null;
	TextView textShowFinalAnswer = null;

	String finalAnswer = "1234";// 正确答案
	int guessTimes = 0;// 猜数字的次数，最多10次
	boolean resultflag = false;// 是否猜对

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		finalAnswer = generateRandomDigits();

		edit1 = (EditText) findViewById(R.id.edit1);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btnCls = (Button) findViewById(R.id.btnCls);
		textShowInput = (TextView) findViewById(R.id.textShowInput);
		textShowResult = (TextView) findViewById(R.id.textShowResult);
		textShowRemainTimes = (TextView) findViewById(R.id.remainTimes);

		textShowReg = (TextView) findViewById(R.id.textShowReg);
		textShowFinalAnswer = (TextView) findViewById(R.id.textShowFinalAnswer);

		btn1.setOnClickListener(new ProcessNum());
		btn2.setOnClickListener(new ShowFinalAnswer());
		btnCls.setOnClickListener(new ExitGuess());

	}

	class ProcessNum implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
					.hideSoftInputFromWindow(GuessNumberActivity.this
							.getCurrentFocus().getWindowToken(),
							InputMethodManager.HIDE_NOT_ALWAYS);
			String result = "0A0B";
			String input = edit1.getText().toString();

			// 验证输入长度
			if (input.length() == 4) {
				if (existRepeat(input))
					dialog();
				else {
					result = processGuess(input, finalAnswer);
					textShowInput.setText(input);
					textShowResult.setText(result);
				}
			}
			else
				dialog();
			
			guessTimes++;
			edit1.setText("");
			textShowRemainTimes.setText("还有"+(10-guessTimes)+"次机会哦。");
			
			if(result.equals("4A0B")) {
				textShowRemainTimes.setText("");
				switch (guessTimes) {
					case 1:textShowReg.setText(getResources().getString(R.string.one));break;
					case 2:textShowReg.setText(getResources().getString(R.string.two));break;
					case 3:textShowReg.setText(getResources().getString(R.string.three));break;
					case 4:textShowReg.setText(getResources().getString(R.string.four));break;
					case 5:textShowReg.setText(getResources().getString(R.string.five));break;
					case 6:textShowReg.setText(getResources().getString(R.string.six));break;
					case 7:textShowReg.setText(getResources().getString(R.string.seven));break;
					case 8:textShowReg.setText(getResources().getString(R.string.eight));break;
					case 9:textShowReg.setText(getResources().getString(R.string.nine));break;
					case 10:textShowReg.setText(getResources().getString(R.string.ten));break;
				}
			}
			
			if(guessTimes >= 10 && (!result.equals("4A0B"))) {
				textShowReg.setText(getResources().getString(R.string.overten));
			}
		}
	}

	class ExitGuess implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			GuessNumberActivity.this.finish();
		}
	}

	class ShowFinalAnswer implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			textShowFinalAnswer.setText(finalAnswer);
		}
	}

	// 消息提示框，若输入不合法，则清空
	protected void dialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("必须输入四个不相同的数字！").setCancelable(false)
				.setPositiveButton("确认", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						edit1.setText("");
					}
				});
		builder.create().show();
	}

	// 根据用户输入数字的进行处理，返回结果，A代表数字对且位置对，B代表数字对位置不对
	public String processGuess(String input, String answer) {
		int a = 0, b = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (input.charAt(i) == answer.charAt(j) && i == j)
					a++;
				else if (input.charAt(i) == answer.charAt(j) && i != j)
					b++;
			}
		}
		return a + "A" + b + "B";
	}

	// 判断字符串中是否有重复字符。
	public boolean existRepeat(String str) {
		for (int i = 0; i < str.length() - 1; i++) {
			for (int j = i + 1; j < str.length(); j++) {
				if (str.charAt(i) == str.charAt(j))
					return true;
			}
		}
		return false;
	}

	// 随机产生四个数字，不可重复
	public String generateRandomDigits() {
		List<Integer> arr = new ArrayList<Integer>();
		String str = "";

		for (int i = 0; i < 10; i++) {
			arr.add(i);
		}

		for (int j = 0; j < 4; j++) {
			int a = (int) (Math.random() * arr.size());
			str += arr.remove(a).toString();
		}

		return str;
	}
}