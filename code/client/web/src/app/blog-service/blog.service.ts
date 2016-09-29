import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

import { Blog } from '.'

@Injectable()
export class BlogService {

  //private blogsUrl = 'api/blogs';  // URL to web api
  private blogsUrl = 'http://localhost/api/blogs';  // URL to web api

  constructor(private http: Http) { }

  public getBlogList(): Observable<Blog[]> {
    return this.http.get(this.blogsUrl).map(response => response.json().data);
  }

  public getBlog(id: string): Observable<Blog> {
    return this.http.get(`${this.blogsUrl}/${id}`).map(response => response.json().data);
  }

}
