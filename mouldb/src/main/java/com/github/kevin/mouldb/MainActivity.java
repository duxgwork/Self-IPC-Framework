package com.github.kevin.mouldb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.github.kevin.baselib.manager.IUserManager;
import com.github.kevin.baselib.manager.ProcessManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProcessManager.getInstance().connect(this, "com.github.kevin.processframework");
    }

    public void getPerson(View view) {
        IUserManager userManager = ProcessManager.getInstance().getInstance(IUserManager.class);
        Toast.makeText(this, "====>"+ userManager.getPerson(),Toast.LENGTH_SHORT).show();
    }


}
