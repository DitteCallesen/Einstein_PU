package com.example.diteh.einstein;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    protected JSONObject jsonResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPass1);
        final EditText etPassword2 = (EditText) findViewById(R.id.etPass2);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);


        final Spinner spin = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.typer, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);


        final Button bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = etName.getText().toString();
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                final String password2 = etPassword2.getText().toString();
                final String email = etEmail.getText().toString();
                final String stilling = spin.getSelectedItem().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            jsonResponse = new JSONObject(response);

                            boolean succes = jsonResponse.getBoolean("success");

                            if (succes) {
                                Toast.makeText(RegisterActivity.this, "Account created",
                                        Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);

                            } else {
                                boolean nameAvail = jsonResponse.getBoolean("nameAvil");
                                boolean emailAvil = jsonResponse.getBoolean("emailAvil");

                                if (!nameAvail) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                    builder.setMessage("Username has already been taken.")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                } else if (!emailAvil) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                    builder.setMessage("Email is already used.")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                if (checkValid(password, password2, email)) {
                    RegisterRequest registerRequest = new RegisterRequest(name, username, email, password, stilling, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(registerRequest);
                }
            }
        });
    }


    public boolean checkValid(String pass1, String pass2, String email) {

        if (!pass1.equals(pass2)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            builder.setMessage("The passwords do not match.")
                    .setNegativeButton("Retry", null)
                    .create()
                    .show();
            return false;
        } else if (!email.contains("@")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            builder.setMessage("Email address is not valid.")
                    .setNegativeButton("Retry", null)
                    .create()
                    .show();
            return false;
        }

        return true;
    }

    public class RegisterRequest extends StringRequest {

        private static final String REGISTER_REQUEST_URL = "https://truongtrxu.000webhostapp.com/Register.php";
        private Map<String, String> params;

        public RegisterRequest(String name, String username, String email, String password, String position, Response.Listener<String> listener) {

            super(Method.POST, REGISTER_REQUEST_URL, listener, null);
            params = new HashMap<>();
            params.put("name", name);
            params.put("username", username);
            params.put("email", email);
            params.put("password", password);
            params.put("position", position);
        }

        @Override
        public Map<String, String> getParams() {
            return params;
        }
    }

    public void backOnClick(View view) {
        Intent intent;
        intent = new Intent(RegisterActivity.this, LoginActivity.class);
        RegisterActivity.this.startActivity(intent);
        finish();
    }

    //use anndroid back button
    @Override
    public void onBackPressed() {
        Intent intent;
        intent = new Intent(RegisterActivity.this, LoginActivity.class);
        RegisterActivity.this.startActivity(intent);
        finish();
    }



}
