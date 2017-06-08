package filemanager.video;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import filemanager.iFile;

import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.MediaMetadataRetriever;
import android.media.MediaRecorder;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.text.format.Time;
import android.view.SurfaceHolder;

//系统唯一对象， 只能有一个视频文件管理器
public class VideoManager implements CameraCallback {
	private String ROOT_PATH = "\\data\\doorbell\\media\\";
	//第一帧在录制结束是抓取、与视频放置在相同文件夹，文件名相同以便查找
	private ArrayList<iFile> video_list = new ArrayList<iFile>();
	private boolean bSearching = false;

	private File dir;

	private MediaRecorder recorder;

	private String getPath,pictureName;




	//开机初始化
	public void init(){
		changeRoot(true);
	}

	//内部路径： getInternalPath()+video
	//外部路径: getExternalPath+video
	//切换盘符，需要重新搜索新路径下文件
	public boolean changeRoot(boolean binternal){
		return false;
	}

	//需要抓拍图片时由此函数提供文件名      全路径名： ROOT_PATH+文件名规则
	public String getNewVideoName(){
		return "";
	}

	//根据视频文件路径查找第一帧图片，如果不存在，则抓取保存并返回
	public Bitmap getVideoFirstFrame(String videoPath){
		Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, 30);
		return bitmap;
	}


	public synchronized ArrayList<iFile> getVideoList(){
		return video_list;
	}

	//video_list不存在该文件，则加入到video_list
	public synchronized boolean addVideo(iFile file){
		return false;
	}

	//删除文件时，并且更新列表
	//需要判断文件是否锁定
	public synchronized boolean deleteVideo(iFile file){
		return false;
	}

	//线程-搜索所有视频文件
	//开机调用
	//开始搜索、结束搜索要发送广播
	public class SearchVideoThread extends Thread {
		String mPath = "";

		public SearchVideoThread(String path) {
			mPath = path;
		}

		public void run() {
			try {
				bSearching = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				bSearching = false;
			}
		}
	}
	public static Bitmap getVideoThumbnail(String videoPath) {
		MediaMetadataRetriever media =new MediaMetadataRetriever();
		media.setDataSource(videoPath);
		Bitmap bitmap = media.getFrameAtTime();
		return bitmap;
	}

	@Override
	public  String[] recordStart(SurfaceHolder mHolder,MediaRecorder recorder) {
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
		String testtime = "/sdcard/doorbellVideo/"+time+"/";
		String specificTime = "";
		String phototPath = "/sdcard/doorbellVideo/"+time+"/";
		specificTime = year+"-"+month+"-"+date+"-"+hour+minute+second;
		try {
//    		File sd = Environment.getExternalStorageDirectory();
//    		String videoPath = sd.getPath()+"doorbellVideo";
//    		File videoFile = new File(videoPath);
//    		if(!videoFile.exists()){
//    			videoFile.mkdir();
//    		}

			/**
			 * 创建doorbellVideo文件夹
			 */
			File file = null;
			file = new File("/sdcard/doorbellVideo/");
			if (!file.exists()) {
				file.mkdir();

			}
			File sd = Environment.getExternalStorageDirectory();
			String path=sd.getPath()+"/doorbellVideo/"+time;   //在sd卡上创建文件夹
			File files=new File(path);
			if(!files.exists())   {
				files.mkdir();
			}

			recorder.reset();
			recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);//视频源
			recorder.setAudioSource(MediaRecorder.AudioSource.MIC); //录音源为麦克风
			recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);//输出格式为3gp
			recorder.setVideoSize(640, 480);//视频尺寸
			recorder.setVideoEncodingBitRate(5*1024*1024);// 设置帧频率，然后就清晰了
			recorder.setVideoEncoder(MediaRecorder.VideoEncoder.H263);//视频编码
			recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);//音频编码
			recorder.setMaxDuration(10000*60*60);//录制最大时间设置
			String localpath = testtime+specificTime+".3gp";
			getPath = "/sdcard/doorbellVideo/"+time+"/"+specificTime+".3gp";

			recorder.setOutputFile(localpath);
			recorder.setPreviewDisplay(mHolder.getSurface());//预览
			recorder.prepare();
			recorder.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] s = {getPath,specificTime,phototPath};

		return s;
	}

	//抓取第一帧，文件非锁定，更新列表
	@Override
	public void recordEnd(String s ,Camera mCamera,SurfaceHolder mHolder,MediaRecorder recorder) {
		// TODO Auto-generated method stub
//		try{
//			getVideoFirstFrame(filename);
//			addVideo(null);
//		}
//		catch(Exception e){
//		}
//
//		recorder = new MediaRecorder();
		recorder.stop();
		recorder.release();
		recorder=null;
		mCamera = getCamera();
		setStartPreview(mCamera, mHolder);
		System.out.println("<<<<<<<<<<<<<<<<<"+pictureName+">>>>>>>>>>>>>>>>>>>>>>");

	}

	public void savePNG_After(Bitmap bitmap, String name,String path) {
		File file = new File(path+name + ".png");
		try {
			FileOutputStream out = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {
				out.flush();
				out.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
}
