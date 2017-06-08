package system;

import java.io.File;

import android.os.Environment;

public class iSystemContext {
	private static String AppName = "doorbell";
	
	public static String getInternalPath() {
		try {
			File fd = new File("/system/media/"+AppName);
			if (!fd.exists()) {
				fd.mkdirs();
			}
			return fd.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String getExternalPath() {
		try {
			File f = new File("/mnt/usb_storage");
			if (f.exists() == false)
				f = new File("/mnt/external_sd");
			if (f.exists() == false)
				f = new File("/mnt/sda1");
			if (f.exists() == false)
				f = Environment.getExternalStorageDirectory();
			if (f != null && f.exists()) {
				File fd = new File(f.toString() + "/" + AppName);
				if (!fd.exists()) {
					fd.mkdirs();
				}
				return fd.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
