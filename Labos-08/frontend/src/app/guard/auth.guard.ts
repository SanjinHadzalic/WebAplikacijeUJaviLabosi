import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { JwtDecoderService } from '../services/jwt-decoder.service';

export const AuthGuard: CanActivateFn = (route, state) => {
  const router = inject(Router)
  const jwtToken = localStorage.getItem('JWT');

  if(jwtToken) {
    return true;
  } else {
    router.navigate(['/login']);
    return false;
  }
};
