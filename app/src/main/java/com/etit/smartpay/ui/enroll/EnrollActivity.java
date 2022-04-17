package com.etit.smartpay.ui.enroll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.etit.smartpay.LoginActivity;
import com.etit.smartpay.R;
import com.etit.smartpay.model.User;
import com.etit.smartpay.network.SMSRequest;
import com.etit.smartpay.network.SMSResponse;
import com.etit.smartpay.ui.payment.CardPaymentActivity;
import com.etit.smartpay.viewmodel.SMSViewModel;

public class EnrollActivity extends AppCompatActivity {

    private User user;
    private LinearLayout layout;
    ProgressDialog dialog;
    private SMSViewModel viewModel;
    private String className;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);

        boolean hasData = getIntent().hasExtra("className");
        if (hasData) {
            className = getIntent().getStringExtra("className");
        }

        user = User.getInstance();
        viewModel = ViewModelProviders.of(this).get(SMSViewModel.class);
        viewModel.getSMSResponseObserver().observe(this, new Observer<SMSResponse>() {
            @Override
            public void onChanged(SMSResponse smsResponse) {
                if (smsResponse != null) {
                    if (dialog != null) {
                        dialog.dismiss();
                    }

                    new AlertDialog.Builder(EnrollActivity.this)
                            .setTitle("Notified Successfully")
                            .setMessage("Your Parent's have been notified via SMS and email.")
                            .setPositiveButton(android.R.string.ok, null)
                            .show();
                }
            }
        });

        TextView greeting = findViewById(R.id.txtGreeting);

        greeting.setText(String.format("Hello, %s %s", user.getFirstName(), user.getLastName()));

        dialog = ProgressDialog.show(EnrollActivity.this, "", "Downloading Lecture Materials...");
        layout = findViewById(R.id.layout);

        notifyParents();
    }

    void notifyParents() {
        dialog.setMessage("Notifying Parents...");
        SMSRequest request = new SMSRequest(user.getFirstName(), user.getParentPhone(), className);
        viewModel.sendSMS(request);
    }
}