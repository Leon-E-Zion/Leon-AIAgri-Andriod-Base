package com.example.comparetest.activity;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.comparetest.MainActivity;
import com.example.comparetest.R;
import com.example.comparetest.network.HiRetrofit;
import com.example.comparetest.network.Service;
import com.google.android.material.card.MaterialCardView;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends BaseActivity implements View.OnClickListener {
    public  String block;
    MaterialCardView card1;
    MaterialCardView card2;
    MaterialCardView card3;
    MaterialCardView card4;
    MaterialCardView card5;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

         card1 = findViewById(R.id.card_1);
         card2 = findViewById(R.id.card_2);
         card3 = findViewById(R.id.card_3);
         card4 = findViewById(R.id.card_4);
         card5 = findViewById(R.id.card_5);

        Toolbar toolbar = findViewById(R.id.Details_toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//添加默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用

        Intent intent = getIntent();
        block = intent.getStringExtra("block");
        System.out.println(block);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);
    }

    private void GetData(String s1) {
        Intent intent = new Intent(DetailsActivity.this,ShowActivity.class);
        intent.putExtra("block",block);
        intent.putExtra("option",s1);
        startActivity(intent);
        finish();
    }

    //返回的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            navigateTo(MainActivity.class);
            finish();
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.card_1:
                GetData("0");
                break;
            case R.id.card_2:
                GetData("1");
                break;
            case R.id.card_3:
                GetData("2");
                break;
            case R.id.card_4:
                GetData("3");
                break;
            case R.id.card_5:
                navigateTo(DisplayActivity.class);
                finish();
                break;
        }
    }

    //监听系统的返回键
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        navigateTo(MainActivity.class);
        finish();
        System.out.println("按下了back键   onBackPressed()");
    }
}