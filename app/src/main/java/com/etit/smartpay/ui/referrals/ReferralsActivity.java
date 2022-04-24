package com.etit.smartpay.ui.referrals;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.etit.smartpay.LoginActivity;
import com.etit.smartpay.R;
import com.etit.smartpay.ui.enroll.EnrollActivity;

public class ReferralsActivity extends AppCompatActivity {

    EditText name, number, subject, year;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refferals);

        name = findViewById(R.id.refer_name);
        number = findViewById(R.id.refer_no);
        subject = findViewById(R.id.refer_subject);
        year = findViewById(R.id.refer_year);

        findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = ProgressDialog.show(ReferralsActivity.this, "", "Submitting Information...");

                name.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        name.setText(null);
                        number.setText(null);
                        subject.setText(null);
                        year.setText(null);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (dialog != null) {
                                    dialog.dismiss();
                                }

                                new AlertDialog.Builder(ReferralsActivity.this)
                                        .setTitle("Referral Submitted")
                                        .setMessage("Your Referral Friend Details Submitted Successfully!")
                                        .setPositiveButton(android.R.string.ok, null)
                                        .show();
                            }
                        });
                    }
                }, 2000);
            }
        });
    }
}