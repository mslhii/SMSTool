package com.kritikalerror.smstool;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
