package com.example.comparetest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.example.comparetest.R;

import java.lang.ref.WeakReference;

public class SplashActivity extends Activity{  //继承Activity可以将actionBar去掉
    private static final int CODE = 1001;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash);

        textView = findViewById(R.id.time_view);

        MyHandler handler = new MyHandler(this);
        Message message = Message.obtain();
        message.what  = CODE;

        //倒计时三秒
        message.arg1 = 3000;
        handler.sendMessage(message);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.start(SplashActivity.this);
                SplashActivity.this.finish();
                handler.removeMessages(CODE);//此时已经点击跳过按钮，关闭Handler的倒计时跳过
            }
        });
    }


    //倒计时
    public static class MyHandler extends Handler{

        public final WeakReference<SplashActivity> mWeakReference;

        public MyHandler(SplashActivity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            SplashActivity activity = mWeakReference.get();
            if (msg.what == CODE){

                if (activity != null){
                    int time = msg.arg1;
                    // 设置textView，更新UI
                    activity.textView.setText(time/1000+"秒，点击跳过");
                    //发送倒计时
                    Message message = Message.obtain();
                    message.what = CODE;
                    message.arg1 = time - 1000;
                    if (time > 0){
                        sendMessageDelayed(message,1000);//间隔一秒发送
                    }else {
                        //跳到下一个页面
                        LoginActivity.start(activity);
                       activity.finish();
                    }
                }
            }
        }
    }
}