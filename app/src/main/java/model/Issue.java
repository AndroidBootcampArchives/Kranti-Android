package model;

public class Issue {
  private String title;
  private String description;

  public Issue(String title, String description) {
    this.title = title;
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public String getTitle() {
    return title;
  }

  @Override
  public String toString() {
    return title;
  }
}
