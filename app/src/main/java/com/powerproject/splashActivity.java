package com.powerproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.powerproject.login.LoginActivity;
import com.powerproject.login.R;

import java.util.ArrayList;

public class splashActivity extends Activity {
    public static ArrayList<String> project;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        project = new ArrayList<String>();

        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(2000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                    finish();
                    startActivity(new Intent(splashActivity.this, powerActivity.class));
                }
            }
        };
        thread.start();
    }
}
