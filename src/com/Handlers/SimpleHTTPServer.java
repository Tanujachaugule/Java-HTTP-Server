package com.Handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLOutput;
import java.util.logging.Handler;

import static com.sun.jndi.ldap.LdapCtx.DEFAULT_PORT;

public class SimpleHTTPServer {
    public static int DEFAULT_PORT = 9000;
    public static int port;

    //private com.sun.net.httpserver.HttpServer HttpServer;
    private void start(int port) {
        this.port = port;
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(port), 0);
            System.out.println("server started at" + port);
            httpServer.createContext("/", new Handlers.RootHandler());
            httpServer.createContext("/echoHeader", new Handlers.EchoHeaderHandler());
            httpServer.createContext("/echoGet", new Handlers.EchoGetHandler());
            httpServer.createContext("/echoPost", new Handlers.EchoPostHandler());
            httpServer.setExecutor(null);
            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SimpleHTTPServer httpServer = new SimpleHTTPServer();
        httpServer.start(DEFAULT_PORT);
    }


}


