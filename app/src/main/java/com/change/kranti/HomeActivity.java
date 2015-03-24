package com.change.kranti;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }

    public void reportIssue(View view){
        Intent intent = new Intent(this, CaptureIssueActivity.class);
        startActivity(intent);
    }
}
