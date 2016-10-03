package com.kritikalerror.smstool;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    private int REQUEST_CODE_ASK_PERMISSIONS = 9999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Handle permissions issue here
        initializeWrapper();

        // Turn into background?
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SplashActivity.this);
        alertDialogBuilder.setTitle("WARNING!!!");
        alertDialogBuilder
                .setMessage("This app is in ALPHA, so it may be unstable. Also I don't take any responsibility if you choose to use this app illegally. If you don't want to use this app press back, otherwise proceed with caution!")
                .setCancelable(false)
                .setPositiveButton("Agree",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Back",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void showOKAlertMessage(String message, DialogInterface.OnClickListener okListener) {
        new android.support.v7.app.AlertDialog.Builder(SplashActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    //
    private boolean initializeWrapper() {
        int hasCameraPermission = ContextCompat.checkSelfPermission(SplashActivity.this,
                Manifest.permission.SEND_SMS);
        if (hasCameraPermission != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this,
                    Manifest.permission.SEND_SMS)) {
                showOKAlertMessage("You need to allow app to use the camera for the app to function properly",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(SplashActivity.this,
                                        new String[]{Manifest.permission.SEND_SMS},
                                        REQUEST_CODE_ASK_PERMISSIONS);
                            }
                        });
            }
            ActivityCompat.requestPermissions(SplashActivity.this,
                    new String[] {Manifest.permission.SEND_SMS},
                    REQUEST_CODE_ASK_PERMISSIONS);
        }

        int hasWriteStoragePermission = ContextCompat.checkSelfPermission(SplashActivity.this,
                Manifest.permission.READ_CONTACTS);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this,
                    Manifest.permission.READ_CONTACTS)) {
                showOKAlertMessage("You need to allow access to external storage to save photos for the app to function properly",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(SplashActivity.this,
                                        new String[]{Manifest.permission.READ_CONTACTS},
                                        REQUEST_CODE_ASK_PERMISSIONS);
                            }
                        });
            }
            ActivityCompat.requestPermissions(SplashActivity.this,
                    new String[] {Manifest.permission.READ_CONTACTS},
                    REQUEST_CODE_ASK_PERMISSIONS);
        }
        return true;
    }
}
