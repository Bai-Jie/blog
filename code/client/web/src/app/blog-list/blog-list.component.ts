import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable'

import { Blog } from '../blog-service'
import { BlogService } from '../blog-service'

@Component({
  selector: 'app-blog-list',
  templateUrl: './blog-list.component.html',
  styleUrls: ['./blog-list.component.css']
})
export class BlogListComponent implements OnInit {

  private blogs: Observable<Blog[]>;

  constructor(private blogService: BlogService) { }

  ngOnInit() {
    this.blogs = this.blogService.getBlogList();
  }

}
