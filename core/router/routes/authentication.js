import  { Router }      from    'express'
import  hash            from    'password-hash'
import  log             from    '../../service/log-service'
import  config          from '../../../resources/config'
import  messages        from '../../../resources/messages'
import  contract        from    '../../models/contract-model'
import  contract_login  from    '../../models/contract-login-model'
import  jwt             from    'jsonwebtoken'
import  exeption        from    '../../service/exception-service'
import  status          from    '../../models/http_status_code'

export default Router()

    .post(`/get-token`, (request,   response) =>  {

        let phone           =   request.body.phone;
        let password_hash   =   request.body.password_hash;
        log.info(`home`,`[/get-token] phone: ${phone}`);

        if (phone)  {
            contract_login.findOne({ phone: phone }).then(contract_login => {

                if (hash.verify(contract_login.password_hash, password_hash)){
                    jwt.sign({ phone: data.phone }, config.JWT.private_key, { algorithm: config.JWT.algorithm }, (error, token) => {
                        exeption.send(200,response,`AUTHENTICATION`, messages.ENG.authentication.authentication_fail);
                    });
                } else {
                    exeption.send(status.STATUS_NOT_FOUND,response, `AUTHENTICATION`, messages.ENG.authentication.authentication_fail)
                }
            });
        }
        response.send('f*ck you')
    })

    .post('/create_contract', (request, response) => {
        // console.log(passwordHash.verify('password123', hashedPassword)); // true

        let phone = request.body.phone;
        let password = request.body.password;

        if (phone && password) {
            let password_hash = hash.generate(password, {
                algorithm: 'RS256',
            });
            new contract({
                id: data.id,
                token: data.token
            }).save().then(contract => {
                new contract_login({
                    phone: phone,
                    password_hash: password_hash,
                    id_contract:  contract.id,
                    id_session: [request.sessionID],
                    last_ip: request.headers['x-forwarded-for'] || request.connection.remoteAddress
                }).save().then(contract_login => {
                    exeption.send(status.STATUS_CREATED, response,`AUTHENTICATION`, `contract and contract_login have been created [id: ${contract_login.id_account}]`);
                }).catch(error => {
                    exeption.send_error(status.STATUS_INTERNAL_SERVER_ERROR, response,`AUTHENTICATION`, `can't save data in db error: ${error.message}`);
                });
            }).catch(error => {
                exeption.send(status.STATUS_INTERNAL_SERVER_ERROR, response,`AUTHENTICATION`, `can't save data in db error: ${error.message}`);
            });

        }
    })
