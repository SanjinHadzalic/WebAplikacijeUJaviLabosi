import { RouterModule, Routes } from '@angular/router';
import { VoziloComponent } from './components/vozilo/vozilo.component';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { VoziloDetailsComponent } from './components/vozilo-details/vozilo-details.component';
import { ReviewListComponent } from './components/review-list/review-list.component';
import { ReviewDetailsComponent } from './components/review-details/review-details.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AuthGuard } from './guard/auth.guard';

export const routes: Routes = [
    {
        path: '',
        component: HomeComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'vozilo',
        component: VoziloComponent
    },
    {
        path: 'vozilo/:id',
        component: VoziloDetailsComponent
    },
    {
        path: 'review',
        component: ReviewListComponent
    },
    {
        path: 'review/:id',
        component: ReviewDetailsComponent
    },
    {
        path: 'login',
        component:LoginComponent
    },
    {
        path:'register',
        component:RegisterComponent
    }
];

