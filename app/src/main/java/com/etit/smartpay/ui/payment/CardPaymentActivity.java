package com.etit.smartpay.ui.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.etit.smartpay.HomeActivity;
import com.etit.smartpay.R;
import com.etit.smartpay.ui.enroll.EnrollActivity;

public class CardPaymentActivity extends AppCompatActivity {

    TextView amountTxt;
    EditText cardNumber, cardName, cvv, month, year;
    ProgressDialog dialog;
    String className;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_payment);

        amountTxt = findViewById(R.id.amount);
        cardName = findViewById(R.id.cardName);
        cardNumber = findViewById(R.id.cardNumber);
        cvv = findViewById(R.id.cvv);
        month = findViewById(R.id.month);
        year = findViewById(R.id.year);

        boolean hasData = getIntent().hasExtra("data");
        if (hasData) {
            String amount = getIntent().getStringExtra("data");
            className = getIntent().getStringExtra("className");
            amountTxt.setText(amount);
        }

        findViewById(R.id.btnPay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = ProgressDialog.show(CardPaymentActivity.this, "", "Payment in Progress.", false, false);

                String name = cardName.getText().toString();
                String number = cardNumber.getText().toString();
                String cvvValue = cvv.getText().toString();
                String exMonth = month.getText().toString();
                String exYear = year.getText().toString();

                year.setText("");
                month.setText("");
                cvv.setText("");
                cardName.setText("");
                cardNumber.setText("");

                amountTxt.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (dialog != null) {
                            dialog.dismiss();
                        }

                        new AlertDialog.Builder(CardPaymentActivity.this)
                                .setTitle("Payment Successful")
                                .setMessage("Start enrolling to the class?")
                                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        openEnrollActivity();
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        openHomeActivity();
                                    }
                                })
                                .show();
                    }
                }, 3000);
            }
        });
    }

    private void openEnrollActivity() {
        Intent intent = new Intent(CardPaymentActivity.this, EnrollActivity.class);
        intent.putExtra("className", className);
        startActivity(intent);
        finish();
    }

    private void openHomeActivity() {
        Intent intent = new Intent(CardPaymentActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}