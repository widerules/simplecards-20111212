package com.ncs.hj;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageView;

public class SiimpleCardsActivity extends Activity {
    /** Called when the activity is first created. */
	private static int[] sl= new int[]{R.drawable.a,R.drawable.b,R.drawable.c};
	Button btn1 = null;
	Button btn2 = null;
	ImageView image1 = null;
	ImageView image2 = null;
	ImageView image3 = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btn1 = (Button)findViewById(R.id.button1);
        btn2 = (Button)findViewById(R.id.button2);
        image1 = (ImageView)findViewById(R.id.image1);
        image2 = (ImageView)findViewById(R.id.image2);
        image3 = (ImageView)findViewById(R.id.image3);
        
        btn1.setOnClickListener(new btnClick());
        btn2.setOnClickListener(new btnClsClick());
        image1.setOnClickListener(new imageClick());
        image2.setOnClickListener(new imageClick());
        image3.setOnClickListener(new imageClick());
    }
    
    class btnClick implements OnClickListener {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			image1.setImageDrawable(getResources().getDrawable(R.drawable.back));
			image2.setImageDrawable(getResources().getDrawable(R.drawable.back));
			image3.setImageDrawable(getResources().getDrawable(R.drawable.back));
			image1.setAlpha(255);
			image2.setAlpha(255);
			image3.setAlpha(255);
			random();
		}
    }
    
    class btnClsClick implements OnClickListener {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
    }
    
    class imageClick implements OnClickListener {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			random();
			((ImageView)v).setAlpha(255);
			image1.setImageDrawable(getResources().getDrawable(sl[0]));
			image2.setImageDrawable(getResources().getDrawable(sl[1]));
			image3.setImageDrawable(getResources().getDrawable(sl[2]));
			
			AnimationSet animationSet = new AnimationSet(true);
			AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
			alphaAnimation.setDuration(1000);
			animationSet.addAnimation(alphaAnimation);
			
			if(image1.getId() != v.getId()) {
				image1.startAnimation(animationSet);
				image1.setAlpha(100);
			}
			if(image2.getId() != v.getId()) {
				image2.startAnimation(animationSet);
				image2.setAlpha(100);
			}
			if(image3.getId() != v.getId()) {
				image3.startAnimation(animationSet);
				image3.setAlpha(100);
			}
		}
    }
    
    private void random() {
    	for(int i = 0; i < 3; i++)
    	{
    		int temp = sl[i];
    		int s = (int)(Math.random()*2);
    		sl[i] = sl[s];
    		sl[s] = temp;
    	}
    }
}