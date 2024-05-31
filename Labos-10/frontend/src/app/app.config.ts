import { ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { jwtInterceptor } from './interceptor/jwt.interceptor';
import { TranslateModule } from '@ngx-translate/core';
import { provideTranslation } from './translate-loader';

export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes),
     provideHttpClient(),
     provideHttpClient(withInterceptors([
      jwtInterceptor
  ])),
    importProvidersFrom(TranslateModule.forRoot(provideTranslation()))
]
};
