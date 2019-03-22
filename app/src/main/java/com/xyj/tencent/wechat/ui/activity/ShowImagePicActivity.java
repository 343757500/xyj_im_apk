package com.xyj.tencent.wechat.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.gyf.barlibrary.ImmersionBar;
import com.xyj.tencent.R;
import com.xyj.tencent.wechat.ui.widget.PhotoView;


public class ShowImagePicActivity extends FragmentActivity {


    private String img;
    private PhotoView pv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image_pic);
        ImmersionBar.with(this).init();
        pv = findViewById(R.id.photo_view);
        img = getIntent().getStringExtra("img");
        Glide.with(this).load(getIntent().getStringExtra("img")).into(pv);



        pv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }


}
