package com.demo.dialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import lib.sjy.dialog.ToastDialog;
import lib.sjy.dialog.callback.ConfigDialog;
import lib.sjy.dialog.params.DialogParams;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                dialog1();
                break;
        }
    }

    private void dialog1() {

        new ToastDialog.Builder(this)
                .setCanceledOnTouchOutside(true)//弹窗外点击允否
                .setCancelable(true)//back键点击允否
                .configDialog(new ConfigDialog() {
                    @Override
                    public void onConfig(DialogParams params) {
                        //                        params.animStyle = R.style.dialogAnimStyle;//动画效果
                        //                        params.alpha = 0.8f;//弹窗透明度
                        // TODO: 2017/11/27 没效果 
                        //                        params.backgroundColor = Color.GRAY;//弹窗背景色 bug
                    }
                })
                .setTitle("提示")
                //                .setTitleColor(Color.RED)
                .setContentText("退出登录")
                .setNegative("取消", null)
                .setPositive("确定", new View.OnClickListener() {//参数二可以为null
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "点击了确定", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}
