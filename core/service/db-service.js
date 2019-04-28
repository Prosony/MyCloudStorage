import  mongoose    from    'mongoose'
import  config      from '../../resources/config'
import  log         from    './log-service'

const URI = config.DataBase.URI;
mongoose.Promise = global.Promise;

mongoose.connect(URI, (error, db) => { // { useNewUrlParser: true },
    if(error) {
        log.error('DATABASE', `Ops! some error with connection: ${error.message}`);
    } else {
        log.success('DATABASE', 'Connection to DB established!');
        // db.close();
    }
});

export default mongoose

