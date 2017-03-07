package com.iaum00557329.androidims.androidims;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class ReplaceActivity extends AppCompatActivity{

    //View Objects
    private Button scanResource;
    private TextView textViewResource;
    private EditText editTextOrderId;

    //code scanner object
    private IntentIntegrator codeScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace);

        //View Objects
        scanResource = (Button) findViewById(R.id.scanResource);
        textViewResource = (TextView) findViewById(R.id.textViewResource);
        editTextOrderId = (EditText) findViewById(R.id.orderId);

        //initializing scan object
        codeScan = new IntentIntegrator(this);

    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if code has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if code contains data
                try {
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());
                    //setting value to resource textview
                    textViewResource.setText(obj.getString("resource"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void scanResource(View view) {
        //initiating the qr code scan
        codeScan.initiateScan();
    }

    public void replaceResource(View view) {
        String orderId = editTextOrderId.getText().toString();
        String resource = textViewResource.getText().toString();
        String type = "replace";

        BackgroundWorkerReplace backgroundWorkerReplace = new BackgroundWorkerReplace(this);
        backgroundWorkerReplace.execute(type, orderId, resource);
    }
}
