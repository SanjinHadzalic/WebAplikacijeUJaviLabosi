import { RouterModule, Routes } from '@angular/router';
import { VoziloComponent } from './components/vozilo/vozilo.component';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { VoziloDetailsComponent } from './components/vozilo-details/vozilo-details.component';

export const routes: Routes = [
    {
        path: '',
        component: HomeComponent
    },
    {
        path: 'vozilo',
        component: VoziloComponent
    },
    {
        path: 'vozilo/:id',
        component: VoziloDetailsComponent
    }
];

