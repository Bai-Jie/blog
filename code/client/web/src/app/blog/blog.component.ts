import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { Blog } from '../blog-service'
import { BlogService } from '../blog-service'

@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.css']
})
export class BlogComponent implements OnInit {

  private blog: Blog;

  constructor(private route: ActivatedRoute, private router: Router, private blogService: BlogService) {}

  ngOnInit() {
    this.route.params.forEach((params: Params) => {
      let id = params['id'];
      this.blogService.getBlog(id).subscribe(next => this.blog = next);
    });
  }

}
