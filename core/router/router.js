import  { Router }      from 'express';
import  authentication  from './routes/authentication';
import  session         from './routes/session';

export  default Router()
        .use(session)
        .use(`/authentication`, authentication)