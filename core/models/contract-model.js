import  mongoose    from    '../service/db-service'

const contract = new mongoose.Schema({
    id: {
        type: String,
        unique: true,
        require: true
    },
    token: {
        type: String,
        unique: true,
        require: true
    }
});

export default mongoose.model('contract', contract)