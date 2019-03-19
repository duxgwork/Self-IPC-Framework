package com.github.kevin.processframework;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.kevin.baselib.manager.ProcessManager;
import com.github.kevin.baselib.bean.Person;
import com.github.kevin.baselib.manager.UserManager;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注册
        ProcessManager.getInstance().register(UserManager.class);
    }

    public void change(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }

    public void sendMsg(View view) {
        UserManager.getInstance().setPerson(new Person("王老师", "199999"));
    }

//    public void getPerson(View view) {
////        Toast.makeText(this, "====>"+ UserManager.getInstance().getPerson(),Toast.LENGTH_SHORT).show(); //接收消息
//    }

}
