import { Routes } from '@angular/router';
import { RegisterComponent } from './components/auth/register/register.component';
import {LoginComponent} from "./components/auth/login/login.component";
import {HomeComponent} from "./components/home/home.component";
import {FantasyGameFormComponent} from "./components/fantasy-game-form/fantasy-game-form.component";

export const routes: Routes = [
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent},
  {path: 'create-fantasy-game', component: FantasyGameFormComponent},
  { path: '', redirectTo: '/register', pathMatch: 'full' }
];

