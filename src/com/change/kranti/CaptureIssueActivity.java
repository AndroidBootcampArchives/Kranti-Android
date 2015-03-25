package com.change.kranti;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import repository.IssueRepository;

public class CaptureIssueActivity extends Activity {
    private IssueRepository issueRepository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        issueRepository = new IssueRepository(getApplicationContext());
        setContentView(R.layout.create);
    }


    public void submit(View view) {

        EditText description = (EditText) findViewById(R.id.descriptionText);
        EditText title = (EditText) findViewById(R.id.titleText);
        String issueTitle =  title.getText().toString();
        String issueDescription =  description.getText().toString();
        issueRepository.createIssue(issueTitle,issueDescription);
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
