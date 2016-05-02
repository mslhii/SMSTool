package com.kritikalerror.smstool;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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

        Button buttonLoad = (Button)findViewById(R.id.start);
        buttonLoad.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                mPhoneNumber = phoneBox.getText().toString();
                mReps = Integer.getInteger(repBox.getText().toString());
                mTextContents = textBox.getText().toString();
                SMSTask task = new SMSTask();
                task.execute();
            }});

    }

    public class SMSTask extends AsyncTask<Void, Void, Void> {

        String results;

        @Override
        protected Void doInBackground(Void... arg0) {
            results = "Success";
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Toast.makeText(MainActivity.this, results, Toast.LENGTH_SHORT).show();
            super.onPostExecute(result);
        }
    }
}
