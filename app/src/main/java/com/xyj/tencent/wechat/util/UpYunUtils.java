package com.xyj.tencent.wechat.util;

import android.util.Log;

import com.upyun.library.common.Params;
import com.upyun.library.common.UploadEngine;
import com.upyun.library.listener.UpCompleteListener;
import com.upyun.library.listener.UpProgressListener;
import com.xyj.tencent.common.base.Const;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;

public class UpYunUtils {
    public static String getUpPicUrl(String copyFile, final UpYunListener upYunListener){
     try {

        File temp = null;
        temp = new File(copyFile);
        final Map<String, Object> paramsMap = new HashMap<>();
        //上传又拍云的命名空间
        paramsMap.put(Params.BUCKET, "cloned");
        final String newVideoLast = copyFile.substring(copyFile.lastIndexOf("/")+1);
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyyMMdd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        //又拍云的保存路径，任选其中一个
        final String savePath="/imSendPic/"+str+"/"+newVideoLast;
        paramsMap.put(Params.SAVE_KEY, savePath);
        //时间戳加上15秒
        paramsMap.put(Params.EXPIRATION, System.currentTimeMillis()+15);
        final boolean[] flag = {false};
        //进度回调，可为空
        UpProgressListener progressListener = new UpProgressListener() {
            @Override
            public void onRequestProgress(final long bytesWrite, final long contentLength) {
                    Log.e(TAG, (100 * bytesWrite) / contentLength + "%");
                    Log.e(TAG, bytesWrite + "::" + contentLength);
            }
        };

        UpCompleteListener completeListener = new UpCompleteListener() {
            @Override
            public void onComplete(boolean isSuccess, String result) {
                // textView.setText(isSuccess + ":" + result);
                Log.e(TAG, isSuccess + ":" + result);
                if (isSuccess==true){
                    upYunListener.success(Const.YOUPAIYUN+savePath);
                }
            }




        };
        //表单上传（本地签名方式）
        UploadEngine.getInstance().formUpload(temp,paramsMap , "unesmall", com.upyun.library.utils.UpYunUtils.md5("unesmall123456"), completeListener, progressListener);
    } catch (Exception e) {
        e.printStackTrace();
    }
        return "";
    }



    public static String getUpVideoUrl(String copyFile, final UpYunListener upYunListener){
        try {

            File temp = null;
            temp = new File(copyFile);
            final Map<String, Object> paramsMap = new HashMap<>();
            //上传又拍云的命名空间
            paramsMap.put(Params.BUCKET, "cloned");
            final String newVideoLast = copyFile.substring(copyFile.lastIndexOf("/")+1);
            SimpleDateFormat formatter = new SimpleDateFormat ("yyyyMMdd");
            Date curDate = new Date(System.currentTimeMillis());//获取当前时间
            String str = formatter.format(curDate);
            //又拍云的保存路径，任选其中一个
            final String savePath="/imSendVieo/"+str+"/"+newVideoLast;
            paramsMap.put(Params.SAVE_KEY, savePath);
            //时间戳加上15秒
            paramsMap.put(Params.EXPIRATION, System.currentTimeMillis()+15);
            final boolean[] flag = {false};
            //进度回调，可为空
            UpProgressListener progressListener = new UpProgressListener() {
                @Override
                public void onRequestProgress(final long bytesWrite, final long contentLength) {
                    Log.e(TAG, (100 * bytesWrite) / contentLength + "%");
                    Log.e(TAG, bytesWrite + "::" + contentLength);
                }
            };

            UpCompleteListener completeListener = new UpCompleteListener() {
                @Override
                public void onComplete(boolean isSuccess, String result) {
                    // textView.setText(isSuccess + ":" + result);
                    Log.e(TAG, isSuccess + ":" + result);
                    if (isSuccess==true){
                        upYunListener.success(Const.YOUPAIYUN+savePath);
                    }
                }




            };
            //表单上传（本地签名方式）
            UploadEngine.getInstance().formUpload(temp,paramsMap , "unesmall", com.upyun.library.utils.UpYunUtils.md5("unesmall123456"), completeListener, progressListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }



    public static String getUpFileUrl(String copyFile, final UpYunListener upYunListener){
        try {

            File temp = null;
            temp = new File(copyFile);
            final Map<String, Object> paramsMap = new HashMap<>();
            //上传又拍云的命名空间
            paramsMap.put(Params.BUCKET, "cloned");
            final String newVideoLast = copyFile.substring(copyFile.lastIndexOf("/")+1);
            SimpleDateFormat formatter = new SimpleDateFormat ("yyyyMMdd");
            Date curDate = new Date(System.currentTimeMillis());//获取当前时间
            String str = formatter.format(curDate);
            //又拍云的保存路径，任选其中一个
            final String savePath="/imSendFile/"+str+"/"+newVideoLast;
            paramsMap.put(Params.SAVE_KEY, savePath);
            //时间戳加上15秒
            paramsMap.put(Params.EXPIRATION, System.currentTimeMillis()+15);
            final boolean[] flag = {false};
            //进度回调，可为空
            UpProgressListener progressListener = new UpProgressListener() {
                @Override
                public void onRequestProgress(final long bytesWrite, final long contentLength) {
                    Log.e(TAG, (100 * bytesWrite) / contentLength + "%");
                    Log.e(TAG, bytesWrite + "::" + contentLength);
                }
            };

            UpCompleteListener completeListener = new UpCompleteListener() {
                @Override
                public void onComplete(boolean isSuccess, String result) {
                    // textView.setText(isSuccess + ":" + result);
                    Log.e(TAG, isSuccess + ":" + result);
                    if (isSuccess==true){
                        upYunListener.success(Const.YOUPAIYUN+savePath);
                    }
                }




            };
            //表单上传（本地签名方式）
            UploadEngine.getInstance().formUpload(temp,paramsMap , "unesmall", com.upyun.library.utils.UpYunUtils.md5("unesmall123456"), completeListener, progressListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public interface UpYunListener{
        void success(String url);
        void fail(String message);
    }
}
