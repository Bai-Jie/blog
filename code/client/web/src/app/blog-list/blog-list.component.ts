import { Component, OnInit } from '@angular/core';

import { Blog } from '../shared'
import { BLOGS } from './dummy-blogs'

@Component({
  selector: 'app-blog-list',
  templateUrl: './blog-list.component.html',
  styleUrls: ['./blog-list.component.css']
})
export class BlogListComponent implements OnInit {

  private blogs: Blog[] = BLOGS;

  constructor() { }

  ngOnInit() {
  }

}
