import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, tap} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private url = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) { }

  register(request: RegisterRequest): Observable<UserResponse> {
    return this.http.post<UserResponse>(`${this.url}/register`, request);
  }

  login(request: LoginRequest): Observable<UserResponse> {
    return this.http.post<UserResponse>(`${this.url}/login`, request).pipe(
      tap({
        next: (res) => {
          if (res.token) {
            localStorage.setItem('token', res.token);
          }
        },
        error: (err) => {
          console.error('Login error: ', err);
        }
      })
    )
  }
}

export interface RegisterRequest {
  username: string;
  email: string;
  password: string;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface UserResponse {
  username: string;
  email: string;
  token?: string;
}
