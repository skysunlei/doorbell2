package com.crg.doorbell.main.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.crg.doorbell.R;
import com.crg.doorbell.adapter.SimpleAdapter;
import com.crg.doorbell.divider.DividerItemDecoration;
import com.crg.doorbell.getFileData.GetFileData;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

public class FileManagementActivity extends Activity{
	private RecyclerView mRecyclerView;

	private List<String> mDatas;

	private SimpleAdapter mSimpleAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				 WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_filemanagement);
		init();
		initData();

		mSimpleAdapter = new SimpleAdapter(this,mDatas);
		mRecyclerView.setAdapter(mSimpleAdapter);

		GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4);
		mRecyclerView.setLayoutManager(gridLayoutManager);

		mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL_LIST));
		mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
	
	}

	private void initData() {
		mDatas = new ArrayList<String>();

//		for (int i= 'A'; i<='z' ; i++){
//			mDatas.add(""+(char)i);
//		}
        mDatas = new GetFileData().GetFileData();
	}

	private void init() {
		mRecyclerView = (RecyclerView) findViewById(R.id.fileManagementRecycleView);

	}

	

}
