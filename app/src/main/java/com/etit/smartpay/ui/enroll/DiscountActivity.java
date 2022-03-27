package com.etit.smartpay.ui.enroll;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.etit.smartpay.HomeActivity;
import com.etit.smartpay.R;
import com.etit.smartpay.model.User;
import com.etit.smartpay.ui.payment.CardPaymentActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class DiscountActivity extends AppCompatActivity {

    ProgressDialog dialog;
    TextView btnEnroll, btnCancel;
    private User user;

    TextView finalPayment, discount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);

        user = User.getInstance();

        btnEnroll = findViewById(R.id.btnEnroll);
        btnCancel = findViewById(R.id.btnCancel);
        TextView className = findViewById(R.id.className);
        TextView tutorName = findViewById(R.id.tutor);
        TextView location = findViewById(R.id.location);
        TextView time = findViewById(R.id.time);
        TextView fee = findViewById(R.id.fee);

        finalPayment = findViewById(R.id.finalPayment);
        discount = findViewById(R.id.txtDiscount);

        boolean hasData = getIntent().hasExtra("data");
        if (hasData) {
            String intentData = getIntent().getStringExtra("data");
            String[] data = intentData.split("&");
            String classID = data[0].split("=")[1];

            className.setText(data[1].split("=")[1]);
            tutorName.setText(data[2].split("=")[1]);
            location.setText(data[3].split("=")[1]);
            time.setText(data[4].split("=")[1]);
            fee.setText(data[5].split("=")[1]);

            dialog = ProgressDialog.show(DiscountActivity.this, "", "Checking Payment Info");
            int payment = checkPayment(classID, user.getId());
            int feeValue = Integer.parseInt(data[5].split("=")[1].split(" ")[0]);

            btnEnroll.postDelayed(new Runnable() {
                @Override
                public void run() {
                    applyDiscount(feeValue, payment);

                    if (dialog != null) {
                        dialog.dismiss();
                    }
                }
            }, 2500);

        } else {
            finish();
        }

        btnEnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnEnroll.getText().equals("Pay")) {
                    showBottomSheetDialog();
                } else {
                    enrollStudent();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiscountActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void enrollStudent(){

    }

    private void showBottomSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog);

        LinearLayout visa = bottomSheetDialog.findViewById(R.id.payVisa);
        LinearLayout amex = bottomSheetDialog.findViewById(R.id.payAmex);
        LinearLayout lankaqr = bottomSheetDialog.findViewById(R.id.payLankaQR);
        LinearLayout ezcash = bottomSheetDialog.findViewById(R.id.payEzCash);

        visa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPaymentView();
            }
        });

        amex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPaymentView();
            }
        });

        bottomSheetDialog.show();
    }

    private void openPaymentView() {
        Intent intent = new Intent(DiscountActivity.this, CardPaymentActivity.class);
        intent.putExtra("data", finalPayment.getText().toString());
        startActivity(intent);
    }

    private int checkPayment(String classID, int studentID) {
        if (studentID == 1) {
            return 50;
        }
        if (classID.equals("c002")) {
            return 20;
        }

        if (classID.equals("c003")) {
            return 30;
        }

        if (studentID == 2) {
            return 100;
        }

        return 0;
    }
    private void applyDiscount(int fee, int payment) {
        if (payment == 100) {
            discount.setText("Fully Paid");
            finalPayment.setText("0 LKR");
            btnEnroll.setText("Enroll");
        } else if (payment == 0) {
            discount.setText(R.string.no_discount);
            finalPayment.setText(String.format("%d LKR", fee));
            btnEnroll.setText("Pay");
        } else {
            discount.setText(String.format("You are eligible for %d%% discount", payment));
            finalPayment.setText(String.format("%d LKR", fee - (fee*payment)/100));
            btnEnroll.setText("Pay");
        }
    }


}