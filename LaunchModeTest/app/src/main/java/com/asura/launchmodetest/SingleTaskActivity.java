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
 *
 * <p>singleTask</p>
 *
 * <p>1、如果在清单文件 AndroidManifest.xml 中没有定义 taskAffinity 属性：</p>
 * <p>每调用一次 startActivity ,如果当前任务栈没有实例，就会实例化一个新的 activity 对象；</p>
 * <p>如果有，则不创建，并回调 onNewIntent(Intent intent) 方法,同时会清空栈内其之上的其他activity实例</p>
 * <p>比如栈里元素 （自下而上）A B C D ,其中 B 是 singleTask ,从 D 跳到 B，则栈元素变成 A B</p>
 *
 * <p>2、如果在清单文件 AndroidManifest.xml 中定义了 taskAffinity 属性：</p>
 * <p>则每调用一次 startActivity ，如果该任务栈没有创建过，会创建一个新的任务栈；</p>
 * <p>如果该新任务栈顶内没有实例，就会实例化一个新的 activity 对象；</p>
 * <p>如果有，则不创建实例，并回调 onNewIntent(Intent intent) 方法,同时会清空新栈内其之上的其他activity实例</p>
 * <p>比如栈里元素 （自下而上）A B C D ,其中 B 是 singleTask ,从 D 跳到 B，则栈元素变成 A B</p>
 * </p>
 */
public class SingleTaskActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_main, btn_self,btn_singleTask_another;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("asura", "SingleTaskActivity onCreate " + hashCode() + "  taskId== " + getTaskId());
        setContentView(R.layout.activity_single_task);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("SingleTaskActivity  " + hashCode());
        }

        btn_main = (Button) findViewById(R.id.btn_main);
        btn_self = (Button) findViewById(R.id.btn_self);
        btn_singleTask_another = (Button) findViewById(R.id.btn_singleTask_another);

        btn_main.setOnClickListener(this);
        btn_self.setOnClickListener(this);
        btn_singleTask_another.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_main:
                intent.setClass(SingleTaskActivity.this, MainActivity.class);
                break;
            case R.id.btn_self:
                intent.setClass(SingleTaskActivity.this, SingleTaskActivity.class);
                break;
            case R.id.btn_singleTask_another:
                intent.setClass(SingleTaskActivity.this, SingleTask2Activity.class);
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
        Log.d("asura", "SingleTaskActivity onNewIntent " + hashCode() + "  taskId== " + getTaskId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("asura", "SingleTaskActivity onDestroy " + hashCode() + "  taskId== " + getTaskId());
    }
}
