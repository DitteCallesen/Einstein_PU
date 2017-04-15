package com.example.diteh.einstein;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        final TextView tvRegisterLink = (TextView) findViewById(R.id.tvRegisterhere);
        final Button bLogin = (Button) findViewById(R.id.blogin);

        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                // Response received from the server
                final Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                String name = jsonResponse.getString("name");
                                String username = jsonResponse.getString("username");
                                String checkpass = jsonResponse.getString("password");
                                String position = jsonResponse.getString("position");
                                if (!checkpass.equals(password)) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                    builder.setMessage("Wrong password")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                } else {
                                    if (position.equals("Student")) {
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        Bundle extras = new Bundle();
                                        extras.putString("name", name);
                                        extras.putString("username", username);
                                        extras.putString("position", position);
                                        intent.putExtras(extras);
                                        LoginActivity.this.startActivity(intent);
                                        finish();
                                    } else {

                                        Intent intent = new Intent(LoginActivity.this, TeachingActivity.class);
                                        Bundle extras = new Bundle();
                                        extras.putString("name", name);
                                        extras.putString("username", username);
                                        extras.putString("position", position);
                                        intent.putExtras(extras);
                                        LoginActivity.this.startActivity(intent);
                                        finish();
                                    }
                                }
                            } else {

                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }


    public class LoginRequest extends StringRequest {

        private static final String LOGIN_REQUEST_URL = "https://truongtrxu.000webhostapp.com/Login.php";
        private Map<String, String> params;

        public LoginRequest(String username, String password, Response.Listener<String> listener) {
            super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
            params = new HashMap<>();
            params.put("username", username);
            params.put("password", password);

        }

        @Override
        public Map<String, String> getParams() {
            return params;
        }
    }


}
