package filemanager.video;

import android.hardware.Camera;
import android.media.MediaRecorder;
import android.view.SurfaceHolder;

public interface CameraCallback {
	public String[] recordStart(SurfaceHolder mHolder,MediaRecorder recorder);
	public void recordEnd(String s , Camera mCamera,SurfaceHolder mHolder,MediaRecorder recorder);
}
