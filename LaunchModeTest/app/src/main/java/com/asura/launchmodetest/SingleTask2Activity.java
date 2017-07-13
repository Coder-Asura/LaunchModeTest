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
 * Created by Asura on 2017/7/11 10:02.
 */
public class SingleTask2Activity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_main, btn_self,btn_singleTask_another;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("asura", "SingleTask2Activity onCreate " + hashCode() + "  taskId== " + getTaskId());
        setContentView(R.layout.activity_single_task);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("SingleTask2Activity  " + hashCode());
        }

        btn_main = (Button) findViewById(R.id.btn_main);
        btn_self = (Button) findViewById(R.id.btn_self);
        btn_singleTask_another = (Button) findViewById(R.id.btn_singleTask_another);

        btn_main.setOnClickListener(this);
        btn_self.setOnClickListener(this);
        btn_singleTask_another.setOnClickListener(this);
        btn_singleTask_another.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_main:
                intent.setClass(SingleTask2Activity.this, MainActivity.class);
                break;
            case R.id.btn_self:
                intent.setClass(SingleTask2Activity.this, SingleTask2Activity.class);
                break;
            case R.id.btn_singleTask_another:
                intent.setClass(SingleTask2Activity.this, SingleTask2Activity.class);
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
        Log.d("asura", "SingleTask2Activity onNewIntent " + hashCode() + "  taskId== " + getTaskId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("asura", "SingleTask2Activity onDestroy " + hashCode() + "  taskId== " + getTaskId());
    }
}
