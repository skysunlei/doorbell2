package com.crg.doorbell.getFileData;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sky on 2017/6/8.
 */

public class GetFileData {

    private long time,time1,time2;
    private List<String> mData,Data;
    private  String PICTURE_PATH = "/sdcard/doorbellphoto/";
    private String lastPath;
    public List<String> GetFileData() {

        mData = new ArrayList<String>();
        Data = new ArrayList<String>();
        File file = new File(PICTURE_PATH);
        File[] listFile = file.listFiles(new Find());
        for (File f : listFile){
            time = f.lastModified();
            mData.add(f.getName());
            System.out.println(f.getName()+">>>>>>>>>>>>>>>>>>>>");
            File file1 = new File(f.getAbsolutePath());
            File[] files = file1.listFiles(new PngFile());
            time2 = 0;
            System.out.println(files.length+"<<<<<<<<<<<<<<<<<<<");
            for (File file2 : files){
                System.out.println(file2.getName()+">>>>>>>>>>>>>>>>>>>");
                time1 = file2.lastModified();
                if (time1>time2){
                    time2 = time1;
                    lastPath = file2.getName();

                }
            }
            System.out.println(lastPath+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");



        }
        return mData;

    }


//		for(File f : listFile){


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
