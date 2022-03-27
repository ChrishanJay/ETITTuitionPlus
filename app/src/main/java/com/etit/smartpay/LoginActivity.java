package com.etit.smartpay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.etit.smartpay.model.User;
import com.etit.smartpay.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText email, pword;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.txtEmail);
        pword = findViewById(R.id.txtPassword);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = email.getText().toString();
                String password = pword.getText().toString();

                dialog = ProgressDialog.show(LoginActivity.this, "", "Login in...");

                email.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        User user = login(username, password);
                        dialog.dismiss();
                        if (user != null) {
                            User.setInstance(user);
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Sorry! Username or Password is incorrect", Toast.LENGTH_LONG).show();
                        }
                    }
                }, 2000);
            }
        });
    }

    private User login(String username, String password) {
        User loggedUser = null;
        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "users.json");
        Log.i("ETIT", jsonFileString);
        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<User>>() { }.getType();
        List<User> users = gson.fromJson(jsonFileString, listUserType);

        if (users == null) {
            return null;
        }

        for (User user: users) {
            if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
                loggedUser = user;
                break;
            }
        }

        return loggedUser;
    }
}