package com.crg.doorbell.main.ui;




import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.crg.doorbell.R;

import filemanager.video.VideoManager;

import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class CameraPreview extends ActionBarActivity implements SurfaceHolder.Callback,OnClickListener{

	private Camera mCamera;

	private SurfaceView mPreview;

	private SurfaceHolder mHolder;

	private File dir;

	private MediaRecorder recorder;

	private File myRecAudioFile;

	private VideoManager videoManager;

	private String[] getpath;

	private Button startTranscribe,stopTranscribe;

	private boolean isRecording = false;

	private PictureCallback mPictureCallback = new PictureCallback() {

		@Override
		public void onPictureTaken(byte[] arg0, Camera arg1) {
			// TODO Auto-generated method stub
			Time t = new Time();
			t.setToNow();
			int year = t.year;
			int month = t.month+1;
			int date = t.monthDay;
			int hour = t.hour; // 0-23  
			int minute = t.minute;
			int second = t.second;
			String time = year+"-"+month+"-"+date;
			String specificTime = year+"-"+month+"-"+date+"-"+hour+":"+minute+":"+second;
			File sd=Environment.getExternalStorageDirectory();
			String path=sd.getPath()+"/doorbellphoto/"+time;   //在sd卡上创建文件夹
			File file=new File(path);
			if(!file.exists())
				file.mkdir();
			File tempFile = new File("/sdcard/doorbellphoto/"+time+"/"+specificTime+".png");

			try {
				FileOutputStream fos = new FileOutputStream(tempFile);
				fos.write(arg0);
				fos.close();
				mCamera.startPreview();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_camera_preview);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		startTranscribe = (Button) findViewById(R.id.startTranscribe);
		stopTranscribe = (Button) findViewById(R.id.stopTranscribe);
		startTranscribe.setOnClickListener(this);
		stopTranscribe.setOnClickListener(this);
		mPreview = (SurfaceView) findViewById(R.id.surfaceView);
		mHolder = mPreview.getHolder();
		mHolder.addCallback(this);
		recorder = new MediaRecorder();
		stopTranscribe.setEnabled(false);


	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (mCamera == null) {
			mCamera = getCamera();
			if (mHolder != null) {
				setStartPreview(mCamera, mHolder);

			}

		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		releaseCamera();
	}

	public void capture(View view){

		Camera.Parameters parameters = mCamera.getParameters();
		parameters.setPictureFormat(ImageFormat.JPEG);
		parameters.setPreviewSize(800, 400);
		parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
		/**
		 * 按下按钮后相机自动对焦，对焦完成后自动拍照
		 */
		mCamera.autoFocus(new Camera.AutoFocusCallback() {

			@Override
			public void onAutoFocus(boolean arg0, Camera arg1) {
				// TODO Auto-generated method stub
				if (arg0) {
					mCamera.takePicture(null, null, mPictureCallback);
				}
			}
		});

	}


	/**
	 * 获取camera对象
	 * @return
	 */
	private Camera getCamera(){
		Camera camera;
		try {
			camera = Camera.open();
		} catch (Exception e) {
			// TODO: handle exception
			camera = null;
		}

		return camera;

	}

	private void setStartPreview(Camera camera,SurfaceHolder holder){
		try {
			camera.setPreviewDisplay(holder);
			camera.startPreview();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * 释放相机资源
	 */
	private void releaseCamera(){
		if (mCamera != null) {
			mCamera.setPreviewCallback(null);
			mCamera.stopPreview();
			mCamera.release();
			mCamera = null;
		}


	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		mCamera.stopPreview();
		setStartPreview(mCamera, mHolder);
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		setStartPreview(mCamera, mHolder);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		releaseCamera();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
			case R.id.startTranscribe:
				startTranscribe.setEnabled(false);
				getpath  = 	new VideoManager().recordStart(mHolder,recorder);
				stopTranscribe.setEnabled(true);
				isRecording = true;
				break;

			case R.id.stopTranscribe:
				if (isRecording) {
					stopTranscribe.setEnabled(false);
					new VideoManager().recordEnd(getpath[0],mCamera, mHolder,recorder);
					recorder = new MediaRecorder();
					Bitmap bitmap  = new VideoManager().getVideoFirstFrame(getpath[0]);
					new VideoManager().savePNG_After(bitmap, getpath[1],getpath[2]);
					startTranscribe.setEnabled(true);
					isRecording = false;
				}
				break;

			default:
				break;
		}

	}


}
