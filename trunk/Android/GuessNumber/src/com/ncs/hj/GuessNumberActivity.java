package com.ncs.hj;

import android.app.Activity;
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
	TextView result1 = null;
	TextView result2 = null;
	TextView result3 = null;
	TextView result4 = null;
	TextView result5 = null;
	TextView result6 = null;
	TextView result7 = null;
	TextView result8 = null;
	TextView result9 = null;
	TextView result10 = null;
	TextView textShowReg = null;
	TextView textShowResult = null;
	
	String result = "0000";//正确答案
	int guessTimes = 0;//猜数字的次数，最多10次
	boolean resultflag = false;//是否猜对
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    	edit1 = (EditText)findViewById(R.id.edit1);
    	btn1 = (Button)findViewById(R.id.btn1);
    	btn2 = (Button)findViewById(R.id.btn2);
    	btnCls = (Button)findViewById(R.id.btnCls);
    	result1 = (TextView)findViewById(R.id.result1);
    	result2 = (TextView)findViewById(R.id.result2);
    	result3 = (TextView)findViewById(R.id.result3);
    	result4 = (TextView)findViewById(R.id.result4);
    	result5 = (TextView)findViewById(R.id.result5);
    	result6 = (TextView)findViewById(R.id.result6);
    	result7 = (TextView)findViewById(R.id.result7);
    	result8 = (TextView)findViewById(R.id.result8);
    	result9 = (TextView)findViewById(R.id.result9);
    	result10 = (TextView)findViewById(R.id.result10);
    	textShowReg = (TextView)findViewById(R.id.textShowReg);
    	textShowResult = (TextView)findViewById(R.id.textShowResult);
    	
    	btn1.setOnClickListener(new ProcessNum());
    	btn2.setOnClickListener(new ShowResult());
    	btnCls.setOnClickListener(new ExitGuess());
    }
    
    class ProcessNum implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
    }
    
    class ExitGuess implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
    }
    
    class ShowResult implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//textShowResult.
		}
    }
    
    //根据用户输入数字的进行处理，返回结果，A代表数字对且位置对，B代表数字对位置不对
    public String ProcessGuess(String input) {
    	return "0A0B";
    }
    
    //验证用户输入，不可有重复的数字
    //随机产生四个数字，不可重复
}