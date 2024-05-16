import { HttpInterceptorFn } from '@angular/common/http';

export const jwtInterceptor: HttpInterceptorFn = (req, next) => {
  const jwtToken = localStorage.getItem('JWT')

  const authReq = req.clone({
    headers: req.headers.set('Authorization', `Bearer ${jwtToken}`)
  });
  return next(authReq);

};
