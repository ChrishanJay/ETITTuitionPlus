package com.etit.smartpay.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.etit.smartpay.R;
import com.etit.smartpay.model.User;

public class ProfileActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user = User.getInstance();

        TextView studentFirstName = findViewById(R.id.first_name);
        TextView studentLastName = findViewById(R.id.last_name);
        TextView studentUserName = findViewById(R.id.user_name);
        TextView parentName = findViewById(R.id.parent_name);
        TextView parentPhone = findViewById(R.id.parent_phone);
        TextView parentEmail = findViewById(R.id.parent_email);

        studentFirstName.setText(user.getFirstName());
        studentLastName.setText(user.getLastName());
        studentUserName.setText(user.getUsername());
        parentName.setText(user.getParentName());
        parentPhone.setText(user.getParentPhone());
        parentEmail.setText(user.getParentEmail());

    }
}