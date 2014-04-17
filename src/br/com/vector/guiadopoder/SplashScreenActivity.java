package br.com.vector.guiadopoder;

import com.example.guiadopoder.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends Activity {

    private static final int SPLASH_DURATION = 2000;
    private Handler myhandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		
		 myhandler = new Handler();
		 
		 myhandler.postDelayed(new Runnable()
	        {
	            @Override
	            public void run() 
	            {
	               finish();
	               Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
	               SplashScreenActivity.this.startActivity(intent);	                  
	            }
	  
	        }, SPLASH_DURATION); 
	}

}
