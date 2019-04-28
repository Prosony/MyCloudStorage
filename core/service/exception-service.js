import log from "./log-service";

const char_param = '?';

const format = (message, params) => {
    for (let counter = 0; counter < params.length; counter++) {
        message = message.replace(char_param, params[counter]);  //TODO rewrite this shit
    }
    return message;
};

const send_response = (status_code, response, module, message) => {
    response.status(status_code);
    log.info(module, message);
    response.send(message);
};

const send_error =  (status_code, response, module, message) => {
    response.status(status_code);
    log.error(module, message);
    response.send(message);
};

export default {
    send: send_response,
    send_error: send_error,
    format: format
}