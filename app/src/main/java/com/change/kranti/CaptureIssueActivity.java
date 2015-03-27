package com.change.kranti;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import model.Issue;
import repository.IssueRepository;

public class CaptureIssueActivity extends Activity {

    private static final int IMAGE_CAPTURE = 0;
    private IssueRepository issueRepository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        issueRepository = new IssueRepository(getApplicationContext());
        setContentView(R.layout.create);
    }

    public void captureIssueImage(View view) {
      Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      startActivityForResult(intent, IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      if (requestCode == IMAGE_CAPTURE && resultCode == Activity.RESULT_OK && data != null) {

        showImage((Bitmap)data.getExtras().get("data"));
        return;
      }
      Toast.makeText(this, "Issue Image not captured !!", Toast.LENGTH_LONG).show();
    }

    public void submit(View view) {
      EditText description = (EditText) findViewById(R.id.descriptionText);
      EditText title = (EditText) findViewById(R.id.titleText);
      String issueTitle =  title.getText().toString();
      String issueDescription =  description.getText().toString();
      issueRepository.createIssue(issueTitle,issueDescription);

      List<Issue> issues = issueRepository.getIssues();
      for (Issue issue : issues) {
        System.out.println("issueTitle = " + issue.getTitle() + "issueDescription = " + issue.getDescription());
      }
      finish();
    }

    private void showImage(Bitmap data) {
      ImageView issueImageView = (ImageView) findViewById(R.id.capturedImage);
      issueImageView.setImageBitmap(data);
    }


}
