package filemanager.video;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by sky on 2017/6/8.
 */

    public class VideoParameter {
    private String pathName;
    private String picturePath;
    private String videoNumber;
    private long lastTime;




    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getVideoNumber() {
        return videoNumber;
    }

    public void setVideoNumber(String videoNumber) {
        this.videoNumber = videoNumber;
    }
}
