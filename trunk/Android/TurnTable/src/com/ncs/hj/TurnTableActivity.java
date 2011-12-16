package com.ncs.hj;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class TurnTableActivity extends Activity {
    /** Called when the activity is first created. */
	Button btn = null;
	ImageView img = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btn = (Button)findViewById(R.id.button1);
        img = (ImageView)findViewById(R.id.imageView1);
        
        btn.setOnClickListener(new TurnRound());
    }
    
    class TurnRound implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			AnimationSet animationSet = new AnimationSet(true);
			
			Animation animationAlpha = AnimationUtils.loadAnimation(TurnTableActivity.this,R.anim.alpha);
			Animation animationRotate = AnimationUtils.loadAnimation(TurnTableActivity.this,R.anim.rotate);
			
			//animationAlpha.setInterpolator(new AccelerateDecelerateInterpolator());
			animationRotate.setInterpolator(new AccelerateDecelerateInterpolator());
			
			animationSet.addAnimation(animationAlpha);
			animationSet.addAnimation(animationRotate);
			animationSet.setStartOffset(500);
			animationSet.setDuration(5000);
			
			animationSet.setAnimationListener(new MyAnimationListener());
			img.startAnimation(animationSet);
		}
		
		class MyAnimationListener implements AnimationListener {

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				System.out.println("OKOKOKOKOK");
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
		}
    }
}