package com.powerproject.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static com.powerproject.login.R.id.etFUsername;

public class ResetPasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pas);

        final EditText etRUsername = (EditText) findViewById(R.id.etRUsername);
        final EditText etRPassword = (EditText) findViewById(R.id.etRPassword);
        final EditText etRPassword1 = (EditText) findViewById(R.id.etRPassword1);
        final Button bContinue1 = (Button) findViewById(R.id.bContinue1);

        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");

        etRUsername.setText(username);
        bContinue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String password = etRPassword.getText().toString();
                final String username1 = etRUsername.getText().toString();
                final String password1 = etRPassword1.getText().toString();
                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(password.equals(password1) && password.length()==6 && password1.length()==6 ) {
                                if (success) {
                                    Intent intent = new Intent(ResetPasActivity.this, LoginActivity.class);
                                    Toast.makeText(ResetPasActivity.this, "Re-Password Success", Toast.LENGTH_SHORT).show();
                                    ResetPasActivity.this.startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(ResetPasActivity.this, "Invalid Reset Password", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(ResetPasActivity.this, "Password Harus Sama atau 6 karakter ",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                ResetPasRequest resetRequest = new ResetPasRequest(username1, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(ResetPasActivity.this);
                queue.add(resetRequest);
            }
        });
    }

}

