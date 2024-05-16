import { HttpInterceptorFn } from '@angular/common/http';

export const jwtInterceptor: HttpInterceptorFn = (req, next) => {
  const jwtToken = localStorage.getItem('JWT')
  if(jwtToken) {
    try {
      const authReq = req.clone({
        
        headers: req.headers.set('Authorization', `Bearer ${jwtToken}`)
      });
      return next(authReq);

    } catch(error) {
      console.error('error in jwt interceptor', error)
    }
  }
  return next(req)
};
