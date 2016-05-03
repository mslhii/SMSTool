package com.kritikalerror.smstool;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final public String CHARGING = "IMMA CHARGIN MA LAZER";
    final public String SHOOP = "SHOOP DA WHOOP";

    protected String mPhoneNumber;
    protected String mTextContents;
    protected int mReps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mReps = 0;
        mPhoneNumber = "";
        mTextContents = "";

        final EditText phoneBox = (EditText)findViewById(R.id.phonebox);
        final EditText textBox = (EditText)findViewById(R.id.textbox);
        final EditText repBox = (EditText)findViewById(R.id.repbox);

        final Button buttonLoad = (Button)findViewById(R.id.start);
        buttonLoad.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                if (buttonLoad.getText().equals(CHARGING))
                {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setTitle("WAIT!");
                    alertDialogBuilder
                            .setMessage("You haven't charged your lazers yet!")
                            .setCancelable(false)
                            .setNegativeButton("Back",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
                else
                {
                    String tempReps = repBox.getText().toString();
                    mPhoneNumber = phoneBox.getText().toString();
                    mReps = Integer.parseInt(tempReps);
                    mTextContents = textBox.getText().toString();
                    SMSTask task = new SMSTask();
                    task.execute();
                }
            }});

        textBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (textBox.getText().toString().isEmpty()) {
                    buttonLoad.setText(CHARGING);
                }
                else {
                    buttonLoad.setText(SHOOP);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });

    }

    public class SMSTask extends AsyncTask<Void, Integer, Void> {
        ProgressDialog mProgressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (mProgressDialog != null)
            {
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Firing lazers...");
            mProgressDialog.setMessage("0 lazers fired");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(mReps);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            //sendSMS();

            for (int i = 0; i < mReps; i++)
            {
                publishProgress(i);
                mProgressDialog.setProgress(i);
                Log.e("TEST", Integer.toString(i));
            }
            return null;
        }

        protected void onProgressUpdate(Integer... progUpdate) {
            mProgressDialog.setMessage(Integer.toString(progUpdate[0]) + " lazers fired");
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (mProgressDialog != null)
            {
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
        }

        private boolean sendSMS(){
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(mPhoneNumber, null, mTextContents, null, null);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            return true;
        }
    }
}
