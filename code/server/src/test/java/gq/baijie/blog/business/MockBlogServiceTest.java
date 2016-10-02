package gq.baijie.blog.business;

import org.junit.Test;

import java.util.List;

public class MockBlogServiceTest {

  @Test
  public void testGetBlog() {
    final Blog blog = new MockBlogService().getBlog("1");
    println(blog);
  }

  @Test
  public void testGetBlogs() {
    final List<Blog> blogs = new MockBlogService().getBlogs();
    blogs.forEach(this::println);
  }

  private void println(Blog blog) {
    println(String.format("##### %s #####", blog.getId()));
    println(blog.getTitle());
    println(blog.getContent().getType());
    println(blog.getContent().getData());
    println("");
  }

  private void println(Object x) {
    System.out.println(x);
  }

}
