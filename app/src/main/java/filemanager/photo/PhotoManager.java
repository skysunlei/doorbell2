package filemanager.photo;

import java.util.ArrayList;

import filemanager.iFile;

//系统唯一对象， 只能有一个图片文件管理器
public class PhotoManager {
	private String ROOT_PATH = "\\data\\doorbell\\media\\";
	private ArrayList<iFile> photo_list = new ArrayList<iFile>();
	private boolean bSearching = false;


	//开机初始化
	public void init(){
		changeRoot(true);
	}

	//内部路径： getInternalPath()+photo
	//外部路径: getExternalPath+photo
	//切换盘符，需要重新搜索新路径下文件
	public boolean changeRoot(boolean binternal){
		return false;
	}

	//需要抓拍图片时由此函数提供文件名      全路径名： ROOT_PATH+文件名规则
	private String getNewPhotoName(){
		return "";
	}

	public ArrayList<iFile> getPhotoList(){
		return photo_list;
	}

	//保存图片，文件非锁定，同步更新列表
	public void savePhoto(byte[] data){
		//使用getNewPhotoName
	}
	//删除文件时，并且更新列表
	//需要判断文件是否锁定
	public boolean deletePhoto(iFile file){
		return false;
	}

	//线程-搜索所有图片文件
	//开机调用
	//开始搜索、结束搜索要发送广播
	public class SearchPhotoThread extends Thread {
		String mPath = "";

		public SearchPhotoThread(String path) {
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
}
