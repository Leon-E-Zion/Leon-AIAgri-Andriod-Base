package com.example.comparetest.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;

import com.example.comparetest.R;
import com.example.comparetest.network.HiRetrofit;
import com.example.comparetest.network.Service;
import com.example.comparetest.network.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity {

    private Button register;
    Service service = HiRetrofit.retrofit.create(Service.class);
    EditText acc;
    EditText pwd;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.Register_toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//添加默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用

        init();

        //处理注册的点击事件
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });

        //处理返回事件
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateTo(LoginActivity.class);
                finish();
            }
        });

    }

    //处理注册事件，注册成功后，会返回到登陆页面进行登录
    private void Register() {
        acc = findViewById(R.id.register_account);
        pwd = findViewById(R.id.register_password);

        String username = acc.getText().toString();
        String password = pwd.getText().toString();

        User user = new User(username,password);

        Call<User> call = service.Register(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().msg.equals("成功")){
                    showToast("注册成功！");
                    navigateTo(LoginActivity.class);
                    finish();
                }else {
                    showToast(response.body().msg);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                showToast(t.getMessage());
            }
        });
    }

    //初始化布局的控件
    private void init() {
        register = findViewById(R.id.register_register);
        back = findViewById(R.id.back1);
    }

    //返回的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }
}