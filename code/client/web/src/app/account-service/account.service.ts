import { Injectable } from '@angular/core';
import { Http, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import { environment } from '../../environments/environment';
import { Account } from '.'

@Injectable()
export class AccountService {

  private authenticateUrl = environment.restApiBaseUrl + 'account';  // URL to web api

  private currentAccount: Account = null;
  private token: string = null;

  constructor(private http: Http) { }

  login(userId: string, password: string): Observable<boolean> {
    return Observable.create(subscriber => {
      let search = new URLSearchParams();
      search.append("accountId", userId);
      search.append("password", password);
      this.http.post(`${this.authenticateUrl}/tokens`, null, {search: search}).subscribe(response => {
        this.token = response.json().data.token;
        this.currentAccount = {id: userId, password: password};
        subscriber.next(true);
      }, error => {
        subscriber.error(error);
      }, () => subscriber.complete());
    });
  }

  logout() {
    this.http.delete(`${this.authenticateUrl}/accounts/${this.currentAccount.id}/tokens/${this.token}`).subscribe(response => {
      this.currentAccount = null;
      this.token = null;
    })
  }

}
