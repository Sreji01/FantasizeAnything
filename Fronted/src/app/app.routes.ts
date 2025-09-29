import { Routes } from '@angular/router';
import { RegisterComponent } from './components/auth/register/register.component';

export const routes: Routes = [
  { path: 'register', component: RegisterComponent },
  { path: '', redirectTo: '/register', pathMatch: 'full' }
];

