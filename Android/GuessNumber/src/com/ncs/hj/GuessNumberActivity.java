package com.ncs.hj;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
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
	
	TextView textShowReg = null;
	TextView textShowFinalAnswer = null;

	String finalAnswer = "1234";// ��ȷ��
	int guessTimes = 0;// �����ֵĴ��������10��
	boolean resultflag = false;// �Ƿ�¶�

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
			
			String result = "0A0B";
			String input = edit1.getText().toString();
			
			// ��֤���볤��
			if (input.length() != 4)
				dialog();
			else if (existRepeat(input))
				dialog();
			result = processGuess(input,finalAnswer);
			textShowInput.setText(input);
			textShowResult.setText(result);
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

	// ��Ϣ��ʾ�������벻�Ϸ��������
	protected void dialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("���������ĸ�����ͬ�����֣�").setCancelable(false)
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						edit1.setText("");
					}
				});
		builder.create().show();
	}

	// �����û��������ֵĽ��д��������ؽ����A�������ֶ���λ�öԣ�B�������ֶ�λ�ò���
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
		return a+"A"+b+"B";
	}

	// �ж��ַ������Ƿ����ظ��ַ���
	public boolean existRepeat(String str) {
		for (int i = 0; i < str.length() - 1; i++) {
			for (int j = i + 1; j < str.length(); j++) {
				if (str.charAt(i) == str.charAt(j))
					return true;
			}
		}
		return false;
	}

	// ��������ĸ����֣������ظ�
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