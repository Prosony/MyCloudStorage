'use strict';
import fs from 'fs';
var deleteFolderRecursive = (path) => {
    if (fs.existsSync(path)) {
        fs.readdirSync(path).forEach(function(file, index){
            var curPath = path + "/" + file;
            if (fs.lstatSync(curPath).isDirectory()) { // recurse
                deleteFolderRecursive(curPath);
            } else { // delete file
                fs.unlinkSync(curPath);
            }
        });
        fs.rmdirSync(path);
    }
};
export default {
    async getListFiles(path) {
         return await fs.readdirSync(path, (error, list_dir_name) => {
             if (error) {
                 console.log(error);
             }
             return list_dir_name
         });
    },

    async getJsonFromFile(path) {
        return await fs.readFileSync(path, (error, file) => {
            if (error) {
                console.log(error);
            }
            return String(file)
        })
    },

    async createDirectory(output){

        if (!fs.existsSync(output)){
            fs.mkdirSync(output);
        } else {
            deleteFolderRecursive(output);
            fs.mkdirSync(output);
        }
        return await true
    },

    async createJson(directory, file_name, data){

        return await fs.writeFileSync(directory+ file_name+`.json`, data, `utf8`);
    }
}