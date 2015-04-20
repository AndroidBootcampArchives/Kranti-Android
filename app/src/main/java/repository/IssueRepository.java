package repository;

import Storage.DataStorage;
import android.content.Context;
import model.Issue;

import java.util.List;

public class IssueRepository {
    private DataStorage dataStorage;

  public IssueRepository(Context context) {
        dataStorage = new DataStorage(context);
    }

    public void createIssue(String title, String description) {
        Issue issue = new Issue(title,description);
        dataStorage.store(issue);
    }
    public List<Issue> getIssues() {
      return dataStorage.getAllIssues();
    }
}
