package com.crg.doorbell.main.ui;

import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.content.Intent;
import android.os.Bundle;
import com.crg.doorbell.R;



public class MainActivity extends ActionBarActivity {
	private ImageView mainActivityPhoto;
	private Button openMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				 WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
		// TODO Auto-generated method stub
		mainActivityPhoto = (ImageView) findViewById(R.id.main_activity_photo);
		mainActivityPhoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this,CameraPreview.class));
			}
		});
		
		openMenu = (Button) findViewById(R.id.open_menu);
		openMenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this,MenuActivity.class));
			}
		});
	}

	@Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    }
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    }
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();
    }

    
}
