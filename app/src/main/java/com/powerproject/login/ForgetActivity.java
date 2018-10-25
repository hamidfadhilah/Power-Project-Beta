package com.powerproject.login;

import android.app.Activity;
import android.content.Intent;
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

public class ForgetActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        final EditText etFUsername = (EditText) findViewById(R.id.etFUsername);

        final Button btFContinue = (Button) findViewById(R.id.btFContinue);

        btFContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etFUsername.getText().toString();

                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(username.isEmpty()){
                                Toast.makeText(ForgetActivity.this, "Username Tidak Boleh Kosong!!!", Toast.LENGTH_SHORT).show();
                            }else if (success) {
                                String question = jsonResponse.getString("pertanyaan");

                                Intent intent = new Intent(ForgetActivity.this, ForgetAreaActivity.class);
                                intent.putExtra("pertanyaan", question);
                                ForgetActivity.this.startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(ForgetActivity.this, "Invalid username", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                ForgetRequest forgetRequest = new ForgetRequest(username, responseListener);
                RequestQueue queue = Volley.newRequestQueue(ForgetActivity.this);
                queue.add(forgetRequest);
            }
        });
    }

}
