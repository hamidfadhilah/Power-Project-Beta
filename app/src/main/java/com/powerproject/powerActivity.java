package com.powerproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.powerproject.login.LoginActivity;
import com.powerproject.login.R;

public class powerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power);

        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(2000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                    finish();
                    startActivity(new Intent(powerActivity.this, LoginActivity.class));
                }
            }
        };
        thread.start();
    }
}
