import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { BlogComponent }  from './blog/blog.component';
import { BlogListComponent }    from './blog-list/blog-list.component';

const routes: Routes = [
  { path: '', redirectTo: '/blog', pathMatch: 'full' },
  {
      path: 'blog',
      children: [
          { path: ':id', component: BlogComponent },
          { path: '', component: BlogListComponent }
      ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: []
})
export class BlogRoutingModule { }
