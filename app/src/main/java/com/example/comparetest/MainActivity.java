package com.example.comparetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.comparetest.activity.BaseActivity;
import com.example.comparetest.activity.DetailsActivity;
import com.example.comparetest.activity.LoginActivity;
import com.example.comparetest.network.HiRetrofit;
import com.example.comparetest.network.Service;
import com.google.android.material.card.MaterialCardView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// 主函数
public class MainActivity extends BaseActivity implements View.OnClickListener {
    Service service = HiRetrofit.retrofit.create(Service.class);

    //定义图片加载器
    public class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        //初始化轮播图
        init();

        //获得数据
//        getData();
    }

    //测试接口数据 - 没有使用到 - 后来软件完成后就弃用了
    private void getData() {
       Call<ResponseBody> call = service.getAll();
       call.enqueue(new Callback<ResponseBody>() {
           @Override
           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               try {
                   System.out.println(response.body().string());
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }

           @Override
           public void onFailure(Call<ResponseBody> call, Throwable t) {

           }
       });

       Call<ResponseBody> call1 = service.getById1();
       call1.enqueue(new Callback<ResponseBody>() {
           @Override
           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               try {
                   System.out.println(response.body().string());
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }

           @Override
           public void onFailure(Call<ResponseBody> call, Throwable t) {

           }
       });
    }

    //处理点击事件 - 图片点击事件，进入新界面
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Tian_1:
                Intent intent1 = new Intent(MainActivity.this,DetailsActivity.class);
                intent1.putExtra("block","0");
                startActivity(intent1);
                finish();
                break;
            case R.id.Tian_2:
                Intent intent2 = new Intent(MainActivity.this,DetailsActivity.class);
                intent2.putExtra("block","1");
                startActivity(intent2);
                finish();
                break;
            case R.id.Tian_3:
                Intent intent3 = new Intent(MainActivity.this,DetailsActivity.class);
                intent3.putExtra("block","2");
                startActivity(intent3);
                finish();
                break;
            case R.id.Tian_4:
                Intent intent4 = new Intent(MainActivity.this,DetailsActivity.class);
                intent4.putExtra("block","3");
                startActivity(intent4);
                finish();
                break;
        }
    }

    //初始化数据
    private void init(){
        Banner banner = (Banner) findViewById(R.id.banner);
        MaterialCardView card1 = findViewById(R.id.Tian_1);
        MaterialCardView card2 = findViewById(R.id.Tian_2);
        MaterialCardView card3 = findViewById(R.id.Tian_3);
        MaterialCardView card4 = findViewById(R.id.Tian_4);
        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        //初始化图片数据 - 界面标志图
        List images = new ArrayList();
        images.add(R.drawable.b1);
        images.add(R.drawable.b2);
        images.add(R.drawable.b3);
        images.add(R.drawable.b4);

        //初始化标题数据
        List titles = new ArrayList();
        titles.add("作物生长影响因素智能调节");
        titles.add("农作物生长预测");
        titles.add("农作物病虫害识别");
        titles.add("农产品溯源");

        //设置图片加载器
        banner.setImageLoader(new MyImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置轮播的动画效果
        banner.setBannerAnimation(Transformer.ZoomOutSlide);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间（设置2.5秒切换下一张图片）
        banner.setDelayTime(2500);
        //设置banner显示样式（带标题的样式）
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //增加监听事件
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(MainActivity.this, "position"+position, Toast.LENGTH_SHORT).show();
            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    private void Back() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("您确定退出程序吗");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                navigateTo(LoginActivity.class);
                finish();
            }
        });
        builder.setNeutralButton("取消",null);
        builder.show(); //调用show()方法来展示对话框
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.back:
                Back();
                break;
        }
        return true;
    }
}
