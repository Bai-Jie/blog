import { Component } from '@angular/core';

import { AccountService } from './account-service'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'Blog Name Here';

  constructor(private accountService: AccountService) { }

  onClickLogin(userId:string, password:string) {
    this.accountService.login(userId, password);
  }

  onClickLogout() {
    this.accountService.logout();
  }

}
