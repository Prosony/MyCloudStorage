import  express             from    'express';
import  Router              from    './router/router';
import  session             from    'express-session';
import  config              from    '../resources/config';
import  log                 from    './service/log-service';
import  body_parser         from    'body-parser';
import  mongoose            from    './service/db-service';
import  connect_mongo       from    'connect-mongo';
import  exception            from    './service/exception-service';
import  messages            from    '../resources/messages';


const   port            = config.Server.PORT;
let     application     = express();
const   mongo_store     = connect_mongo(session);

application
    .use(body_parser.json())
    .use(body_parser.urlencoded({ extended: false }))
    .use(session({
            secret: config.Session.SECRET,
            resave: config.Session.RESAVE,
            saveUninitialized: config.Session.SAVEUNITILAZIED,
            store: new mongo_store({ mongooseConnection: mongoose.connection })
    }))
    .use((request, response, next) => {
            response.header('Access-Control-Allow-Origin', '*');
            log.success('MIDDLEWARE', 'Access-Control-Allow-Origin allow');
            response.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');
            log.success('MIDDLEWARE', `Access-Control-Allow-Headers allow`);
            next()
    })
    .listen(port, () => {
        log.info(`server`,`server start on ${port}`);
    });

application.use(Router);
let mass = [];
mass.push(12);
mass.push(23);
exception.format(messages.ENG.authentication.registration_fail, mass );

// let getFiles = () => {
//
//     const path =`./contracts/`;
//     const output =`./output/`;
//     let first = true;
//
//     fs.getListFiles(path).then(list_dir_name => {
//
//         list_dir_name.forEach(file_name => {
//             fs.getJsonFromFile(path + file_name).then(file => {
//
//                 let json = JSON.parse(file);
//
//
//                 json_service.change_contract(json.contract);
//                 json_service.change_internet_accounts(json);
//                 json_services.change_tv_account(json);
//
//
//
//                 if (first) {
//                     first = false;
//                     fs.createDirectory(output);
//                     fs.createJson(output,json.contract.accountNumber, JSON.stringify(json))
//                 } else {
//                     fs.createJson(output,json.contract.accountNumber, JSON.stringify(json))
//                 }
//             })
//         })
//     });
// }


