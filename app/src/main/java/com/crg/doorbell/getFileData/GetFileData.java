package com.crg.doorbell.getFileData;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import filemanager.video.VideoParameter;

/**
 * Created by sky on 2017/6/8.
 */

public class GetFileData {

    private Context mContext;
    private long time,time1,time2;
    private List<String> mData,Data;
    private  String PICTURE_PATH = "/sdcard/doorbellVideo/";
    private String lastPath;
    public List<String> GetFileData(Context context) {
//        mContext = context;

        JSONArray jsonArray = new JSONArray();
        mData = new ArrayList<String>();
        Data = new ArrayList<String>();
        File file = new File(PICTURE_PATH);
        File[] listFile = file.listFiles(new Find());
        for (File f : listFile){
            time = f.lastModified();
            mData.add(f.getName());

            File file1 = new File(f.getAbsolutePath());
            File[] files = file1.listFiles(new PngFile());
            time2 = 0;
            System.out.println(files.length+"<<<<<<<<<<<<<<<<<<<");
            for (File file2 : files){
                System.out.println(file2.getName()+">>>>>>>>>>>>>>>>>>>");
                time1 = file2.lastModified();
                if (time1>time2){
                    time2 = time1;
                    lastPath = file2.getAbsolutePath();

                }
            }
            VideoParameter videoParameter = new VideoParameter();
            videoParameter.setLastTime(f.lastModified());
            videoParameter.setPathName(f.getName());
            videoParameter.setPicturePath(lastPath);
            videoParameter.setVideoNumber(files.length+"");

            Gson gson = new Gson();
            String string = gson.toJson(videoParameter);
//            System.out.println(lastPath+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
//            Toast.makeText(context,string,Toast.LENGTH_LONG).show();

            jsonArray.put(string);


        }

        JSONObject jsonObject =null;

        if (jsonObject !=null){

            Gson gson = new Gson();
            VideoParameter videoParameter  = new VideoParameter();
            videoParameter = gson.fromJson(jsonObject.toString(),VideoParameter.class);


            Toast.makeText(context,videoParameter.getPathName()+">>>>",Toast.LENGTH_LONG).show();

        }

//        Toast.makeText(context,jsonArray.toString(),Toast.LENGTH_LONG).show();


        return mData;

    }



    public class Find implements FilenameFilter{

        @Override
        public boolean accept(File arg0, String arg1) {
            // TODO Auto-generated method stub
            return arg0.isDirectory();
        }


    }
    public class PngFile implements FilenameFilter {

        @Override
        public boolean accept(File arg0, String arg1) {
            // TODO Auto-generated method stub
            return (arg1.endsWith(".png"));
        }

    }
}
