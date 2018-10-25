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

import static com.powerproject.login.R.id.etFAnswer;
import static com.powerproject.login.R.id.etUsername;

public class ForgetAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_area);

        Intent intent = getIntent();
        final String question = intent.getStringExtra("pertanyaan");

        final EditText etQuetion = (EditText) findViewById(R.id.etFQuestion);
        final EditText etAnswer = (EditText) findViewById(etFAnswer);
        final Button bContinue = (Button) findViewById(R.id.bContinue);

        // Display user details
        etQuetion.setText(question);

        bContinue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String question = etQuetion.getText().toString();
                final String answer = etAnswer.getText().toString();

                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(answer.isEmpty()){
                                Toast.makeText(ForgetAreaActivity.this, "Tidak Boleh Kosong!!!", Toast.LENGTH_SHORT).show();
                            }else if (success) {
                                String username = jsonResponse.getString("username");
                                Intent intent = new Intent(ForgetAreaActivity.this, ResetPasActivity.class);
                                intent.putExtra("username", username);
                                ForgetAreaActivity.this.startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(ForgetAreaActivity.this, "Invalid Answer", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                ForgetAreaRequest forgetAreaActivity = new ForgetAreaRequest(question, answer, responseListener);
                RequestQueue queue = Volley.newRequestQueue(ForgetAreaActivity.this);
                queue.add(forgetAreaActivity);
            }
        });
    }
}
