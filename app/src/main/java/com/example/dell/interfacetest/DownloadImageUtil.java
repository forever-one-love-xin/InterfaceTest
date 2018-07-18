package com.example.dell.interfacetest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DownloadImageUtil {
    private static ImageStateInterface mImageStateInterface;

    public static void setImageStateInterface(final ImageStateInterface imageStateInterface, final Context context) {
        mImageStateInterface = imageStateInterface;
        if(imageStateInterface != null) {
            //该imageStateInterface使其得知，是从哪里注册过来的，然后根据其来源调用其实现的接口方法。

            //如下，此时调用的就是MainActivity.this中实现的onImageStart方法
            imageStateInterface.onImageStart ();

            new Thread (new Runnable () {
                @Override
                public void run() {
                    try {
                        new Thread ().sleep (5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace ();
                    }

                    Bitmap bitmap = BitmapFactory.decodeResource (context.getResources (),R.drawable.demo2);
                    imageStateInterface.onImageSuccess (bitmap);
                }
            }).start ();
            imageStateInterface.onEnd ();
        }
    }
}
