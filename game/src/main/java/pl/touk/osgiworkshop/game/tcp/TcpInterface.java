/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game.tcp;

import org.slf4j.*;
import org.springframework.beans.factory.InitializingBean;
import pl.touk.osgiworkshop.game.domain.Game;
import pl.touk.osgiworkshop.game.domain.Interface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author arkadius
 */
public class TcpInterface implements Interface, InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(TcpInterface.class);
    private ServerSocket serverSocket;
    private BufferedReader rdr;
    private PrintWriter wrt;
    private Socket clientSocket;
    private Game game;


    public void afterPropertiesSet() throws Exception {
        new Thread() {
            @Override
            public void run() {
                try {
                    clientSocket = serverSocket.accept();
                    rdr = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    wrt = new PrintWriter(clientSocket.getOutputStream());
                    // run the game after after connect
                    game.start();
                } catch (IOException e) {
                    log.error(null, e);
                }
            }
        }.start();
    }

    public String readLine() {
        try {
            return rdr.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printOut(String str) {
        wrt.print(str); wrt.flush();
    }

    public void printOutLine(String line) {
        wrt.println(line);
    }

    public void printErrLine(String line) {
        wrt.println("[EE]" + line);
    }

    public void setPort(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void close() throws IOException {
        wrt.close();
        rdr.close();
        clientSocket.close();
        serverSocket.close();
    }
}
