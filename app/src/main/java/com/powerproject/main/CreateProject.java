package com.powerproject.main;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.powerproject.login.LoginActivity;
import com.powerproject.login.R;
import com.powerproject.login.RegisterActivity;
import com.powerproject.login.RegisterRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import static com.powerproject.main.MainActivity.username1;
import static com.powerproject.main.ProjectFragment.nameproject;
import static com.powerproject.splashActivity.project;
//import static com.powerproject.main.ProjectFragment.project;

public class CreateProject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        final EditText etProjectName = (EditText) findViewById(R.id.etProjectName);
        final EditText etProjectDesc = (EditText) findViewById(R.id.etProjectDesc);
        final EditText etAlotMember = (EditText) findViewById(R.id.etAlotMember);
        final DatePicker projectStart = (DatePicker) findViewById(R.id.dpProjectStart);
        final DatePicker projectFinish = (DatePicker) findViewById(R.id.dpProjectFinish);

        Button bCreate = (Button) findViewById(R.id.bCreateProject);

        bCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String projectName = etProjectName.getText().toString();
                nameproject = projectName;
                final String projectDesc = etProjectDesc.getText().toString();
                int   Sday  = projectStart.getDayOfMonth();
                int   Smonth= projectStart.getMonth() + 1;
                int   Syear = projectStart.getYear();
                int   Fday  = projectFinish.getDayOfMonth();
                int   Fmonth= projectFinish.getMonth() + 1;
                int   Fyear = projectFinish.getYear();
                final String Start = Syear+"-"+Smonth+"-"+Sday;
                final String Finish = Fyear+"-"+Fmonth+"-"+Fday;
                final String owner = "";
                final int member = Integer.parseInt(etAlotMember.getText().toString());


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                if (nameproject.equals("") || nameproject == null){

                                }else
                                    project.add(nameproject);
                                startActivity(new Intent(CreateProject.this, MainActivity.class));
                                Toast.makeText(CreateProject.this, "Create Project Success", Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                //startActivity(new Intent(CreateProject.this, MainActivity.class));
                                Toast.makeText(CreateProject.this, "Create Project Failed", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                CreateProjectRequest createProjectRequest = new CreateProjectRequest(projectName, projectDesc, Start, Finish, owner, username1, member, responseListener);
                RequestQueue queue = Volley.newRequestQueue(CreateProject.this);
                queue.add(createProjectRequest);
            }
        });
    }
}
