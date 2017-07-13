package com.asura.launchmodetest;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * 任务栈中遵循 “后进先出 LIFO” 的规则
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_standard, btn_singleTop, btn_singleTask, btn_singleInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("asura", "MainActivity onCreate " + hashCode() + "  taskId== " + getTaskId());
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("MainActivity  " + hashCode());
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        btn_standard = (Button) findViewById(R.id.btn_standard);
        btn_singleTop = (Button) findViewById(R.id.btn_singleTop);
        btn_singleTask = (Button) findViewById(R.id.btn_singleTask);
        btn_singleInstance = (Button) findViewById(R.id.btn_singleInstance);

        btn_standard.setOnClickListener(this);
        btn_singleTop.setOnClickListener(this);
        btn_singleTask.setOnClickListener(this);
        btn_singleInstance.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_standard:
                intent.setClass(MainActivity.this, StandardActivity.class);
                break;
            case R.id.btn_singleTop:
                intent.setClass(MainActivity.this, SingleTopActivity.class);
                break;
            case R.id.btn_singleTask:
                intent.setClass(MainActivity.this, SingleTaskActivity.class);
                break;
            case R.id.btn_singleInstance:
                intent.setClass(MainActivity.this, SingleInstanceActivity.class);
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
    protected void onDestroy() {
        super.onDestroy();
        Log.d("asura", "MainActivity onDestroy " + hashCode() + "  taskId== " + getTaskId());
    }
}
