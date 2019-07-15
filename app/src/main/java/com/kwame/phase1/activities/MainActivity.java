package com.kwame.phase1.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kwame.phase1.R;

public class MainActivity extends AppCompatActivity {

    private Context context = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void about(View view) {
        startActivity(new Intent(context, AboutActivity.class));
    }

    public void profile(View view) {
        startActivity(new Intent(context, ProfileActivity.class));
    }
}
