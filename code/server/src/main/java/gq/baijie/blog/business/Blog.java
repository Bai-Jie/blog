package gq.baijie.blog.business;

public class Blog {

  private String id;

  private String title;

  private BlogContent content;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public BlogContent getContent() {
    return content;
  }

  public void setContent(BlogContent content) {
    this.content = content;
  }

}
