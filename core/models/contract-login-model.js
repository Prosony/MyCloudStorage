import  mongoose    from    '../service/db-service'

const contract_login = new mongoose.Schema({
    phone: {
        type: String,
        unique: true,
        require: true
    },
    password_hash: {
        type: String,
        require: true
    },
    id_contract: {
        type: String,
        require: true
    },
    id_session: {
        type: [String],
        require: true
    },
    last_ip: {
      type: String,
      require: true
    },

    auth_fail: {
        type: Number,
        default: 0
    },
    last_auth: {
        type : Date,
        default: Date.now,
        // require: true
    }
});

export default mongoose.model('contract_login', contract_login)