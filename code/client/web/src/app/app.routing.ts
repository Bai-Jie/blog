import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { BlogComponent }  from './blog/blog.component';
import { BlogListComponent }    from './blog-list/blog-list.component';

const appRoutes: Routes = [
  { path: '', redirectTo: '/blog', pathMatch: 'full' },
  {
      path: 'blog',
      children: [
          { path: ':id', component: BlogComponent },
          { path: '', component: BlogListComponent }
      ]
  }
];

export const appRoutingProviders: any[] = [

];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
