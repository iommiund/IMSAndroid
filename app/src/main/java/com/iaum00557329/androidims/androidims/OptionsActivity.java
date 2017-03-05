package com.iaum00557329.androidims.androidims;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }


    public void openInstall(View view) {

        Intent intent = new Intent(OptionsActivity.this,InstallActivity.class);
        startActivity(intent);

    }

    public void openReplace(View view) {

        Intent intent = new Intent(OptionsActivity.this,ReplaceActivity.class);
        startActivity(intent);

    }

    public void openCollect(View view) {

        Intent intent = new Intent(OptionsActivity.this,CollectActivity.class);
        startActivity(intent);

    }
}
