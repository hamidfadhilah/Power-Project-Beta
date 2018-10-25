package com.powerproject.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.R.attr.checked;

public class RegisterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText etQuestion = (EditText) findViewById(R.id.etQuestion);
        final EditText etAnswer = (EditText) findViewById(R.id.etAnswer);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etPassword1 = (EditText) findViewById(R.id.etREPassword);
        final Button bRegister = (Button) findViewById(R.id.bRegister);
        final CheckBox bCek = (CheckBox) findViewById(R.id.bCek);

        bCek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bCek.isChecked()) {
                    bRegister.setEnabled(true);
                }else
                    bRegister.setEnabled(false);
            }
            });

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
                progressDialog.setMessage("Registering");
                progressDialog.show();

                final String name = etName.getText().toString();
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                final String password1 = etPassword1.getText().toString();
                final String question = etQuestion.getText().toString();
                final String answer = etAnswer.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            if (name.isEmpty() || username.isEmpty() || password.isEmpty() || password1.isEmpty() || question.isEmpty() || answer.isEmpty()) {
                                Toast.makeText(RegisterActivity.this, "Harus diisi jangan kosong ",Toast.LENGTH_SHORT).show();
                            }else if(password.equals(password1) && password.length()==6 && password1.length()==6 && bCek.isChecked()){
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                progressDialog.dismiss();
                                if (success) {
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                                    RegisterActivity.this.startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(RegisterActivity.this, "Password Harus Sama dan 6 karakter ",Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(name, username, password, question, answer, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}