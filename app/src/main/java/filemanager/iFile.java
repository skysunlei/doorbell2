package filemanager;

import java.io.File;


public class iFile extends File {
	private boolean bLock = false;

	public iFile(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}

	public iFile(String path,boolean lock) {
		super(path);
		bLock = lock;
	}

	//锁定办法  先用 File Writable属性，不可写就认为锁定的，可写认为非锁定； 还要注意T卡是否对该属性有效，包括用其他工具对文件操作后属性是否还有效
	//如果写属性不可靠，使用数据库维护，对文件操作时同步更新数据库状态
	public void setLock(boolean lock){
		bLock = lock;
	}

	public boolean isLock(){
		return bLock;
	}

	public boolean delete(){
		if( bLock )
			return false;
		super.delete();
		return true;
	}
}
