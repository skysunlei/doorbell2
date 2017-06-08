package com.crg.doorbell.main.ui;

import com.crg.doorbell.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuActivity extends Activity implements OnClickListener{

	private Button button1,button2,button3,button4,button5,button6,button7,button8;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				 WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_menu);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		button1 = (Button) findViewById(R.id.monitoring);
		button2 = (Button) findViewById(R.id.image);
		button3 = (Button) findViewById(R.id.networkSet);
		button4 = (Button) findViewById(R.id.QRcode);
		button5 = (Button) findViewById(R.id.visitorRecord);
		button6 = (Button) findViewById(R.id.fileManagement);
		button7 = (Button) findViewById(R.id.systemSet);
		button8 = (Button) findViewById(R.id.quitMenu);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
		button5.setOnClickListener(this);
		button6.setOnClickListener(this);
		button7.setOnClickListener(this);
		button8.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.monitoring:
			startActivity(new Intent(MenuActivity.this,MonitoringActivity.class));
			break;
		case R.id.image:
			startActivity(new Intent(MenuActivity.this,ImageActivity.class));
			break;
		case R.id.networkSet:
			startActivity(new Intent(MenuActivity.this,NetworkSetActivity.class));
			break;
		case R.id.QRcode:
			startActivity(new Intent(MenuActivity.this,QRCodeActivity.class));
			break;
		case R.id.visitorRecord:
			startActivity(new Intent(MenuActivity.this,VisitorRecordActivity.class));
			break;
		case R.id.fileManagement:
			startActivity(new Intent(MenuActivity.this,FileManagementActivity.class));
			break;
		case R.id.systemSet:
			startActivity(new Intent(MenuActivity.this,SystemSetActivity.class));
			break;
		case R.id.quitMenu:
			finish();
			break;

		default:
			break;
		}
	}


}
