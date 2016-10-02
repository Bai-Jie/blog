package gq.baijie.blog.business;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MockBlogService implements BlogService {

  private static final int BLOG_NUMBER = 11;

  private static final BlogContent CONTENT = new BlogContent();

  static {
    CONTENT.setType("text/markdown");
    CONTENT.setData(readSampleContent());
  }

  private static String readSampleContent() {
    try {
      return IOUtils.toString(
          MockBlogService.class.getResourceAsStream("SampleBlogContent.md"),
          "UTF-8"
      );
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Blog> getBlogs() {
    List<Blog> blogs = new ArrayList<>(BLOG_NUMBER);
    for (int i = 0; i < BLOG_NUMBER; i++) {
      blogs.add(getBlog(String.valueOf(i)));
    }

    return blogs;
  }

  @Override
  public Blog getBlog(String id) {
    Blog blog = new Blog();
    blog.setId(id);
    blog.setTitle("Blog " + id);
    blog.setContent(CONTENT);

    return blog;
  }

}
