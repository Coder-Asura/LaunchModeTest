package com.asura.launchmodetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Asura on 2017/7/11 10:36.
 *  *
 * <p>singleInstance</p>
 *
 * <p>1、每调用一次 startActivity ,如果新的任务栈不存在，则会创建一个新的任务栈并例化一个新的 activity 对象；</p>
 * <p>如果已经是在新的任务栈中且有 activity 的实例，则不创建，并回调 onNewIntent(Intent intent) 方法</p>
 *
 * <p>2、如果在新的任务栈中再跳转到另一个 singleInstance 的 Activity 中，则又会创建一个新的任务栈</p>
 *
 * <p>3、也就是说每一个 singleInstance 模式的 Activity 都会拥有一个单独的任务栈，且任务栈中只有自己一个实例</p>
 */
public class SingleInstanceActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_main, btn_self,btn_singleInstance_another;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("asura", "SingleInstanceActivity onCreate " + hashCode() + "  taskId== " + getTaskId());
        setContentView(R.layout.activity_single_instance);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("SingleInstanceActivity  " + hashCode());
        }

        btn_main = (Button) findViewById(R.id.btn_main);
        btn_self = (Button) findViewById(R.id.btn_self);
        btn_singleInstance_another = (Button) findViewById(R.id.btn_singleInstance_another);

        btn_main.setOnClickListener(this);
        btn_self.setOnClickListener(this);
        btn_singleInstance_another.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_main:
                intent.setClass(SingleInstanceActivity.this, MainActivity.class);
                break;
            case R.id.btn_self:
                intent.setClass(SingleInstanceActivity.this, SingleInstanceActivity.class);
                break;
            case R.id.btn_singleInstance_another:
                intent.setClass(SingleInstanceActivity.this, SingleInstance2Activity.class);
                break;
        }
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("asura", "SingleInstanceActivity onNewIntent " + hashCode() + "  taskId== " + getTaskId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("asura", "SingleInstanceActivity onDestroy " + hashCode() + "  taskId== " + getTaskId());
    }
}
