package gq.baijie.blog.business;

import java.util.List;

public interface BlogService {

  List<Blog> getBlogs();

  Blog getBlog(String id);

}
