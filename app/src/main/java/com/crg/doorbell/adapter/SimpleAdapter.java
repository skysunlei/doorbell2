package com.crg.doorbell.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.crg.doorbell.R;

import java.util.List;

/**
 * Created by sky on 2017/6/8.
 */

public class SimpleAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private LayoutInflater mLayoutInflater;

    private Context mComtext;

    private List<String> mDatas;



    public SimpleAdapter(Context context , List<String> datas) {

        this.mComtext = context;
        this.mDatas = datas;

        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = mLayoutInflater.inflate(R.layout.simpleadapter_item,viewGroup,false);

        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.textView.setText(mDatas.get(i));

        String s = "/sdcard/doorbellVideo/2017-6-8/2017-6-8-101349.png";
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bm = BitmapFactory.decodeFile(s, options);
        myViewHolder.imageView.setImageBitmap(bm);


    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.item_imageView);
            textView = (TextView) itemView.findViewById(R.id.tv);
        }
    }

