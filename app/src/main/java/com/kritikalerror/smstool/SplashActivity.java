package com.kritikalerror.smstool;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SplashActivity.this);
        alertDialogBuilder.setTitle("WARNING!!!");
        alertDialogBuilder
                .setMessage("This app is in ALPHA, so it may be unstable. Also I don't take any responsibility if you choose to use this app illegally. If you don't want to use this app press back, otherwise proceed with caution!")
                .setCancelable(false)
                .setPositiveButton("Agree",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
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

        // Handle permissions issue here
    }
}
