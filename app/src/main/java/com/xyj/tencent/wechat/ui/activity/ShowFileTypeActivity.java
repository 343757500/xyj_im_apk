package com.xyj.tencent.wechat.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Response;
import com.xyj.tencent.BuildConfig;
import com.xyj.tencent.R;
import com.xyj.tencent.wechat.ui.widget.TemplateTitle;
import com.xyj.tencent.wechat.util.FileOpenUtils;
import com.xyj.tencent.wechat.util.NetWorkUtils;

import java.io.File;

public class ShowFileTypeActivity extends FragmentActivity implements View.OnClickListener {

    private ImageView ivType;
    private TextView tvName;
    private Button btnDownLoad;
   // private GifLoadingView mGifLoadingView;
    String download;

    File sdFile;
    String suff;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_file_type);
       // mGifLoadingView=new GifLoadingView();
        ivType = findViewById(R.id.iv_type);
        tvName = findViewById(R.id.tv_name);
        btnDownLoad = findViewById(R.id.btn_download);
        btnDownLoad.setOnClickListener(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        TemplateTitle title = findViewById(R.id.chat_title);
        title.setTitleText("文件预览");

        download = getIntent().getStringExtra("download");
        String name=download.substring(download.lastIndexOf("/")+1);
        sdFile=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/weixinliao/"+name);
        if(sdFile.exists()){
            btnDownLoad.setText("打开文件");
        }
         suff=name.substring(name.lastIndexOf(".")+1);
        tvName.setText(download.substring(download.lastIndexOf("/")+1));
        if("txt".equals(suff)){
            Glide.with(this).load(R.mipmap.file_txt).into(ivType);
        }else if("doc".equals(suff)){
            Glide.with(this).load(R.mipmap.file_doc).into(ivType);
        }else if("xls".equals(suff)||"xlsx".equals(suff)){
            Glide.with(this).load(R.mipmap.file_excel).into(ivType);
        }else if("ppt".equals(suff)){
            Glide.with(this).load(R.mipmap.file_ppt).into(ivType);
        }else if("mp3".equals(suff)||"amr".equals(suff)){
            Glide.with(this).load(R.mipmap.file_mp3).into(ivType);
        }else if("avi".equals(suff)){
            Glide.with(this).load(R.mipmap.file_video).into(ivType);
        }else if("apk".equals(suff)){
            Glide.with(this).load(R.mipmap.file_apk).into(ivType);
        }else{
            Glide.with(this).load(R.mipmap.file_unknow).into(ivType);
        }


    }

    @Override
    public void onClick(View view) {

        try {
            switch (view.getId()) {
                case R.id.btn_download:
                    if(TextUtils.equals("打开文件",btnDownLoad.getText().toString())){
                        if(TextUtils.equals("doc",suff)||TextUtils.equals("docx",suff)){
                            Intent intent = FileOpenUtils.getWordFileIntent(sdFile);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID+".fileprovider", sdFile);
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                intent.setDataAndType(contentUri,  "application/msword");
                            } else {
                                intent.setDataAndType(Uri.fromFile(sdFile), "application/msword");
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                            startActivity(intent);
                        }else if(TextUtils.equals("xls",suff)||TextUtils.equals("xlsx",suff)){
                            Intent intent = FileOpenUtils.getExcelFileIntent(sdFile);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID+".fileprovider", sdFile);
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                intent.setDataAndType(contentUri,  "application/vnd.ms-excel");
                            } else {
                                intent.setDataAndType(Uri.fromFile(sdFile), "application/vnd.ms-excel");
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                            startActivity(intent);
                        }else if(TextUtils.equals("ppt",suff)){
                            Intent intent = FileOpenUtils.getPPTFileIntent(sdFile);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID+".fileprovider", sdFile);
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                intent.setDataAndType(contentUri,  "application/vnd.ms-powerpoint");
                            } else {
                                intent.setDataAndType(Uri.fromFile(sdFile), "application/vnd.ms-powerpoint");
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                            startActivity(intent);
                        }else if(TextUtils.equals("apk",suff)){
                            Intent intent = FileOpenUtils.getApkFileIntent(sdFile);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID+".fileprovider", sdFile);
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                intent.setDataAndType(contentUri,  "application/vnd.android.package-archive");
                            } else {
                                intent.setDataAndType(Uri.fromFile(sdFile), "application/vnd.android.package-archive");
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                            startActivity(intent);
                        }else if(TextUtils.equals("pdf",suff)){
                            Intent intent = FileOpenUtils.getPdfFileIntent(sdFile);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID+".fileprovider", sdFile);
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                intent.setDataAndType(contentUri,  "application/pdf");
                            } else {
                                intent.setDataAndType(Uri.fromFile(sdFile), "application/pdf");
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                            startActivity(intent);
                        }else if(TextUtils.equals("html",suff)){
                            Intent intent = FileOpenUtils.getHtmlFileIntent(sdFile);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID+".fileprovider", sdFile);
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                intent.setDataAndType(contentUri,  "text/html");
                            } else {
                                intent.setDataAndType(Uri.fromFile(sdFile), "text/html");
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                            startActivity(intent);
                        }else if(TextUtils.equals("txt",suff)){
                            Intent intent = FileOpenUtils.getTextFileIntent(sdFile);
//判断是否是AndroidN以及更高的版本
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID+".fileprovider", sdFile);
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                intent.setDataAndType(contentUri,  "text/plain");
                            } else {
                                intent.setDataAndType(Uri.fromFile(sdFile), "text/plain");
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                            startActivity(intent);

                        }else if(TextUtils.equals("mp3",suff)||TextUtils.equals("amr",suff)){
                            Intent intent = FileOpenUtils.getAudioFileIntent(sdFile);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID+".fileprovider", sdFile);
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                intent.setDataAndType(contentUri,  "audio/*");
                            } else {
                                intent.setDataAndType(Uri.fromFile(sdFile), "audio/*");
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                            startActivity(intent);
                        }else if(TextUtils.equals("avi",suff)||TextUtils.equals("mp4",suff)||TextUtils.equals("wmv",suff)){
                            Intent intent = FileOpenUtils.getVideoFileIntent(sdFile);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID+".fileprovider", sdFile);
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                intent.setDataAndType(contentUri,  "video/*");
                            } else {
                                intent.setDataAndType(Uri.fromFile(sdFile), "video/*");
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                            startActivity(intent);
                        }else if(TextUtils.equals("jpg",suff)||TextUtils.equals("jpeg",suff)||TextUtils.equals("png",suff)||TextUtils.equals("bmp",suff)||TextUtils.equals("gif",suff)){
                            Intent intent = FileOpenUtils.getImageFileIntent(sdFile);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID+".fileprovider", sdFile);
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                intent.setDataAndType(contentUri,  "image/*");
                            } else {
                                intent.setDataAndType(Uri.fromFile(sdFile), "image/*");
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                            startActivity(intent);
                        }else{
                            Intent intent = FileOpenUtils.getTextFileIntent(sdFile);
//判断是否是AndroidN以及更高的版本
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID+".fileprovider", sdFile);
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                intent.setDataAndType(contentUri,  "text/plain");
                            } else {
                                intent.setDataAndType(Uri.fromFile(sdFile), "text/plain");
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                            startActivity(intent);
                        }

                        return;
                    }
                    downLoad();
                    break;
            }
        }catch (Exception e){
            Log.e("111",e.toString());
        }

    }

    private void downLoad() {
        if(!NetWorkUtils.isNetworkConnected(this)){
            Toast.makeText(this, "网络未连接", Toast.LENGTH_SHORT).show();
            return;
        }
        showDialog();
        if(!TextUtils.isEmpty(download)){
            File sd = Environment.getExternalStorageDirectory();
            String dir=sd.getAbsolutePath()+"/weixinliao";
            String name = download.substring(download.lastIndexOf("/") + 1);
            OkGo.<File>get(download)
                    .tag(this)
                    .execute(new FileCallback(dir, name) {
                        @Override
                        public void onSuccess(Response<File> response) {
                            dismissDialog();
                            btnDownLoad.setText("打开文件");
                        }

                        @Override
                        public void onError(Response<File> response) {
                            super.onError(response);
                            dismissDialog();
                        }
                    });
        }else{
            Toast.makeText(this, "获取文件链接失效", Toast.LENGTH_SHORT).show();
            dismissDialog();
        }
    }

    private void showDialog(){
       // mGifLoadingView.setImageResource(R.mipmap.cat);
       /* mGifLoadingView.show(getFragmentManager(), "");
        mGifLoadingView.setCancelable(false);*/

    }


    private void dismissDialog(){
      /*  boolean cancelable = mGifLoadingView.isCancelable();
        if(mGifLoadingView!=null){
            mGifLoadingView.dismiss();
        }*/
    }
}
