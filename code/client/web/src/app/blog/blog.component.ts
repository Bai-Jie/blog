import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import * as MarkdownIt from 'markdown-it';

import { Blog } from '../blog-service'
import { BlogService } from '../blog-service'

@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.css']
})
export class BlogComponent implements OnInit {

  private blog: Blog;

  private blogStringContent: string;
  private blogHtmlContent: string;

  constructor(private route: ActivatedRoute, private router: Router, private blogService: BlogService) {}

  ngOnInit() {
    this.route.params.forEach((params: Params) => {
      let id = params['id'];
      this.blogService.getBlog(id).subscribe(next => this.setBlog(next));
    });
  }

  private setBlog(blog: Blog) {
    this.blog = blog;
    if (blog.content.type === 'text/markdown') {
      let md = new MarkdownIt({linkify: true});
      this.blogHtmlContent = md.render(blog.content.data);
    } else {
      this.blogStringContent = blog.content.data;
    }
  }

}
