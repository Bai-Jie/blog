import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Rx';

import { Blog } from '.'
import { BLOGS } from './dummy-blogs'

@Injectable()
export class BlogService {

  constructor() { }

  public getBlogList(): Observable<Blog[]> {
    return Observable.of(BLOGS);
  }

  public getBlog(id: string): Observable<Blog> {
    return Observable.from(BLOGS).filter(blog => blog.id === id);
  }

}
