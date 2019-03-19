package com.github.kevin.processframework.activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.kevin.baselib.ProcessManager;
import com.github.kevin.baselib.bean.Person;
import com.github.kevin.processframework.R;
import com.github.kevin.processframework.UserManager;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProcessManager.getInstance().register(UserManager.class);
        UserManager.getInstance().setPerson(new Person("王老师", "199999"));

    }

    public void change(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }

    public void getPerson(View view) {
//        Toast.makeText(this, "====>"+ UserManager.getInstance().getPerson(),Toast.LENGTH_SHORT).show();
    }
}
