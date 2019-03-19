package com.github.kevin.processframework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.github.kevin.baselib.bean.Person;
import com.github.kevin.baselib.manager.ProcessManager;
import com.github.kevin.baselib.manager.IUserManager;

public class SecondActivity extends AppCompatActivity {
    private IUserManager mIUserManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ProcessManager.getInstance().connect(this);
    }

    public void getUser(View view) {
        //获取对象
        mIUserManager = ProcessManager.getInstance().getInstance(IUserManager.class);
//        mIUserManager.setPerson(new Person("王老师", "199999"));//发射消息

        //执行方法
        Person person = mIUserManager.getPerson();
        Toast.makeText(this, "====>"+ person,Toast.LENGTH_SHORT).show();
    }

}
