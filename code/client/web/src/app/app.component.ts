import { Component } from '@angular/core';

import { AccountService } from './account-service'

declare var $: any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'Blog Name Here';

  isLoginFailed = false;

  constructor(private accountService: AccountService) { }

  onClickLogin(userId:string, password:string) {
    this.isLoginFailed = false;
    this.accountService.login(userId, password).subscribe(result => {
      $('#loginModal').modal('hide');
    }, error => {
      this.isLoginFailed = true;
      console.log(`error: ${error}`);
    });
  }

  onClickLogout() {
    this.accountService.logout();
  }

}
