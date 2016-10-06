import { Injectable } from '@angular/core';

import { Account } from '.'

@Injectable()
export class AccountService {

  private currentAccount: Account = null;

  constructor() { }

  login(userId: string, password: string) {
    this.currentAccount = {id: userId, password: password};
  }

  logout() {
    this.currentAccount = null;
  }

}
