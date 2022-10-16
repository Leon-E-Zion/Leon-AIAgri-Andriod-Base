package com.example.comparetest.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.comparetest.R;

public class DisplayActivity extends BaseActivity implements View.OnClickListener {
    EditText text1;
    EditText text2;
    EditText text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Toolbar toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//添加默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用

        Button button = findViewById(R.id.sendData);
        button.setOnClickListener(this);
         text1 = findViewById(R.id.edit_1);
         text2 = findViewById(R.id.edit_2);
         text3 = findViewById(R.id.edit_3);
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

    //监听系统的返回键
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        navigateTo(DetailsActivity.class);
        finish();
        System.out.println("按下了back键   onBackPressed()");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sendData:
                Back();
                break;
        }
    }

    private void Back() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("您确定要提交当前数据吗");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(DisplayActivity.this, "数据提交成功！", Toast.LENGTH_SHORT).show();
                text1.setText("");
                text2.setText("");
                text3.setText("");
            }
        });
        builder.setNeutralButton("取消",null);
        builder.show(); //调用show()方法来展示对话框
    }
}
