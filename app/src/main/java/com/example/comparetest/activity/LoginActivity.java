package com.example.comparetest.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.comparetest.MainActivity;
import com.example.comparetest.R;
import com.example.comparetest.network.HiRetrofit;
import com.example.comparetest.network.Service;
import com.example.comparetest.network.User;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {
    
    private TextView register;
    private Button login;
    EditText account;
    Service service = HiRetrofit.retrofit.create(Service.class);
    EditText pwd;
    CheckBox remember_Password;
    SharedPreferences.Editor editor;
    SharedPreferences pref;
    ImageView meet;
    private Boolean bPwdSwitch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        //注册处理
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到注册页面
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //登陆处理
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

        //处理记住密码
        Boolean isRemember = pref.getBoolean("remember_password",false);
        if (isRemember){
            rememberPassword();
        }

        ChangePwdStatus();
    }

    //处理记住密码事件
    private void rememberPassword() {
        String password = pref.getString("password","");
        String name = pref.getString("username","");
        account.setText(name);
        pwd.setText(password);
        remember_Password.setChecked(true);
    }

    //初始化数据
    private void init() {
        pref = getSharedPreferences("data",MODE_PRIVATE);
        register = findViewById(R.id.Register);
        login = findViewById(R.id.login);
        account = findViewById(R.id.et_account);
        pwd = findViewById(R.id.et_pwd);
        remember_Password = (CheckBox) findViewById(R.id.remember_password);
        meet = findViewById(R.id.iv_pwd_switch);
    }

    //处理密码的可见状态
    private void ChangePwdStatus() {
        meet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bPwdSwitch = !bPwdSwitch;
                if (bPwdSwitch) {
                    meet.setImageResource(R.drawable.nomeet);
                    pwd.setInputType(
                            InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    meet.setImageResource(
                            R.drawable.meet);
                    pwd.setInputType(
                            InputType.TYPE_TEXT_VARIATION_PASSWORD |
                                    InputType.TYPE_CLASS_TEXT);
                    pwd.setTypeface(Typeface.DEFAULT);
                }
            }
        });
    }

    //登陆处理
    private void Login() {
        String username = account.getText().toString();
        String password = pwd.getText().toString();
        Map<String,String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);

        System.out.println(map);

        User user = new User(username,password);

        Call<User> call = service.Login(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().msg.equals("成功")){
                    showToast("登陆成功！");
                    //将权限进行存储
                    editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                    if (remember_Password.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("password",password);
                        editor.putString("username",username);
                        editor.apply();
                    }else {
                        editor.clear();
                    }
                    navigateTo(MainActivity.class);
                    finish();
                }else {
                    showToast(response.body().msg);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("error:"+t.getMessage());
                System.out.println(t.getCause());
            }
        });
    }

    //用于跳转到LoginActivity
    public static void start(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}