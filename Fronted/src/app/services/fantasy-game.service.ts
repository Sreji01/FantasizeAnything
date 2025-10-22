import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FantasyGameService {
  private url = 'http://localhost:8080/api';


  constructor(private http: HttpClient) { }

  createFantasyGame(fantasyGame: FantasyGame): Observable<FantasyGame>{
    return this.http.post<FantasyGame>(`${this.url}/fantasy-game`, fantasyGame);
  }

}

export interface ScoringRule {
  description: string,
  numberOfPoints: number
}

export interface Characteristic {
  name: string,
  value: string
}

export interface Player {
  firstName: string,
  lastName: string,
  birthDate: string;
  price: number,
  image: File | string,
  characteristics: Characteristic[]
}

export interface FantasyGame {
  name: string,
  description: string,
  numberOfPlayersPerTeam: number,
  budget: number,
  scoringRules: ScoringRule[],
  players: Player[]
}
