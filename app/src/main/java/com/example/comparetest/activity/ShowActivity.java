package com.example.comparetest.activity;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.comparetest.R;
public class ShowActivity extends BaseActivity {
    String block;
    String option;
    String baseurl;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView image5;
    private ImageView image6;
    SharedPreferences.Editor editor;
    SharedPreferences pref;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        toolbar =  findViewById(R.id.show_toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//添加默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true);//设置返回键可用

        Intent intent = getIntent();
        block = intent.getStringExtra("block");
        option = intent.getStringExtra("option");
        pref = getSharedPreferences("data1",MODE_PRIVATE);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        baseurl = "http://192.168.3.61:8080/download?fileName=2022-07-06-";
        initView();
    }

    private void initView() {
        //将数据进行存储
        if (block != null && option !=null){
            editor = getSharedPreferences("data1",MODE_PRIVATE).edit();
            editor.putString("1",block);
            editor.putString("2",option);
            editor.apply();
            init();
        }else {
            block = pref.getString("1","a");
//            option = pref.getString("2","a");
            System.out.println("aaaa"+block);
            System.out.println("aaaa"+option);
            init();
        }
    }

    public void init(){
        if (option.equals("0")){
            toolbar.setTitle("作物生长影响因素智能调节");
            String url = baseurl+block+option+"0";
            System.out.println(url);
            Glide.with(this).load(url).into(image1);
            String url1 = baseurl+block+option+"1";
            System.out.println(url1);
            Glide.with(this).load(url1).into(image2);
            String url2 = baseurl+block+option+"2";
            System.out.println(url2);
            Glide.with(this).load(url2).into(image3);
            String url3 = baseurl+block+option+"3";
            System.out.println(url3);
            Glide.with(this).load(url3).into(image4);
            String url4 = baseurl+block+option+"4";
            System.out.println(url4);
            Glide.with(this).load(url4).into(image5);
            String url5 = baseurl+block+option+"5";
            System.out.println(url5);
            Glide.with(this).load(url5).into(image6);
        }
        else if (option.equals("1")){
            toolbar.setTitle("农作物生长预测");
            String s = "0";
            String url = baseurl+block+s+"0";
            System.out.println(url);
            Glide.with(this).load(url).into(image1);
            String url1 = baseurl+block+s+"1";
            System.out.println(url1);
            Glide.with(this).load(url1).into(image2);
            String url2 = baseurl+block+s+"2";
            System.out.println(url2);
            Glide.with(this).load(url2).into(image3);
            String url3 = baseurl+block+s+"3";
            System.out.println(url3);
            Glide.with(this).load(url3).into(image4);
            String url4 = baseurl+block+s+"4";
            System.out.println(url4);
            Glide.with(this).load(url4).into(image5);
            String url5 = baseurl+block+s+"5";
            System.out.println(url5);
            Glide.with(this).load(url5).into(image6);
        }
        else if (option.equals("2")){
            toolbar.setTitle("农作物病虫害识别");
            String url = baseurl+block+"1"+"0";
            System.out.println(url);
            Glide.with(this).load(url).into(image1);
        }
        else {
            toolbar.setTitle("农产品溯源");
            String url = baseurl+block+"1"+"0";
            System.out.println(url);
            Glide.with(this).load(url).into(image1);
        }
//        else if (option.equals("3")){
//            String url = baseurl+block+"1"+"0";
//            System.out.println(url);
//            Glide.with(this).load(url).into(image1);
//        }
    }

    //返回的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            navigateTo(DetailsActivity.class);
            finish();
        }
        return true;
    }

//    监听系统的返回键
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        navigateTo(DetailsActivity.class);
        finish();
        System.out.println("按下了back键   onBackPressed()");
    }
}