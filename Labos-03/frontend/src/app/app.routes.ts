import { RouterModule, Routes } from '@angular/router';
import { VoziloComponent } from './components/vozilo/vozilo.component';
import { NgModule } from '@angular/core';

export const routes: Routes = [
    {
        path: 'vozila',
        component: VoziloComponent
    }
];

