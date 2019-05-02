package com.example.jstore_android_sennafaris;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText emailInput = (EditText) findViewById(R.id.emailInput);
        final EditText passwordInput = (EditText) findViewById(R.id.passwordInput);
        final Button loginButton = (Button) findViewById(R.id.loginButton);
        final TextView registerClicakble = (TextView) findViewById(R.id.registerClickable);
        registerClicakble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regisIntent = new Intent (LoginActivity.this, RegisterActivity.class);
                startActivity(regisIntent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = emailInput.getText().toString();
                final String password = passwordInput.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String> () {
                    @Override
                    public void onResponse(String response) {
                     try {
                         JSONObject jsonResponse = new JSONObject(response);
                         if(jsonResponse != null) {
                             AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginActivity.this);
                             builder1.setMessage("Login Sukses").create().show();
                         }
                     } catch (JSONException e) {
                         AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginActivity.this);
                         builder1.setMessage("Login Gagal").create().show();
                     }
                    }
                };
                LoginRequest loginRequest = new LoginRequest (email, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);



//                if (email.equals("test@mail.com") && password.equals("test")) {
//                    AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginActivity.this);
//                    builder1.setMessage("Login Sukses").create().show();
//                }
//
//                else {
//                    AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginActivity.this);
//                    builder1.setMessage("Login Gagal").create().show();
//                }
            }
        });
    }

}
