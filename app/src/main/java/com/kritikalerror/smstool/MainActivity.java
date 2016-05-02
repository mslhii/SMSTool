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

    private Button buttonLoad;
    private EditText phoneBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLoad = (Button)findViewById(R.id.start);
        buttonLoad.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                SMSTask task = new SMSTask();
                task.execute();
            }});

        phoneBox = (EditText)findViewById(R.id.phonebox);
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
