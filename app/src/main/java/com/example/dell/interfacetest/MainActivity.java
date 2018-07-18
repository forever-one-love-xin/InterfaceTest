package com.example.dell.interfacetest;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CallBack, ImageStateInterface {
    private Button mButton;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.card_view_test);

        new Handler ().postDelayed (new Runnable () {
            @Override
            public void run() {
                CallBackUtils.setCallBack (MainActivity.this);
            }
        }, 1000);

        //实现方式一 (推荐)
//        onLincoln ();
    }

    /**
     * 实现方式一  这个需要  implements ImageStateInterface 接口
     */
    private void onLincoln() {
        mButton = findViewById (R.id.test_btn);
        mTextView = findViewById (R.id.test_txt);

        mButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                //调用StartDownLoad方法，目的是将MainActivity注册到接口那里，
                //使接口知道，需要调用的是哪个类中的实现该接口的方法。
                DownloadImageUtil.setImageStateInterface (MainActivity.this, getApplicationContext ());
            }
        });
    }

    @Override
    public void dosomething(String s) {
        Toast.makeText (this, "我拿到了接口传来的数据  " + s,
                Toast.LENGTH_SHORT).show ();
    }

    @Override
    public void onImageStart() {
        Log.d ("LIU", "onImageStart: ");
        mButton.setText ("Start");
        mTextView.setText ("Start");
    }

    @Override
    public void onImageSuccess(final Bitmap bitmap) {
        runOnUiThread (new Runnable () {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                mButton.setText ("Success");
                mTextView.setText ("Success");
                mButton.setBackground (new BitmapDrawable (bitmap));
            }
        });
    }

    @Override
    public void onImageFailed() {

    }

    @Override
    public void onEnd() {
        Toast.makeText (this,"图片传输已完成！",Toast.LENGTH_SHORT).show ();
    }
}
