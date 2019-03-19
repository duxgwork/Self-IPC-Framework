package com.github.kevin.processframework.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.github.kevin.baselib.ProcessManager;
import com.github.kevin.baselib.bean.Person;
import com.github.kevin.processframework.IUserManager;
import com.github.kevin.processframework.R;
import com.github.kevin.processframework.UserManager;

public class SecondActivity extends AppCompatActivity {
    private IUserManager mIUserManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ProcessManager.getInstance().connect(this);
    }

    /**
     * 获取对象
     * @param view
     */
    public void userManager(View view) {
        mIUserManager = ProcessManager.getInstance().getInstance(IUserManager.class);
    }

    //执行方法
    public void getUser(View view) {
        Toast.makeText(this, "====>"+ mIUserManager.getPerson(),Toast.LENGTH_SHORT).show();

//        mIUserManager.setPerson(new Person("王老师", "199999"));
    }

}
