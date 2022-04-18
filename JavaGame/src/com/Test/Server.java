package com.Test;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server implements Serializable {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(6789);
        Socket s = ss.accept();

        System.out.println("Client Connected!");

        OutputStream outputStream = s.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        Object x = new Object();
        objectOutputStream.writeObject(x);
    }
}
