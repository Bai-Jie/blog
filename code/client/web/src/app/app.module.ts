import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { BlogRoutingModule } from './app-routing.module'
import { AccountService } from './account-service'
import { BlogService } from './blog-service'
import { AppComponent } from './app.component';
import { BlogListComponent } from './blog-list/blog-list.component';
import { BlogComponent } from './blog/blog.component';

@NgModule({
  declarations: [
    AppComponent,
    BlogListComponent,
    BlogComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    BlogRoutingModule
  ],
  providers: [
    AccountService,
    BlogService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
