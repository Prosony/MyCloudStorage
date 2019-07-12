package com.prsn.dispatcher;

import javax.enterprise.context.Dependent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;


/**
 * @author プロソニーPRSN
 */
@Dependent
public class SocketServer {

    ServerSocket server;
    public void startup() {
        Socket socket;
        try {
            server = new ServerSocket(15123);
            socket = server.accept();
            socket.
//            socket = new Socket(); //"127.0.0.1",15123
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
