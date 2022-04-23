package com.etit.smartpay;

import android.Manifest;
import android.animation.Animator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.etit.smartpay.adapters.ClassAdapter;
import com.etit.smartpay.model.TuitionClass;
import com.etit.smartpay.model.User;
import com.etit.smartpay.ui.profile.ProfileActivity;
import com.etit.smartpay.ui.qr.QRScanActivity;
import com.etit.smartpay.ui.referrals.RefferalsActivity;
import com.etit.smartpay.ui.search.SearchActivity;
import com.etit.smartpay.utils.Utils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements ActivityCompat.OnRequestPermissionsResultCallback {

    private static final int PERMISSION_REQUEST_CAMERA = 0;

    FloatingActionButton fab, fab1, fab2, fab3, fab4, fab5;
    LinearLayout fabLayout1, fabLayout2, fabLayout3, fabLayout4, fabLayout5;
    boolean isFABOpen = false;
    private User user;
    List<TuitionClass> classes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        classes = getClasses();
        user = User.getInstance();
        TextView greeting = findViewById(R.id.txtGreeting);
        RecyclerView rvClasses = (RecyclerView) findViewById(R.id.homeList);

        ClassAdapter adapter = new ClassAdapter(classes);
        rvClasses.setAdapter(adapter);
        rvClasses.setLayoutManager(new LinearLayoutManager(this));

        greeting.setText(String.format("Greetings, %s %s", user.getFirstName(), user.getLastName()));

        fabLayout1 = findViewById(R.id.fabLayout1);
        fabLayout2 = findViewById(R.id.fabLayout2);
        fabLayout3 = findViewById(R.id.fabLayout3);
        fabLayout4 = findViewById(R.id.fabLayout4);
        fabLayout5 = findViewById(R.id.fabLayout5);
        fab = findViewById(R.id.fab);
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        fab3 = findViewById(R.id.fab3);
        fab4 = findViewById(R.id.fab4);
        fab5 = findViewById(R.id.fab5);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFABOpen) {
                    showFABMenu();
                } else {
                    closeFABMenu();
                }
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCameraPreview();
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSearch();
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startProfile();
            }
        });

        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startReferrals();
            }
        });

        fab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDiscounts();
            }
        });
    }

    private List<TuitionClass> getClasses() {
        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "classes.json");
        Log.i("ETIT", jsonFileString);
        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<TuitionClass>>() { }.getType();
        List<TuitionClass> classes = gson.fromJson(jsonFileString, listUserType);
        return classes;
    }


    private void showFABMenu() {
        isFABOpen = true;
        fabLayout1.setVisibility(View.VISIBLE);
        fabLayout2.setVisibility(View.VISIBLE);
        fabLayout3.setVisibility(View.VISIBLE);
        fabLayout4.setVisibility(View.VISIBLE);
        fabLayout5.setVisibility(View.VISIBLE);
        fab.animate().rotationBy(90);
        fabLayout1.animate().translationY(-getResources().getDimension(R.dimen.standard_40));
        fabLayout2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        fabLayout3.animate().translationY(-getResources().getDimension(R.dimen.standard_165));
        fabLayout4.animate().translationY(-getResources().getDimension(R.dimen.standard_225));
        fabLayout5.animate().translationY(-getResources().getDimension(R.dimen.standard_285));
    }

    private void closeFABMenu() {
        isFABOpen = false;
        fab.animate().rotation(0);
        fabLayout1.animate().translationY(0);
        fabLayout2.animate().translationY(0);
        fabLayout3.animate().translationY(0);
        fabLayout4.animate().translationY(0);
        fabLayout5.animate().translationY(0);
        fabLayout5.animate().translationY(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (!isFABOpen) {
                    fabLayout1.setVisibility(View.GONE);
                    fabLayout2.setVisibility(View.GONE);
                    fabLayout3.setVisibility(View.GONE);
                    fabLayout4.setVisibility(View.GONE);
                    fabLayout5.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    private void startCamera() {
        Intent intent = new Intent(this, QRScanActivity.class);
        startActivity(intent);
    }

    private void startSearch() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    private void startProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    private void startReferrals() {
        Intent intent = new Intent(this, RefferalsActivity.class);
        startActivity(intent);
    }

    private void startDiscounts() {
        Intent intent = new Intent(this, QRScanActivity.class);
        startActivity(intent);
    }

    private void showCameraPreview() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            startCamera();
        } else {
            requestCameraPermission();
        }
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CAMERA) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera();
            } else {
                Toast.makeText(getApplicationContext(), "Camera Access Needed for the QR Scan Function", Toast.LENGTH_LONG).show();
            }
        }
    }
}